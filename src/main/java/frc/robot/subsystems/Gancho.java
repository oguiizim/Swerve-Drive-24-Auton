package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Motors;

public class Gancho extends SubsystemBase {

    CANSparkMax hook;

    public Gancho() {
      hook = new CANSparkMax(Motors.gancho, MotorType.kBrushless);
      hook.setIdleMode(IdleMode.kBrake);
    }

    public void subirGarra() {
      hook.set(-0.3);
    }

    public void descerGarra() {
      hook.set(0.3);
    }

    public void pararGarra() {
      hook.stopMotor();
    }
}
