// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveTrainConstants;

public class Chassis extends SubsystemBase {
  private VictorSP m_leftFront;
  private VictorSP m_leftRear;
  private VictorSP m_rightFront;
  private VictorSP m_rightRear;

  private ADIS16470_IMU m_imu;

  private MecanumDrive m_drive;

  public Chassis() {
    m_leftFront = new VictorSP(DriveTrainConstants.kLeftFrontPort);
    m_leftRear = new VictorSP(DriveTrainConstants.kLeftRearPort);
    m_rightFront = new VictorSP(DriveTrainConstants.kRightFrontPort);
    m_rightRear = new VictorSP(DriveTrainConstants.kRightRearPort);

    m_imu = new ADIS16470_IMU();

    m_rightFront.setInverted(true);
    m_rightRear.setInverted(true);

    m_drive = new MecanumDrive(m_leftFront, m_leftRear, m_rightFront, m_rightRear);
  }

  /**
   * <h3> Drives the robot using the joystick values </h3>
   * <p> Uses the driveCartesian method of the MecanumDrive class </p>
   * 
   * @param  joystick  The joystick to use
   */
  public void drive(CommandXboxController joystick){

    // m_drive.driveCartesian(joystick.getLeftX(), -joystick.getLeftY(), joystick.getRightX(), new Rotation2d(m_imu.getAngle()));

    double speed = Math.abs(joystick.getLeftY()) + Math.abs(joystick.getLeftX()) + Math.abs(joystick.getRightX());

    if (speed > 0.3){
      m_leftFront.set(-joystick.getLeftY() + joystick.getRightX() + joystick.getLeftX());
      m_leftRear.set(-joystick.getLeftY() + joystick.getRightX() - joystick.getLeftX());
  
      m_rightFront.set(-joystick.getLeftY() - joystick.getRightX() - joystick.getLeftX());
      m_rightRear.set(-joystick.getLeftY() - joystick.getRightX() + joystick.getLeftX());
    }
  }

}