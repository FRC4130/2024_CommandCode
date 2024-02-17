package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.TunerConstants;

public class Climb extends SubsystemBase{
    TalonFX climb;

    public Climb(){
        climb = new TalonFX(TunerConstants.kClimbID, "CTRE Chain");        
    }

    public void setSpeedClimb(double speed){
        climb.set(speed);
    }

    public void setSpeedClimbZero(){
        setSpeedClimb(0);
    }
}
