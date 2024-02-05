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
        lancadorUp = new CANSparkMax(Motors.lancador2, MotorType.kBrushless);
        lancadorDown = new CANSparkMax(Motors.lancador1, MotorType.kBrushless);
        lancadorMeio = new CANSparkMax(Motors.lancador3, MotorType.kBrushless);

        lancadorUp.setIdleMode(IdleMode.kBrake);
        lancadorDown.setIdleMode(IdleMode.kBrake);
        lancadorMeio.setIdleMode(IdleMode.kBrake);

        lancadorUp.setInverted(false);
        lancadorDown.setInverted(true);

    }

    public void shooter(Joystick operatorControl) {

        if (operatorControl.getRawButton(Controle.kY)) {
            lancadorUp.set(0.32);
            lancadorDown.set(0.10);

            if (operatorControl.getRawButton(Controle.kBack)) {
                lancadorMeio.set(1);
            } else {
                lancadorMeio.stopMotor();
            }

        } else if (operatorControl.getRawButton(Controle.kX)) {
            lancadorUp.set(0.60);
            lancadorDown.set(0.20);

            if (operatorControl.getRawButton(Controle.kBack)) {
                lancadorMeio.set(1);
            } else {
                lancadorMeio.stopMotor();
            }

        } else {
            lancadorDown.stopMotor();
            lancadorUp.stopMotor();
            lancadorMeio.stopMotor();
        }

    }

    public void shooterMidAuto() {
        lancadorMeio.set(0.6);

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
        lancadorMeio.stopMotor();

    }

    @Override
    public void periodic() {
    }
}
