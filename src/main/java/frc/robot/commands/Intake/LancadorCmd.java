package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Controle;
import frc.robot.subsystems.Intake.Lancador;

public class LancadorCmd extends Command{
    Lancador lancador;
    Joystick operatorControl;

    public LancadorCmd(Lancador subsystem, Joystick operatorControl){
        lancador = subsystem;
        this.operatorControl = operatorControl;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute() {
        if (operatorControl.getRawAxis(Controle.rightTrigger) != 0) 
            lancador.shootSpeakerAuto();
        else if (operatorControl.getRawAxis(Controle.leftTrigger) != 0) 
            lancador.shootAmpAuto();
        else
            lancador.stop();

        
        if (operatorControl.getRawButton(Controle.kY)) {
            lancador.coletorLancador();
            lancador.cuspir();
        } else if (operatorControl.getRawButton(Controle.kB)) {
            lancador.cuspir();
        } else if (operatorControl.getRawButton(Controle.kA)) {
            lancador.coletar();
        } else if (operatorControl.getRawButton(Controle.kX)){
            lancador.coletar();
        } else {
            lancador.stopCondutor();
        }
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
