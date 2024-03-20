package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.climbTwoMode;
import frc.robot.subsystems.LeftClimb;

public class setLeftClimbMode extends Command{
    private LeftClimb climbSub;
    private climbTwoMode climbMode; 
    public setLeftClimbMode(LeftClimb climbSub, climbTwoMode climbMode){
        this.climbSub = climbSub;
        this.climbMode = climbMode;
        addRequirements(climbSub);
    }
    @Override
    public void initialize() {
         climbSub.stop();
    }
    @Override
    public void execute() {
         switch(climbMode){
            case upClimbTwo:
                climbSub.upClimbTwo();
            break;
            case downClimbTwo:
                climbSub.downClimbTwo();
            break;
            case stop:
                climbSub.stop();
            break;
         }
    }
    @Override
    public void end(boolean interrupted) {
        climbSub.stop();
    }
}
