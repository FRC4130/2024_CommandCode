package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmWrist;
import frc.robot.generated.TunerConstants.armWristMode;

public class setArmWristMode extends Command{
    public ArmWrist armWristSubsystem;
    public armWristMode armWristMode;

    public setArmWristMode (ArmWrist armWristSubsystem, armWristMode armWristMode){
        this.armWristSubsystem = armWristSubsystem;
        this.armWristMode = armWristMode;

        addRequirements(armWristSubsystem);
    }
    

    public void intialize(){
        armWristSubsystem.stop();

    }
    public void execute(){
        switch(armWristMode){
            case pos1:
                armWristSubsystem.pos1();
            break;
            case pos2:
                armWristSubsystem.pos2();
            break; 
            case pos3:
                armWristSubsystem.pos3();
            break;
            case stop:
                armWristSubsystem.stop();
            break; 
        }
    }
    public void end(boolean interrupted){
        armWristSubsystem.stop();
    }
}
