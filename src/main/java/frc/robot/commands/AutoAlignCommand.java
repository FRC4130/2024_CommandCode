// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain.SwerveDriveState;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.CommandSwerveDrivetrain;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class AutoAlignCommand extends Command {
  double tx = 0;
  double targetDistance = 75; //target distance in inches from the speaker
  double driveSpeed = 1; //speed used when driving towards or away from target m/sec
  CommandSwerveDrivetrain _drivetrain;
  /** Creates a new AutoAlignCommand. */
  public AutoAlignCommand(CommandSwerveDrivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    _drivetrain = drivetrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
  // Called every time the scheduler runs while the command is scheduled.

  @Override
  public void execute() {
      tx = LimelightHelpers.getTX("limelight");
      SwerveDriveState pose = _drivetrain.getState();
      var alliance = DriverStation.getAlliance();

      double targetOffsetAngle_Vertical = LimelightHelpers.getTY("limelight");

      // how many degrees back is your limelight rotated from perfectly vertical?
      double limelightMountAngleDegrees = 21.0; 
  
      // distance from the center of the Limelight lens to the floor
      double limelightLensHeightInches = 13.5; 
  
      // distance from the target to the floor
      double goalHeightInches = 57.0; 
  
      double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
      double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
  
      //calculate distance
      double k_LLDistanceToAprilTag = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);

      if (Math.abs(k_LLDistanceToAprilTag - targetDistance) >= 6) { //get within +/-X" of the targetDistance
        //drive towards or away from target
        if (k_LLDistanceToAprilTag > targetDistance) {
          Constants.k_drive_target = driveSpeed; //drive forward
        } else {
          Constants.k_drive_target = driveSpeed * -1.0; //drive backwards
        }
      } else {
        Constants.k_drive_target = 0; //stop moving
      }

      if (alliance.get() == DriverStation.Alliance.Blue) {

        if (tx <= 35) { //blue alliance
          Constants.k_steering_target = new Rotation2d(Math.toRadians(pose.Pose.getRotation().getDegrees() - tx));
        }
        else {
          Constants.k_steering_target = new Rotation2d(Math.toRadians(pose.Pose.getRotation().getDegrees()));
        }
      } else { //Red alliance
        if (tx > 35) {
          Constants.k_steering_target = new Rotation2d(Math.toRadians(pose.Pose.getRotation().getDegrees() - tx - 180));
        }
        else if(tx <= 35){
          Constants.k_steering_target = new Rotation2d(Math.toRadians(pose.Pose.getRotation().getDegrees() - tx + 180));
        }
        else {
          Constants.k_steering_target = new Rotation2d(Math.toRadians(pose.Pose.getRotation().getDegrees()));
        }
      }

    System.out.println("limelight tx: " + tx);
    System.out.println("Rotation2d: " +_drivetrain.getState().Pose.getRotation().getDegrees());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return false;
    if (tx < 1) {
      return true;
    } else {
      return false;
    }
  }
}