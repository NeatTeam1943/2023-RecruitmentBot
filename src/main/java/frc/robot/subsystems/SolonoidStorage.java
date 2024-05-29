package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.StorageConstants;

public class SolonoidStorage extends SubsystemBase {
  private DoubleSolenoid m_topStorageSolonoid;
  private DoubleSolenoid m_bottomStorageSolonid;

  public SolonoidStorage() {
    m_topStorageSolonoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, StorageConstants.kTopSolonoidDeploy, StorageConstants.kTopSolonoidRetract);
    m_bottomStorageSolonid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, StorageConstants.kBottomSolonoidDeploy, StorageConstants.kBottomSolonoidRetract);
  }

  public enum Handler {
    TOP,
    BOTTOM,
  }

  public void set(Handler handler, boolean bark) {
    switch (handler) {
      case TOP: 
        if (!bark)
          m_bottomStorageSolonid.set(DoubleSolenoid.Value.kReverse);
        else
          m_topStorageSolonoid.set(DoubleSolenoid.Value.kForward);
        break;
      case BOTTOM: 
        if (!bark)
          m_bottomStorageSolonid.set(DoubleSolenoid.Value.kReverse);
        else
          m_topStorageSolonoid.set(DoubleSolenoid.Value.kForward);
        break;
    }
  }
}