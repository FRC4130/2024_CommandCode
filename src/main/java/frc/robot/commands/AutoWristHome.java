package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.Wrist;

public class AutoWristHome extends ParallelCommandGroup{
    public AutoWristHome(Wrist wristSub){
    addCommands(
        new setWristMode(wristSub, wristMode.home)
    );
    }
}
