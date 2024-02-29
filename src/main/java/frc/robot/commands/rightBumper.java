package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.generated.TunerConstants.armMode;
import frc.robot.generated.TunerConstants.armRollersMode;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ArmRollers;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Wrist;

public class rightBumper extends SequentialCommandGroup{
    public rightBumper(Wrist wristSub, Arm armSub, ArmRollers armRollersSub, Intake intake){
        addCommands(
            new setWristMode(wristSub, wristMode.mid).withTimeout(1.5),
            new setArmMode(armSub, armMode.pos3).withTimeout(1),
            new setWristMode(wristSub, wristMode.home).alongWith(new setArmMode(armSub, armMode.pos3))
           // new setArmRollersMode(armRollersSub, armRollersMode.intaking).alongWith(new setArmMode(armSub, armMode.pos3))
        );
    }
}
