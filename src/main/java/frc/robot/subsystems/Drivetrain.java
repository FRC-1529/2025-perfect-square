package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.MecanumDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
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
    private Pose2d m_currentPose = new Pose2d(startPose.getX(), startPose.getY(), startPose.getRotation());
    private Pose2d m_targetPose = new Pose2d(m_currentPose.getX(), m_currentPose.getY(), m_currentPose.getRotation());
    private ArrayList<Pose2d> poses;
    private double m_linearGain = DriveConstants.defaultLinearGain;
    private double m_angularGain = DriveConstants.defaultAngularGain;
    private double m_linearTolerance = DriveConstants.defaultLinearTolerance;
    private double m_angularTolerance = DriveConstants.defaultAngularTolerance;
    private double m_snappingDistance = DriveConstants.defaultSnappingDistance;
    private boolean m_doSnap = true;
    private boolean m_userDriving = true;

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
        m_kinematics, Rotation2d.fromDegrees(m_gyro.getAngle()), getWheelPositions(), startPose);


    public Drivetrain() {
        m_robotDrive.setSafetyEnabled(false);
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
        Preferences.initDouble(Constants.Keys.linearGainKey, m_linearGain);
        Preferences.initDouble(Constants.Keys.angularGainKey, m_angularGain);
        Preferences.initDouble(Constants.Keys.linearToleranceKey, m_linearTolerance);
        Preferences.initDouble(Constants.Keys.angularToleranceKey, m_angularTolerance);
        Preferences.initDouble(Constants.Keys.snappingDistanceKey, m_snappingDistance);
        Preferences.initBoolean(Constants.Keys.doSnapKey, m_doSnap);
        if (DriverStation.getAlliance().get() == Alliance.Red) {
            poses = Constants.Poses.Red.poses;
        } else {
            poses = Constants.Poses.Blue.poses;
        }
    }

    public void setPower(double xSpeed, double ySpeed, double zRotation) {
        if (Math.abs(xSpeed) >= 0.1 || Math.abs(ySpeed) >= 0.1 || Math.abs(zRotation) >= 0.1) {
            m_userDriving = true;
            m_robotDrive.driveCartesian(xSpeed, ySpeed, zRotation);
        } else if (m_doSnap) {
            if (m_userDriving) {
                m_targetPose = m_currentPose.nearest(poses);
                m_userDriving = m_currentPose.getTranslation().getDistance(m_targetPose.getTranslation()) >= m_snappingDistance;
            } else {
                snapToTarget();
            }
        }
        m_currentPose = m_poseEstimator.update(Rotation2d.fromDegrees(m_gyro.getAngle()), getWheelPositions());
    }

    public void snapToTarget() {
        double distance = m_currentPose.getTranslation().getDistance(m_targetPose.getTranslation());
        Rotation2d angle = new Rotation2d(Math.atan2(m_targetPose.getY() - m_currentPose.getY(), m_targetPose.getX() - m_currentPose.getY()));
        double rotation = m_targetPose.minus(m_currentPose).getRotation().getRadians();
        m_robotDrive.drivePolar(distance * m_linearGain, angle, rotation * m_angularGain);
    }

    public void updatePoseWithCamera() {
        LimelightHelpers.setCameraPose_RobotSpace("", 
        Units.inchesToMeters(14.6875), // Forward
        Units.inchesToMeters(0.0), // Left
        Units.inchesToMeters(12.6875), // Up
        0.0, // Roll
        0.0, // Pitch (SET THIS TO IMU MEASUREMENT)
        0.0 // Yaw
        );
        double robotYaw = m_gyro.getAngle();
        LimelightHelpers.SetRobotOrientation(getName(), robotYaw, 0.0, 0.0, 0.0, 0.0, 0.0);
        LimelightHelpers.PoseEstimate limelightMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue("");

        m_poseEstimator.setVisionMeasurementStdDevs(VecBuilder.fill(.5, .5, 9999999));
        if (limelightMeasurement != null) {
            m_poseEstimator.addVisionMeasurement(limelightMeasurement.pose, limelightMeasurement.timestampSeconds);
        }
    }

    public void driveMe(double xSpeed, double ySpeed, double zRotation) {
        updatePoseWithCamera();
        setPower(xSpeed, ySpeed, zRotation);
    }

    public Command setAutoTarget(Pose2d target) {
        return this.runOnce(() -> {
            m_userDriving = false;
            m_targetPose = target;
        });
    }

    public MecanumDriveWheelPositions getWheelPositions() {
        return new MecanumDriveWheelPositions(
            m_leftFrontDrive.getPosition().getValueAsDouble() * DriveConstants.axialMetersPerTick,
            m_rightFrontDrive.getPosition().getValueAsDouble() * DriveConstants.axialMetersPerTick,
            m_leftBackDrive.getPosition().getValueAsDouble() * DriveConstants.axialMetersPerTick,
            m_rightBackDrive.getPosition().getValueAsDouble() * DriveConstants.axialMetersPerTick
        );
    }

    public void stopMotor() {
        m_robotDrive.stopMotor();
    }

    public Pose2d getCurrentPose() {
        return m_currentPose;
    }

    public Pose2d getCurrentTarget() {
        return m_targetPose;
    }

    public boolean isLocked() {
        //System.out.println(m_currentPose.getTranslation().getDistance(m_targetPose.getTranslation()) <= m_linearTolerance &&
        //m_targetPose.minus(m_currentPose).getRotation().getDegrees() <= m_angularTolerance);
        return m_currentPose.getTranslation().getDistance(m_targetPose.getTranslation()) <= m_linearTolerance &&
        m_targetPose.minus(m_currentPose).getRotation().getDegrees() <= m_angularTolerance;
    }
    
    public void setSnap(boolean doSnap) {
        Preferences.setBoolean(Constants.Keys.doSnapKey, doSnap);
        this.m_doSnap = doSnap;
    }

    public Command toggleSnap() {
       return this.run(() -> setSnap(!m_doSnap));
    }

    public void loadPreferences() {
        m_linearGain = Preferences.getDouble(Constants.Keys.linearGainKey, m_linearGain);
        m_angularGain = Preferences.getDouble(Constants.Keys.angularGainKey, m_angularGain);
        m_linearTolerance = Preferences.getDouble(Constants.Keys.linearToleranceKey, m_linearTolerance);
        m_angularTolerance = Preferences.getDouble(Constants.Keys.angularToleranceKey, m_angularTolerance);
        m_snappingDistance = Preferences.getDouble(Constants.Keys.snappingDistanceKey, m_snappingDistance);
        m_doSnap = Preferences.getBoolean(Constants.Keys.doSnapKey, m_doSnap);
    }

    public void setPose(double x, double y, double h) {
        /*if (x != m_currentPose.getX() || y != m_currentPose.getY() || h != m_currentPose.getRotation().getDegrees()) {
            Pose2d potentialTarget = m_currentPose.nearest(poses);
            if (m_currentPose.getTranslation().getDistance(potentialTarget.getTranslation()) >= m_snappingDistance) {
                m_targetPose = null;
                m_userDriving = true;
            } else {
                m_targetPose = potentialTarget;
            }
        }*/
        m_poseEstimator.resetPose(new Pose2d(x, y, Rotation2d.fromDegrees(h)));
    }

    public Command plusX() {
        return this.runOnce(() -> setPose(m_currentPose.getX() + 0.25, m_currentPose.getY(), m_currentPose.getRotation().getDegrees()));
    }

    public Command minusX() {
        return this.runOnce(() -> setPose(m_currentPose.getX() - 0.25, m_currentPose.getY(), m_currentPose.getRotation().getDegrees()));
    }

    public Command plusY() {
        return this.runOnce(() -> setPose(m_currentPose.getX(), m_currentPose.getY() + 0.25, m_currentPose.getRotation().getDegrees()));
    }

    public Command minusY() {
        return this.runOnce(() -> setPose(m_currentPose.getX(), m_currentPose.getY() - 0.25, m_currentPose.getRotation().getDegrees()));
    }

    public Command plusH() {
        return this.runOnce(() -> setPose(m_currentPose.getX(), m_currentPose.getY(), m_currentPose.getRotation().getDegrees() + 10));
    }

    public Command minusH() {
        return this.runOnce(() -> setPose(m_currentPose.getX(), m_currentPose.getY(), m_currentPose.getRotation().getDegrees() - 10));
    }

    public void simulation() {
        setPower(0, 0, 0);
    }
}