// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.NavigableMap;
import java.util.TreeMap;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class FiringSolutionSubsystem extends SubsystemBase {

  private NavigableMap<Double, Double> shooterSpeeds = new TreeMap<Double, Double>();
  /** Creates a new FiringSolutionSubsystem. */
  public FiringSolutionSubsystem() {
    setUpSpeedLookUpTable();
  }

  private void setUpSpeedLookUpTable() {
    shooterSpeeds.put(0.0, (double) 3600);
    shooterSpeeds.put(36.0, (double) 3600);
    shooterSpeeds.put(48.0, (double) 3900);
    shooterSpeeds.put(60.0, (double) 4200);
    shooterSpeeds.put(72.0, (double) 4500);
    shooterSpeeds.put(84.0, (double) 4800);
    shooterSpeeds.put(96.0, (double) 5100);
    shooterSpeeds.put(108.0, (double) 5400);
    shooterSpeeds.put(120.0, (double) 5700);
    shooterSpeeds.put(132.0, (double) 6000);
    shooterSpeeds.put(144.0, (double) 6300);
  }

  private double getDistance() {
    //figure out the distance to the target
    //math to calculate the distance goes here (d = (h2-h1) / tan(a1+a2)

    double targetOffsetAngle_Vertical = LimelightHelpers.getTY("limelight");

    // how many degrees back is your limelight rotated from perfectly vertical?
    double limelightMountAngleDegrees = 20.0; 

    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches = 22.0; 

    // distance from the target to the floor
    double goalHeightInches = 56.0; 

    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    Constants.k_LLDistanceToAprilTag = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);

    return Constants.k_LLDistanceToAprilTag;
  }

/**
   * Gets wanted shooter speed by interpolating between two closest data points
   * based on distance
   * 
   */
  private double getShooterSpeed() {
    double closeDistance = shooterSpeeds.floorKey(getDistance());
    double farDistance = shooterSpeeds.ceilingKey(getDistance());

    double closeShooter = shooterSpeeds.floorEntry(getDistance()).getValue();

    double farShooter = shooterSpeeds.ceilingEntry(getDistance()).getValue();

    Constants.k_FiringSolutionSpeed = ((farShooter - closeShooter) / (farDistance - closeDistance))* (getDistance() - farDistance) + farShooter;

    return ((farShooter - closeShooter) / (farDistance - closeDistance))* (getDistance() - farDistance) + farShooter;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Constants.k_LLDistanceToAprilTag > 0 && Constants.k_LLDistanceToAprilTag < 144) {
      Constants.k_FiringSolutionSpeed = getShooterSpeed();
    }
    else {
      Constants.k_FiringSolutionSpeed = 3600; //min shooter speed
    }
  }
}
