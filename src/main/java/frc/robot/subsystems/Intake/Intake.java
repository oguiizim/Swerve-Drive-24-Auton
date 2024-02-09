package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkLowLevel.MotorType;

import java.util.ResourceBundle.Control;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Controle;
import frc.robot.Constants.Motors;

public class Intake extends SubsystemBase {
    CANSparkMax coletor1, coletor2, escalator, shooterUp, shooterMid, shooterDown;

    public Intake() {
        coletor1 = new CANSparkMax(9, MotorType.kBrushless);
        coletor2 = new CANSparkMax(10, MotorType.kBrushless);

        escalator = new CANSparkMax(Motors.gancho, MotorType.kBrushless);

        shooterUp = new CANSparkMax(Motors.lancador1, MotorType.kBrushless);
        shooterDown = new CANSparkMax(Motors.lancador2, MotorType.kBrushless);
        shooterMid = new CANSparkMax(Motors.lancador3, MotorType.kBrushless);

        coletor1.setIdleMode(IdleMode.kBrake);
        coletor2.setIdleMode(IdleMode.kBrake);

        escalator.setIdleMode(IdleMode.kBrake);

        shooterUp.setIdleMode(IdleMode.kBrake);
        shooterDown.setIdleMode(IdleMode.kBrake);
        shooterMid.setIdleMode(IdleMode.kBrake);

        shooterUp.setInverted(false);
        shooterDown.setInverted(true);

        coletor1.setInverted(true);
        coletor2.setInverted(false);
    }

    public void intakeTeleop(Joystick operatorControl) {

        if (operatorControl.getRawButton(Controle.kA)) {
            coletor1.set(1);
            coletor2.set(1);

            if (operatorControl.getRawButton(Controle.kBack)) {
                shooterMid.set(0.2);
            } else {
                shooterMid.stopMotor();
            }
        } else if (operatorControl.getRawButton(Controle.kStart)) {
            coletor1.set(-1);
            coletor2.set(-1);

            if (operatorControl.getRawButton(Controle.kBack)) {
                shooterMid.set(-0.4);
            } else {
                shooterMid.stopMotor();
            }
        } else if (operatorControl.getRawButton(Controle.kRB)) {
            escalator.set(0.4);
        } else if (operatorControl.getRawButton(Controle.kLB)) {
            escalator.set(-0.4);
        } else if (operatorControl.getRawAxis(Controle.leftTrigger) > 0) {
            shooterUp.set(0.32);
            shooterDown.set(0.10);

            if (operatorControl.getRawButton(Controle.kBack)) {
                shooterMid.set(1);
            } else {
                shooterMid.stopMotor();
            }
        } else if (operatorControl.getRawAxis(Controle.rightTrigger) > 0) {
            shooterUp.set(0.60);
            shooterDown.set(0.20);

            if (operatorControl.getRawButton(Controle.kBack)) {
                shooterMid.set(1);
            } else {
                shooterMid.stopMotor();
            }
        } else if(operatorControl.getRawButton(Controle.kY)){
            shooterUp.set(-0.30);
            shooterDown.set(-0.30);

            if(operatorControl.getRawButton(Controle.kBack)){
                shooterMid.set(-0.4);
            }
            else {
                shooterMid.stopMotor();
            }
        } 
        else {
            shooterDown.stopMotor();
            shooterMid.stopMotor();
            shooterUp.stopMotor();
            coletor1.stopMotor();
            coletor2.stopMotor();
            escalator.stopMotor();
        }

    }

    public void collectAuto(){
        coletor1.set(1);
        coletor2.set(1);
    }

    public void stopColetor(){
        coletor1.stopMotor();
        coletor2.stopMotor();
    }

    public void shooterMidAuto() {
        shooterMid.set(0.6);

    }

    public void shootAmpAuto() {
        shooterUp.set(0.32);
        shooterDown.set(0.10);

    }

    public void shootSpeakerAuto() {
        shooterUp.set(0.60);
        shooterDown.set(0.20);

    }

    public void stopShooter() {
        shooterUp.stopMotor();
        shooterDown.stopMotor();
        shooterMid.stopMotor();

    }

    public void collect(){
        coletor1.set(1);
        coletor2.set(1);
    }

    public void collectSource(){
        shooterUp.set(-0.30);
        shooterDown.set(-0.30);
    }

    public void shootSpeaker(){
        shooterUp.set(0.60);
        shooterDown.set(0.20);
    }

    public void shootAmp(){
        shooterUp.set(0.32);
        shooterDown.set(0.10);
    }

    public void shooterMidFwd(){
        shooterMid.set(0.4);
    }

    public void shooterMidBcd(){
        shooterMid.set(-0.4);
    }

    public void escalatorFwd(){
        escalator.set(0.3);
    }

    public void escalatorBcd(){
        escalator.set(-0.3);
    }

}
