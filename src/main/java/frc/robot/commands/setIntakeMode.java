package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.intakeMode;
import frc.robot.subsystems.Intake;

public class setIntakeMode extends Command{
    private Intake intakeSub;
    private intakeMode intakeMode;

    public setIntakeMode(Intake intakeSub,intakeMode intakeMode){
        this.intakeSub = intakeSub;
        this.intakeMode = intakeMode;

        addRequirements(intakeSub);
    }

    public void intialize(){
        intakeSub.stop();
    }

    public void execute(){
        switch(intakeMode){
            case intaking:
                intakeSub.intaking();
            break;
            case outtaking:
                intakeSub.outtaking();
            break;
            case outtakingSlow:
                intakeSub.outtakingSlow();
            break;
            case autoIntaking:
                intakeSub.autoIntaking();
            break;
            case stop:
                intakeSub.stop();
            break;
        }
    }
    public void end(boolean interrupted){
        intakeSub.stop();
    }
}
