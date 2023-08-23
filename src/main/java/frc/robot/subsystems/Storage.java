// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.StorageConstants;

public class Storage extends SubsystemBase {
  private DoubleSolenoid m_topStorageSolonoid;
  private DoubleSolenoid m_bottomStorageSolonid;

  public Storage() {
    m_topStorageSolonoid = new DoubleSolenoid(
        PneumaticsModuleType.REVPH,
        StorageConstants.kTopSolonoidDeploy,
        StorageConstants.kTopSolonoidRetract);

    m_bottomStorageSolonid = new DoubleSolenoid(
        PneumaticsModuleType.REVPH,
        StorageConstants.kBottomSolonoidDeploy,
        StorageConstants.kBottomSolonoidRetract);
  }

/**
 * <h3> Chooses which storage solonoid to use.</h3>
 */
  enum WhichStorage{
    TOP,
    Bottom
  }

  /**
   * <h2> Sets the DoubleSolenoid's value. </h2>
   * <p> basically -> Should the solonoid be retracked or deployed :D </p>
   * 
   * @param solonoid The DoubleSolenoid to set
   * @param value The value to set the DoubleSolenoid to 
   */
  public void setSolonid(WhichStorage solonoid, Value value){
    switch(solonoid){
      case TOP:
        m_topStorageSolonoid.set(value);
        break;

      case Bottom:
        m_bottomStorageSolonid.set(value);
        break;
    }
  }
}