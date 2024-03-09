package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ForwardLimitValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.generated.TunerConstants;

public class Intake extends SubsystemBase{
    public static TalonFX intake;
    private LED ledSub;

    public Intake(){
        intake = new TalonFX(Constants.kIntakeID, "CTRE Chain");
        ledSub = new LED();

        intake.setNeutralMode(NeutralModeValue.Coast);
    }

    public void setSpeedIntake(double speed){
        intake.set(speed);
    }

    public void intaking(){
        setSpeedIntake(0.5);
    }

    public void outtaking(){
        setSpeedIntake(-0.25);
    }

    public void outtakingSlow(){
        setSpeedIntake(-0.05);
    }

    //AUTO SPEEDS ???
    public void autoIntaking(){
        setSpeedIntake(0.6);
    }

    public void stop(){
        setSpeedIntake(0);
    }
}
