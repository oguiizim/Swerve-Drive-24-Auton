package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Controle;
import frc.robot.Constants.Motors;

public class Coletor extends SubsystemBase{
    CANSparkMax coletor1;
    CANSparkMax coletor2;

    double speed;

    public Coletor(){
        coletor1 = new CANSparkMax(Motors.coletor1, MotorType.kBrushless);
        coletor2 = new CANSparkMax(Motors.coletor2, MotorType.kBrushless);

        coletor2.setInverted(false);
        coletor1.setInverted(true);

        coletor1.setIdleMode(IdleMode.kBrake);
        coletor2.setIdleMode(IdleMode.kBrake);

    }

    public void collectWithA(Joystick operatorControl){
        if(operatorControl.getRawButton(Controle.kA)){
            coletor1.set(1);
            coletor2.set(1);
        }
        else if(operatorControl.getRawButton(Controle.kStart)){
            coletor1.set(-1);
            coletor2.set(-1);
        }
        else{
            coletor1.set(0);
            coletor2.set(0);
        }
    }

    public void coletar() {
        coletor1.set(1);
        coletor2.set(1);
    }

    public void cuspir(){
        coletor1.set(-1);
        coletor2.set(-1);
    }

    public void collectAuto(){
        coletor1.set(1);
        coletor2.set(1);
    }

    public void stop(){
        coletor1.stopMotor();
        coletor2.stopMotor();
    }

    @Override
    public void periodic(){}
}
