package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RightClimb extends SubsystemBase{
    TalonFX climb;

    public RightClimb(){
        climb = new TalonFX(Constants.kClimbID, "CTRE Chain");
        climb.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setSpeedClimb(double speed){
        climb.set(speed);
        
    }

    public void upClimb(){
        setSpeedClimb(0.5);
    }

    public void downClimb(){
        setSpeedClimb(-0.5);
    }

    public void stop(){
        setSpeedClimb(0);
    }

}
