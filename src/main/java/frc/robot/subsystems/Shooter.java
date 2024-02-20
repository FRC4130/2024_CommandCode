package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    public TalonFX right;
    public TalonFX left;

    public Shooter(){
        right = new TalonFX(Constants.kRightID, "CTRE Chain");
        left = new TalonFX(Constants.kLeftID, "CTRE Chain");

        right.setNeutralMode(NeutralModeValue.Coast);
        left.setNeutralMode(NeutralModeValue.Coast);

        left.setInverted(true);
    }

    public void setSpeedShooter(double speedRight, double speedLeft){
        right.set(speedRight);
        left.set(speedLeft);
    }

    public void outtakingDefault(){
        setSpeedShooter(0.4, 0.4);
    }

    public void outtakingFast(){
        setSpeedShooter(0.65, 0.65);
    }

    public void outtakingSlow(){
        setSpeedShooter(0.35, 0.35);
    }

    public void resetpos(){}

    public void stop(){
        setSpeedShooter(0, 0);
    }
}
