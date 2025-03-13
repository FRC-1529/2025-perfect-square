package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
  
    private final RobotContainer m_robotContainer;

    private final Field2d m_field = new Field2d();

    private final XboxController controller = new XboxController(1);
  
    public Robot() {
        enableLiveWindowInTest(true);

        m_robotContainer = new RobotContainer();
    
        DataLogManager.start();
        DriverStation.startDataLog(DataLogManager.getLog(), true);

        SmartDashboard.putData("Field", m_field);

        Preferences.initDouble("X", 0.0);
        Preferences.initDouble("Y", 0.0);
        Preferences.initDouble("H", 0.0);
    }
  
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        m_robotContainer.loadPreferences();
        m_field.setRobotPose(m_robotContainer.getPose());
        if (m_robotContainer.getTarget() == null) {
            m_field.getObject("Target").setPose(-100.0, -100.0, Rotation2d.kZero);
        } else {
            m_field.getObject("Target").setPose(m_robotContainer.getTarget());
        }
        SmartDashboard.putData("Field", m_field);
    }

    @Override
    public void disabledInit() {}
  
    @Override
    public void disabledPeriodic() {}

    @Override
    public void autonomousInit() {
        m_robotContainer.restartTimer();
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }
  
    @Override
    public void autonomousPeriodic() {}
  
    @Override
    public void teleopInit() {
        m_robotContainer.restartTimer();
        m_robotContainer.resetEncoders();
    }
  
    @Override
    public void teleopPeriodic() {
    }
  
    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
        m_robotContainer.m_hang.setPower(controller.getLeftBumper() ? 0.1 : -controller.getLeftY(), controller.getRightBumper() ? 0.1 : -controller.getRightY());
        //m_robotContainer.setPose(Preferences.getDouble("X", 0.0), Preferences.getDouble("Y", 0.0), Preferences.getDouble("H", 0.0));
    }
  }
  