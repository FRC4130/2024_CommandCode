package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.intakeMode;
import frc.robot.Constants.wristMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Wrist;

public class AutoWristWaitIntakeTwo extends SequentialCommandGroup{
    public AutoWristWaitIntakeTwo(Wrist wristSub, Intake intakeSub){
    addCommands(
        new WaitCommand(1.5),
        new setWristMode(wristSub, wristMode.autoLow).withTimeout(0.5),
        new setWristMode(wristSub, wristMode.autoLow).alongWith(new setIntakeMode(intakeSub, intakeMode.autoIntaking))
    );
    }
}
