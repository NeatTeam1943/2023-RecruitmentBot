package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Storage;

public class useSolanoid extends CommandBase {
  public enum WhichStorage {
    TOP,
    BOTTOM
  }

  Value m_value;
  WhichStorage m_solonoid;

  Storage m_storage;

  public useSolanoid(WhichStorage whichSolanoid, Value value, Storage storage) {
    m_solonoid = whichSolanoid;
    m_storage = storage;
    m_value = value;

    addRequirements(storage);
  }

  @Override
  public void initialize() {
    switch (m_solonoid) {
      case TOP:
        m_storage.getTopSolonoid().set(m_value);
        System.out.println("Which Storage:" + m_solonoid + "\nValue:" + m_value);
        break;

      case BOTTOM:
        m_storage.getBottomSolonoid().set(m_value);
        System.out.println("Which Storage:" + m_solonoid + "\nValue:" + m_value);
        break;
    }
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
