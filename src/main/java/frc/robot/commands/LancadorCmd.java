package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake.Lancador;

public class LancadorCmd extends Command{
    Lancador lancador;

    double speed;

    public LancadorCmd(Lancador subsystem){
        lancador = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        RobotContainer.lSubsystem.stop();
    }

    @Override
    public void execute(){
        lancador.shooterFwd(RobotContainer.operatorControl, speed);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
