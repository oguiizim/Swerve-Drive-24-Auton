package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Controle;
import frc.robot.Constants.Motors;

public class Gancho extends SubsystemBase {

    CANSparkMax hook;

    double speed;

    DutyCycleEncoder encoderHook;

    public Gancho() {

        hook = new CANSparkMax(Motors.gancho, MotorType.kBrushless);

        encoderHook = new DutyCycleEncoder(0);

        hook.setIdleMode(IdleMode.kBrake);
    }

    public void escalatorVelocity(Joystick operatorControl, double speed) {
        if(operatorControl.getRawButton(Controle.kLeftAxisButton)){
            encoderHook.reset();
          }
        else if(operatorControl.getRawButton(6)){
            // Positive Speed = Hook to ground
      
            if(encoderHook.get() >= 3.55){
              hook.stopMotor();
            } else {
              hook.set(0.2);
            }
          }
        else if(operatorControl.getRawButton(5)){
            // Negative Speed = Hook to sky
      
            if(encoderHook.get() <= -3.70){
              hook.stopMotor();
            } else if(encoderHook.get() >= 0.10){
              hook.stopMotor();
            } 
            else{
              hook.set(-0.2);
            }
      
          } else {
            hook.stopMotor();
          }
    }

    public void stop() {
        hook.stopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Escalator Encoder", encoderHook.get());
    }
}
