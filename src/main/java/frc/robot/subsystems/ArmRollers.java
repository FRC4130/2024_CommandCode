package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmRollers extends SubsystemBase{
    public TalonFX armIntake;

    public ArmRollers(){
        armIntake = new TalonFX(Constants.kArmIntakeID, "CTRE Chain");

        armIntake.setNeutralMode(NeutralModeValue.Coast);
    }

    public void setSpeedArmIntake(double speed){
        armIntake.set(speed);
    }

    public void intaking(){
        setSpeedArmIntake(0.25);
    }

    public void stop(){
        setSpeedArmIntake(0);
    }
}
