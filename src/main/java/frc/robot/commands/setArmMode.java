package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.armMode;
import frc.robot.subsystems.Arm;

public class setArmMode extends Command{
    public Arm armSubsystem;
    public armMode armMode;

    public setArmMode (Arm armSubsystem, armMode armMode){
        this.armSubsystem = armSubsystem;
        this.armMode = armMode;

        addRequirements(armSubsystem);
    }
    

    public void intialize(){
        armSubsystem.stop();

    }
    public void execute(){
        switch(armMode){
            case home:
                armSubsystem.home();
            break;
            case exchange:
                armSubsystem.exchange(); //exchange 
            break; 
            case amp:
                armSubsystem.amp();
            break;
            case stop:
                armSubsystem.stop();
            break; 
        }
    }
    public void end(boolean interrupted){
        armSubsystem.stop();
    }
}
