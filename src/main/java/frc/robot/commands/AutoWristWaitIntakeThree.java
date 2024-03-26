package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.intakeMode;
import frc.robot.Constants.wristMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Wrist;

public class AutoWristWaitIntakeThree extends SequentialCommandGroup{
    public AutoWristWaitIntakeThree(Wrist wristSub, Intake intakeSub){
    addCommands(
        new WaitCommand(0.5),
        new setWristMode(wristSub, wristMode.autoLow).withTimeout(0.5),
        new setWristMode(wristSub, wristMode.autoLow).alongWith(new setIntakeMode(intakeSub, intakeMode.autoIntaking))
    );
    }
}
