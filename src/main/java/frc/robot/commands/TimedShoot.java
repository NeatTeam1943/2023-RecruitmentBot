// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class TimedShoot extends CommandBase {
  private Shooter m_shooter;
  private Timer m_time;
  
  public TimedShoot(Shooter shooter){
    m_shooter = shooter;
    m_time = new Timer();

    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
    m_time.start();
  }

  @Override
  public void execute() {
    m_shooter.setFlyWheelSpeed(1);
  }

  @Override
  public boolean isFinished() {
    return m_time.get() >= 5;
  }
}
