package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Lift {
    private final SparkMax m_lift;
    private final DoubleSolenoid m_expansionPiston;

    public Lift() {
        m_expansionPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0);
        m_lift = new SparkMax(4, MotorType.kBrushless);
    }

    public void update(double liftPower) {
        m_lift.set(liftPower);
    }

    public void update() {
        update(0.0);
    }

    public void support() {
        m_expansionPiston.set(DoubleSolenoid.Value.kForward);
    }

    public void collapse() {
        m_expansionPiston.set(DoubleSolenoid.Value.kReverse);
    }
}
