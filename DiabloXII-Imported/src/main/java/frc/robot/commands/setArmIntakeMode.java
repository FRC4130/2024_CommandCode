package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.generated.TunerConstants.armIntakeMode;
import frc.robot.generated.TunerConstants.intakeMode;
import frc.robot.subsystems.ArmIntake;
import frc.robot.subsystems.Intake;

public class setArmIntakeMode extends Command{
    // private ArmIntake armIntakeSub;
    // private armIntakeMode armIntakeMode;

    // public setArmIntakeMode(ArmIntake armIntakeSub, armIntakeMode armIntakeMode){
    //     this.armIntakeSub = armIntakeSub;
    //     this.armIntakeMode = armIntakeMode;

    //     addRequirements(armIntakeSub);
    // }

    // public void intialize(){
    //     armIntakeSub.stop();
    // }

    // public void execute(){
    //     switch(armIntakeMode){
    //         case intaking:
    //             armIntakeSub.intaking();
    //         break;
    //         case outtaking:
    //             armIntakeSub.outtaking();
    //         break;
    //         case stop:
    //             armIntakeSub.stop();
    //         break;
    //     }
    // }
    // public void end(boolean interrupted){
    //     armIntakeSub.stop();
    // }
}
