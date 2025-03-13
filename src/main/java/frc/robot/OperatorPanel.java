package frc.robot;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.event.EventLoop;

public class OperatorPanel extends GenericHID implements Sendable {
    public enum Button {
        kGround(0),
        kL2(1),
        kL3(2),
        kL4(3),
        kLowAlgae(4),
        kHighAlgae(5),
        kCollapse(6),
        kSupport(7),
        kCoralIntake(8),
        kCoralOuttake(9),
        kAlgaeIntake(10),
        kAlgaeOuttake(11),
        kHang(12),
        kResetHang(13);

        public final int value;

        Button(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            // Remove leading `k`
            return this.name().substring(1) + "Button";
        }
    }

    public enum Axis {
        kLiftY(0);

        public final int value;
    
        Axis(int value) {
          this.value = value;
        }

        @Override
        public String toString() {
          var name = this.name().substring(1); // Remove leading `k`
          if (name.endsWith("Trigger")) {
            return name + "Axis";
          }
          return name;
        }
      }

    public OperatorPanel(final int port) {
        super(port);
        HAL.report(tResourceType.kResourceType_Joystick, port + 1);
    }

    public double getLiftY() {
        return getRawAxis(Axis.kLiftY.value);
    }

    // Methods for kGround
    public boolean getGroundButton() {
        return getRawButton(Button.kGround.value);
    }

    public boolean getGroundButtonPressed() {
        return getRawButtonPressed(Button.kGround.value);
    }

    public boolean getGroundButtonReleased() {
        return getRawButtonReleased(Button.kGround.value);
    }

    public BooleanEvent ground(EventLoop loop) {
        return button(Button.kGround.value, loop);
    }

    // Methods for kL2
    public boolean getL2Button() {
        return getRawButton(Button.kL2.value);
    }

    public boolean getL2ButtonPressed() {
        return getRawButtonPressed(Button.kL2.value);
    }

    public boolean getL2ButtonReleased() {
        return getRawButtonReleased(Button.kL2.value);
    }

    public BooleanEvent l2(EventLoop loop) {
        return button(Button.kL2.value, loop);
    }

    // Methods for kL3
    public boolean getL3Button() {
        return getRawButton(Button.kL3.value);
    }

    public boolean getL3ButtonPressed() {
        return getRawButtonPressed(Button.kL3.value);
    }

    public boolean getL3ButtonReleased() {
        return getRawButtonReleased(Button.kL3.value);
    }

    public BooleanEvent l3(EventLoop loop) {
        return button(Button.kL3.value, loop);
    }

    // Methods for kL4
    public boolean getL4Button() {
        return getRawButton(Button.kL4.value);
    }

    public boolean getL4ButtonPressed() {
        return getRawButtonPressed(Button.kL4.value);
    }

    public boolean getL4ButtonReleased() {
        return getRawButtonReleased(Button.kL4.value);
    }

    public BooleanEvent l4(EventLoop loop) {
        return button(Button.kL4.value, loop);
    }

    // Methods for kLowAlgae
    public boolean getLowAlgaeButton() {
        return getRawButton(Button.kLowAlgae.value);
    }

    public boolean getLowAlgaeButtonPressed() {
        return getRawButtonPressed(Button.kLowAlgae.value);
    }

    public boolean getLowAlgaeButtonReleased() {
        return getRawButtonReleased(Button.kLowAlgae.value);
    }

    public BooleanEvent lowAlgae(EventLoop loop) {
        return button(Button.kLowAlgae.value, loop);
    }

    // Methods for kHighAlgae
    public boolean getHighAlgaeButton() {
        return getRawButton(Button.kHighAlgae.value);
    }

    public boolean getHighAlgaeButtonPressed() {
        return getRawButtonPressed(Button.kHighAlgae.value);
    }

    public boolean getHighAlgaeButtonReleased() {
        return getRawButtonReleased(Button.kHighAlgae.value);
    }

    public BooleanEvent highAlgae(EventLoop loop) {
        return button(Button.kHighAlgae.value, loop);
    }

    // Methods for kSupport
    public boolean getSupportButton() {
        return getRawButton(Button.kSupport.value);
    }

    public boolean getSupportButtonPressed() {
        return getRawButtonPressed(Button.kSupport.value);
    }

    public boolean getSupportButtonReleased() {
        return getRawButtonReleased(Button.kSupport.value);
    }

    public BooleanEvent support(EventLoop loop) {
        return button(Button.kSupport.value, loop);
    }

    // Methods for kCoralIntake
    public boolean getCoralIntakeButton() {
        return getRawButton(Button.kCoralIntake.value);
    }

    public boolean getCoralIntakeButtonPressed() {
        return getRawButtonPressed(Button.kCoralIntake.value);
    }

