package frc.robot.subsystems;

import static com.revrobotics.spark.SparkBase.ControlType.kMAXMotionPositionControl;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Lift;


public class Lift extends SubsystemBase {
    private final SparkMax m_lift = new SparkMax(4, MotorType.kBrushless);
    private final SparkClosedLoopController m_controller = m_lift.getClosedLoopController();
    private final DoubleSolenoid m_expansionPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0);

    private double targetHeight = Lift.heightMin;
    private int heightIndex = 0;

    public void resetEncoder() {
        m_lift.getEncoder().setPosition(0.0);
    }

    public void setPower(double liftPower) {
        m_lift.set(liftPower);
    }

    public void setTargetHeight(double newTarget) {
        if (newTarget <= Lift.heightMax && newTarget >= Lift.heightMin) {
            targetHeight = newTarget;
        }
        double setpoint = (targetHeight - Lift.heightMin) * Lift.rotationsPerMeter;
        m_controller.setReference(setpoint, kMAXMotionPositionControl);
    }

    public void runByHeightIndex() {
        setTargetHeight(Lift.heights[heightIndex]);
    }

    public Command nextHeight() {
        return this.runOnce(() -> heightIndex = Math.min(heightIndex + 1, Lift.heights.length - 1));
    }

    public Command prevHeight() {
        return this.runOnce(() -> heightIndex = Math.max(heightIndex - 1, 0));
    }

    public Command support() {
        return this.runOnce(() -> m_expansionPiston.set(kForward));
    }

    public Command collapse() {
        return this.runOnce(() -> m_expansionPiston.set(kReverse));
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        // Publish the solenoid state to telemetry.
        builder.addBooleanProperty("supported", () -> m_expansionPiston.get() == kForward, null);
        builder.addBooleanProperty("collapsed", () -> m_expansionPiston.get() == kReverse, null);
        builder.addIntegerProperty("height index", () -> heightIndex, null);
        builder.addDoubleProperty("target height", () -> targetHeight, null);
    }
}
