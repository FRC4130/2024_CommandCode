// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.JoystickWrist;
import frc.robot.commands.WristJoystick;
import frc.robot.commands.climbJoystick;
import frc.robot.commands.delayButton;
import frc.robot.commands.delayButtonSlowed;
import frc.robot.commands.setArmIntakeMode;
import frc.robot.commands.setIntakeMode;
import frc.robot.commands.setShooterMode;
import frc.robot.commands.setWristMode;
import frc.robot.generated.TunerConstants;
import frc.robot.generated.TunerConstants.armIntakeMode;
import frc.robot.generated.TunerConstants.intakeMode;
import frc.robot.generated.TunerConstants.shooterMode;
import frc.robot.generated.TunerConstants.wristMode;
import frc.robot.subsystems.ArmIntake;
import frc.robot.subsystems.ArmWrist;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Wrist;

public class RobotContainer {
  private final Shooter shooterSubsystem = new Shooter();
  private final Wrist wristSubsystem = new Wrist();
  private final Intake intakeSubsystem = new Intake();
  private final Climb climbSubsystem = new Climb();
  private final ArmIntake armIntakeSubsytem = new ArmIntake();
  private final ArmWrist armWristSubsystem = new ArmWrist();

  private double MaxSpeed = 6; // 6 meters per second desired top speed
  private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandXboxController joystick = new CommandXboxController(0); // My joystick
  private final CommandPS4Controller opJoystick = new CommandPS4Controller(1);
  private final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
  private final Telemetry logger = new Telemetry(MaxSpeed);

  private void configureBindings() {
    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ));

    joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
    joystick.b().whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(joystick.getLeftY(), joystick.getLeftX()))));

    // reset the field-centric heading on left bumper press
    joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    if (Utils.isSimulation()) {
      drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
    }
    drivetrain.registerTelemetry(logger::telemeterize);

    //Operator Controller

    //Shooter Controls
    // opJoystick.circle().whileTrue(new setShooterMode(shooterSubsystem, shooterMode.intakingSlow));
    opJoystick.square().whileTrue(new setShooterMode(shooterSubsystem, shooterMode.outtakingSlow));
    opJoystick.cross().whileTrue(new setShooterMode(shooterSubsystem, shooterMode.outtakingDefault));
    //Wrist Controls on DPAD
    opJoystick.povDown().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.low));
    opJoystick.povUp().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.home));
    opJoystick.povRight().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.mid));
    //Intake Controls
    opJoystick.triangle().whileTrue(new setIntakeMode(intakeSubsystem, intakeMode.intaking));
    opJoystick.circle().whileTrue(new setIntakeMode(intakeSubsystem, intakeMode.outtaking));
    //Both
    //opJoystick.R1().whileTrue(new setIntakeMode(intakeSubsystem, intakeMode.outtaking).alongWith(new setShooterMode(shooterSubsystem, shooterMode.outtakingFast)));
    opJoystick.R2().whileTrue(new delayButton(intakeSubsystem, shooterSubsystem));

    //Driver Controller
    joystick.leftTrigger().toggleOnTrue(new setIntakeMode(intakeSubsystem, intakeMode.intaking).alongWith(new setWristMode(wristSubsystem, wristMode.low)));
    joystick.rightTrigger().toggleOnTrue(new setIntakeMode(intakeSubsystem, intakeMode.stop).alongWith(new setWristMode(wristSubsystem, wristMode.home)));
    joystick.rightBumper().whileTrue(new delayButton(intakeSubsystem, shooterSubsystem));
    joystick.leftBumper().whileTrue(new delayButtonSlowed(intakeSubsystem, shooterSubsystem));
  }

  public RobotContainer() {
    shooterSubsystem.setDefaultCommand(new setShooterMode(shooterSubsystem, shooterMode.stop));
    intakeSubsystem.setDefaultCommand(new setIntakeMode(intakeSubsystem, intakeMode.stop));
    //armIntakeSubsytem.setDefaultCommand(new setArmIntakeMode(armIntakeSubsytem, armIntakeMode.stop));
    wristSubsystem.setDefaultCommand(new setWristMode(wristSubsystem, wristMode.stop));
    // wristSubsystem.setDefaultCommand(new WristJoystick(wristSubsystem,
    // () -> opJoystick.getLeftY()
    // ));
    // armWristSubsystem.setDefaultCommand(new JoystickWrist(armWristSubsystem,
    // () -> opJoystick.getLeftY()
    // ));
    // climbSubsystem.setDefaultCommand(new climbJoystick(climbSubsystem, 
    // () -> opJoystick.getRightY()
    // ));

    configureBindings();
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
