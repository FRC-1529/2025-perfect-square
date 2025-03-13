package frc.robot;

import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.util.Units;

public class Constants {
    public static final class Keys {
        // Power scalar keys
        public static final String linearPowerScalarKey = "LinearPowerScalar";
        public static final String angularPowerScalarKey = "AngularPowerScalar";
        public static final String liftPowerScalarKey = "LiftPowerScalar";

        // Tuned parameter keys
        public static final String linearGainKey = "LinearGain";
        public static final String angularGainKey = "AngularGain";

        // Tolerance keys
        public static final String linearToleranceKey = "LinearTolerance";
        public static final String angularToleranceKey = "AngularTolerance";
        public static final String liftHeightToleranceKey = "LiftHeightTolerance";

        // Snapping keys
        public static final String snappingDistanceKey = "SnappingDistance";
        public static final String doSnapKey = "DoSnap";
    }

    public static final class DriveConstants {
        // Power scalars
        public static final double defaultLinearPowerScalar = 1.0;
        public static final double defaultAngularPowerScalar = 1.0;

        // Odometry conversion factors
        public static final double axialMetersPerTick = 0.03817911261;
        public static final double lateralMetersPerTick = 0.043621742;

        // Tuned parameters
        public static final double defaultLinearGain = 0.0;
        public static final double defaultAngularGain = 0.0;

        // Tolerances
        public static final double defaultLinearTolerance = 0.25;
        public static final double defaultAngularTolerance = 10;

        // Snapping
        public static final double defaultSnappingDistance = 0.0;
    }

