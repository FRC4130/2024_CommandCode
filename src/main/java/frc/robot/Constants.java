// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;

/** Add your docs here. */
public class Constants {

    //tenniswoods code?

    public static Rotation2d k_steering_target = new Rotation2d(Math.toRadians(0)); //used by AutoAlignCommand and "y" to rotate to target
    public static boolean k_ShoulderMMisMoving = false;
    public static boolean k_WristMMisMoving = false;
    public static final double k_MMRange = .005; //this is the range MM is considered finished

    public static boolean k_NoteisReady = false;
    public static final double ShootDelayTime = 0.5; //how long to wait before feeding note into shooter
    public static final double HomeDelayTime = 0.5; //how long to wait after shooting to home shoulder and wrist

    //Shoulder MM postions
    public static final double k_ShoulderHomePosition = 2;
    public static final double k_ShoulderShootPosition = 40; //40
    public static final double k_ShoulderAmpPosition = 50; //this is a guess

    //Wrist MM Position
    public static double k_WristHomePosition = 0.035;
    public static double k_WristShootPosition = 0.137; //
    public static double k_WristAmpPosition = 0.3; //this is a quess

    //FiringSolutionSubsystem
    public static double k_LLDistanceToAprilTag = 0.0;
    public static double k_FiringSolutionSpeed = 3600.0;

    //my code

    //Climb
    public static final int kClimbID = 51;

    //Wrist
    public static final int kWristID = 11;

    public static enum wristMode {
        home,
        low,
        exchange,
        resetpos,
        stop
      }
    //Shooter
    public static final int kRightID = 44;
    public static final int kLeftID = 42;
    
    public static enum shooterMode {
        outtakingFast,
        outtakingDefault,
        outtakingSlow,
        resetpos,
        stop
    }

    //Intake
    public static final int kIntakeID = 45;

    public static enum intakeMode {
        intaking,
        outtaking,
        outtakingSlow,
        stop
    }

    //Arm Wrist
    public static final int kArmWristID = 50;

    public static enum armMode {
        home,
        exchange,
        amp,
        stop
    }

    //Arm Intake
    public static final int kArmIntakeID = 49;

    public static enum armRollersMode {
        intaking,
        stop
    }

}
