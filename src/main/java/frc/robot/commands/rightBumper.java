package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.armMode;
import frc.robot.Constants.armRollersMode;
import frc.robot.Constants.wristMode;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Wrist;

public class rightBumper extends SequentialCommandGroup{
    public rightBumper(Wrist wristSub, Arm armSub, Intake intake){
        addCommands(
            new setWristMode(wristSub, wristMode.low).withTimeout(1.5),
            new setArmMode(armSub, armMode.pos3).withTimeout(1),
            new setWristMode(wristSub, wristMode.home).alongWith(new setArmMode(armSub, armMode.pos3))
           // new setArmRollersMode(armRollersSub, armRollersMode.intaking).alongWith(new setArmMode(armSub, armMode.pos3))
        );
    }
}
