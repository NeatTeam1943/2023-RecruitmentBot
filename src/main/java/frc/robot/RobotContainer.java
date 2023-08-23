package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Shoot;
import frc.robot.commands.ShootingPreparation;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Storage.WhichStorage;

import java.util.Dictionary;
import java.util.Hashtable;

import org.ejml.data.FSubmatrixD1;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  private final Storage m_storage = new Storage();
  private final Shooter m_shooter = new Shooter();

  private final Shoot m_shoot = new Shoot(m_shooter);

  private final Dictionary<String, Command> shootingCommands = new Hashtable<>();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    shootingCommands.put("Get to the right rpm", m_shoot);
    shootingCommands.put("Retracke base Solonoid",
        Commands.run(() -> m_storage.setSolonid(WhichStorage.BOTTOM, Value.kReverse), m_storage));
    shootingCommands.put("wait 3s", new WaitCommand(3));
    shootingCommands.put("Deploy base solonoid",
        Commands.run(() -> m_storage.setSolonid(WhichStorage.BOTTOM, Value.kForward), m_storage));
    shootingCommands.put("Retracke top solonoid",
        Commands.run(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kReverse), m_storage));
    shootingCommands.put("Deploy top solonoid",
        Commands.run(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kForward), m_storage));
    shootingCommands.put("Toggle top solonoid",
        Commands.run(() -> m_storage.toggleSolonoid(WhichStorage.TOP), m_storage));
    shootingCommands.put("Toggle base solonoid",
        Commands.run(() -> m_storage.toggleSolonoid(WhichStorage.BOTTOM), m_storage));

    m_driverController.x()
        .whileTrue(
            Commands.sequence(
                shootingCommands.get("Get to the right rpm"),
                shootingCommands.get("Retracke base Solonoid"),
                shootingCommands.get("wait 3s"),
                shootingCommands.get("Deploy base solonoid"),
                shootingCommands.get("Retracke top solonoid"),
                shootingCommands.get("wait 3s"),
                shootingCommands.get("Deploy top solonoid")));

    m_driverController.y().onTrue(shootingCommands.get("Toggle top solonoid"));
    m_driverController.a().onTrue(shootingCommands.get("Toggle base solonoid"));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
