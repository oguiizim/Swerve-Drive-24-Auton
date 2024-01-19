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
    CANSparkMax lancador1;
    CANSparkMax lancador2;
    CANSparkMax lancadorMeio;

    double speed;

    public Lancador() {
        lancador1 = new CANSparkMax(Motors.lancador1, MotorType.kBrushless);
        lancador2 = new CANSparkMax(Motors.lancador2, MotorType.kBrushless);
        lancadorMeio = new CANSparkMax(Motors.lancador3, MotorType.kBrushless);

        lancador1.setIdleMode(IdleMode.kBrake);
        lancador2.setIdleMode(IdleMode.kBrake);
        lancadorMeio.setIdleMode(IdleMode.kBrake);
    }

    public void shooterMax(Joystick operatorControl, double speed) {
        if (operatorControl.getRawButton(Controle.kX)) {
            lancador1.set(1);
            lancador2.set(1);
        } else {
            lancador1.stopMotor();
            lancador2.stopMotor();
        }
    }

    public void shooterAmp(Joystick operatorControl, double speed) {
        if(operatorControl.getRawButton(Controle.kY)){
            lancador1.set(0.5);
            lancador2.set(0.5);
        } 
        else {
            lancador1.stopMotor();
            lancador2.stopMotor();
        }
    }

    public void shooterMid(Joystick operatorControl, double speed) {
        if (operatorControl.getRawButton(Controle.kB)) {
            lancadorMeio.set(1);
        } else {
            lancadorMeio.stopMotor();
        }
    }

    public void stop() {
        lancador1.stopMotor();
        lancador2.stopMotor();
        lancadorMeio.stopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Lançador Speed", speed);
    }
}
