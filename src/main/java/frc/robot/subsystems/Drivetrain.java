package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private final TalonFX m_leftBackDrive;
    private final TalonFX m_leftFrontDrive;
    private final TalonFX m_rightFrontDrive;
    private final TalonFX m_rightBackDrive;
    private final MecanumDrive m_robotDrive;

    public Drivetrain() {
        m_leftBackDrive = new TalonFX(1, "rio");
        addChild("Left Back", m_leftBackDrive);
        m_leftFrontDrive = new TalonFX(2, "rio");
        addChild("Left Front", m_leftFrontDrive);
        m_rightFrontDrive = new TalonFX(3, "rio");
        addChild("Right Front", m_rightFrontDrive);
        m_rightBackDrive = new TalonFX(0, "rio");
        addChild("Right Back", m_rightBackDrive);
        m_robotDrive = new MecanumDrive(
        m_leftFrontDrive::set, m_leftBackDrive::set, m_rightFrontDrive::set, m_rightBackDrive::set);
        addChild("Drive", m_robotDrive);
    }

    public void drive(double xSpeed, double ySpeed, double zRotation) {
        m_robotDrive.driveCartesian(xSpeed, ySpeed, zRotation);
    }

    public void stopMotor() {
        m_robotDrive.stopMotor();
    }
}
