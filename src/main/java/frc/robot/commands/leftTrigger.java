package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.generated.TunerConstants.armMode;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Wrist;

public class leftTrigger extends SequentialCommandGroup{
    public leftTrigger(Wrist wristSub, Arm armSub){
        addCommands(
            new setWristMode(wristSub, wristMode.low).withTimeout(1),
            new setArmMode(armSub, armMode.pos2).alongWith(new setWristMode(wristSub, wristMode.low)).withTimeout(1),
            new setWristMode(wristSub, wristMode.mid).alongWith(new setArmMode(armSub, armMode.pos2))
        );
    }
}
