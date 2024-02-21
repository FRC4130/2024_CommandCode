package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.TunerConstants;

public class Climb extends SubsystemBase{
    TalonFX climb;

    public Climb(){
        climb = new TalonFX(TunerConstants.kClimbID, "CTRE Chain");   
        climb.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setSpeedClimb(double speed){
        climb.set(speed);
    }

    public void setSpeedClimbZero(){
        setSpeedClimb(0);
    }
}
