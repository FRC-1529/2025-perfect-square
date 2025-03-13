package frc.robot.subsystems;

import static com.revrobotics.spark.SparkBase.ControlType.kPosition;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.HangConstants;

public class Hang extends SubsystemBase {
    private final SparkMax m_leftMotor = new SparkMax(12, MotorType.kBrushless);
    private final SparkMax m_rightMotor = new SparkMax(13, MotorType.kBrushless);
    private final SparkClosedLoopController m_leftController = m_leftMotor.getClosedLoopController();
    private final SparkClosedLoopController m_rightController = m_rightMotor.getClosedLoopController();

    private boolean m_open;
    private double m_timeElapsed = 0.0;

    public void resetEncoders() {
        m_leftMotor.getEncoder().setPosition(0.0);
        m_rightMotor.getEncoder().setPosition(0.0);
    }

    public void setPower(double leftPower, double rightPower) {
        m_leftMotor.set(leftPower);
        m_rightMotor.set(rightPower);
    }

    public void open(double dt) {
        if (!m_open) {
            if (m_leftMotor.getEncoder().getPosition() <= HangConstants.leftPressRotations + HangConstants.tolerance &&
            m_rightMotor.getEncoder().getPosition() <= HangConstants.rightPressRotations + HangConstants.tolerance) {
                m_timeElapsed = 0;
                m_open = true;
            } else {
                m_timeElapsed = Math.min(0, m_timeElapsed - dt);
                setOffsetTargets();
            }
        } else {
            m_leftController.setReference(0.0, kPosition);
            m_rightController.setReference(0.0, kPosition);
        }
    }

    public void hang(double dt) {
        if (m_open) {
            m_leftController.setReference(HangConstants.leftPressRotations, kPosition);
            m_leftController.setReference(HangConstants.rightPressRotations, kPosition);
            if (m_leftMotor.getEncoder().getPosition() >= HangConstants.leftPressRotations - HangConstants.tolerance &&
            m_rightMotor.getEncoder().getPosition() >= HangConstants.rightPressRotations - HangConstants.tolerance) {
                m_timeElapsed = 0;
                m_open = false;
            }
        } else {
            m_timeElapsed = Math.max(HangConstants.hangTimeSeconds, m_timeElapsed + dt);
            setOffsetTargets();
        }
    }

    private void setOffsetTargets() {
        double leftTarget = HangConstants.leftPressRotations + (HangConstants.leftDisplacement) * m_timeElapsed / HangConstants.hangTimeSeconds;
        double rightTarget = HangConstants.rightPressRotations + (HangConstants.rightDisplacement) * m_timeElapsed / HangConstants.hangTimeSeconds;
        double leftError = leftTarget - m_leftMotor.getEncoder().getPosition();
        double rightError = rightTarget - m_rightMotor.getEncoder().getPosition();
        double leftOffset = leftError - (rightError/HangConstants.rightDisplacement) * HangConstants.leftDisplacement;
        double rightOffset = rightError - (leftError/HangConstants.leftDisplacement) * HangConstants.rightDisplacement;
        leftTarget = leftTarget + leftOffset;
        rightTarget = rightTarget + rightOffset;
        m_leftController.setReference(leftTarget, kPosition);
        m_rightController.setReference(rightTarget, kPosition);
    }
}
