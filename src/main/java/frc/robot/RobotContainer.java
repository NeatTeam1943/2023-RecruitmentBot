package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ShooterCostants;
import frc.robot.Constants.SmartDashboardValues;
// import frc.robot.commands.Shoot;
import frc.robot.commands.TimedShoot;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Storage.WhichStorage;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class RobotContainer {
    private final CommandXboxController m_driverController = new CommandXboxController(
            OperatorConstants.kDriverControllerPort);
    private final CommandXboxController m_overrideController = new CommandXboxController(
            OperatorConstants.kOverrideControllerPort);

    private final Chassis m_drive = new Chassis();
    private final Storage m_storage = new Storage();
    private final Shooter m_shooter = new Shooter();

    private final TimedShoot m_timedShoot = new TimedShoot(m_shooter);

    public RobotContainer() {
        configureBindings();

        m_drive.setDefaultCommand(Commands.run(() -> m_drive.drive(m_driverController, m_overrideController), m_drive));
        m_shooter.setDefaultCommand(
                Commands.run(() -> m_shooter.setFlyWheelSpeed(m_overrideController.getRightTriggerAxis(), false),
                        m_shooter));

        SmartDashboard.putNumber(ShooterCostants.kMaxPowerKey,
                ShooterCostants.kMaxPower * ShooterCostants.kSliderMaxValue);
        SmartDashboard.putNumber(ShooterCostants.kMaxPowerKeyAuto, ShooterCostants.kSliderMaxValue);

    }

    private void configureBindings() {

        Command timeShoot = Commands.sequence(
                Commands.runOnce(() -> SmartDashboard.putBoolean(SmartDashboardValues.Basic.kLed0Key, true)),
                Commands.runOnce(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kForward), m_storage),
                m_timedShoot,
                Commands.runOnce(() -> m_storage.setSolonid(WhichStorage.BOTTOM, Value.kReverse), m_storage),
                new WaitCommand(1),
                Commands.runOnce(() -> m_storage.setSolonid(WhichStorage.BOTTOM, Value.kForward), m_storage),
                Commands.runOnce(() -> m_shooter.setFlyWheelSpeed(0, false), m_shooter),
                Commands.runOnce(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kReverse), m_storage),
                new WaitCommand(1),
                Commands.runOnce(() -> m_storage.setSolonid(WhichStorage.TOP, Value.kForward), m_storage),
                Commands.runOnce(() -> {
                    m_driverController.getHID().setRumble(RumbleType.kBothRumble, 1.0);
                    m_overrideController.getHID().setRumble(RumbleType.kBothRumble, 1.0);
                }),
                new WaitCommand(0.5),
                Commands.runOnce(() -> SmartDashboard.putBoolean(SmartDashboardValues.Basic.kLed0Key, false)),
                Commands.runOnce(() -> {
                    m_driverController.getHID().setRumble(RumbleType.kBothRumble, 0);
                    m_overrideController.getHID().setRumble(RumbleType.kBothRumble, 0);
                }));

        m_driverController.leftBumper().onTrue(Commands.runOnce(() -> {
                if (m_overrideController.getLeftTriggerAxis() != 0) {
                        timeShoot.schedule();
                }
        }));
        m_overrideController.leftBumper().onTrue(timeShoot);
        m_driverController.rightBumper().onTrue(Commands.sequence(
                Commands.runOnce(() -> timeShoot.cancel()),
                Commands.runOnce(() -> SmartDashboard.putBoolean(SmartDashboardValues.Basic.kLed0Key, false))));
        m_overrideController.rightBumper().onTrue(Commands.sequence(
                Commands.runOnce(() -> timeShoot.cancel()),
                Commands.runOnce(() -> SmartDashboard.putBoolean(SmartDashboardValues.Basic.kLed0Key, false))));

        m_overrideController.x().onTrue(Commands.run(() -> m_storage.getTopSolonoid().set(Value.kForward), m_storage));
        m_overrideController.y().onTrue(Commands.sequence(
                Commands.runOnce(() -> m_storage.getBottomSolonoid().set(Value.kForward), m_storage),
                Commands.runOnce(() -> m_storage.getTopSolonoid().set(Value.kReverse), m_storage)));

        m_overrideController.a()
                .onTrue(Commands.run(() -> m_storage.getBottomSolonoid().set(Value.kForward), m_storage));
        Command openBottom = Commands.sequence(
                Commands.runOnce(() -> m_storage.getTopSolonoid().set(Value.kForward), m_storage),
                Commands.runOnce(() -> m_storage.getBottomSolonoid().set(Value.kReverse), m_storage));
        m_overrideController.b().onTrue(Commands.runOnce(() -> {
            if (m_shooter.getLastSpeed() > 0.2) {
                openBottom.schedule();
            }
        }));

        m_driverController.back().onTrue(Commands.sequence(
                Commands.runOnce(() -> m_storage.getBottomSolonoid().set(Value.kForward), m_storage),
                Commands.runOnce(() -> m_storage.getTopSolonoid().set(Value.kReverse), m_storage)));
        m_overrideController.back().onTrue(Commands.sequence(
                Commands.runOnce(() -> m_storage.getBottomSolonoid().set(Value.kForward), m_storage),
                Commands.runOnce(() -> m_storage.getTopSolonoid().set(Value.kReverse), m_storage)));

    }

    public Storage getStorage() {
        return m_storage;
    }

    public Command getAutonomousCommand() {
        return null;
    }

    public Command resetSolenoides() {

        return Commands.sequence(
            Commands.runOnce(() -> m_storage.getBottomSolonoid().set(Value.kForward), m_storage),
            Commands.runOnce(() -> m_storage.getTopSolonoid().set(Value.kReverse), m_storage));
    }

    public void stopRumble() {        
        m_driverController.getHID().setRumble(RumbleType.kBothRumble, 0);
        m_overrideController.getHID().setRumble(RumbleType.kBothRumble, 0);
    }
}