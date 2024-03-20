package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class ArmJoystick extends Command{
    private Arm armSubsystem;
    private DoubleSupplier speed;

    public ArmJoystick(Arm armSubsystem, DoubleSupplier speed){
        this.armSubsystem = armSubsystem;
        this.speed = speed;

        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        armSubsystem.stop();
    }

    @Override
    public void execute() {
        armSubsystem.setSpeedArmWrist(speed.getAsDouble()*0.25);
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.setSpeedArmWrist(0);
    }
}
