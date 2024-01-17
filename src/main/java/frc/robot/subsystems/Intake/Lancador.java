package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Controle;
import frc.robot.Constants.Motors;

public class Lancador extends SubsystemBase{
    CANSparkMax lancador1;
    CANSparkMax lancador2;
    CANSparkMax lancador3;
    
    double speed;

    public Lancador(){
        lancador1 = new CANSparkMax(Motors.lancador1, MotorType.kBrushless);
        lancador2 = new CANSparkMax(Motors.lancador2, MotorType.kBrushless);
        lancador3 = new CANSparkMax(Motors.lancador3, MotorType.kBrushless);

        lancador1.setIdleMode(IdleMode.kBrake);
        lancador2.setIdleMode(IdleMode.kBrake);
        lancador3.setIdleMode(IdleMode.kBrake);
    }

    public void shooterFwd(Joystick operatorControl, double speed){
        lancador1.set(operatorControl.getRawAxis(Controle.rightTrigger)*speed);
        lancador2.set(operatorControl.getRawAxis(Controle.rightTrigger)*speed);
        lancador3.set(operatorControl.getRawAxis(Controle.rightTrigger)*speed);

        this.speed = speed;
    }

    public void stop(){
        lancador1.stopMotor();
        lancador2.stopMotor();
        lancador3.stopMotor();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Lançador Speed", speed);
    }
}
