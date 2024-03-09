package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.armRollersMode;
import frc.robot.subsystems.ArmRollers;

public class setArmRollersMode extends Command{
    private ArmRollers armRollersSub;
    private armRollersMode armRollersMode;

    public setArmRollersMode(ArmRollers armRollersSub, armRollersMode armRollersMode){
        this.armRollersSub = armRollersSub;
        this.armRollersMode = armRollersMode;

        addRequirements(armRollersSub);
    }

    public void intialize(){
        armRollersSub.stop();
    }

    public void execute(){
        switch(armRollersMode){
            case intaking:
                armRollersSub.intaking();
            break;
            case stop:
                armRollersSub.stop();
            break;
        }
    }
    public void end(boolean interrupted){
        armRollersSub.stop();
    }
}