    public boolean getCoralIntakeButtonReleased() {
        return getRawButtonReleased(Button.kCoralIntake.value);
    }

    public BooleanEvent coralIntake(EventLoop loop) {
        return button(Button.kCoralIntake.value, loop);
    }

    // Methods for kCollapse
    public boolean getCollapseButton() {
        return getRawButton(Button.kCollapse.value);
    }

    public boolean getCollapseButtonPressed() {
        return getRawButtonPressed(Button.kCollapse.value);
    }

    public boolean getCollapseButtonReleased() {
        return getRawButtonReleased(Button.kCollapse.value);
    }

    public BooleanEvent collapse(EventLoop loop) {
        return button(Button.kCollapse.value, loop);
    }

    // Methods for kCoralOuttake
    public boolean getCoralOuttakeButton() {
        return getRawButton(Button.kCoralOuttake.value);
    }

    public boolean getCoralOuttakeButtonPressed() {
        return getRawButtonPressed(Button.kCoralOuttake.value);
    }

    public boolean getCoralOuttakeButtonReleased() {
        return getRawButtonReleased(Button.kCoralOuttake.value);
    }

    public BooleanEvent coralOuttake(EventLoop loop) {
        return button(Button.kCoralOuttake.value, loop);
    }

    // Methods for kAlgaeIntake
    public boolean getAlgaeIntakeButton() {
        return getRawButton(Button.kAlgaeIntake.value);
    }

    public boolean getAlgaeIntakeButtonPressed() {
        return getRawButtonPressed(Button.kAlgaeIntake.value);
    }

    public boolean getAlgaeIntakeButtonReleased() {
        return getRawButtonReleased(Button.kAlgaeIntake.value);
    }

    public BooleanEvent algaeIntake(EventLoop loop) {
        return button(Button.kAlgaeIntake.value, loop);
    }

    // Methods for kAlgaeOuttake
    public boolean getAlgaeOuttakeButton() {
        return getRawButton(Button.kAlgaeOuttake.value);
    }

    public boolean getAlgaeOuttakeButtonPressed() {
        return getRawButtonPressed(Button.kAlgaeOuttake.value);
    }

    public boolean getAlgaeOuttakeButtonReleased() {
        return getRawButtonReleased(Button.kAlgaeOuttake.value);
    }

    public BooleanEvent algaeOuttake(EventLoop loop) {
        return button(Button.kAlgaeOuttake.value, loop);
    }

    // Methods for kHang
    public boolean getHangButton() {
        return getRawButton(Button.kHang.value);
    }

    public boolean getHangButtonPressed() {
        return getRawButtonPressed(Button.kHang.value);
    }

    public boolean getHangButtonReleased() {
        return getRawButtonReleased(Button.kHang.value);
    }

    public BooleanEvent hang(EventLoop loop) {
        return button(Button.kHang.value, loop);
    }

    // Methods for kResetHang
    public boolean getResetHangButton() {
        return getRawButton(Button.kResetHang.value);
    }

    public boolean getResetHangButtonPressed() {
        return getRawButtonPressed(Button.kResetHang.value);
    }

    public boolean getResetHangButtonReleased() {
        return getRawButtonReleased(Button.kResetHang.value);
    }

    public BooleanEvent resetHang(EventLoop loop) {
        return button(Button.kResetHang.value, loop);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("HID");
        builder.publishConstString("ControllerType", "Custom Operator Panel");
        builder.addDoubleProperty("LiftY", this::getLiftY, null);
        builder.addBooleanProperty("Ground", this::getGroundButton, null);
        builder.addBooleanProperty("L2", this::getL2Button, null);
        builder.addBooleanProperty("L3", this::getL3Button, null);
        builder.addBooleanProperty("L4", this::getL4Button, null);
        builder.addBooleanProperty("LowAlgae", this::getLowAlgaeButton, null);
        builder.addBooleanProperty("HighAlgae", this::getHighAlgaeButton, null);
        builder.addBooleanProperty("Support", this::getSupportButton, null);
        builder.addBooleanProperty("Collapse", this::getCollapseButton, null);
        builder.addBooleanProperty("CoralIntake", this::getCoralIntakeButton, null);
        builder.addBooleanProperty("CoralOuttake", this::getCoralOuttakeButton, null);
        builder.addBooleanProperty("AlgaeIntake", this::getAlgaeIntakeButton, null);
        builder.addBooleanProperty("AlgaeOuttake", this::getAlgaeOuttakeButton, null);
        builder.addBooleanProperty("ResetHang", this::getResetHangButton, null);
        builder.addBooleanProperty("Hang", this::getHangButton, null);
    }
}
