// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.Controle;
import frc.robot.commands.Teleop;
import frc.robot.commands.Intake.IntakeCmd;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.Intake.Intake;
import java.io.File;

import javax.print.attribute.standard.JobKOctetsSupported;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.StadiaController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  // Inicialização da swerve
  private SwerveSubsystem swerve = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  // Inicialização dos subsystems
  // public static final Coletor cSubsystem = new Coletor();
  // public static final Gancho gSubsystem = new Gancho();
  // public static final Lancador lSubsystem = new Lancador();
  public static final Intake iSubsystem = new Intake();

  // Inicialização dos commands
  // public static final ColetorCmd cCommand = new ColetorCmd(cSubsystem);
  // public static final ColetorAutoCmd cAutoCommand = new ColetorAutoCmd(cSubsystem);
  // public static final GanchoCmd gCommand = new GanchoCmd(gSubsystem);
  // public static final LancadorCmd lCommand = new LancadorCmd(lSubsystem);
  // public static final IntakeCmd iCommand = new IntakeCmd(iSubsystem);
  public static final IntakeCmd iCommand = new IntakeCmd(iSubsystem);

  // Controles
  public static final XboxController controleXbox = new XboxController(Controle.xboxControle);
  public static final Joystick operatorControl = new Joystick(Controle.controle2);

  // Auto Choser para autonômo
  private final SendableChooser<Command> autoChooser;

  public RobotContainer() {

    // NamedCommands.registerCommand("collect", new ColetorAutoCmd(cSubsystem));

    // Intake commands on auto
    NamedCommands.registerCommand("collect", new InstantCommand(iSubsystem::collectAuto));
    NamedCommands.registerCommand("stopIntake", new InstantCommand(iSubsystem::stopColetor));

    // // Shooter commands on auto
    NamedCommands.registerCommand("shootSpeaker", new InstantCommand(iSubsystem::shootSpeakerAuto));
    NamedCommands.registerCommand("shootAmp", new InstantCommand(iSubsystem::shootAmpAuto));
    NamedCommands.registerCommand("shooterMidMotor", new InstantCommand(iSubsystem::shooterMidAuto));
    NamedCommands.registerCommand("stopShooter", new InstantCommand(iSubsystem::stopShooter));

    // Definimos o comando padrão como a tração
    swerve.setDefaultCommand(new Teleop(swerve,
        // Aqui dentro temos vários inputs do nossos gamepad, estaremos passando a
        // própria função pelo método,
        // apenas preferência de sintaxe, o desempenho em si não se altera
        () -> -MathUtil.applyDeadband(controleXbox.getLeftY(), Controle.DEADBAND),
        () -> -MathUtil.applyDeadband(controleXbox.getLeftX(), Controle.DEADBAND),
        () -> -MathUtil.applyDeadband(controleXbox.getRightX(), Controle.DEADBAND)));

    /*
     * AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
     * () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
     * Controle.DEADBAND),
     * () -> -MathUtil.applyDeadband(driverXbox.getLeftY(),
     * Controle.DEADBAND),
     * () -> MathUtil.applyDeadband(driverXbox.getRightX(),
     * Controle.DEADBAND),
     * driverXbox::getYButtonPressed,
     * driverXbox::getAButtonPressed,
     * driverXbox::getXButtonPressed,
     * driverXbox::getBButtonPressed);
     */


    // cSubsystem.setDefaultCommand(cCommand);
    // gSubsystem.setDefaultCommand(gCommand);
    // lSubsystem.setDefaultCommand(lCommand);

    // iSubsystem.setDefaultCommand(iCommand);

    // Colocar os comandos definidos no PathPlanner 2024 da seguinte forma

    autoChooser = AutoBuilder.buildAutoChooser();

    SmartDashboard.putData("Auto Chooser", autoChooser);

    // Configure the trigger bindings
    configureBindings();
  }

  // Função onde os eventos (triggers) são configurados
  private void configureBindings() {
    // Botão para resetar o gyro do robô
    new JoystickButton(controleXbox, Button.kA.value).onTrue(new InstantCommand(swerve::zeroGyro));

    // Comands Coletor
    new JoystickButton(operatorControl, XboxController.Button.kA.value).onTrue(new InstantCommand(iSubsystem::collect)); // Coletor On
    new JoystickButton(operatorControl, XboxController.Button.kA.value).onFalse(new InstantCommand(iSubsystem::stopColetor)); // Coletor Off
    new JoystickButton(operatorControl, XboxController.Button.kStart.value).onTrue(new InstantCommand(iSubsystem::shooterMidFwd)); // KitBot Fwd On
    new JoystickButton(operatorControl, XboxController.Button.kStart.value).onFalse(new InstantCommand(iSubsystem::stopShooter)); // KitBot Fwd Off

    // Commands Shooter
    new JoystickButton(operatorControl, XboxController.Button.kBack.value).onTrue(new InstantCommand(iSubsystem::shooterMidBcd)); // KitBot Bcd On
    new JoystickButton(operatorControl, XboxController.Button.kBack.value).onFalse(new InstantCommand(iSubsystem::stopShooter)); // KitBot Bcd Off
    new JoystickButton(operatorControl, XboxController.Button.kY.value).onTrue(new InstantCommand(iSubsystem::collectSource)); // Shooter Source On
    new JoystickButton(operatorControl, XboxController.Button.kY.value).onFalse(new InstantCommand(iSubsystem::stopShooter)); // Shooter Source Off
    new JoystickButton(operatorControl, XboxController.Button.kRightBumper.value).onTrue(new InstantCommand(iSubsystem::shootSpeaker)); // Shooter Speaker On
    new JoystickButton(operatorControl, XboxController.Button.kRightBumper.value).onFalse(new InstantCommand(iSubsystem::stopShooter)); // Shooter Speaker Off
    new JoystickButton(operatorControl, XboxController.Button.kLeftBumper.value).onTrue(new InstantCommand(iSubsystem::shootAmp)); // Shooter Amp On
    new JoystickButton(operatorControl, XboxController.Button.kLeftBumper.value).onFalse(new InstantCommand(iSubsystem::stopShooter)); // Shooter Amp Off

    // Commands Climber
    new JoystickButton(operatorControl, XboxController.Button.kRightStick.value).onTrue(new InstantCommand(iSubsystem::escalatorFwd)); // Escalator Fwd On
    new JoystickButton(operatorControl, XboxController.Button.kRightStick.value).onFalse(new InstantCommand(iSubsystem::stopEscalator)); // Escalator Fwd Off
    new JoystickButton(operatorControl, XboxController.Button.kLeftStick.value).onTrue(new InstantCommand(iSubsystem::escalatorBcd)); // Escalator Bcd On
    new JoystickButton(operatorControl, XboxController.Button.kLeftStick.value).onFalse(new InstantCommand(iSubsystem::stopEscalator)); // Escalator Bcd Off

  }

  // Função que retorna o autônomo
  public Command getAutonomousCommand() {

    return autoChooser.getSelected();

    // Feito pela StemOs
    // return swerve.getAutonomousCommand(Trajetoria.NOME_TRAJETORIA, Trajetoria.ALIANCA, true);

  }

  // Define os motores como coast ou brake
  public void setMotorBrake(boolean brake) {
    swerve.setMotorBrake(brake);
  }
}
