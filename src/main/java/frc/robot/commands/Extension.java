package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Wrist;

public class Extension extends Command{
    private Wrist wristSub;
    private Arm armSub;
    private BooleanSupplier isWristCollapsed; //Wrist Collapsed = not in exchange position
    private BooleanSupplier isArmCollapsed; //Arm Collapsed = not in exchange position

    public Extension(BooleanSupplier isWristCollapsed, BooleanSupplier isArmCollapsed, Wrist wristSub, Arm armSub) {
        this.isWristCollapsed = isWristCollapsed;
        this.isArmCollapsed = isArmCollapsed;
        this.wristSub = wristSub;
        this.armSub = armSub;
        addRequirements(wristSub, armSub);
    }

    @Override
    public void execute() {
        if (!isArmCollapsed.getAsBoolean()) {
            wristSub.exchange(); //If arm is not collapsed then wrist will be in exchange
        } else if(!isWristCollapsed.getAsBoolean()){
            armSub.exchange(); //If wrist is not collapsed then arm will be in exchange (will this work or will both wrist and arm be stuck in exchange, lets find out)
        // } else if(!isArmCollapsed.getAsBoolean() && !isWristCollapsed.getAsBoolean()){
        //     return;
        } else return;
    }
    
}
