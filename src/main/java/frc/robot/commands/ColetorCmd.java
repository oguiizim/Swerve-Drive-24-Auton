package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Coletor;

public class ColetorCmd extends Command{

    Coletor coletor;

    public ColetorCmd(Coletor subsystem){
        coletor = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
