package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.TunerConstants;

public class ArmRollers extends SubsystemBase{
    public TalonFX armIntake;

    public ArmRollers(){
        armIntake = new TalonFX(TunerConstants.kArmIntakeID, "CTRE Chain");

        armIntake.setNeutralMode(NeutralModeValue.Coast);
    }

    public void setSpeedArmIntake(double speed){
        armIntake.set(speed);
    }

    public void intaking(){
        setSpeedArmIntake(0.2);
    }

    public void stop(){
        setSpeedArmIntake(0);
    }
}
