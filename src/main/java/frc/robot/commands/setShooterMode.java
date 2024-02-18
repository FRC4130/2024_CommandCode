package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.generated.TunerConstants.shooterMode;
import frc.robot.subsystems.Shooter;

public class setShooterMode extends Command{
    private Shooter shooter;
    private shooterMode shooterMode;

    public setShooterMode(Shooter shooter,shooterMode shooterMode){
        this.shooter = shooter;
        this.shooterMode = shooterMode;

        addRequirements(shooter);
    }

    public void intialize(){
        shooter.stop();
    }

    public void execute(){
        switch(shooterMode){
            case outtakingFast:
                shooter.outtakingFast();
            break;
            case outtakingDefault:
                shooter.outtakingDefault();
            break;
            case outtakingSlow:
                shooter.outtakingSlow();
            break;
            case resetpos:
                shooter.resetpos();
            break;
            case stop:
                shooter.stop();
            break;
        }
    }
    public void end(boolean interrupted){
        shooter.stop();
    }
}
