package frc.robot;

import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

@SuppressWarnings("MethodName")
public class CommandOperatorPanel extends CommandGenericHID {
    private final OperatorPanel m_hid;

    public CommandOperatorPanel(int port) {
        super(port);
        m_hid = new OperatorPanel(port);
    }

    @Override
    public OperatorPanel getHID() {
        return m_hid;
    }

    public double getLiftY() {
        return m_hid.getLiftY();
    }

    public Trigger liftY(double threshold, EventLoop loop) {
        return axisMagnitudeGreaterThan(OperatorPanel.Axis.kLiftY.value, threshold, loop);
    }

    public Trigger liftY(double threshold) {
        return liftY(threshold, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    // Methods for kGround
    public Trigger ground() {
        return ground(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger ground(EventLoop loop) {
        return button(OperatorPanel.Button.kGround.value);
    }

    // Methods for kL2
    public Trigger l2() {
        return l2(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger l2(EventLoop loop) {
        return button(OperatorPanel.Button.kL2.value, loop);
    }

    // Methods for kL3
    public Trigger l3() {
        return l3(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger l3(EventLoop loop) {
        return button(OperatorPanel.Button.kL3.value, loop);
    }

    // Methods for kL4
    public Trigger l4() {
        return l4(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger l4(EventLoop loop) {
        return button(OperatorPanel.Button.kL4.value, loop);
    }

    // Methods for kLowAlgae
    public Trigger lowAlgae() {
        return lowAlgae(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger lowAlgae(EventLoop loop) {
        return button(OperatorPanel.Button.kLowAlgae.value, loop);
    }

    // Methods for kHighAlgae
    public Trigger highAlgae() {
        return highAlgae(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger highAlgae(EventLoop loop) {
        return button(OperatorPanel.Button.kHighAlgae.value, loop);
    }

    // Methods for kSupport
    public Trigger support() {
        return support(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger support(EventLoop loop) {
        return button(OperatorPanel.Button.kSupport.value, loop);
    }

    // Methods for kCoralIntake
    public Trigger coralIntake() {
        return coralIntake(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger coralIntake(EventLoop loop) {
        return button(OperatorPanel.Button.kCoralIntake.value, loop);
    }

    // Methods for kCollapse
    public Trigger collapse() {
        return collapse(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger collapse(EventLoop loop) {
        return button(OperatorPanel.Button.kCollapse.value, loop);
    }

    // Methods for kCoralOuttake
    public Trigger coralOuttake() {
        return coralOuttake(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger coralOuttake(EventLoop loop) {
        return button(OperatorPanel.Button.kCoralOuttake.value, loop);
    }

    // Methods for kAlgaeIntake
    public Trigger algaeIntake() {
        return algaeIntake(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger algaeIntake(EventLoop loop) {
        return button(OperatorPanel.Button.kAlgaeIntake.value, loop);
    }

    // Methods for kAlgaeOuttake
    public Trigger algaeOuttake() {
        return algaeOuttake(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger algaeOuttake(EventLoop loop) {
        return button(OperatorPanel.Button.kAlgaeOuttake.value, loop);
    }

    // Methods for kHang
    public Trigger hang() {
        return hang(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger hang(EventLoop loop) {
        return button(OperatorPanel.Button.kHang.value, loop);
    }

    // Methods for kResetHang
    public Trigger resetHang() {
        return resetHang(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger resetHang(EventLoop loop) {
        return button(OperatorPanel.Button.kResetHang.value, loop);
    }
}