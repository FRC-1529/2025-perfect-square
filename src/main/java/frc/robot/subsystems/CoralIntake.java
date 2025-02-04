package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class CoralIntake {
    private final VictorSPX m_leftMotor;
    private final VictorSPX m_rightMotor;

    private double intakePower = 0.0;

    public CoralIntake() {
        m_leftMotor = new VictorSPX(5);
        m_rightMotor = new VictorSPX(6);
    }

    public void update() {
        m_leftMotor.set(VictorSPXControlMode.PercentOutput, intakePower);
        m_rightMotor.set(VictorSPXControlMode.PercentOutput, intakePower);
    }

    public void set(double power) {
        intakePower = power;
    }

    public void startIntake() {
        intakePower = 1.0;
    }

    public void startOuttake() {
        intakePower = -1.0;
    }

    public void stopIntake() {
        intakePower = 0.0;
    }
}