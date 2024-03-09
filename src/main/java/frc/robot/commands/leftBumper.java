package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.armMode;
import frc.robot.Constants.wristMode;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Wrist;

public class leftBumper extends SequentialCommandGroup{
    public leftBumper(Wrist wristSub, Arm armSub){
        addCommands(
            new setWristMode(wristSub, wristMode.low).withTimeout(0.5),
            new setArmMode(armSub, armMode.pos1).alongWith(new setWristMode(wristSub, wristMode.low)).withTimeout(0.5),
            new setWristMode(wristSub, wristMode.home).alongWith(new setArmMode(armSub, armMode.pos1))
        );
        }
    }
