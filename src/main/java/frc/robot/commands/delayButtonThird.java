package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.intakeMode;
import frc.robot.Constants.shooterMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class delayButtonThird extends SequentialCommandGroup{
    public delayButtonThird(Intake intakeSub, Shooter shooterSub){
        addCommands(
            new setShooterMode(shooterSub, shooterMode.outtakingSlightlyFasterThanSlow).withTimeout(0.125),
            new ParallelCommandGroup(
                new setShooterMode(shooterSub, shooterMode.outtakingSlightlyFasterThanSlow),
                new setIntakeMode(intakeSub, intakeMode.outtaking)
            )
        );
    }
}