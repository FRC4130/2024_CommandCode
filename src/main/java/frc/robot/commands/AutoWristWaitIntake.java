package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.generated.TunerConstants.intakeMode;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Wrist;

public class AutoWristWaitIntake extends SequentialCommandGroup{
    public AutoWristWaitIntake(Wrist wristSub, Intake intakeSub){
    addCommands(
        new setWristMode(wristSub, wristMode.autoLow).withTimeout(0.5),
        new setWristMode(wristSub, wristMode.autoLow).alongWith(new setIntakeMode(intakeSub, intakeMode.autoIntaking))
    );
    }
}
