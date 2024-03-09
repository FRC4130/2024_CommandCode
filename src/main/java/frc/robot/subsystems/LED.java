package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix6.signals.ForwardLimitValue;
import com.ctre.phoenix.led.CANdle.LEDStripType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.generated.TunerConstants;


public class LED extends SubsystemBase{
    // Instance
    private static CANdle mInstance = null;
    // private DriveTrain mDrive;
    // private final Climb mClimber;
    // private final Intake mIntaker;

    // CANdle
    private final CANdle mCANdle;

    // State
    private LEDState mState;


    public enum LEDState {
        IDLE, BALL_DETECT, AIM, PREP_CLIMB, CLIMBY_PARTY_MODE
    }

    public LED() {
        mCANdle = new CANdle(Constants.kCANbusID, "CTRE Chain");
        mCANdle.configLEDType(LEDStripType.GRB);

        // mDrive = Subsystems.driveTrain;
        // mIndexer = Subsystems.index;
        // mClimber = Subsystems.climb;

        mState = LEDState.IDLE;
    }

    public void Idle(){
        mCANdle.setLEDs(0, 0, 255);
        //mCANdle.setLEDs(0, 0, 0);

    }

    public void RainbowParty(){

        var rainbowAnim = new RainbowAnimation(1, 1, 255);

        mCANdle.animate(rainbowAnim);
    }

    public void Tracked(){
        mCANdle.setLEDs(0, 255, 0);
    }

    // public void fullIndex(){

    //     mCANdle.setLEDs(0, 255, 0);

    // }

    public void flames(){
        mCANdle.setLEDs(255, 0, 0);
    }

    public void green(){
        mCANdle.setLEDs(0, 255, 0);
    }

    public void LEDS(){
        if(Intake.intake.getForwardLimit().getValue() == ForwardLimitValue.ClosedToGround){
            green();
        } else{
            Idle();
        }
    }
}