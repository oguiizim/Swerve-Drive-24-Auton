package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Lancador;

public class LancadorCmd extends Command {
    Lancador lancador;

    public LancadorCmd(Lancador subsystem) {
        lancador = subsystem;

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
        return false;
    }
}
