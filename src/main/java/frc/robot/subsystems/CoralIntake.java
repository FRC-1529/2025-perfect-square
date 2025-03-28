package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIntake extends SubsystemBase {
    private final VictorSPX m_leftMotor = new VictorSPX(5);
    private final VictorSPX m_rightMotor = new VictorSPX(6);
    private double m_power = 0.0;

    public Command run() {
        return this.run(() -> {
            m_leftMotor.set(VictorSPXControlMode.PercentOutput, m_power);
            m_rightMotor.set(VictorSPXControlMode.PercentOutput, m_power);
        });
    }

    public CoralIntake() {
        m_leftMotor.setInverted(true);
    }

    public Command startIntake() {
        return set(1.0);
    }
    public Command stopIntake() {
        return set(0.0);
    }
    
    public Command startOuttake() {
        return set(-1.0);
    }

    public Command set(double power) {
        return this.runOnce(() -> m_power = power);
    }
}