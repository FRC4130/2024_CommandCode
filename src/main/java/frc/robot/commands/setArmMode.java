package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;
import frc.robot.generated.TunerConstants.armMode;

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
            case pos1:
                armSubsystem.pos1();
            break;
            case pos2:
                armSubsystem.pos2(); //exchange 
            break; 
            case pos3:
                armSubsystem.pos3();
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
