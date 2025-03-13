package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.util.Units;
import java.util.ArrayList;
import java.util.Arrays;

public class EagleUtil {
  public static ArrayList<Pose2d> m_redPoses;
  public static ArrayList<Pose2d> m_bluePoses;
  public static ArrayList<Pose2d> m_redCoralStationPoses;
  public static ArrayList<Pose2d> m_blueCoralStationPoses;

  private static double BLUE_REEF_X = Units.inchesToMeters(144 + (93.5 - 14 * 2) / 2);
  private static double BLUE_REEF_Y = Units.inchesToMeters(158.50);
  //private static Pose2d BLUE_REEF = new Pose2d(BLUE_REEF_X, BLUE_REEF_Y, Rotation2d.kZero);
  private static Pose2d BLUE_REEF_INVERT = new Pose2d(-BLUE_REEF_X, -BLUE_REEF_Y, Rotation2d.kZero);
  private static double RED_REEF_X = Units.inchesToMeters(546.875 - (93.5 - 14 * 2) / 2);
  private static double RED_REEF_Y = Units.inchesToMeters(158.50);
  //private static Pose2d RED_REEF = new Pose2d(RED_REEF_X, RED_REEF_Y, Rotation2d.kZero);
  private static Pose2d RED_REEF_INVERT = new Pose2d(-RED_REEF_X, -RED_REEF_Y, Rotation2d.kZero);

  private static double REEF_LENGTH = Units.inchesToMeters(35);
  private static double REEF_TO_REEF_DISTANCE = 0.33;
  private static double ROBOT_AWAY_FROM_REEF = Units.inchesToMeters(28.3125/2+9.625);

  private static double X = -REEF_LENGTH - ROBOT_AWAY_FROM_REEF;
  private static double Y = REEF_TO_REEF_DISTANCE / 2;
  private static double Y_OFFSET = Units.inchesToMeters(0.5);

  private static Rotation2d CORAL_STATION_ROTATION = Rotation2d.fromDegrees(54.0);
  private static Pose2d BLUE_CORAL_STATION_RIGHT = new Pose2d(-0.843, -0.644, Rotation2d.kZero);
  private static Pose2d BLUE_CORAL_STATION_LEFT = new Pose2d(-0.843, -8.052+0.644, Rotation2d.kZero);
  private static Pose2d RED_CORAL_STATION_RIGHT = new Pose2d(-17.548+0.843, -8.052+0.644, Rotation2d.kZero);
  private static Pose2d RED_CORAL_STATION_LEFT = new Pose2d(-17.548+0.843, -0.644, Rotation2d.kZero);

  private static double SLOT_TO_SLOT_DISTANCE = Units.inchesToMeters(8.0);
  private static double ROBOT_AWAY_FROM_CORAL_STATION = Units.inchesToMeters(28.3125/2);

  private static double ROBOT_AWAY_FROM_PROCESSOR = ROBOT_AWAY_FROM_CORAL_STATION;
  private static Pose2d BLUE_PROCESSOR = new Pose2d(-5.987, 0, Rotation2d.kZero);
  private static Pose2d RED_PROCESSOR = new Pose2d(-17.548+5.987, -8.052, Rotation2d.kZero);

  private static Pose2d[] bluePoses = new Pose2d[12];
  private static Pose2d[] redPoses = new Pose2d[12];
  private static Pose2d[] blueCoralStationPoses = new Pose2d[6];
  private static Pose2d[] redCoralStationPoses = new Pose2d[6];

