package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.Wrist;

public class AutoWristLow extends ParallelCommandGroup{
    public AutoWristLow(Wrist wristSub){
    addCommands(
        new setWristMode(wristSub, wristMode.low)
    );
    }
}
