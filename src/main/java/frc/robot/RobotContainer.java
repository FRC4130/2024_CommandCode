// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.utility.PhoenixPIDController;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.commands.*;
import frc.robot.generated.TunerConstants;
import frc.robot.Constants.armMode;
import frc.robot.Constants.climbMode;
import frc.robot.Constants.climbTwoMode;
import frc.robot.Constants.intakeMode;
import frc.robot.Constants.shooterMode;
import frc.robot.Constants.wristMode;
import frc.robot.subsystems.*;

public class RobotContainer {
  private double MaxSpeed = TunerConstants.kSpeedAt12VoltsMps; // kSpeedAt12VoltsMps desired top speed
  private double MinSpeed = TunerConstants.kSpeedAt12VoltsMps / 2; // min speed used during go slow
  private double MaxAngularRate = 2.5 * Math.PI; // 3/4 of a rotation per second max angular velocity  1.5
  private double POVSpeed = TunerConstants.kSpeedAt12VoltsMps / 6; //min speed used with POV buttons


    //subsystems used
  //public static FiringSolutionSubsystem firingsolutionsubsystem = new FiringSolutionSubsystem();
    private final Shooter shooterSubsystem = new Shooter();
  private final Wrist wristSubsystem = new Wrist();
  private final Intake intakeSubsystem = new Intake();
  private final RightClimb rightClimbSubsystem = new RightClimb();
  private final LeftClimb leftClimbSubsystem = new LeftClimb();
  private final Arm armSubsystem = new Arm();
  private final LED ledSubsystem = new LED();

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandXboxController joystick = new CommandXboxController(0); // My joystick
  private final CommandPS4Controller opJoystick = new CommandPS4Controller(1);
  public final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric().withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1).withDriveRequestType(DriveRequestType.OpenLoopVoltage); //10% deadband openloop driving
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.RobotCentric forwardStraight = new SwerveRequest.RobotCentric().withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1).withDriveRequestType(DriveRequestType.OpenLoopVoltage);
  //private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
  private final SwerveRequest.FieldCentricFacingAngle fieldcentricfacingangle = new SwerveRequest.FieldCentricFacingAngle().withDeadband(MaxSpeed * 0.1).withDriveRequestType(DriveRequestType.OpenLoopVoltage);
  

  /* Path follower */
  //private Command runAuto = drivetrain.getAutoPath("Tests");

  private final Telemetry logger = new Telemetry(MaxSpeed);

  //Auto Chooser
  private final SendableChooser<Command> autochooser;

  private void configureBindings() {
    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ).ignoringDisable(true));

