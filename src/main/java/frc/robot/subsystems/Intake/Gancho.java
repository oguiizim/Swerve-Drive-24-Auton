package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Controle;
import frc.robot.Constants.Motors;

public class Gancho extends SubsystemBase{

    CANSparkMax gancho;

    double speed;

    public Gancho(){
        gancho = new CANSparkMax(Motors.gancho, MotorType.kBrushless);

        gancho.setIdleMode(IdleMode.kBrake);
    }

    public void escalatorVelocity(Joystick operatorControl, double speed){
        if(operatorControl.getRawButtonPressed(Controle.kRB)){
            gancho.set(0.5);
        }
        else if(operatorControl.getRawButtonPressed(Controle.kRB)){
            gancho.set(-0.5);
        }
        this.speed = speed;
    }

    public void stop(){
        gancho.stopMotor();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Escalator Speed", speed);
    }
}
