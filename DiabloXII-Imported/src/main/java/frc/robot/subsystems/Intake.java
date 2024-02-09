package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.TunerConstants;

public class Intake extends SubsystemBase{
    public TalonFX intake;

    public Intake(){
        intake = new TalonFX(TunerConstants.kIntakeID, "CTRE Chain");

        intake.setNeutralMode(NeutralModeValue.Coast);
    }

    public void setSpeedIntake(double speed){
        intake.set(speed);
    }

    public void intaking(){
        setSpeedIntake(0.3);
    }

    public void outtaking(){
        setSpeedIntake(-0.25);
    }

    public void stop(){
        setSpeedIntake(0);
    }
}
