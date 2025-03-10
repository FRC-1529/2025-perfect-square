package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIntake extends SubsystemBase {
    private final VictorSPX m_leftMotor = new VictorSPX(5);
    private final VictorSPX m_rightMotor = new VictorSPX(6);

    public void setPower(double power) {
        m_leftMotor.set(VictorSPXControlMode.PercentOutput, power);
        m_rightMotor.set(VictorSPXControlMode.PercentOutput, power);
    }

    public CoralIntake() {
        m_leftMotor.setInverted(true);
    }

    public Command startIntake() {
        return this.run(() -> setPower(1.0));
    }
    public Command stopIntake() {
        return this.run(() -> setPower(0.0));
    }
    
    public Command startOuttake() {
        return this.run(() -> setPower(-1.0));
    }

    public Command set(double power) {
        return this.run(() -> setPower(power));
    }
}