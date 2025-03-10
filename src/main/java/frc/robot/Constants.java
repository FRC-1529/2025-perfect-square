package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class Constants {
    public static final class Keys {
        // Power scalar keys
        public static final String linearPowerScalarKey = "LinearPowerScalar";
        public static final String angularPowerScalarKey = "AngularPowerScalar";
        public static final String liftPowerScalarKey = "LiftPowerScalar";

        // Drive gain keys
        public static final String axialGainKey = "AxialGain";
        public static final String lateralGainKey = "LateralGain";
        public static final String headingGainKey = "HeadingGain";
    }

    public static final class Drive {
        // Power scalars
        public static final double defaultLinearPowerScalar = 1.0;
        public static final double defaultAngularPowerScalar = 1.0;

        // Odometry conversion factors
        public static final double axialMetersPerTick = 0.03817911261;
        public static final double lateralMetersPerTick = 0.043621742;

        // Feedback gains
        public static final double defaultAxialGain = 0.0;
        public static final double defaultLateralGain = 0.0;
        public static final double defaultHeadingGain = 0.0;
    }

    public static final class Poses {
        public static final class Blue {
            public static final Pose2d rightStationLeft = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d rightStationCenter = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d rightStationRight = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d leftStationLeft = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d leftStationCenter = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d leftStationRight = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch1 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch2 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch3 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch4 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch5 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch6 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch7 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch8 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch9 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch10 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch11 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch12 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae1 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae2 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae3 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae4 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae5 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae6 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d processor = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d[] poses = new Pose2d[]{
                rightStationLeft, rightStationCenter, rightStationRight, 
                leftStationLeft, leftStationCenter, leftStationRight, 
                branch1, branch2, branch3, branch4, branch5, branch6, branch7, branch8, branch9, branch10, branch11, branch12, 
                algae1, algae2, algae3, algae4, algae5, algae6, processor
            };
        }
        public static final class Red {
            public static final Pose2d rightStationLeft = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d rightStationCenter = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d rightStationRight = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d leftStationLeft = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d leftStationCenter = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d leftStationRight = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch1 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch2 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch3 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch4 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch5 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch6 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch7 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch8 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch9 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch10 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch11 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d branch12 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae1 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae2 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae3 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae4 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae5 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d algae6 = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d processor = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
            public static final Pose2d[] poses = new Pose2d[]{
                rightStationLeft, rightStationCenter, rightStationRight, 
                leftStationLeft, leftStationCenter, leftStationRight, 
                branch1, branch2, branch3, branch4, branch5, branch6, branch7, branch8, branch9, branch10, branch11, branch12, 
                algae1, algae2, algae3, algae4, algae5, algae6, processor
            };
        }
    }

    public static final class Lift {
        // Power scalar
        public static final double defaultLiftPowerScalar = 0.25;

        // Heights
        public static final double heightMin = 0.76;
        //public static final double heightL1 = 0.46;
        public static final double heightL2 = 0.81;
        public static final double heightL3 = 1.21;
        public static final double heightL4 = 1.83;
        public static final double heightMax = 2.14;
        public static final double rotationsPerMeter = 61.217;
        public static final double[] heights = {heightMin, heightL2, heightL3, heightL4};
    }
}