package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.generated.TunerConstants;

public class Wrist extends SubsystemBase{
    public TalonFX wrist;


    public Wrist(){
        wrist = new TalonFX(Constants.kWristID, "CTRE Chain");
        wrist.setNeutralMode(NeutralModeValue.Brake);
        var talonFXConfigs = new TalonFXConfiguration();
        var slot0Configs = talonFXConfigs.Slot0;
        slot0Configs.kS = 0.25;
        slot0Configs.kV = 0.12;
        slot0Configs.kA = 0.01;
        slot0Configs.kP = 4.8;
        slot0Configs.kI = 0;
        slot0Configs.kD = 0.1;

        var motionMagicConfigs = talonFXConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = 80;
        motionMagicConfigs.MotionMagicAcceleration = 160;
        motionMagicConfigs.MotionMagicJerk = 1600;

        wrist.getConfigurator().apply(talonFXConfigs);
    }

    public void doMagic(int voltage, double position){
        final MotionMagicVoltage m_request = new MotionMagicVoltage(voltage);
        wrist.setControl(m_request.withPosition(position));
    }
    
    // public void doMagic(int voltage, int position){
    //     final MotionMagicVoltage m_request = new MotionMagicVoltage(voltage);
    //     wrist.setControl(m_request.withPosition(position));
    // } 

    public void resetPosition(){
        wrist.setPosition(0);
    }

    public void setSpeedWrist(double speed){
        wrist.set(speed);
    }
    public void home(){
        doMagic(15, 0);
    }
    public void low(){
        doMagic(0, -32.77); //55
    }
    public void mid (){
        doMagic(20, -24);
    }

    //AUTO POSITIONS ???
    public void autoLow(){
        doMagic(0, -34);
    }
    
    public void stop(){
        setSpeedWrist(0);
        home();
    }
}   