    public static final class Poses {
        public static final class Blue {
            public static final ArrayList<Pose2d> coralStationPoses = EagleUtil.calculateBlueCoralStationSetPoints();
            public static final Pose2d rightStationCenter = coralStationPoses.get(0);
            public static final Pose2d rightStationLeft = coralStationPoses.get(1);
            public static final Pose2d rightStationRight = coralStationPoses.get(2);
            public static final Pose2d leftStationCenter = coralStationPoses.get(3);
            public static final Pose2d leftStationLeft = coralStationPoses.get(4);
            public static final Pose2d leftStationRight = coralStationPoses.get(5);
            public static final ArrayList<Pose2d> reefPoses = EagleUtil.calculateBlueReefSetPoints();
            public static final Pose2d reefA = reefPoses.get(0);
            public static final Pose2d reefB = reefPoses.get(1);
            public static final Pose2d reefC = reefPoses.get(2);
            public static final Pose2d reefD = reefPoses.get(3);
            public static final Pose2d reefE = reefPoses.get(4);
            public static final Pose2d reefF = reefPoses.get(5);
            public static final Pose2d reefG = reefPoses.get(6);
            public static final Pose2d reefH = reefPoses.get(7);
            public static final Pose2d reefI = reefPoses.get(8);
            public static final Pose2d reefJ = reefPoses.get(9);
            public static final Pose2d reefK = reefPoses.get(10);
            public static final Pose2d reefL = reefPoses.get(11);
            public static final Pose2d algaeAB = new Pose2d((reefA.getX() + reefB.getX())/2, (reefA.getY() + reefB.getY())/2, reefA.getRotation());
            public static final Pose2d algaeCD = new Pose2d((reefC.getX() + reefD.getX())/2, (reefC.getY() + reefD.getY())/2, reefC.getRotation());
            public static final Pose2d algaeEF = new Pose2d((reefE.getX() + reefF.getX())/2, (reefE.getY() + reefF.getY())/2, reefE.getRotation());
            public static final Pose2d algaeGH = new Pose2d((reefG.getX() + reefH.getX())/2, (reefG.getY() + reefH.getY())/2, reefG.getRotation());
            public static final Pose2d algaeIJ = new Pose2d((reefI.getX() + reefJ.getX())/2, (reefI.getY() + reefJ.getY())/2, reefI.getRotation());
            public static final Pose2d algaeKL = new Pose2d((reefK.getX() + reefL.getX())/2, (reefK.getY() + reefL.getY())/2, reefK.getRotation());
            public static final ArrayList<Pose2d> algaePoses = new ArrayList<Pose2d>(Arrays.asList(
                algaeAB, algaeCD, algaeEF, algaeGH, algaeIJ, algaeKL
            ));
            public static final Pose2d processor = EagleUtil.calculateBlueProcessorSetPoint();
            public static final ArrayList<Pose2d> poses = new ArrayList<Pose2d>(Arrays.asList(
                rightStationCenter, rightStationRight, rightStationLeft, 
                leftStationCenter, leftStationLeft, leftStationRight, 
                reefA, reefB, reefC, reefD, reefE, reefF, reefG, reefH, reefI, reefJ, reefK, reefL, 
                algaeAB, algaeCD, algaeEF, algaeGH, algaeIJ, algaeKL, processor
            ));
        }
        public static final class Red {
            public static final ArrayList<Pose2d> coralStationPoses = EagleUtil.calculateRedCoralStationSetPoints();
            public static final Pose2d rightStationCenter = coralStationPoses.get(0);
            public static final Pose2d rightStationLeft = coralStationPoses.get(1);
            public static final Pose2d rightStationRight = coralStationPoses.get(2);
            public static final Pose2d leftStationCenter = coralStationPoses.get(3);
            public static final Pose2d leftStationLeft = coralStationPoses.get(4);
            public static final Pose2d leftStationRight = coralStationPoses.get(5);
            public static final ArrayList<Pose2d> reefPoses = EagleUtil.calculateRedReefSetPoints();
            public static final Pose2d reefA = reefPoses.get(0);
            public static final Pose2d reefB = reefPoses.get(1);
            public static final Pose2d reefC = reefPoses.get(2);
            public static final Pose2d reefD = reefPoses.get(3);
            public static final Pose2d reefE = reefPoses.get(4);
            public static final Pose2d reefF = reefPoses.get(5);
            public static final Pose2d reefG = reefPoses.get(6);
            public static final Pose2d reefH = reefPoses.get(7);
            public static final Pose2d reefI = reefPoses.get(8);
            public static final Pose2d reefJ = reefPoses.get(9);
            public static final Pose2d reefK = reefPoses.get(10);
            public static final Pose2d reefL = reefPoses.get(11);
            public static final Pose2d algaeAB = new Pose2d((reefA.getX() + reefB.getX())/2, (reefA.getY() + reefB.getY())/2, reefA.getRotation());
            public static final Pose2d algaeCD = new Pose2d((reefC.getX() + reefD.getX())/2, (reefC.getY() + reefD.getY())/2, reefC.getRotation());
            public static final Pose2d algaeEF = new Pose2d((reefE.getX() + reefF.getX())/2, (reefE.getY() + reefF.getY())/2, reefE.getRotation());
            public static final Pose2d algaeGH = new Pose2d((reefG.getX() + reefH.getX())/2, (reefG.getY() + reefH.getY())/2, reefG.getRotation());
            public static final Pose2d algaeIJ = new Pose2d((reefI.getX() + reefJ.getX())/2, (reefI.getY() + reefJ.getY())/2, reefI.getRotation());
            public static final Pose2d algaeKL = new Pose2d((reefK.getX() + reefL.getX())/2, (reefK.getY() + reefL.getY())/2, reefK.getRotation());
            public static final ArrayList<Pose2d> algaePoses = new ArrayList<Pose2d>(Arrays.asList(
                algaeAB, algaeCD, algaeEF, algaeGH, algaeIJ, algaeKL
            ));
            public static final Pose2d processor = EagleUtil.calculateRedProcessorSetPoint();
            public static final ArrayList<Pose2d> poses = new ArrayList<Pose2d>(Arrays.asList(
                rightStationCenter, rightStationRight, rightStationLeft, 
                leftStationCenter, leftStationLeft, leftStationRight, 
                reefA, reefB, reefC, reefD, reefE, reefF, reefG, reefH, reefI, reefJ, reefK, reefL, 
                algaeAB, algaeCD, algaeEF, algaeGH, algaeIJ, algaeKL, processor
            ));
        }
    }

    public static final class LiftConstants {
        // Power scalar
        public static final double defaultLiftPowerScalar = 0.25;

        // Heights
        public static final double algaeOffset = Units.inchesToMeters(15.0);
        public static final double heightMin = 0.76;
        //public static final double heightL1 = 0.46;
        public static final double heightL2 = 0.791;
        public static final double heightL3 = 1.194;
        public static final double heightL4 = 1.829;
        public static final double heightLowAlgae = 0.909 + algaeOffset;
        public static final double heightHighAlgae = 1.313 + algaeOffset;
        public static final double heightMax = 2.14;
        public static final double rotationsPerMeter = 61.217;
        public static final double[] heights = {heightMin, heightL2, heightL3, heightL4, heightLowAlgae, heightHighAlgae};
        public static final double defaultLiftHeightTolerance = 0.1;
    }

    public static final class HangConstants {
        public static final double leftPressRotations = 0.0;
        public static final double rightPressRotations = 40.0;
        public static final double tolerance = 0.0;
        public static final double leftHangRotations = 0.0;
        public static final double leftDisplacement = leftHangRotations - leftPressRotations;
        public static final double rightHangRotations = 80.0;
        public static final double rightDisplacement = rightHangRotations - rightPressRotations;
        public static final double hangTimeSeconds = 100.0;
    }
}