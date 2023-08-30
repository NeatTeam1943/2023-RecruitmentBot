package frc.robot;

import frc.robot.Constants.OperatorConstants;
// import frc.robot.commands.Shoot;
import frc.robot.commands.TimedShoot;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Storage.WhichStorage;


import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private final CommandXboxController m_driverController = new CommandXboxController(
            OperatorConstants.kDriverControllerPort);

    private final Chassis m_drive = new Chassis();
    private final Storage m_storage = new Storage();
    private final Shooter m_shooter = new Shooter();

    private final TimedShoot m_timedShoot = new TimedShoot(m_shooter); 

    // private final CommandBase m_toggleTopSolonid = Commands.run(() -> m_storage.getTopSolonoid().toggle(), m_storage);
    // private final CommandBase m_toggleBottomSolonid = Commands.run(() -> m_storage.getBottomSolonoid().toggle(), m_storage);

    // private final CommandBase m_retrackeBaseSolonoid  = Commands.run(() -> m_storage.setSolonid(WhichStorage.BOTTOM, Value.kReverse), m_storage);
    // private final CommandBase m_wait1Sec = new WaitCommand(1);
    // private final CommandBase m_deployBaseSolonoid = Commands.run(() -> m_storage.setSolonid(WhichStorage.BOTTOM, Value.kForward), m_storage);
    // private final CommandBase m_retrackeTopSolonoid = Commands.run(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kReverse), m_storage);
    // private final CommandBase m_deployTopsolonoid = Commands.run(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kForward), m_storage);

    public RobotContainer() {
        configureBindings();

        m_drive.setDefaultCommand(Commands.run(() -> m_drive.drive(m_driverController),  m_drive));
        m_shooter.setDefaultCommand(Commands.run(() -> m_shooter.setFlyWheelSpeed(m_driverController.getRightTriggerAxis()), m_shooter));
    }

    private void configureBindings() {
        m_driverController.leftBumper().whileTrue(Commands.sequence(
          m_timedShoot,
          Commands.run(() -> m_storage.setSolonid(WhichStorage.BOTTOM, Value.kReverse), m_storage),
          new WaitCommand(1),
          Commands.run(() -> m_storage.setSolonid(WhichStorage.BOTTOM, Value.kForward), m_storage),
          Commands.run(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kReverse), m_storage),
          new WaitCommand(1),
          Commands.run(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kForward), m_storage)));


        m_driverController.x().onTrue(Commands.run(() -> m_storage.getTopSolonoid().set(Value.kForward), m_storage));
        m_driverController.y().onTrue(Commands.run(() -> m_storage.getTopSolonoid().set(Value.kReverse), m_storage));

        m_driverController.a().onTrue(Commands.run(() -> m_storage.getBottomSolonoid().set(Value.kForward), m_storage));
        m_driverController.b().onTrue(Commands.run(() -> m_storage.getBottomSolonoid().set(Value.kReverse), m_storage));
    }

    public Storage getStorage(){
        return m_storage;
    }

    public Command getAutonomousCommand() {
        return null;
    }
}