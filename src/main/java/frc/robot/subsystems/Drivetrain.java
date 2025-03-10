package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.estimator.MecanumDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {
    private final TalonFX m_leftBackDrive = new TalonFX(1, "rio");
    private final TalonFX m_leftFrontDrive = new TalonFX(2, "rio");
    private final TalonFX m_rightFrontDrive = new TalonFX(3, "rio");
    private final TalonFX m_rightBackDrive = new TalonFX(0, "rio");
    private final MecanumDrive m_robotDrive = new MecanumDrive(
        m_leftFrontDrive::set, m_leftBackDrive::set, m_rightFrontDrive::set, m_rightBackDrive::set);
    private final ADIS16470_IMU m_gyro = new ADIS16470_IMU();

    private final Pose2d startPose = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
    private Pose2d currentPose = new Pose2d();

    // Locations of the wheels relative to the robot center.
    Translation2d m_frontLeftLocation = new Translation2d(0.513, 0.310);
    Translation2d m_frontRightLocation = new Translation2d(0.513, -0.310);
    Translation2d m_backLeftLocation = new Translation2d(-0.513, 0.310);
    Translation2d m_backRightLocation = new Translation2d(-0.513, -0.310);
    // Creating my kinematics object using the wheel locations.
    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
    m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation
    );
    private final MecanumDrivePoseEstimator m_poseEstimator = new MecanumDrivePoseEstimator(
        m_kinematics, new Rotation2d(Math.toRadians(m_gyro.getAngle())), getWheelPositions(), startPose);


    public Drivetrain() {
        addChild("Left Back", m_leftBackDrive);
        addChild("Left Front", m_leftFrontDrive);
        addChild("Right Front", m_rightFrontDrive);
        addChild("Right Back", m_rightBackDrive);
        addChild("Drive", m_robotDrive);
        m_gyro.reset();
        m_leftBackDrive.setPosition(0.0);
        m_leftFrontDrive.setPosition(0.0);
        m_rightFrontDrive.setPosition(0.0);
        m_rightBackDrive.setPosition(0.0);
    }

    public void setPower(double xSpeed, double ySpeed, double zRotation) {
        m_robotDrive.driveCartesian(xSpeed, ySpeed, zRotation);
        currentPose = m_poseEstimator.update(new Rotation2d(Math.toRadians(m_gyro.getAngle())), getWheelPositions());
    }

    public MecanumDriveWheelPositions getWheelPositions() {
        return new MecanumDriveWheelPositions(
            m_leftFrontDrive.getPosition().getValueAsDouble() * DriveConstants.axialMetersPerTick,
            m_rightFrontDrive.getPosition().getValueAsDouble() * DriveConstants.axialMetersPerTick,
            m_leftBackDrive.getPosition().getValueAsDouble() * DriveConstants.axialMetersPerTick,
            m_rightBackDrive.getPosition().getValueAsDouble() * DriveConstants.axialMetersPerTick
        );
    }

    public void run(double xSpeed, double ySpeed, double zRotation) {
        m_robotDrive.driveCartesian(xSpeed, ySpeed, zRotation);
    }

    public void stopMotor() {
        m_robotDrive.stopMotor();
    }

    public Pose2d getCurrentPose() {
        return m_poseEstimator.getEstimatedPosition();
    }
}
