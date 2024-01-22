package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
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

    RelativeEncoder g_encoder;

    public Gancho(){

        
        gancho = new CANSparkMax(Motors.gancho, MotorType.kBrushless);
        
        g_encoder = gancho.getEncoder();
        gancho.setIdleMode(IdleMode.kBrake);
    }

    public void escalatorVelocity(Joystick operatorControl, double speed){
        if(operatorControl.getRawButton(Controle.kRB)){
            gancho.set(0.4);

            // if(g_encoder.getPosition() >=70){
            //     gancho.stopMotor();
            // }
            // else{
            //     gancho.set(0.2);
            // }
        }
        else if(operatorControl.getRawButton(Controle.kLB)){
            gancho.set(-0.4);

            // if(g_encoder.getPosition() <=5){
            //     gancho.stopMotor();
            // }
            // else{
            //     gancho.set(-0.2);
            // }
        }
        else{
            gancho.stopMotor();
        }
        this.speed = speed;

    }

    public void escalatorFwd(Joystick operatorControl, double speed){
    
    }

    public void stop(){
        gancho.stopMotor();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Escalator Speed", speed);
        SmartDashboard.putNumber("Escalator Encoder", g_encoder.getPosition());
    }
}
