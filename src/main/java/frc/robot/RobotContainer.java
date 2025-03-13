package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.Keys;
import frc.robot.Constants.LiftConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.AlgaeIntake;
//import frc.robot.subsystems.Camera;
import frc.robot.subsystems.CoralIntake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Drivetrain;
//import frc.robot.subsystems.Hang;
import frc.robot.subsystems.Hang;

public class RobotContainer {
    // DRIVE
    private final Drivetrain m_drive = new Drivetrain();
    // LIFT
    private final Lift m_lift = new Lift();
    // CORAL
    private final CoralIntake m_coralIntake = new CoralIntake();
    // ALGAE
    private final AlgaeIntake m_algaeIntake = new AlgaeIntake();
    // CAMERA
    //  private final Camera m_camera = new Camera();
    // HANG
    public final Hang m_hang = new Hang();

    private final Command m_blueAuto = Autos.blueAuto(m_drive, m_lift, m_coralIntake, m_algaeIntake);

    SendableChooser<Command> m_chooser = new SendableChooser<>();

    CommandXboxController m_driver = new CommandXboxController(0);
    // CommandXboxController m_operator = new CommandXboxController(1);
    CommandOperatorPanel m_operator = new CommandOperatorPanel(1);
    
    private final Timer m_timer = new Timer();
    private double m_lastTickTS = 0.0;

    private double m_linearPowerScalar = DriveConstants.defaultLinearPowerScalar;
    private double m_angularPowerScalar = DriveConstants.defaultAngularPowerScalar;
    private double m_liftPowerScalar = LiftConstants.defaultLiftPowerScalar;

    public RobotContainer() {
        configureButtonBindings();
        DriverStation.silenceJoystickConnectionWarning(true);

        Preferences.initDouble(Keys.linearPowerScalarKey, m_linearPowerScalar);
        Preferences.initDouble(Keys.angularPowerScalarKey, m_angularPowerScalar);
        Preferences.initDouble(Keys.liftPowerScalarKey, m_liftPowerScalar);

        // DRIVE
        m_drive.setDefaultCommand(Commands.run(() -> m_drive.driveMe(
            -m_driver.getLeftY(),
            m_driver.getLeftX(),
            m_driver.getRightX()), m_drive
        ));
        // LIFT
        m_lift.setDefaultCommand(m_lift.runByHeightIndex());
        // CORAL INTAKE
        m_coralIntake.setDefaultCommand(m_coralIntake.run());

        m_chooser.setDefaultOption("Blue Auto", m_blueAuto);
        //m_chooser.addOption();

        Shuffleboard.getTab("Autonomous").add(m_chooser);

        // Put subsystems to dashboard.
        Shuffleboard.getTab("Drive").add(m_drive);
        Shuffleboard.getTab("Lift").add(m_lift);
        Shuffleboard.getTab("CoralIntake").add(m_coralIntake);
        Shuffleboard.getTab("AlgaeIntake").add(m_algaeIntake);

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
        // Drive
        m_driver.a().onTrue(m_drive.toggleSnap());
        m_driver.povRight().onTrue(m_drive.plusX());
        m_driver.povLeft().onTrue(m_drive.minusX());
        m_driver.povUp().onTrue(m_drive.plusY());
        m_driver.povDown().onTrue(m_drive.minusY());
        m_driver.leftBumper().onTrue(m_drive.plusH());
        m_driver.rightBumper().onTrue(m_drive.minusH());
        // LIFT
        /*m_operator.rightTrigger(0.01).or(m_operator.leftTrigger(0.01)).onTrue(
            Commands.run(() -> m_lift.setDefaultCommand(Commands.run(() -> m_lift.setPower((m_operator.getRightTriggerAxis() - m_operator.getLeftTriggerAxis()) * m_liftPowerScalar), m_lift)))
        );
        m_operator.povUp().or(m_operator.povDown()).onTrue(
            Commands.run(() -> m_lift.setDefaultCommand(Commands.run(() -> m_lift.runByHeightIndex(), m_lift)))
        );
        m_operator.povUp().onTrue(m_lift.nextHeight());
        m_operator.povDown().onTrue(m_lift.prevHeight());
        m_operator.a().onTrue(m_lift.support());
        m_operator.b().onTrue(m_lift.collapse());*/
        m_operator.liftY(0.01).onTrue(
            Commands.run(() -> m_lift.setDefaultCommand(Commands.run(() -> m_lift.setPower(m_operator.getLiftY() * m_liftPowerScalar), m_lift)))
        );
        m_operator.ground().or(m_operator.l2()).or(m_operator.l3()).or(m_operator.l4()).or(m_operator.lowAlgae()).or(m_operator.highAlgae()).onTrue(
            Commands.run(() -> m_lift.setDefaultCommand(Commands.run(() -> m_lift.runByHeightIndex(), m_lift)))
        );
        m_operator.ground().onFalse(m_lift.ground());
        m_operator.l2().onFalse(m_lift.l2());
        m_operator.l3().onFalse(m_lift.l3());
        m_operator.l4().onFalse(m_lift.l4());
        m_operator.lowAlgae().onFalse(m_lift.lowAlgae());
        m_operator.highAlgae().onFalse(m_lift.highAlgae());
        m_operator.support().onFalse(m_lift.support());
        m_operator.collapse().onFalse(m_lift.collapse());
        // CORAL INTAKE
        /*m_operator.rightBumper().onTrue(m_coralIntake.startIntake());
        m_operator.leftBumper().onTrue(m_coralIntake.startOuttake());
        m_operator.rightBumper().or(m_operator.leftBumper()).whileFalse(m_coralIntake.stopIntake());*/
        m_operator.coralIntake().onFalse(m_coralIntake.startIntake());
        m_operator.coralOuttake().onFalse(m_coralIntake.startOuttake());
        m_operator.coralIntake().and(m_operator.coralOuttake()).whileTrue(m_coralIntake.stopIntake());
        // ALGAE INTAKE
        m_operator.algaeIntake().onFalse(m_algaeIntake.startIntake());
        m_operator.algaeOuttake().onFalse(m_algaeIntake.startOuttake());
        m_operator.algaeIntake().and(m_operator.coralOuttake()).whileTrue(m_algaeIntake.stopIntake());
        // CAMERA
        // HANG
        //m_operator.y().whileTrue(Commands.run(() -> m_hang.open(Timer.getTimestamp() - m_lastTickTS)));
        //m_operator.x().whileTrue(Commands.run(() -> m_hang.hang(Timer.getTimestamp() - m_lastTickTS)));
    }

    public void restartTimer() {
        m_timer.restart();
    }

    public void resetEncoders() {
        m_lift.resetEncoder();
        //m_hang.resetEncoders();
    }

    public void loadPreferences() {
        m_linearPowerScalar = Preferences.getDouble(Keys.linearPowerScalarKey, m_linearPowerScalar);
        m_angularPowerScalar = Preferences.getDouble(Keys.angularPowerScalarKey, m_angularPowerScalar);
        m_liftPowerScalar = Preferences.getDouble(Keys.liftPowerScalarKey, m_liftPowerScalar);
        m_drive.loadPreferences();
        m_lift.loadPreferences();
        m_lastTickTS = Timer.getTimestamp();
    }

    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }

    public Pose2d getPose() {
        return m_drive.getCurrentPose();
    }

    public Pose2d getTarget() {
        return m_drive.getCurrentTarget();
    }

    public void setPose(double x, double y, double h) {
        m_drive.simulation();
        m_drive.setPose(x, y, h);
        m_drive.driveMe(0, 0, 0);
    }
}
