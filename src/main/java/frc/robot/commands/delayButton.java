package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.intakeMode;
import frc.robot.Constants.shooterMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class delayButton extends SequentialCommandGroup{
    public delayButton(Intake intakeSub, Shooter shooterSub){
        addCommands(
            new setShooterMode(shooterSub, shooterMode.outtakingFast).withTimeout(0.125),
            new ParallelCommandGroup(
                new setShooterMode(shooterSub, shooterMode.outtakingFast),
                new setIntakeMode(intakeSub, intakeMode.outtaking)
            )
        );
    }
}