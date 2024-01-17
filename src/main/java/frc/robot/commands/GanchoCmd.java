package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake.Gancho;

public class GanchoCmd extends Command{

    Gancho gancho;

    public GanchoCmd(Gancho subsystem){
        gancho = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        RobotContainer.gSubsystem.stop();
    }

    @Override
    public void execute(){
        gancho.escalatorVelocity(RobotContainer.operatorControl, Constants.Tracao.ganhcoSpd);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
