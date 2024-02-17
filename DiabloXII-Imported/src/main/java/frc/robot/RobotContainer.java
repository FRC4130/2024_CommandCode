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
import frc.robot.commands.climbJoystick;
import frc.robot.commands.delayButton;
import frc.robot.commands.delayButtonSlowed;
import frc.robot.commands.leftBumper;
import frc.robot.commands.leftTrigger;
import frc.robot.commands.setArmIntakeMode;
import frc.robot.commands.setArmWristMode;
import frc.robot.commands.setIntakeMode;
import frc.robot.commands.setShooterMode;
import frc.robot.commands.setWristMode;
import frc.robot.generated.TunerConstants;
import frc.robot.generated.TunerConstants.armIntakeMode;
import frc.robot.generated.TunerConstants.armWristMode;
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
  //Subsystems being created, similar to motors being created, but with subsystems
  private final Shooter shooterSubsystem = new Shooter();
  private final Wrist wristSubsystem = new Wrist();
  private final Intake intakeSubsystem = new Intake();
  private final Climb climbSubsystem = new Climb();
  private final ArmIntake armIntakeSubsytem = new ArmIntake();
  private final ArmWrist armWristSubsystem = new ArmWrist();


  //SHH DONT LOOK AT THIS PART ITS CONFUSING LIKE JOHNS HAND WRITING
  
  //for further explanation, this code is generated and not made by me. It makes the drivetrain work n stuff
  //look at your own risk?


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


    //ok you can look now !!
    //this code is actually made by me and not generated, therefore we can all understand it together


    //Operator Controller
    //DPAD
    opJoystick.povDown().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.low)); // wrist to pickup/low position
    opJoystick.povRight().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.mid)); // exchange position wrist
    opJoystick.povUp().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.home)); // wrist to stored/home position
    opJoystick.povLeft().whileTrue(new setIntakeMode(intakeSubsystem, intakeMode.outtakingSlow)); // intake outtaking slower than default
    //SHAPES
    opJoystick.square().toggleOnTrue(new setArmWristMode(armWristSubsystem, armWristMode.pos2)); // exchange position amp arm
    opJoystick.cross().toggleOnTrue(new setArmWristMode(armWristSubsystem, armWristMode.pos3)); // amp/trap position amp arm
    opJoystick.triangle().toggleOnTrue(new setArmWristMode(armWristSubsystem, armWristMode.pos1)); // home postition amp arm
    opJoystick.circle().whileTrue(new setArmIntakeMode(armIntakeSubsytem, armIntakeMode.intaking)); //intaking/outaking amp arm
    //BUMPERS AND TRIGGERS
    opJoystick.R1().onTrue(new setWristMode(wristSubsystem, wristMode.resetpos)); // wrist home position reset (he thinks this is home when pressed)
    opJoystick.R2().whileTrue(new delayButton(intakeSubsystem, shooterSubsystem)); // Default shoot button - shooter runs then shooter + intake outtakes run together
    opJoystick.L1().whileTrue(new leftBumper(wristSubsystem, armWristSubsystem)); // Left bumper command - Wrist low position delayed then amp arm goes home
    opJoystick.L2().whileTrue(new leftTrigger(wristSubsystem, armWristSubsystem)); // Left trigger command - Wrist low position delayed, armp arm exchange position delayed, wrist exchange position

    //Driver Controller
    joystick.leftTrigger().toggleOnTrue(new setIntakeMode(intakeSubsystem, intakeMode.intaking).alongWith(new setWristMode(wristSubsystem, wristMode.low))); // Intake intaking while the wrist goes to low position - runs at the same time
    joystick.rightTrigger().toggleOnTrue(new setIntakeMode(intakeSubsystem, intakeMode.stop).alongWith(new setWristMode(wristSubsystem, wristMode.home))); // intake stops running while the wrist goes to home position - runs at the same time
    joystick.rightBumper().whileTrue(new delayButton(intakeSubsystem, shooterSubsystem)); // Default shoot button, see above for further description
    joystick.leftBumper().whileTrue(new delayButtonSlowed(intakeSubsystem, shooterSubsystem)); // Default shoot button but slower
  }

  public RobotContainer() {
    // ALL COMMANDS ARE BEING RUN ON STARTUP, THIS IS THERE DEFAULT POSITION THEY WILL AUTOMATICALLY GO TO IF NOT BEING TOLD OTHERWISE
    shooterSubsystem.setDefaultCommand(new setShooterMode(shooterSubsystem, shooterMode.stop)); // shooter not running
    intakeSubsystem.setDefaultCommand(new setIntakeMode(intakeSubsystem, intakeMode.stop)); // intake not running
    armIntakeSubsytem.setDefaultCommand(new setArmIntakeMode(armIntakeSubsytem, armIntakeMode.stop)); // arm intake not running
    wristSubsystem.setDefaultCommand(new setWristMode(wristSubsystem, wristMode.stop)); // wrist being at home position and not running
    armWristSubsystem.setDefaultCommand(new JoystickWrist(armWristSubsystem,
    () -> opJoystick.getRightY()
    )); // Arm wrist being manually controlled by the Right joystick on the Y axis (vertical)
    climbSubsystem.setDefaultCommand(new climbJoystick(climbSubsystem, 
    () -> opJoystick.getLeftY()
    )); // Climb being manually controlled by the Left joystick on the Y axis (vertical)

    configureBindings();
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