  /**
   * @return returns the calculated set points
   */
  public static ArrayList<Pose2d> calculateBlueReefSetPoints() {
    if (m_bluePoses != null) {
      return m_bluePoses;
    }

    double[][] blueReefOffsets = {
      // in meters
      {0, 0}, // reef A
      {0, 0}, // reef B
      {0, 0}, // reef C
      {0, 0}, // reef D
      {0, 0}, // reef E
      {0, 0}, // reef F
      {0, 0}, // reef G
      {0, 0}, // reef H
      {0, 0}, // reef I
      {0, 0}, // reef J
      {0, 0}, // reef K
      {0, 0} // reef L
    };

    bluePoses[0] = new Pose2d(X, Y + Y_OFFSET, Rotation2d.kZero);
    bluePoses[1] = new Pose2d(X, -Y + Y_OFFSET, Rotation2d.kZero);

    Rotation2d sixty = Rotation2d.fromDegrees(60);

    for (int i = 2; i < bluePoses.length; i++) {
      bluePoses[i] = bluePoses[i - 2].rotateBy(sixty);
    }

    for (int i = 0; i < bluePoses.length; i++) {
      bluePoses[i] = bluePoses[i].relativeTo(BLUE_REEF_INVERT);
      bluePoses[i] =
          new Pose2d(
              bluePoses[i].getX() + blueReefOffsets[i][0],
              bluePoses[i].getY() + blueReefOffsets[i][1],
              bluePoses[i].getRotation());
    }

    m_bluePoses = new ArrayList<Pose2d>(Arrays.asList(bluePoses));
    return m_bluePoses;
  }

  /**
   * @return returns calculated setpoints
   */
  public static ArrayList<Pose2d> calculateRedReefSetPoints() {
    if (m_redPoses != null) {
      return m_redPoses;
    }

    redPoses[0] = new Pose2d(X, Y + Y_OFFSET, Rotation2d.kZero);
    redPoses[1] = new Pose2d(X, -Y + Y_OFFSET, Rotation2d.kZero);

    double[][] redReefOffsets = {
      // also in meters
      {0, 0}, // reef G
      {0, 0}, // reef H
      {0, 0}, // reef I
      {0, 0}, // reef J
      {0, 0}, // reef K
      {0, 0}, // reef L
      {0, 0}, // reef A
      {0, 0}, // reef B
      {0, 0}, // reef C
      {0, 0}, // reef D
      {0, 0}, // reef E
      {0, 0} // reef F
    };

    Rotation2d sixty = Rotation2d.fromDegrees(60);

    for (int i = 2; i < redPoses.length; i++) {
      redPoses[i] = redPoses[i - 2].rotateBy(sixty);
    }

    for (int i = 0; i < redPoses.length; i++) {
      redPoses[i] = redPoses[i].relativeTo(RED_REEF_INVERT);
      redPoses[i] =
          new Pose2d(
              redPoses[i].getX() + redReefOffsets[i][0],
              redPoses[i].getY() + redReefOffsets[i][1],
              redPoses[i].getRotation());
    }

    m_redPoses = new ArrayList<Pose2d>(Arrays.asList(redPoses));

    return m_redPoses;
  }

  public static ArrayList<Pose2d> calculateBlueCoralStationSetPoints() {
    if (m_blueCoralStationPoses != null) {
      return m_blueCoralStationPoses;
    }

    blueCoralStationPoses[0] = new Pose2d(-ROBOT_AWAY_FROM_CORAL_STATION, Y_OFFSET, Rotation2d.kZero);
    blueCoralStationPoses[1] = blueCoralStationPoses[0].transformBy(new Transform2d(0.0,  SLOT_TO_SLOT_DISTANCE*3, Rotation2d.kZero));
    blueCoralStationPoses[2] = blueCoralStationPoses[0].transformBy(new Transform2d(0.0, -SLOT_TO_SLOT_DISTANCE*3, Rotation2d.kZero));
    for (int i = 0; i < 3; i++) {
      blueCoralStationPoses[i] = blueCoralStationPoses[i].rotateBy(Rotation2d.k180deg.minus(CORAL_STATION_ROTATION));
      blueCoralStationPoses[i] = blueCoralStationPoses[i].relativeTo(BLUE_CORAL_STATION_LEFT);
    }
    blueCoralStationPoses[3] = new Pose2d(-ROBOT_AWAY_FROM_CORAL_STATION, Y_OFFSET, Rotation2d.kZero);
    blueCoralStationPoses[4] = blueCoralStationPoses[3].transformBy(new Transform2d(0.0,  SLOT_TO_SLOT_DISTANCE*3, Rotation2d.kZero));
    blueCoralStationPoses[5] = blueCoralStationPoses[3].transformBy(new Transform2d(0.0, -SLOT_TO_SLOT_DISTANCE*3, Rotation2d.kZero));
    for (int i = 3; i < 6; i++) {
      blueCoralStationPoses[i] = blueCoralStationPoses[i].rotateBy(Rotation2d.k180deg.plus(CORAL_STATION_ROTATION));
      blueCoralStationPoses[i] = blueCoralStationPoses[i].relativeTo(BLUE_CORAL_STATION_RIGHT);
    }

    m_blueCoralStationPoses = new ArrayList<Pose2d>(Arrays.asList(blueCoralStationPoses));

    return m_blueCoralStationPoses;
  }

