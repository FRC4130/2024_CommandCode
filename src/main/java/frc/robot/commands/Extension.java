package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Wrist;

public class Extension extends Command{
    private Wrist wristSub;
    private Arm armSub;
    private BooleanSupplier isWristLow; //Wrist Collapsed = not in exchange position
    private BooleanSupplier isArmAmp; //Arm Collapsed = not in exchange position
    private BooleanSupplier isWristExchange;
    private BooleanSupplier isArmExchange;
    private BooleanSupplier isWristHome;
    private BooleanSupplier isAmpHome;

    public Extension(BooleanSupplier isWristLow, BooleanSupplier isArmAmp,BooleanSupplier isWristExchange, BooleanSupplier isArmExchange, BooleanSupplier isWristHome, BooleanSupplier isArmHome, Wrist wristSub, Arm armSub) {
        this.isWristLow = isWristLow;
        this.isArmAmp = isArmAmp;
        this.isWristExchange = isWristExchange;
        this.isArmExchange = isArmExchange;
        this.isWristHome = isWristHome;
        this.isAmpHome = isArmHome;
        this.wristSub = wristSub;
        this.armSub = armSub;
        addRequirements(wristSub, armSub);
    }

    @Override
    public void execute() {
        if (){

        }
    }
    
}
