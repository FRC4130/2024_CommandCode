package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class JoystickWrist extends Command{
    private Arm armWristSubsystem;
    private DoubleSupplier speed;

    public JoystickWrist(Arm armWristSubsystem, DoubleSupplier speed){
        this.armWristSubsystem = armWristSubsystem;
        this.speed = speed;

        addRequirements(armWristSubsystem);
    }

    @Override
    public void initialize() {
        armWristSubsystem.setSpeedArmWrist(0);
    }

    @Override
    public void execute() {
        armWristSubsystem.setSpeedArmWrist(speed.getAsDouble()*0.25);
    }

    @Override
    public void end(boolean interrupted) {
        armWristSubsystem.setSpeedArmWrist(0);
    }
}
