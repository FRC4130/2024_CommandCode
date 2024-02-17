package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.generated.TunerConstants.armWristMode;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.ArmWrist;
import frc.robot.subsystems.Wrist;

public class leftTrigger extends SequentialCommandGroup{
    public leftTrigger(Wrist wristSub, ArmWrist armWristSub){
        addCommands(
            new setWristMode(wristSub, wristMode.low).withTimeout(1),
            new setArmWristMode(armWristSub, armWristMode.pos2).withTimeout(1),
            new setWristMode(wristSub, wristMode.mid)
        );
    }
}
