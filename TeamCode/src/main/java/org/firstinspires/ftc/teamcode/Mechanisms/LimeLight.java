package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

public class LimeLight {
    public static double KprotBase = -0.32;
    public static double Kp = 0.195;
    public static double distanceFactorRotBase = 1.9;
    public static double distanceFactorExtBase = 0.065;
    public static int HorizontalSampleClose=120;
    public static int HorizontalSampleFar=90;
    private double objectwidth = 0.0;
    private double xError = 0.0;
    private double yError = 0.0;
    public static double xErrorThreshold = 10002; // In degrees
    public static double yErrorThreshold = 10002; // In degrees
    public Limelight3A limelight;
    public LimeLight(HardwareMap hardwareMap){
    limelight= hardwareMap.get(Limelight3A.class, "LimeLight");
    limelight.pipelineSwitch(1);
    limelight.start();
    }

    public double AngleMovement() {
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
            double maxWidth = 325 - 100;
            double normalizedSignalAng;
            if(Math.abs(yError)<=10
            )
            {
                if(objectwidth<HorizontalSampleClose)normalizedSignalAng =0.52;
                else normalizedSignalAng =0.25;
            }
            else {
                if (objectwidth < HorizontalSampleFar) normalizedSignalAng = 0.52;
                else normalizedSignalAng = 0.25;
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


            }
            double distanceFactorRot = distanceFactorRotBase * Math.abs(xError + 0.01);


            if (Math.abs(xError) < xErrorThreshold &&Math.abs(yError) < yErrorThreshold) {
                distanceFactorRot = 1;
            }
            double normalizedSignalRot=0;
            double Kprot = KprotBase * distanceFactorRot;
            if(Math.abs(xError)>4) {
                double xErrorMax = 24;
                double normalizedErrorRot = Math.max(-1.0, Math.min(1.0, (Kprot * xError) / xErrorMax));
                double targetPositionRot = 0.405 + (normalizedErrorRot * 0.42);
                normalizedSignalRot = Math.min(0.73, Math.max(0.15, targetPositionRot));
            }
            else
                normalizedSignalRot = 0.42;

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
                xError = fr.getTargetXDegrees();


            }

            double distanceFactorExt = distanceFactorExtBase * Math.abs(xError + 0.01);

            if (distanceFactorExtBase==1|| Math.abs(xError)<15) {
                distanceFactorExt = 1;
            }
            double KpScaled = Kp * distanceFactorExt;

            double yErrorMax = 26;
            double normalizedErrorExt = Math.max(-1, Math.min(1, (KpScaled * yError) / yErrorMax));
            double targetPositionExt = 0.69+ (normalizedErrorExt * 0.8);
            double normalizedSignalExt = Math.min(1, Math.max(0.69, targetPositionExt));

            return normalizedSignalExt;


        }

        return 0.69;
    }

    public boolean is_detecting() {
        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid() && !result.getDetectorResults().isEmpty()) {
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
            return true;
        }
            return false;

    }
}
