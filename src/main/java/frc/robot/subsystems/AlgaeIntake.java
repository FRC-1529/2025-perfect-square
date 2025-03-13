package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AlgaeIntake extends SubsystemBase {
    private final VictorSPX m_motor = new VictorSPX(15);
    private double m_power;

    public Command run() {
        return this.run(() -> m_motor.set(VictorSPXControlMode.PercentOutput, m_power));
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