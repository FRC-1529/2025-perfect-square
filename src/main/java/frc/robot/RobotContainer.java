package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Drive;
import frc.robot.Constants.Keys;
import frc.robot.Constants.Lift;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.CoralIntake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
    // DRIVE
    private final Drivetrain m_drive = new Drivetrain();
    // LIFT
    private final Lift m_lift = new Lift();
    // CORAL
    private final CoralIntake m_coralIntake = new CoralIntake();
    // CAMERA
    private final Camera m_camera = new Camera();

    CommandXboxController m_driver = new CommandXboxController(0);
    CommandXboxController m_operator = new CommandXboxController(1);
    private final Timer m_timer = new Timer();

    private double m_linearPowerScalar = Drive.defaultLinearPowerScalar;
    private double m_angularPowerScalar = Drive.defaultAngularPowerScalar;
    private double m_liftPowerScalar = Lift.defaultLiftPowerScalar;

    public RobotContainer() {
        configureButtonBindings();

        Preferences.initDouble(Keys.linearPowerScalarKey, m_linearPowerScalar);
        Preferences.initDouble(Keys.angularPowerScalarKey, m_angularPowerScalar);
        Preferences.initDouble(Keys.liftPowerScalarKey, m_liftPowerScalar);

        // DRIVE
        m_drive.setDefaultCommand(
            Commands.run(() ->  
            m_drive.setPower(
                -m_driver.getLeftY() * m_linearPowerScalar,
                m_driver.getLeftX() * m_linearPowerScalar,
                m_driver.getRightX() * m_angularPowerScalar),
            m_drive));
        // LIFT
        m_lift.setDefaultCommand(
            Commands.run(() -> m_lift.runByHeightIndex(), m_lift));

        // Put subsystems to dashboard.
        Shuffleboard.getTab("Drive").add(m_drive);
        Shuffleboard.getTab("Lift").add(m_lift);

        // Set the scheduler to log Shuffleboard events for command initialize, interrupt, finish
        CommandScheduler.getInstance()
            .onCommandInitialize(
                command ->
                    Shuffleboard.addEventMarker(
                        "Command initialized", command.getName(), EventImportance.kNormal));
        CommandScheduler.getInstance()
            .onCommandInterrupt(
                command ->
                    Shuffleboard.addEventMarker(
                        "Command interrupted", command.getName(), EventImportance.kNormal));
        CommandScheduler.getInstance()
            .onCommandFinish(
                command ->
                    Shuffleboard.addEventMarker(
                        "Command finished", command.getName(), EventImportance.kNormal));
    }

    private void configureButtonBindings() {
        // LIFT
        m_operator.povUp().onTrue(m_lift.nextHeight());
        m_operator.povDown().onTrue(m_lift.prevHeight());
        m_operator.a().onTrue(m_lift.support());
        m_operator.b().onTrue(m_lift.collapse());
        // CORAL INTAKE
        m_operator.rightBumper().onTrue(m_coralIntake.startIntake());
        m_operator.leftBumper().onTrue(m_coralIntake.startOuttake());
        m_operator.rightBumper().or(m_operator.leftBumper()).whileFalse(m_coralIntake.stopIntake());
        // CAMERA
        m_operator.y().onTrue(m_camera.lookUp());
        m_operator.y().onFalse(m_camera.lookForward());
    }

    public void restartTimer() {
        m_timer.restart();
    }

    public void resetLift() {
        m_lift.resetEncoder();
    }

    public void loadPreferences() {
        m_linearPowerScalar = Preferences.getDouble(Keys.linearPowerScalarKey, m_linearPowerScalar);
        m_angularPowerScalar = Preferences.getDouble(Keys.angularPowerScalarKey, m_angularPowerScalar);
        m_liftPowerScalar = Preferences.getDouble(Keys.liftPowerScalarKey, m_liftPowerScalar);
    }

    public Pose2d getPose() {
        return m_drive.getCurrentPose();
    }
}
