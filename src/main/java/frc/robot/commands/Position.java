package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class Position extends Command {

    PIDController anglePIDController;

    SwerveSubsystem swerve;

    public Position(SwerveSubsystem subsystem) {
        swerve = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return anglePIDController.atSetpoint();
    }
}
