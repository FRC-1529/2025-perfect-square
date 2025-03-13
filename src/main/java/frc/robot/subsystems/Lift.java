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
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Keys;
import frc.robot.Constants.LiftConstants;


public class Lift extends SubsystemBase {
    private final SparkMax m_lift = new SparkMax(4, MotorType.kBrushless);
    private final SparkClosedLoopController m_controller = m_lift.getClosedLoopController();
    private final DoubleSolenoid m_expansionPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0);

    private double m_liftHeightTolerance = LiftConstants.defaultLiftHeightTolerance;
    private double m_targetHeight = LiftConstants.heightMin;
    private int m_heightIndex = 0;

    public Lift() {
        Preferences.initDouble(Keys.liftHeightToleranceKey, m_liftHeightTolerance);
    }

    public void resetEncoder() {
        m_lift.getEncoder().setPosition(0.0);
    }

    public void setPower(double liftPower) {
        m_lift.set(liftPower);
    }

    public void setM_targetHeight(double newTarget) {
        if (newTarget <= LiftConstants.heightMax && newTarget >= LiftConstants.heightMin) {
            m_targetHeight = newTarget;
        }
        double setpoint = (m_targetHeight - LiftConstants.heightMin) * LiftConstants.rotationsPerMeter;
        m_controller.setReference(setpoint, kMAXMotionPositionControl);
    }

    public Command runByHeightIndex() {
        return this.run(() -> setM_targetHeight(LiftConstants.heights[m_heightIndex]));
    }

    public Command nextHeight() {
        return this.runOnce(() -> m_heightIndex = Math.min(m_heightIndex + 1, 3/*LiftConstants.heights.length - 1*/));
    }

    public Command prevHeight() {
        return this.runOnce(() -> m_heightIndex = Math.max(m_heightIndex - 1, 0));
    }

    public Command ground() {
        return this.runOnce(() -> m_heightIndex = 0);
    }

    public Command l2() {
        return this.runOnce(() -> m_heightIndex = 1);
    }
    
    public Command l3() {
        return this.runOnce(() -> m_heightIndex = 2);
    }

    public Command l4() {
        return this.runOnce(() -> m_heightIndex = 3);
    }

    public Command lowAlgae() {
        return this.runOnce(() -> m_heightIndex = 4);
    }

    public Command highAlgae() {
        return this.runOnce(() -> m_heightIndex = 5);
    }

    public boolean isLocked() {
        //System.out.println(Math.abs(getM_targetHeight() - getCurrentHeight()) <= m_liftHeightTolerance);
        return Math.abs(getM_targetHeight() - getCurrentHeight()) <= m_liftHeightTolerance;
    }

    public double getCurrentHeight() {
        return LiftConstants.heights[m_heightIndex];
    }

    public double getM_targetHeight() {
        return m_lift.getEncoder().getPosition() / LiftConstants.rotationsPerMeter + LiftConstants.heightMin;
    }

    public Command support() {
        return this.runOnce(() -> m_expansionPiston.set(kForward));
    }

    public Command collapse() {
        return this.runOnce(() -> m_expansionPiston.set(kReverse));
    }

    public void loadPreferences() {
        m_liftHeightTolerance = Preferences.getDouble(Keys.liftHeightToleranceKey, m_liftHeightTolerance);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        // Publish the solenoid state to telemetry.
        builder.addBooleanProperty("supported", () -> m_expansionPiston.get() == kForward, null);
        builder.addBooleanProperty("collapsed", () -> m_expansionPiston.get() == kReverse, null);
        builder.addIntegerProperty("height index", () -> m_heightIndex, null);
        builder.addDoubleProperty("target height", () -> m_targetHeight, null);
    }
}
