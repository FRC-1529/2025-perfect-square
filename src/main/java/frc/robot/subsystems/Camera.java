package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {

    private final Servo m_pitchServo = new Servo(2);

    public Command lookForward() {
        return this.run(() -> m_pitchServo.setAngle(0));
    }

    public Command lookUp() {
        return this.run(() -> m_pitchServo.setAngle(45));
    }
}
