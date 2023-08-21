package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class DriveTrainConstants {
    public static final int kLeftFrontPort = 1;
    public static final int kLeftRearPort = 2;
    public static final int kRightFrontPort = 3;
    public static final int kRightRearPort = 4;
  }

  public static class StorageConstants {
    public static final int kTopSolonoidDeploy = 0;
    public static final int kTopSolonoidRetract = 1;

    public static final int kBottomSolonoidDeploy = 4;
    public static final int kBottomSolonoidRetract = 3;
  }

  public static class ShooterCostants {
    public static final int kLeftFlyWheel = 0;
    public static final int kRightFlyWheel = 1;

    public static final int kEncoderDistancePerPulse = 1;

    public static final int kLeftEncoderChannelA = 0;
    public static final int kLeftEncoderChannelB = 1;

    public static final int kRightEncoderChannelA = 0;
    public static final int kRightEncoderChannelB = 1;
  }
}