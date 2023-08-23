// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveTrainConstants;

public class Chassis extends SubsystemBase {
  private WPI_TalonFX m_leftFront;
  private WPI_TalonFX m_leftRear;
  private WPI_TalonFX m_rightFront;
  private WPI_TalonFX m_rightRear;

  private ADIS16470_IMU m_imu;

  private MecanumDrive m_drive;

  public Chassis() {
    m_leftFront = new WPI_TalonFX(DriveTrainConstants.kLeftFrontPort);
    m_leftRear = new WPI_TalonFX(DriveTrainConstants.kLeftRearPort);
    m_rightFront = new WPI_TalonFX(DriveTrainConstants.kRightFrontPort);
    m_rightRear = new WPI_TalonFX(DriveTrainConstants.kRightRearPort);

    m_leftFront.setNeutralMode(NeutralMode.Brake);
    m_leftRear.setNeutralMode(NeutralMode.Brake);
    m_rightFront.setNeutralMode(NeutralMode.Brake);
    m_rightRear.setNeutralMode(NeutralMode.Brake);

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
    m_drive.driveCartesian(joystick.getLeftX(), joystick.getLeftY(), joystick.getRightX(), new Rotation2d(m_imu.getAngle()));
  }
}