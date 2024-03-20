package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LeftClimb extends SubsystemBase{
    TalonFX climbTwo;

    public LeftClimb(){
        climbTwo = new TalonFX(Constants.kClimbTwoID, "CTRE Chain");
        climbTwo.setNeutralMode(NeutralModeValue.Brake);

        climbTwo.setInverted(true);
    }

    public void setSpeedClimbTwo(double speed){
        climbTwo.set(speed);
    }

    public void upClimbTwo(){
        setSpeedClimbTwo(0.5);
    }

    public void downClimbTwo(){
        setSpeedClimbTwo(-0.5);
    }

    public void stop(){
        setSpeedClimbTwo(0);
    }

}
