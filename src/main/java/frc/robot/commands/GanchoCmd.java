package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Gancho;

public class GanchoCmd extends Command{

    Gancho gancho;
    XboxController controle;
    double speed;

    public GanchoCmd(Gancho subsystem, XboxController controle){
        gancho = subsystem;
        this.controle = controle;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        gancho.pararGarra();
    }

    @Override
    public void execute() {
        if (controle.getRightBumper())
            gancho.subirGarra();
        else if (controle.getLeftBumper())
            gancho.descerGarra();
        else
            gancho.pararGarra();
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