//assign driver joystick buttons to drivetrain functions
    //Robot centric driving "aka forwardStraight"
    /*
    joystick.y().toggleOnTrue(drivetrain.applyRequest(() -> forwardStraight.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
    .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
    .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
    ).ignoringDisable(true));
    */

    //Go Slow mode
    // joystick.rightBumper().whileTrue(drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * MinSpeed) // Drive forward with negative Y (forward) / 2
    //                                     .withVelocityY(-joystick.getLeftX() * MinSpeed) // Drive left with negative X (left) / 2
    //                                     .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
    //                                     ).ignoringDisable(true));

    //X-stop brake mode
    joystick.x().whileTrue(drivetrain.applyRequest(() -> brake));

    //AutoAlign to apriltag
    //fieldcentricfacingangle.HeadingController can be found in the POV button section
    joystick.rightStick().whileTrue(drivetrain.applyRequest(() -> fieldcentricfacingangle.withVelocityX(-Constants.k_drive_target)
                                        .withVelocityY(-joystick.getLeftX() * MaxSpeed)
                                        .withTargetDirection(Constants.k_steering_target) //this would be the angle to line up with
                                        ).ignoringDisable(true))
                                        .whileTrue(new AutoAlignCommand(drivetrain).repeatedly());

       // reset the field-centric heading on left bumper press
    joystick.back().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    // if (Utils.isSimulation()) {
    //   drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
    // }
    drivetrain.registerTelemetry(logger::telemeterize);

    //Operator Controller
    //DPAD
    opJoystick.povDown().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.low)); // wrist to pickup/low position
    opJoystick.povRight().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.mid)); // exchange position wrist
    opJoystick.povUp().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.home)); // wrist to stored/home position
    opJoystick.povLeft().whileTrue(new setIntakeMode(intakeSubsystem, intakeMode.outtakingSlow)); // intake outtaking slower than default
    //SHAPES
    opJoystick.square().toggleOnTrue(new setArmMode(armSubsystem, armMode.pos2)); // exchange position amp arm
    opJoystick.triangle().toggleOnTrue(new setArmMode(armSubsystem, armMode.pos3)); // amp/trap position amp arm
    opJoystick.cross().toggleOnTrue(new setArmMode(armSubsystem, armMode.pos1)); // home postition amp arm
    opJoystick.circle().whileTrue(new setIntakeMode(intakeSubsystem, intakeMode.intaking));
    //BUMPERS AND TRIGGERS
    opJoystick.R1().whileTrue(new setLeftClimbMode(leftClimbSubsystem, climbTwoMode.upClimbTwo));
    opJoystick.R2().whileTrue(new setLeftClimbMode(leftClimbSubsystem, climbTwoMode.downClimbTwo));
    opJoystick.L1().whileTrue(new setRightClimbMode(rightClimbSubsystem, climbMode.upClimb));
    opJoystick.L2().whileTrue(new setRightClimbMode(rightClimbSubsystem, climbMode.downClimb));
    //SHARE N OPTIONS
    opJoystick.share().whileTrue(new setArmMode(armSubsystem, armMode.resetPos));
    opJoystick.options().whileTrue(new setWristMode(wristSubsystem, wristMode.resetpos));

    //Driver Controller
    joystick.leftTrigger().toggleOnTrue(new setIntakeMode(intakeSubsystem, intakeMode.intaking).alongWith(new setWristMode(wristSubsystem, wristMode.low))); // Intake intaking while the wrist goes to low position - runs at the same time
    joystick.rightTrigger().whileTrue(new delayButtonThird(intakeSubsystem, shooterSubsystem)); // intake stops running while the wrist goes to home position - runs at the same time
    joystick.rightBumper().whileTrue(new delayButton(intakeSubsystem, shooterSubsystem)); // Default shoot button, see above for further description
    joystick.leftBumper().toggleOnTrue(new setWristMode(wristSubsystem, wristMode.home).alongWith(new setIntakeMode(intakeSubsystem, intakeMode.stop))); // Default shoot button but slower
    joystick.start().onTrue(new setWristMode(wristSubsystem, wristMode.resetpos).alongWith(new setArmMode(armSubsystem, armMode.resetPos)));


    //POV buttons slow mode auto rotate to zero
    fieldcentricfacingangle.HeadingController = new PhoenixPIDController(10, 0, 0);//was 10
    SendableRegistry.setName(fieldcentricfacingangle.HeadingController, "AutoAlign", "fcfa HeadingController");
    Rotation2d alignangle = Rotation2d.fromDegrees(0); //sets the angle the robot should face to zero
    joystick.pov(0).whileTrue(drivetrain.applyRequest(()->fieldcentricfacingangle.withVelocityX(POVSpeed).withVelocityY(0).withTargetDirection(alignangle)));
    joystick.pov(180).whileTrue(drivetrain.applyRequest(()->fieldcentricfacingangle.withVelocityX(-POVSpeed).withVelocityY(0).withTargetDirection(alignangle)));
    joystick.pov(90).whileTrue(drivetrain.applyRequest(()->fieldcentricfacingangle.withVelocityX(0.0).withVelocityY(-POVSpeed).withTargetDirection(alignangle)));
    joystick.pov(270).whileTrue(drivetrain.applyRequest(()->fieldcentricfacingangle.withVelocityX(0.0).withVelocityY(POVSpeed).withTargetDirection(alignangle)));



   
    /* Bindings for drivetrain characterization */
    /* These bindings require multiple buttons pushed to swap between quastatic and dynamic */
    /* Back/Start select dynamic/quasistatic, Y/X select forward/reverse direction */
    joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
    joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
    joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
    joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));
  }

  //Pathplanner named commands, assigning the named commands in pathplanner with the commands in code
  private void namedcommands(){
    //wrist
    NamedCommands.registerCommand("Wrist Low", new AutoWristLow(wristSubsystem).withTimeout(1));
    NamedCommands.registerCommand("Wrist Low 05", new AutoWristLow(wristSubsystem).withTimeout(0.5));
    NamedCommands.registerCommand("Wrist Low 15", new AutoWristLow(wristSubsystem).withTimeout(1.5));
    NamedCommands.registerCommand("Wrist Home", new AutoWristHome(wristSubsystem).withTimeout(0.5));
    NamedCommands.registerCommand("Arm pos3", new AutoArm(armSubsystem).withTimeout(2));
    //intake
    NamedCommands.registerCommand("Wrist Wait Intake", new AutoWristWaitIntake(wristSubsystem, intakeSubsystem).withTimeout(1.5));
    NamedCommands.registerCommand("Wrist Wait Intake Two", new AutoWristWaitIntakeTwo(wristSubsystem, intakeSubsystem).withTimeout(3.4));
    NamedCommands.registerCommand("Wrist Wait Intake Three", new AutoWristWaitIntakeThree(wristSubsystem, intakeSubsystem).withTimeout(3.4));
    NamedCommands.registerCommand("Wrist Wait Intake Three 2", new AutoWristWaitIntakeThree(wristSubsystem, intakeSubsystem).withTimeout(3));
    NamedCommands.registerCommand("Wrist Wait Intake Three 25", new AutoWristWaitIntakeThree(wristSubsystem, intakeSubsystem).withTimeout(2.5));
    NamedCommands.registerCommand("Wrist Wait Intake Three 4", new AutoWristWaitIntakeThree(wristSubsystem, intakeSubsystem).withTimeout(2));
    NamedCommands.registerCommand("Wrist Wait Intake 17", new AutoWristWaitIntake(wristSubsystem, intakeSubsystem).withTimeout(1.7));
    NamedCommands.registerCommand("Wrist Wait Intake 1", new AutoWristWaitIntake(wristSubsystem, intakeSubsystem).withTimeout(2));
    NamedCommands.registerCommand("Intake Intaking", new AutoIntakeIntaking(intakeSubsystem).withTimeout(2));
    NamedCommands.registerCommand("Intake Outtaking", new AutoIntakeOuttaking(intakeSubsystem).withTimeout(2));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 15", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(1.5));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 0125", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(0.125));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 0135", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(0.135));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 1", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(1));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 2", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(2));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 25", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(2.5));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 3", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(3));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 4", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(4));
    NamedCommands.registerCommand("Wrist Low And Intake Intaking 5", new AutoWristLowAndIntakeIntaking(wristSubsystem, intakeSubsystem).withTimeout(5));
    NamedCommands.registerCommand("Shoot", new delayButton(intakeSubsystem, shooterSubsystem).withTimeout(0.35));
    NamedCommands.registerCommand("Auto Shoot", new AutoShoot(intakeSubsystem, shooterSubsystem).withTimeout(1));
    NamedCommands.registerCommand("Auto Shoot Two", new AutoShootTwo(intakeSubsystem, shooterSubsystem).withTimeout(1.2));
    NamedCommands.registerCommand("Auto Low Shoot", new AutoLowShoot(intakeSubsystem, shooterSubsystem).withTimeout(1));
    NamedCommands.registerCommand("Auto Align", new AutoAlignCommand(drivetrain).withTimeout(0.8));
    NamedCommands.registerCommand("Auto Align 1", new AutoAlignCommand(drivetrain).withTimeout(1));
  }

  public RobotContainer() {
    configureBindings();
    namedcommands(); //pathplanner namedcommands

    //pathplanner sendablechooser
    autochooser = AutoBuilder.buildAutoChooser("None");
    SmartDashboard.putData("Auto Chooser", autochooser);

    // ALL COMMANDS ARE BEING RUN ON STARTUP, THIS IS THERE DEFAULT POSITION THEY WILL AUTOMATICALLY GO TO IF NOT BEING TOLD OTHERWISE
    shooterSubsystem.setDefaultCommand(new setShooterMode(shooterSubsystem, shooterMode.outtakingSlow)); // shooter not running
    intakeSubsystem.setDefaultCommand(new setIntakeMode(intakeSubsystem, intakeMode.stop)); // intake not running
    armSubsystem.setDefaultCommand(new setArmMode(armSubsystem, armMode.pos3)); // wrist being at home position and not running
    wristSubsystem.setDefaultCommand(new JoystickWrist(wristSubsystem,
    () -> opJoystick.getLeftY()
    )); // Arm wrist being manually controlled by the Right joystick on the Y axis (vertical)
    rightClimbSubsystem.setDefaultCommand(new setRightClimbMode(rightClimbSubsystem, climbMode.stop));
    leftClimbSubsystem.setDefaultCommand(new setLeftClimbMode(leftClimbSubsystem, climbTwoMode.stop));
    ledSubsystem.setDefaultCommand(new LEDCommand(ledSubsystem));
  }

  public Command getAutonomousCommand() {
    /* First put the drivetrain into auto run mode, then run the auto */
    //return runAuto;
    return autochooser.getSelected();
  }
}
