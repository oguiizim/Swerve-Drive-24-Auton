package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake.Coletor;

public class ColetorCmd extends Command{

    Coletor coletor;

    double speed;

    public ColetorCmd(Coletor subsystem){
        coletor = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        RobotContainer.cSubsystem.stop();
    }

    @Override
    public void execute(){
        coletor.collectWithA(RobotContainer.operatorControl, speed);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}