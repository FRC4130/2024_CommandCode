package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Wrist;

public class  WristJoystick extends Command{
    private Wrist WristSubsystem;
    private DoubleSupplier speed;

    public WristJoystick(Wrist WristSubsystem, DoubleSupplier speed){
        this.WristSubsystem = WristSubsystem;
        this.speed = speed;

        addRequirements(WristSubsystem);
    }

    @Override
    public void initialize() {
        WristSubsystem.setSpeedWrist(0);
    }

    @Override
    public void execute() {
        WristSubsystem.setSpeedWrist(speed.getAsDouble()*0.25);
    }

    @Override
    public void end(boolean interrupted) {
        WristSubsystem.setSpeedWrist(0);
    }
}
