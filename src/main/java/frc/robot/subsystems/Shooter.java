// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterCostants;

public class Shooter extends SubsystemBase {
  private WPI_TalonFX m_leftFlyWheel;
  private WPI_TalonFX m_rightFlyWheel;

  private Encoder m_leftEncoder;
  private Encoder m_rightEncoder;

  public Shooter() {
    m_leftFlyWheel = new WPI_TalonFX(ShooterCostants.kLeftFlyWheel);
    m_rightFlyWheel = new WPI_TalonFX(ShooterCostants.kRightFlyWheel);

    m_leftEncoder = new Encoder(ShooterCostants.kLeftEncoderChannelA, ShooterCostants.kLeftEncoderChannelB);
    m_rightEncoder = new Encoder(ShooterCostants.kRightEncoderChannelA, ShooterCostants.kRightEncoderChannelB);

    m_leftEncoder.setReverseDirection(false);
    m_leftEncoder.setDistancePerPulse(ShooterCostants.kEncoderDistancePerPulse);
  }

  /**
   * <h3>Calculates the RPM of an individual motor</h3>
   * 
   * @param encoder The encoder that linked to the chosen motor.
   * @param distancePerPulse The scale factor that will be used to convert pulses to useful units.
   * @return The motor's RPM.
   */
  private double calcMotorRpm(Encoder encoder, double distancePerPulse){
    double rate = encoder.getRate();
    double rpm = rate * 60 / distancePerPulse;

    return rpm;
  }

  /**
   * <h3> Gets the FlyWheel's shooter RPM </h3>
   * 
   * @returns the FlyWheel's RPM
   */
  public double getFlyWheelRpm(){
    double leftMotorRpm = calcMotorRpm(m_leftEncoder, ShooterCostants.kEncoderDistancePerPulse);
    double rightMotorRpm = calcMotorRpm(m_rightEncoder, ShooterCostants.kEncoderDistancePerPulse);

    double flyWheelRpm = (leftMotorRpm + rightMotorRpm) / 2;

    return flyWheelRpm;
  }

  /**
   * <h3>Set Flywheel Speed</h3>
   * 
   * @param speed speed of the flywheel
   */
  public void setFlyWheelSpeed(double speed) {
    m_leftFlyWheel.set(speed);
    m_rightFlyWheel.set(speed);
  }
}