  public static ArrayList<Pose2d> calculateRedCoralStationSetPoints() {
    if (m_redCoralStationPoses != null) {
      return m_redCoralStationPoses;
    }

    redCoralStationPoses[0] = new Pose2d(-ROBOT_AWAY_FROM_CORAL_STATION, Y_OFFSET, Rotation2d.kZero);
    redCoralStationPoses[1] = redCoralStationPoses[0].transformBy(new Transform2d(0.0,  SLOT_TO_SLOT_DISTANCE*3, Rotation2d.kZero));
    redCoralStationPoses[2] = redCoralStationPoses[0].transformBy(new Transform2d(0.0, -SLOT_TO_SLOT_DISTANCE*3, Rotation2d.kZero));
    for (int i = 0; i < 3; i++) {
      redCoralStationPoses[i] = redCoralStationPoses[i].rotateBy(Rotation2d.kZero.minus(CORAL_STATION_ROTATION));
      redCoralStationPoses[i] = redCoralStationPoses[i].relativeTo(RED_CORAL_STATION_LEFT);
    }
    redCoralStationPoses[3] = new Pose2d(-ROBOT_AWAY_FROM_CORAL_STATION, Y_OFFSET, Rotation2d.kZero);
    redCoralStationPoses[4] = redCoralStationPoses[3].transformBy(new Transform2d(0.0,  SLOT_TO_SLOT_DISTANCE*3, Rotation2d.kZero));
    redCoralStationPoses[5] = redCoralStationPoses[3].transformBy(new Transform2d(0.0, -SLOT_TO_SLOT_DISTANCE*3, Rotation2d.kZero));
    for (int i = 3; i < 6; i++) {
      redCoralStationPoses[i] = redCoralStationPoses[i].rotateBy(Rotation2d.kZero.plus(CORAL_STATION_ROTATION));
      redCoralStationPoses[i] = redCoralStationPoses[i].relativeTo(RED_CORAL_STATION_RIGHT);
    }

    m_redCoralStationPoses = new ArrayList<Pose2d>(Arrays.asList(redCoralStationPoses));

    return m_redCoralStationPoses;
  }

  public static Pose2d calculateBlueProcessorSetPoint() {
    Pose2d blueProcessorPose = new Pose2d(-ROBOT_AWAY_FROM_PROCESSOR, 0, Rotation2d.kZero);
    blueProcessorPose = blueProcessorPose.rotateBy(Rotation2d.kCW_90deg);
    blueProcessorPose = blueProcessorPose.relativeTo(BLUE_PROCESSOR);
    return blueProcessorPose;
  }

  public static Pose2d calculateRedProcessorSetPoint() {
    Pose2d redProcessorPose = new Pose2d(-ROBOT_AWAY_FROM_PROCESSOR, 0, Rotation2d.kZero);
    redProcessorPose = redProcessorPose.rotateBy(Rotation2d.kCCW_90deg);
    redProcessorPose = redProcessorPose.relativeTo(RED_PROCESSOR);
    return redProcessorPose;
  }
}
