package frc.robot;

public final class Constants {
  public static class SmartDashboardValues {
    public static class Basic {
      public static final double kSliderMaxValue = 5.0;
      public static final String kSlider0Key = "DB/Slider 0";
      public static final String kSlider1Key = "DB/Slider 1";
      public static final String kSlider2Key = "DB/Slider 2";
      public static final String kSlider3Key = "DB/Slider 3";

      public static final String kLed0Key = "DB/LED 0";
      public static final String kLed1Key = "DB/LED 1";
      public static final String kLed2Key = "DB/LED 2";
      public static final String kLed3Key = "DB/LED 3";

      public static final String kButton0Key = "DB/Button 0";
      public static final String kButton1Key = "DB/Button 1";
      public static final String kButton2Key = "DB/Button 2";
      public static final String kButton3Key = "DB/Button 3";

      public static final String kString0Key = "DB/String 0";
      public static final String kString1Key = "DB/String 1";
      public static final String kString2Key = "DB/String 2";
      public static final String kString3Key = "DB/String 3";
      public static final String kString4Key = "DB/String 4";
      public static final String kString5Key = "DB/String 5";
      public static final String kString6Key = "DB/String 6";
      public static final String kString7Key = "DB/String 7";
      public static final String kString8Key = "DB/String 8";
      public static final String kString9Key = "DB/String 9";
    }

  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class DriveTrainConstants {
    public static final int kLeftFrontPort = 3;
    public static final int kLeftRearPort = 9;
    public static final int kRightFrontPort = 7;
    public static final int kRightRearPort = 8;
  }

  public static class StorageConstants {
    public static final int kTopSolonoidDeploy = 0;
    public static final int kTopSolonoidRetract = 1;

    public static final int kBottomSolonoidDeploy = 4;
    public static final int kBottomSolonoidRetract = 6;

  }

  public static class ShooterCostants {
    public static final int kLeftFlyWheel = 2;
    public static final int kRightFlyWheel = 5;

    public static final int kEncoderDistancePerPulse = 1;

    public static final int kLeftEncoderChannelA = 0;
    public static final int kLeftEncoderChannelB = 1;

    public static final int kRightEncoderChannelA = 3;
    public static final int kRightEncoderChannelB = 2;

    public static final double kShoot = 15000;

    public static final double kTimedShoot = 1.5;
    public static final double kMaxPower = 0.8;
    public static final String kMaxPowerKey = SmartDashboardValues.Basic.kSlider0Key;
    public static final String kMaxPowerKeyAuto = SmartDashboardValues.Basic.kSlider1Key;
    public static final double kSliderMaxValue = 5.0;

  }
}