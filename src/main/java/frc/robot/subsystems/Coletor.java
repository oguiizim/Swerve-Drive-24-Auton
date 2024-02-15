package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Motors;

public class Coletor extends SubsystemBase{
    CANSparkMax coletor1;
    CANSparkMax coletor2;

    public Coletor(){
        coletor1 = new CANSparkMax(Motors.coletor1, MotorType.kBrushless);
        coletor2 = new CANSparkMax(Motors.coletor2, MotorType.kBrushless);

        coletor2.setInverted(false);
        coletor1.setInverted(true);

        coletor1.setIdleMode(IdleMode.kBrake);
        coletor2.setIdleMode(IdleMode.kBrake);

    }

    public void collect() {
        coletor1.set(1);
        coletor2.set(1);
    }

    public void stop(){
        coletor1.stopMotor();
        coletor2.stopMotor();
    }

}
