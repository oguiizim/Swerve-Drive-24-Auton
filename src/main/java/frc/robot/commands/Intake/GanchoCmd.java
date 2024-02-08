    package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake.Gancho;

public class GanchoCmd extends Command{

    Gancho gancho;

    double speed;

    public GanchoCmd(Gancho subsystem){
        gancho = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        // gancho.escalatorVelocity(RobotContainer.operatorControl, speed);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
