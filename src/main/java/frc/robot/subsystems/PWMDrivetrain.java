// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
/* This class declares the subsystem for the robot drivetrain if controllers are connected via PWM. If using SPARK MAX
 * controllers connected to CAN, go to RobotContainer and comment out the line declaring this subsystem and uncomment
 * the line for the CANDrivetrain.
 *
 * The subsystem contains the objects for the hardware contained in the mechanism and handles low level logic
 * for control. Subsystems are a mechanism that, when used in conjuction with command "Requirements", ensure
 * that hardware is only being used by 1 command at a time.
 */
public class PWMDrivetrain extends SubsystemBase {
  /*Class member variables. These variables represent things the class needs to keep track of and use between
  different method calls. */
  DifferentialDrive m_drivetrain;

  /*Constructor. This method is called when an instance of the class is created. This should generally be used to set up
   * member variables and perform any configuration or set up necessary on hardware.
   */
  public PWMDrivetrain() {
    
    //Creates Motor Controllers using PWM Spark motor controllers.
    Spark leftFront = new Spark(kLeftFrontID);
    Spark leftRear = new Spark(kLeftRearID);
    Spark rightFront = new Spark(kRightFrontID);
    Spark rightRear = new Spark(kRightRearID);

    /*Create MotorControllerGroups for each side of the drivetrain. These are declared here, and not at the class level
     * as we will not need to reference them directly anymore after we put them into a DifferentialDrive.
     */
    MotorControllerGroup leftMotors =
        new MotorControllerGroup(leftFront, leftRear);
    MotorControllerGroup rightMotors =
        new MotorControllerGroup(rightFront, rightRear);

    // Invert left side motors so both sides drive forward with positive output values
    leftMotors.setInverted(true);
    rightMotors.setInverted(false);

    // Disable motor safety
    leftFront.setExpiration(2);
    leftRear.setExpiration(2);
    rightFront.setExpiration(2);
    rightRear.setExpiration(2);

    leftFront.feed();
    leftRear.feed();
    rightFront.feed();
    rightRear.feed();

    // Put our controller groups into a DifferentialDrive object. This object represents all 4 motor
    // controllers in the drivetrain
    m_drivetrain = new DifferentialDrive(leftMotors, rightMotors);
  }
  
  /*Method to control the drivetrain using arcade drive. Arcade drive takes a speed in the X (forward/back) direction
   * and a rotation about the Z (turning the robot about it's center) and uses these to control the drivetrain motors */
  public void arcadeDrive(double speed, double rotation) {
    m_drivetrain.arcadeDrive(speed, rotation);
    m_drivetrain.feed();
  }

  //Creates tank drive for utility.
  public void tankDrive(double left, double right){
    m_drivetrain.tankDrive(left, right);
    m_drivetrain.feed();
  };

  public void stop(){
    m_drivetrain.tankDrive(0, 0);
  }

  public void curvatureDrive(double speed, double rotation, boolean quickturn){
    m_drivetrain.curvatureDrive(speed*.75, -rotation, quickturn);
  }

  @Override
  public void periodic() {
    /*This method will be called once per scheduler run. It can be used for running tasks we know we want to update each
     * loop such as processing sensor data. Our drivetrain is simple so we don't have anything to put here */
  }
}
