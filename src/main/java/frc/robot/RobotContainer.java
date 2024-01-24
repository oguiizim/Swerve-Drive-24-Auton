// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.Controle;
import frc.robot.commands.Teleop;
import frc.robot.commands.Intake.ColetorCmd;
import frc.robot.commands.Intake.GanchoCmd;
import frc.robot.commands.Intake.LancadorCmd;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.Intake.Coletor;
import frc.robot.subsystems.Intake.Gancho;
import frc.robot.subsystems.Intake.Lancador;

import java.io.File;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  // Aqui iniciamos o swerve
  private SwerveSubsystem swerve = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  // Aqui é onde chamamos o subsystem de cada parte do intake
  public static final Coletor cSubsystem = new Coletor();
  public static final Gancho gSubsystem = new Gancho();
  public static final Lancador lSubsystem = new Lancador();

  // Aqui é onde chamaremos os commands de cada parte do intake
  public static final ColetorCmd cCommand = new ColetorCmd(cSubsystem);
  public static final GanchoCmd gCommand = new GanchoCmd(gSubsystem);
  public static final LancadorCmd lCommand = new LancadorCmd(lSubsystem);

  // Controles
  public static final XboxController controleXbox = new XboxController(Controle.xboxControle);
  public static final Joystick operatorControl = new Joystick(Controle.controle2);

  // Auto Choser para autonômo
  private final SendableChooser<Command> autoChooser;

  public RobotContainer() {

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

    // Colocar os comandos definidos no PathPlanner 2024 da seguinte forma
    NamedCommands.registerCommand("Intake", new PrintCommand("Intake"));
    // NamedCommands.registerCommand("invertForward", new InvertMotors(driveTrain, true));
    // NamedCommands.registerCommand("autoBalance", swerve.autoBalanceCommand());
    // NamedCommands.registerCommand("exampleCommand",
    // Lancador.shooterMax(RobotContainer.operatorControl, Tracao.lancadorMax));
    // NamedCommands.registerCommand("someOtherCommand", new SomeOtherCommand());

    cCommand.addRequirements(cSubsystem);
    cSubsystem.setDefaultCommand(cCommand);

    gCommand.addRequirements(gSubsystem);
    gSubsystem.setDefaultCommand(gCommand);

    lCommand.addRequirements(lSubsystem);
    lSubsystem.setDefaultCommand(lCommand);

    autoChooser = AutoBuilder.buildAutoChooser();

    SmartDashboard.putData("Auto Chooser", autoChooser);
    // Configure the trigger bindings
    configureBindings();
  }

  // Função onde os eventos (triggers) são configurados
  private void configureBindings() {
    // Botão para resetar o gyro do robô
    new JoystickButton(controleXbox, XboxController.Button.kA.value).onTrue(new InstantCommand(swerve::zeroGyro));
    // new JoystickButton(operatorControl, 3).onTrue(() -> gSubsystem.)
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
