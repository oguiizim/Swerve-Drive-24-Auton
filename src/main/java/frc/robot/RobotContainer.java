// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.Controle;
import frc.robot.commands.GanchoCmd;
import frc.robot.commands.Gyro;
import frc.robot.commands.LancadorCmd;
import frc.robot.commands.Teleop;
import frc.robot.subsystems.Coletor;
import frc.robot.subsystems.Gancho;
import frc.robot.subsystems.Lancador;
import frc.robot.subsystems.SwerveSubsystem;

import java.io.File;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.StadiaController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  static final SwerveSubsystem swerve = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  public static final Coletor cSubsystem = new Coletor();
  public static final Gancho gSubsystem = new Gancho();
  public static final Lancador lSubsystem = new Lancador();

  public static final XboxController controleXbox = new XboxController(Controle.xboxControle);
  public static final Joystick operatorControl = new Joystick(Controle.controle2);

  private final SendableChooser<Command> autoChooser;

  public RobotContainer() {

    NamedCommands.registerCommand("shootSpeaker",
        new InstantCommand(lSubsystem::shootSpeaker, lSubsystem));

    NamedCommands.registerCommand("shootCondutor", new InstantCommand(lSubsystem::shooterMidCollectDown, lSubsystem));
    NamedCommands.registerCommand("stopShooter", new InstantCommand(lSubsystem::stop, lSubsystem));
    NamedCommands.registerCommand("stopCondutor", new InstantCommand(lSubsystem::stopCondutor, lSubsystem));
    NamedCommands.registerCommand("collect", new InstantCommand(cSubsystem::collect, cSubsystem));
    NamedCommands.registerCommand("stopIntake", new InstantCommand(cSubsystem::stop, cSubsystem));

    // setPoinit positvo = Giro para esquerda
    // setPoint negativo = Giro para direita
    NamedCommands.registerCommand("gyro-60", new Gyro(swerve, -50));
    NamedCommands.registerCommand("gyro0", new Gyro(swerve, 0.5));
    NamedCommands.registerCommand("gyro-90", new Gyro(swerve, 90));

    swerve.setDefaultCommand(new Teleop(swerve,
        () -> -MathUtil.applyDeadband(controleXbox.getLeftY(), Controle.DEADBAND),
        () -> -MathUtil.applyDeadband(controleXbox.getLeftX(), Controle.DEADBAND),
        () -> -MathUtil.applyDeadband(controleXbox.getRightX(), Controle.DEADBAND)));

    gSubsystem.setDefaultCommand(new GanchoCmd(gSubsystem, controleXbox));
    lSubsystem.setDefaultCommand(new LancadorCmd(lSubsystem));

    autoChooser = AutoBuilder.buildAutoChooser();

    SmartDashboard.putData("Auto Chooser", autoChooser);

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(controleXbox, Button.kA.value).onTrue(new InstantCommand(swerve::zeroGyro));

    new JoystickButton(operatorControl, XboxController.Button.kA.value).whileTrue(Commands.runEnd(
        () -> {
          cSubsystem.collect();
          lSubsystem.shooterMidCollectDown();
        },
        () -> {
          cSubsystem.stop();
          lSubsystem.stopCondutor();
        },
        lSubsystem));

    new JoystickButton(operatorControl, XboxController.Button.kY.value).whileTrue(Commands.runEnd(
        () -> {
          lSubsystem.collectShooter();
          lSubsystem.shooterMidCollectUp();
        },
        () -> {
          lSubsystem.stop();
          lSubsystem.stopCondutor();
        },
        lSubsystem));

    new JoystickButton(operatorControl, XboxController.Button.kB.value).whileTrue(Commands.startEnd(
        () -> lSubsystem.shooterMidCollectUp(),
        () -> lSubsystem.stopCondutor(),
        lSubsystem));

    new JoystickButton(operatorControl, XboxController.Button.kX.value).whileTrue(Commands.startEnd(
        () -> lSubsystem.shooterMidCollectDown(),
        () -> lSubsystem.stopCondutor(),
        lSubsystem));

    new Trigger(this::getOperatorRightTrigger).onTrue(Commands.runOnce(
        () -> lSubsystem.shootSpeaker(),
        lSubsystem)).onFalse(Commands.runOnce(
            () -> lSubsystem.stop(),
            lSubsystem));

    new Trigger(this::getOperatorLeftTrigger).onTrue(Commands.runOnce(
        () -> lSubsystem.shootAmp(),
        lSubsystem)).onFalse(Commands.runOnce(
            () -> lSubsystem.stop(),
            lSubsystem));

  }

  private boolean getOperatorRightTrigger() {
    if (operatorControl.getRawAxis(Controle.rightTrigger) != 0) {
      return true;
    }
    return false;
  }

  private boolean getOperatorLeftTrigger() {
    if (operatorControl.getRawAxis(Controle.leftTrigger) != 0) {
      return true;
    }
    return false;
  }

  public Command getAutonomousCommand() {
    swerve.zeroGyro();
    return autoChooser.getSelected();
  }

  public void setMotorBrake(boolean brake) {
    swerve.setMotorBrake(brake);
  }
}
