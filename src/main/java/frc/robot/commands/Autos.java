package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.Poses;
import frc.robot.subsystems.AlgaeIntake;
import frc.robot.subsystems.CoralIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;

public class Autos {
  public static Command blueAuto(Drivetrain drive, Lift lift, CoralIntake coralIntake, AlgaeIntake algaeIntake) {
    boolean red = DriverStation.getAlliance().get().equals(DriverStation.Alliance.Red);
    return Commands.sequence(
        lift.support(),
        lift.l3(),
        drive.setAutoTarget(red ? Poses.Red.reefH : Poses.Blue.reefH),
        Commands.waitUntil(() -> (drive.isLocked() && lift.isLocked())),
        coralIntake.startOuttake(),
        Commands.waitSeconds(2.0),
        coralIntake.stopIntake(),
        lift.lowAlgae(),
        drive.setAutoTarget(red ? Poses.Red.algaeGH : Poses.Blue.algaeGH),
        Commands.waitUntil(() -> (drive.isLocked() && lift.isLocked())),
        algaeIntake.startIntake(),
        Commands.run(() -> drive.driveMe(0.1, 0.0, 0.0), drive).withTimeout(1.0),
        algaeIntake.stopIntake(),
        lift.ground(),
        drive.setAutoTarget(red ? Poses.Red.processor : Poses.Blue.processor),
        Commands.waitUntil(() -> (drive.isLocked() && lift.isLocked())),
        algaeIntake.startOuttake(),
        Commands.waitSeconds(2.0),
        lift.collapse(),
        drive.setAutoTarget(red ? Poses.Blue.leftStationLeft : Poses.Blue.leftStationLeft)
    );
  }

  /*private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }*/
}