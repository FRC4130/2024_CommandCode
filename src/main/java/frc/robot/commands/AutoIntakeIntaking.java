package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.generated.TunerConstants.intakeMode;
import frc.robot.subsystems.Intake;

public class AutoIntakeIntaking extends ParallelCommandGroup{
    public AutoIntakeIntaking(Intake intakeSub){
    addCommands(
        new setIntakeMode(intakeSub, intakeMode.intaking)
    );
    }
}
