package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Controle;
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

    public void coletar() {
        lancadorMeio.set(0.6);
    }

    public void cuspir() {
        lancadorMeio.set(-0.5);
    }

    public void coletorLancador(){
        lancadorUp.set(-0.30);
        lancadorDown.set(-0.30);
    }
    
    public void stopCondutor() {
        lancadorMeio.stopMotor();
    }

    public void shootAmpAuto() {
        lancadorUp.set(0.32);
        lancadorDown.set(0.10);
    }

    public void shootSpeakerAuto() {
        lancadorUp.set(0.60);
        lancadorDown.set(0.20);
    }

    public void stop() {
        lancadorUp.stopMotor();
        lancadorDown.stopMotor();
    }

    @Override
    public void periodic() {
    }
}
