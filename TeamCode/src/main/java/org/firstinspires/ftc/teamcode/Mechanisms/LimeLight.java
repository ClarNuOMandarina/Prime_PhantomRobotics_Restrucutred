package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

public class LimeLight {
    private double objectwidth = 0.0;
    private double xError = 0.0;
    private double yError = 0.0;

    public static double KprotBaseClose = -0.32;
    public static double KprotBaseCloseY = -0.32;
    public static double KprotBaseFarY = -0.5;
    public static double KprotBaseFarthestY = -0.5;
    public static double KprotBaseMaxY = -0.5;



    public static double KpClose = 0.25;
    public static double KpCloseY = 0.26;
    public static double KpFarY = 0.25;
    public static double KpFarthestY = 0.27;
    public static double KpMaxY = 0.29;


    public static int HorizontalSampleClose=120;
    public static int HorizontalSampleMedium=70;
    public static int HorizontalSampleFar=30;
    public static int HorizontalSampleLimit=30;
    public double YDetectlimit=10;
    public double XDetectlimit=17;
    public static double Ylimit=10;
    public static double Xlimit=17;
    public static double YFar=17;
    public static double YFarthest=28;
    public static double YMax=29.5;

    public Limelight3A limelight;
    public LimeLight(HardwareMap hardwareMap){
        limelight= hardwareMap.get(Limelight3A.class, "LimeLight");
        limelight.pipelineSwitch(1);
        limelight.start();
    }

    public double AngleMovement() {
        LLResult result =limelight.getLatestResult();

        if (result != null && result.isValid() && !result.getDetectorResults().isEmpty()) {
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();

            for (LLResultTypes.DetectorResult fr : detectorResults) {

                xError = fr.getTargetXDegrees();

                double corner1 = fr.getTargetCorners().get(0).get(0);
                double corner2 = fr.getTargetCorners().get(3).get(0);
                double corner3 = fr.getTargetCorners().get(2).get(0);
                double corner4 = fr.getTargetCorners().get(1).get(0);

                double leftmostX = Math.min(Math.min(corner1, corner2), Math.min(corner3, corner4));
                double rightmostX = Math.max(Math.max(corner1, corner2), Math.max(corner3, corner4));

                objectwidth = rightmostX - leftmostX - 100;
            }
            double normalizedSignalAng;

            if (Math.abs(yError) <= 10
            ) {
                if (objectwidth < HorizontalSampleClose) normalizedSignalAng = 0.52;
                else normalizedSignalAng = 0.25;
            } else if (Math.abs(yError) <= 25) {
                if (objectwidth < HorizontalSampleMedium) normalizedSignalAng = 0.52;
                else normalizedSignalAng = 0.25;
            }
            else {
                if (objectwidth < HorizontalSampleFar) normalizedSignalAng = 0.52;
                else normalizedSignalAng = 0.25;
            }

            if(Math.abs(xError)>Xlimit&& Math.abs(yError)>Ylimit) {

                if (objectwidth >HorizontalSampleLimit) {
                    normalizedSignalAng = 0.25;

                }
                else{
                    normalizedSignalAng = 0.52;

                }

            }

            return normalizedSignalAng;

        }
        return 0.52;
    }

    public double TurretMovement() {
        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid() && !result.getDetectorResults().isEmpty()) {
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();

            for (LLResultTypes.DetectorResult fr : detectorResults) {

                xError = fr.getTargetXDegrees();
                double corner1 = fr.getTargetCorners().get(0).get(0);
                double corner2 = fr.getTargetCorners().get(3).get(0);
                double corner3 = fr.getTargetCorners().get(2).get(0);
                double corner4 = fr.getTargetCorners().get(1).get(0);

                double leftmostX = Math.min(Math.min(corner1, corner2), Math.min(corner3, corner4));
                double rightmostX = Math.max(Math.max(corner1, corner2), Math.max(corner3, corner4));

                objectwidth = rightmostX - leftmostX - 100;

            }


            double normalizedSignalRot;
            double Kprot = KprotBaseClose;

            if( Math.abs(yError)>Ylimit){
                Kprot = KprotBaseCloseY;

            }
            if( Math.abs(yError)>YFar){
                Kprot = KprotBaseFarY;

            }
            if( Math.abs(yError)>YFarthest){
                Kprot = KprotBaseFarthestY;

            }


            double xErrorMax = 24;
            double normalizedErrorRot = Math.max(-1.0, Math.min(1.0, (Kprot * xError) / xErrorMax));
            double targetPositionRot = 0.405 + (normalizedErrorRot * 0.42);
            normalizedSignalRot = Math.min(0.73, Math.max(0.15, targetPositionRot));

            if(Math.abs(xError)<=2 ) {
                normalizedSignalRot = 0.42;

            }

            return normalizedSignalRot;

        }
        return 0;
    }

    public double ExtendoMovement() {
        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid() && !result.getDetectorResults().isEmpty()) {
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();

            for (LLResultTypes.DetectorResult fr : detectorResults) {

                yError = -(fr.getTargetYDegrees() - 8);
                xError =  fr.getTargetXDegrees();

                double corner1 = fr.getTargetCorners().get(0).get(0);
                double corner2 = fr.getTargetCorners().get(3).get(0);
                double corner3 = fr.getTargetCorners().get(2).get(0);
                double corner4 = fr.getTargetCorners().get(1).get(0);

                double leftmostX = Math.min(Math.min(corner1, corner2), Math.min(corner3, corner4));
                double rightmostX = Math.max(Math.max(corner1, corner2), Math.max(corner3, corner4));

                objectwidth = rightmostX - leftmostX - 100;
            }
            double KpScaled;
            KpScaled = KpClose;

            if( Math.abs(yError)<YFar){
                KpScaled = KpCloseY;

                if(Math.abs(yError)<Ylimit)
                    KpScaled=KpClose;

            }
            if( Math.abs(yError)>YFar){
                KpScaled = KpFarY;

            }
            if( Math.abs(yError)>YFarthest){
                KpScaled = KpFarthestY;

            }
            if( Math.abs(yError)>YMax){
                KpScaled = KpMaxY;

            }



            double yErrorMax = 26;
            double normalizedErrorExt = Math.max(-1, Math.min(1, (KpScaled * yError) / yErrorMax));
            double targetPositionExt = 0.69+ (normalizedErrorExt * 0.8);
            double normalizedSignalExt;

            normalizedSignalExt = Math.min(1, Math.max(0.69, targetPositionExt));

            return normalizedSignalExt;



        }

        return 0.69;
    }


    public boolean is_detecting() {
        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid() && !result.getDetectorResults().isEmpty()) {
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
            for (LLResultTypes.DetectorResult fr : detectorResults) {

                yError = -(fr.getTargetYDegrees() - 8);
                xError = fr.getTargetXDegrees();


            }
            if( (Math.abs(yError)<YDetectlimit && Math.abs(xError)>4))
                return false;

            if(Math.abs(xError)>XDetectlimit)
                return false;
            if(Math.abs(yError)>29.5)
                return false;
            return true;
        }
        return false;

    }
}
