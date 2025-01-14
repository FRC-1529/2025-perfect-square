// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drivetrain;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {
  private Drivetrain m_drive;
  private final XboxController m_controller;
  private final Timer m_timer;

  private double m_linearPowerScalar = Constants.defaultLinearPowerScalar;
  private double m_angularPowerScalar = Constants.defaultAngularPowerScalar;

  /** Called once at the beginning of the robot program. */
  public Robot() {
    enableLiveWindowInTest(true);

    m_drive = new Drivetrain();

    Preferences.initDouble(Constants.linearPowerScalarKey, m_linearPowerScalar);
    Preferences.initDouble(Constants.angularPowerScalarKey, m_angularPowerScalar);

    m_controller = new XboxController(0);
    m_timer = new Timer();
  }

  @Override
  public void robotPeriodic() {
    loadPreferences();
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      m_drive.drive(0.5, 0.0, 0.0);
    } else {
      m_drive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_drive.drive(-m_controller.getLeftY() * m_linearPowerScalar, 
    m_controller.getLeftX() * m_linearPowerScalar, 
    m_controller.getRightX() * m_angularPowerScalar);
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
  }
}
