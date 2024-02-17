package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.generated.TunerConstants.armWristMode;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.ArmWrist;
import frc.robot.subsystems.Wrist;

public class leftBumper extends SequentialCommandGroup{
    public leftBumper(Wrist wristSub, ArmWrist armWristSub){
        addCommands(
            new setWristMode(wristSub, wristMode.low).withTimeout(0.5),
            new ParallelCommandGroup(
                new setWristMode(wristSub, wristMode.low),
                new setArmWristMode(armWristSub, armWristMode.pos1)
            )
        );
    }
}
