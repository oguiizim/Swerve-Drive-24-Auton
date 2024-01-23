package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Controle;
import frc.robot.Constants.Motors;

public class Lancador extends SubsystemBase {
    CANSparkMax lancadorUp;
    CANSparkMax lancadorDown;
    CANSparkMax lancadorMeio;

    double speed;

    public Lancador() {
        lancadorUp = new CANSparkMax(Motors.lancador2, MotorType.kBrushless);
        lancadorDown = new CANSparkMax(Motors.lancador1, MotorType.kBrushless);
        lancadorMeio = new CANSparkMax(Motors.lancador3, MotorType.kBrushless);

        lancadorUp.setIdleMode(IdleMode.kBrake);
        lancadorDown.setIdleMode(IdleMode.kBrake);
        lancadorMeio.setIdleMode(IdleMode.kBrake);

        lancadorDown.setInverted(true);
    }

    public void shooter(Joystick operatorControl, double speed){

        if (operatorControl.getRawButton(Controle.kB)) {
            lancadorMeio.set(1);

        }
        else if(operatorControl.getRawButton(Controle.kY)){
            lancadorUp.set(0.5);
            lancadorDown.set(0.5);

        }
        else if(operatorControl.getRawButton(Controle.kX)){
            lancadorUp.set(1);
            lancadorDown.set(1);

        }
        else {
            lancadorDown.stopMotor();
            lancadorUp.stopMotor();
            lancadorMeio.stopMotor();

        }
    }

    public void stop() {
        lancadorUp.stopMotor();
        lancadorDown.stopMotor();
        lancadorMeio.stopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Lançador Speed", speed);
    }
}
