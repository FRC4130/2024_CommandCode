package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase{
    TalonFX climb;
    TalonFX climbTwo;

    public Climb(){
        climb = new TalonFX(Constants.kClimbID, "CTRE Chain");
        climbTwo = new TalonFX(Constants.kClimbTwoID, "CTRE Chain");
        climb.setNeutralMode(NeutralModeValue.Brake);
        climb.setNeutralMode(NeutralModeValue.Brake);

        climbTwo.setInverted(true);
    }

    public void setSpeedClimb(double speed){
        climb.set(speed);
        climbTwo.set(speed);
    }

    public void setSpeedClimbZero(){
        setSpeedClimb(0);
    }
}
