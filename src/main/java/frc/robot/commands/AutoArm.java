package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.armMode;
import frc.robot.subsystems.Arm;

public class AutoArm extends SequentialCommandGroup{
    public AutoArm(Arm armSub){
        addCommands(
            new setArmMode(armSub, armMode.pos3)
        );
    }
}
