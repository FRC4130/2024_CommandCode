package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.climbMode;
import frc.robot.subsystems.RightClimb;

public class setRightClimbMode extends Command{
    private RightClimb climbSub;
    private climbMode climbMode; 
    public setRightClimbMode(RightClimb climbSub, climbMode climbMode){
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
            case upClimb:
                climbSub.upClimb();
            break;
            case downClimb:
                climbSub.downClimb();
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
