package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Motors;

public class Lancador extends SubsystemBase {
    CANSparkMax lancadorUp;
    CANSparkMax lancadorDown;
    CANSparkMax lancadorMeio;

    double timer;

    public Lancador() {
        lancadorUp = new CANSparkMax(Motors.lancador1, MotorType.kBrushless);
        lancadorDown = new CANSparkMax(Motors.lancador2, MotorType.kBrushless);
        lancadorMeio = new CANSparkMax(Motors.lancador3, MotorType.kBrushless);

        lancadorUp.setIdleMode(IdleMode.kBrake);
        lancadorDown.setIdleMode(IdleMode.kBrake);
        lancadorMeio.setIdleMode(IdleMode.kBrake);

        lancadorUp.setInverted(false);
        lancadorDown.setInverted(true);

    }

    // Bolata Grande Gira mais rapido

    public void shooterMidCollectDown() {
        lancadorMeio.set(0.8);
    }

    public void shooterMidCollectUp() {
        lancadorMeio.set(-0.5);
    }

    public void collectShooter(){
        lancadorUp.set(-0.30);
        lancadorDown.set(-0.30);
    }
    
    public void stopCondutor() {
        lancadorMeio.stopMotor();
    }

    public void shootAmp() {
        lancadorUp.set(0.40);
        lancadorDown.set(0.20);
    }

    public void shootSpeaker() {
        lancadorUp.set(0.60);
        lancadorDown.set(0.20);
    }

    public void shootSpeakerAuto() {
        lancadorUp.set(0.65);
        lancadorDown.set(0.22);
    }

    public void stop() {
        lancadorUp.stopMotor();
        lancadorDown.stopMotor();
    }
}
