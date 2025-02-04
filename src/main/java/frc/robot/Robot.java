// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.subsystems.CoralIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {
  private final Drivetrain m_drive;
  private final Lift m_lift;
  private final CoralIntake m_coralIntake;
  private final XboxController m_controller;
  private final Timer m_timer;

  private double m_linearPowerScalar = Constants.defaultLinearPowerScalar;
  private double m_angularPowerScalar = Constants.defaultAngularPowerScalar;
  private double m_liftPowerScalar = Constants.defaultLiftPowerScalar;

  /** Called once at the beginning of the robot program. */
  public Robot() {
    enableLiveWindowInTest(true);

    Preferences.initDouble(Constants.linearPowerScalarKey, m_linearPowerScalar);
    Preferences.initDouble(Constants.angularPowerScalarKey, m_angularPowerScalar);

    m_drive = new Drivetrain();
    m_lift = new Lift();
    m_coralIntake = new CoralIntake();

    m_controller = new XboxController(0);
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
    m_lift.support();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_drive.drive(-m_controller.getLeftY() * m_linearPowerScalar, 
    m_controller.getLeftX() * m_linearPowerScalar, 
    m_controller.getRightX() * m_angularPowerScalar);
    m_lift.update((m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis()) * m_liftPowerScalar);
    m_coralIntake.update();

    if (m_controller.getRightBumperButton()) {
      m_coralIntake.startIntake();
    } else if (m_controller.getLeftBumperButton()) {
      m_coralIntake.startOuttake();
    } else {
      m_coralIntake.stopIntake();
    }
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  public void loadPreferences() {
    m_linearPowerScalar = Preferences.getDouble(Constants.linearPowerScalarKey, m_linearPowerScalar);
    m_angularPowerScalar = Preferences.getDouble(Constants.angularPowerScalarKey, m_angularPowerScalar);
    m_liftPowerScalar = Preferences.getDouble(Constants.liftPowerScalarKey, m_liftPowerScalar);
  }
}
