// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.generated.TunerConstants;

//public class LED extends SubsystemBase{
    // // Instance
    // private static CANdle mInstance = null;

    // // CANdle
    // private final CANdle mCANdle;

    // // State
    // private LEDState mState;

    // LED = new AddressableLED(0);


    // public enum LEDState {
    //     IDLE, BALL_DETECT, AIM, PREP_CLIMB, CLIMBY_PARTY_MODE
    // }

    // public LED() {
    //     mCANdle = TunerConstants.kCANbusName;
    //     mCANdle.configLEDType(LEDStripType.GRB);

    //     mState = LEDState.IDLE;
    // }

    // public void Idle(){
    //     mCANdle.setLEDs(0, 0, 255);
    //     //mCANdle.setLEDs(0, 0, 0);

    // }

    // public void RainbowParty(){

    //     var rainbowAnim = new RainbowParty(1, 1, 255);

    //     mCANdle.animate(rainbowAnim);
    // }

    // public void Tracked(){
    //     mCANdle.setLEDs(0, 255, 0);
    // }

    // public void fillIndex(){
    //     if(!mIndexer.sensor.isPressed()){
    //         mCANdle.setLEDs(255, 0, 0, 0, 0, 50);
    //         //mCANdle.setLEDs(255, 0, 0, 0, 125, 50);
    //     }
    // }

    // public void fullIndex(){

    //     mCANdle.setLEDs(0, 255, 0);

    // }

    // public void flames(){
    //     mCANdle.setLEDs(255, 0, 0);
    // }
//}
