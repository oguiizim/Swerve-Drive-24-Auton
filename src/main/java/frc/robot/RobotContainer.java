// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.Controle;
import frc.robot.commands.GanchoCmd;
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

public class RobotContainer {
  private SwerveSubsystem swerve = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  public static final Coletor cSubsystem = new Coletor();
  public static final Gancho gSubsystem = new Gancho();
  public static final Lancador lSubsystem = new Lancador();

  // public static final ColetorCmd cCommand = new ColetorCmd(cSubsystem);
  // public static final ColetorAutoCmd cAutoCommand = new
  // ColetorAutoCmd(cSubsystem);
  // public static final GanchoCmd gCommand = new GanchoCmd(gSubsystem);
  // public static final LancadorCmd lCommand = new LancadorCmd(lSubsystem);
  // public static final IntakeCmd iCommand = new IntakeCmd(iSubsystem);

  // Controles
  public static final XboxController controleXbox = new XboxController(Controle.xboxControle);
  public static final Joystick operatorControl = new Joystick(Controle.controle2);

  // Auto Choser para autonômo
  private final SendableChooser<Command> autoChooser;

  public RobotContainer() {

    NamedCommands.registerCommand("zeroGyro", new InstantCommand(swerve::zeroGyro));

    NamedCommands.registerCommand("shootSpeaker", new InstantCommand(lSubsystem::shootSpeakerAuto));
    NamedCommands.registerCommand("shooterMidMotor", new InstantCommand(lSubsystem::coletar));
    NamedCommands.registerCommand("stopShooter", new InstantCommand(lSubsystem::stop));
    NamedCommands.registerCommand("stopCondutor", new InstantCommand(lSubsystem::stopCondutor));
    NamedCommands.registerCommand("collect", new InstantCommand(cSubsystem::coletar));
    NamedCommands.registerCommand("stopIntake", new InstantCommand(cSubsystem::stop));

    // Definimos o comando padrão como a tração
    swerve.setDefaultCommand(new Teleop(swerve,
        () -> -MathUtil.applyDeadband(controleXbox.getLeftY(), Controle.DEADBAND),
        () -> -MathUtil.applyDeadband(controleXbox.getLeftX(), Controle.DEADBAND),
        () -> -MathUtil.applyDeadband(controleXbox.getRightX(), Controle.DEADBAND)));

    gSubsystem.setDefaultCommand(new GanchoCmd(gSubsystem, controleXbox));
    lSubsystem.setDefaultCommand(new LancadorCmd(lSubsystem, operatorControl));

    autoChooser = AutoBuilder.buildAutoChooser();

    SmartDashboard.putData("Auto Chooser", autoChooser);

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(controleXbox, Button.kA.value).onTrue(new InstantCommand(swerve::zeroGyro));

    new JoystickButton(operatorControl, XboxController.Button.kA.value).onTrue(Commands.runOnce(() -> {
      cSubsystem.coletar();
    }, cSubsystem, lSubsystem)).onFalse(Commands.runOnce(() -> {
      cSubsystem.stop();
    }, cSubsystem));
  }

  public Command getAutonomousCommand() {
    // swerve.zeroGyro();

    return autoChooser.getSelected();

    // Feito pela StemOs
    // return swerve.getAutonomousCommand(Trajetoria.NOME_TRAJETORIA,
    // Trajetoria.ALIANCA, true);

  }

  public void setMotorBrake(boolean brake) {
    swerve.setMotorBrake(brake);
  }
}
