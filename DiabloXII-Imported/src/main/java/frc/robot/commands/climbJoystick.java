package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climb;

public class climbJoystick extends Command{
    // private Climb climbSubsystem;
    // private DoubleSupplier speed;
    // // private RobotContainer opJoystick;

    // public climbJoystick(Climb climbSubsystem, DoubleSupplier speed){
    //     this.climbSubsystem = climbSubsystem;
    //     this.speed = speed;

    //     addRequirements(climbSubsystem);
    // }

    // @Override
    // public void initialize() {
    //     climbSubsystem.setSpeedClimbZero();
    // }

    // @Override
    // public void execute() {
    //     climbSubsystem.setSpeedClimb(speed.getAsDouble()*0.25);
    //     // double error = 0.15;
    //     // if(opJoystick.get]o < error){
    //     //     climbSubsystem.setSpeedClimbZero();
    //     // } else{
    //     //     climbSubsystem.setSpeedClimb(speed.getAsDouble()*0.25);
    //     // }
    // }

    // @Override
    // public void end(boolean interrupted) {
    //     climbSubsystem.setSpeedClimb(0);
    // }
}
