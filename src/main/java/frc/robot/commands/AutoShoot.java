package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.generated.TunerConstants.intakeMode;
import frc.robot.generated.TunerConstants.shooterMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends SequentialCommandGroup{
    public AutoShoot(Intake intakeSub, Shooter shooterSub){
        addCommands(
            new setShooterMode(shooterSub, shooterMode.outtakingFast).withTimeout(0.25),
            new ParallelCommandGroup(
                new setShooterMode(shooterSub, shooterMode.outtakingFast),
                new setIntakeMode(intakeSub, intakeMode.outtaking)
            )
        );
    }
}