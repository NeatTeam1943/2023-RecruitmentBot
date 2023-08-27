// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator.Validity;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.StorageConstants;

public class Storage extends SubsystemBase {
  private DoubleSolenoid m_topStorageSolonoid;
  private DoubleSolenoid m_bottomStorageSolonid;

  private boolean m_toggleTopSolonoid = false;
  private boolean m_toggleBottomSolonoid = false;

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
   * <h3>Chooses which storage solonoid to use.</h3>
   */
  public enum WhichStorage {
    TOP,
    BOTTOM
  }

  /**
   * <h2>Sets the DoubleSolenoid's value.</h2>
   * <p>
   * basically -> Should the solonoid be retracked or deployed :D
   * </p>
   * 
   * @param solonoid The DoubleSolenoid to set
   * @param value    The value to set the DoubleSolenoid to
   */
  public void setSolonid(WhichStorage solonoid, Value value) {
    switch (solonoid) {
      case TOP:
        m_topStorageSolonoid.set(value);
        break;

      case BOTTOM:
        m_bottomStorageSolonid.set(value);
        break;
    }
  }

  /**
   * <h3>Toggles the DoubleSolenoid's value.</h3>
   * 
   * @param solonoid The DoubleSolenoid to toggle
   */
  public void toggleSolonoid(WhichStorage solonoid) {
    switch (solonoid) {
      case TOP:
        setSolonid(WhichStorage.TOP, m_toggleTopSolonoid ? Value.kReverse : Value.kForward);
        m_toggleTopSolonoid = !m_toggleTopSolonoid;
        break;

      case BOTTOM:
        setSolonid(WhichStorage.BOTTOM, m_toggleBottomSolonoid ? Value.kReverse : Value.kForward);
        m_toggleBottomSolonoid = !m_toggleBottomSolonoid;
        break;
    }
  }

  public DoubleSolenoid getTopSolonoid(){
    return m_topStorageSolonoid;
  }

  public DoubleSolenoid getBottomSolonoid(){
    return m_bottomStorageSolonid;
  }
}
