package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PID;
import frc.robot.subsystems.SwerveSubsystem;

public class Gyro extends Command {

    PIDController anglePIDController;

    SwerveSubsystem swerve;

    double setPoint;

    public Gyro(SwerveSubsystem subsystem, double setPoint) {
        swerve = subsystem;
        this.setPoint = setPoint;
        anglePIDController = new PIDController(PID.angleAutoPID.p, PID.angleAutoPID.i, PID.angleAutoPID.d);
        anglePIDController.enableContinuousInput(-180, 180);
        // anglePIDController.setTolerance(0.1);

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double outPut = anglePIDController.calculate(swerve.getHeading().getRadians(), Math.toRadians(setPoint));
        swerve.setChassisSpeeds(new ChassisSpeeds(0,0,outPut));
    }

    @Override
    public void end(boolean interrupted) {
        swerve.setChassisSpeeds(new ChassisSpeeds(0,0,0));
    }

    @Override
    public boolean isFinished() {
        return anglePIDController.atSetpoint();
    }
}
