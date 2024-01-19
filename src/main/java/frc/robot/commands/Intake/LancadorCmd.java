package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Constants.Tracao;
import frc.robot.subsystems.Intake.Lancador;

public class LancadorCmd extends Command{
    Lancador lancador;

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
        lancador.shooterMax(RobotContainer.operatorControl, Tracao.lancadorMax);
        lancador.shooterMid(RobotContainer.operatorControl, Tracao.lancadorMid);
        lancador.shooterAmp(RobotContainer.operatorControl, Tracao.lancadorAmp);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
