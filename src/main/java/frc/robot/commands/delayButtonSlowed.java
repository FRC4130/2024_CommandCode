package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.generated.TunerConstants.intakeMode;
import frc.robot.generated.TunerConstants.shooterMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class delayButtonSlowed extends SequentialCommandGroup{
    public delayButtonSlowed(Intake intakeSub, Shooter shooterSub){
        addCommands(
            new setShooterMode(shooterSub, shooterMode.outtakingDefault).withTimeout(0.125),
            new ParallelCommandGroup(
                new setShooterMode(shooterSub, shooterMode.outtakingDefault),
                new setIntakeMode(intakeSub, intakeMode.outtaking)
            )
        );
    }
}