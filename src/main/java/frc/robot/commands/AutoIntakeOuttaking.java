package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.intakeMode;
import frc.robot.subsystems.Intake;

public class AutoIntakeOuttaking extends ParallelCommandGroup{
    public AutoIntakeOuttaking(Intake intakeSub){
    addCommands(
        new setIntakeMode(intakeSub, intakeMode.outtaking)
    );
    }
}
