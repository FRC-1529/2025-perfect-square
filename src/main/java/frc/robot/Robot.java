package frc.robot;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  
    private final RobotContainer m_robotContainer;

    private final Field2d m_field = new Field2d();
  
    public Robot() {
        m_robotContainer = new RobotContainer();
    
        DataLogManager.start();
        DriverStation.startDataLog(DataLogManager.getLog(), true);

        SmartDashboard.putData("Field", m_field);
    }
  
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        m_robotContainer.loadPreferences();
        m_field.setRobotPose(m_robotContainer.getPose());
    }

    @Override
    public void disabledInit() {}
  
    @Override
    public void disabledPeriodic() {}

    @Override
    public void autonomousInit() {
        m_robotContainer.restartTimer();
    }
  
    @Override
    public void autonomousPeriodic() {}
  
    @Override
    public void teleopInit() {
        m_robotContainer.restartTimer();
        m_robotContainer.resetLift();
    }
  
    @Override
    public void teleopPeriodic() {
    }
  
    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {}
  }
  