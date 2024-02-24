package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.Wrist;

public class setWristMode extends Command{
    public Wrist wristSubsystem;
    public wristMode wristMode;

    public setWristMode (Wrist wristSubsystem, wristMode wristMode){
        this.wristSubsystem = wristSubsystem;
        this.wristMode = wristMode;

        addRequirements(wristSubsystem);
    }
    

    public void intialize(){
        wristSubsystem.stop();

    }
    public void execute(){
        switch(wristMode){
            case home:
                wristSubsystem.home();
            break;
            case low:
                wristSubsystem.low();
            break; 
            case mid:
                wristSubsystem.mid();
            break;
            case resetpos:
                wristSubsystem.resetPosition();
            break;
            case autoLow:
                wristSubsystem.autoLow();
            break;
            case stop:
                wristSubsystem.stop();
            break; 
        }
    }
    public void end(boolean interrupted){
        wristSubsystem.stop();
    }
}
