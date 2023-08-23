// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterCostants;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  private Shooter m_shooter;
  
  @SuppressWarnings("Unused")
  private double m_rpmLimit;

  public Shoot(Shooter shooter){
    m_shooter = shooter;

    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
    m_rpmLimit = ShooterCostants.kShoot + m_shooter.getFlyWheelRpm();
  }

  @Override
  public void execute() {
    m_shooter.setFlyWheelSpeed(1);
  }

  @Override
  public boolean isFinished() {
    return m_rpmLimit <= m_shooter.getFlyWheelRpm();
  }
}
