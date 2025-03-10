package frc.robot.archive;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.Drive;
import frc.robot.Constants.Keys;
import frc.robot.Constants.Lift;

public class zRobotArchive extends TimedRobot {
  // (DRIVE) 
  private final zDrivetrainArchive m_drive;
  // (LIFT) 
  private final zLiftArchive m_lift;
  // (CORAL)
  private final zCoralIntakeArchive m_coralIntake;
  // (HANG)

  private final XboxController m_driver;
  private final XboxController m_operator;
  private final Timer m_timer;

  private double m_linearPowerScalar = Drive.defaultLinearPowerScalar;
  private double m_angularPowerScalar = Drive.defaultAngularPowerScalar;
  private double m_liftPowerScalar = Lift.defaultLiftPowerScalar;

  public zRobotArchive() {
    enableLiveWindowInTest(true);

    Preferences.initDouble(Keys.linearPowerScalarKey, m_linearPowerScalar);
    Preferences.initDouble(Keys.angularPowerScalarKey, m_angularPowerScalar);
    Preferences.initDouble(Keys.liftPowerScalarKey, m_liftPowerScalar);

    // (DRIVE) 
    m_drive = new zDrivetrainArchive();
    // (LIFT) 
    m_lift = new zLiftArchive();
    // (CORAL)
    m_coralIntake = new zCoralIntakeArchive();

    m_driver = new XboxController(0);
    m_operator = new XboxController(1);
    m_timer = new Timer();
  }

  @Override
  public void robotPeriodic() {
    loadPreferences();
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    m_timer.restart();
    // (LIFT) 
    m_lift.support();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    // (DRIVE) 
    m_drive.update(-m_driver.getLeftY() * m_linearPowerScalar, m_driver.getLeftX() * m_linearPowerScalar, m_driver.getRightX() * m_angularPowerScalar);
    // (LIFT) 
    m_lift.update((m_operator.getRightTriggerAxis() - m_operator.getLeftTriggerAxis()) * m_liftPowerScalar);
    // (CORAL) 
    m_coralIntake.update();

    /* */
    if (m_operator.getRightBumperButton()) {
      m_coralIntake.startIntake();
    } else if (m_operator.getLeftBumperButton()) {
      m_coralIntake.startOuttake();
    } else {
      m_coralIntake.stopIntake();
    }
    /* */

    if (m_operator.getAButton()) {
      m_lift.support();
    } else if (m_operator.getBButton()) {
      m_lift.collapse();
    }
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  public void loadPreferences() {
    m_linearPowerScalar = Preferences.getDouble(Keys.linearPowerScalarKey, m_linearPowerScalar);
    m_angularPowerScalar = Preferences.getDouble(Keys.angularPowerScalarKey, m_angularPowerScalar);
    m_liftPowerScalar = Preferences.getDouble(Keys.liftPowerScalarKey, m_liftPowerScalar);
  }
}
