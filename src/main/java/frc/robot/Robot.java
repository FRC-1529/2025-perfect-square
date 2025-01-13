// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.ctre.phoenix6.hardware.TalonFX;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {
  private final TalonFX m_leftBackDrive = new TalonFX(1, "rio");
  private final TalonFX m_leftFrontDrive = new TalonFX(2, "rio");
  private final TalonFX m_rightFrontDrive = new TalonFX(3, "rio");
  private final TalonFX m_rightBackDrive = new TalonFX(0, "rio");
  private final MecanumDrive m_robotDrive = new MecanumDrive(
    m_leftFrontDrive::set, m_leftBackDrive::set, m_rightFrontDrive::set, m_rightBackDrive::set);
  private final XboxController m_controller = new XboxController(0);
  private final Timer m_timer = new Timer();

  /** Called once at the beginning of the robot program. */
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftBackDrive);
    SendableRegistry.addChild(m_robotDrive, m_leftFrontDrive);
    SendableRegistry.addChild(m_robotDrive, m_rightFrontDrive);
    SendableRegistry.addChild(m_robotDrive, m_rightBackDrive);
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
      m_robotDrive.driveCartesian(0.5, 0.0, 0.0);
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.driveCartesian(-m_controller.getLeftY(), m_controller.getLeftX(), m_controller.getRightX());
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
