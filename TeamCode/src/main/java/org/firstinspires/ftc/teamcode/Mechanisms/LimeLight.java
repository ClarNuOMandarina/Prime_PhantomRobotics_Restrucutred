package org.firstinspires.ftc.teamcode.Mechanisms;

import static java.lang.Math.pow;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;

public class LimeLight {
    private double objectwidth = 0.0;
    private double Actualobjectwidth = 0.0;
    private double xError = 0.0;
    private double yError = 0.0;

    public static double KpTurret = -0.3;
    public static double KpTurretRightOffset = -0.7;

    public static double KpExtendo = 0.13;
    public double YDetectlimit = 29;
    public double Xetectlimit = 19;

    public static double ObjWithXMod = 1.55;
    public static double ObjWithYMod = 0.7;
    public static double IsHorizontal = 125;


    public Limelight3A limelight;

    public LimeLight(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "LimeLight");
        limelight.pipelineSwitch(1);
        limelight.start();
    }

    public double ExtendoMovement(LimeLight limeLight) {
        LLResult result = limeLight.limelight.getLatestResult();

        if (result != null && result.isValid() && !result.getDetectorResults().isEmpty()) {
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();

            for (LLResultTypes.DetectorResult fr : detectorResults) {

                yError = -(fr.getTargetYDegrees() - 8);
                xError =  fr.getTargetXDegrees();
            }
            double KpScaled;
            KpScaled = KpExtendo;




            double yErrorMax = 26;
            double normalizedErrorExt = Math.max(-1, Math.min(1, (KpScaled * yError) / yErrorMax));
            double targetPositionExt = 0.69+ Math.tan((normalizedErrorExt * 0.8));
            double normalizedSignalExt;

            normalizedSignalExt = Math.min(1, Math.max(0.69, targetPositionExt));

            return normalizedSignalExt;


        }

        return 0.69;
    }

    public double AngleMovement(LimeLight limeLight) {
        LLResult result = limeLight.limelight.getLatestResult();

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

                objectwidth = rightmostX - leftmostX ;
                Actualobjectwidth=objectwidth-(Math.sqrt(Math.abs(xError) )/(objectwidth*ObjWithXMod ))+(pow(yError*4.5,ObjWithYMod));
            }
            double normalizedSignalAng;

            if(Actualobjectwidth<125) {

                normalizedSignalAng = 0.52;

            }
            else {

                normalizedSignalAng = 0.25;

            }

            return normalizedSignalAng;

        }
        return 0.52;
    }
    public double TurretMovement(LimeLight limeLight) {
        LLResult result = limeLight.limelight.getLatestResult();

        if (result != null && result.isValid() && !result.getDetectorResults().isEmpty()) {
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();

            for (LLResultTypes.DetectorResult fr : detectorResults) {

                xError = fr.getTargetXDegrees();

            }


            double normalizedSignalRot;
            double Kprot = KpTurret;


            if(xError<0 && xError>-4.5){
                Kprot = KpTurretRightOffset;

                if(xError>-3){
                    Kprot*=2*1/Math.abs(xError);
                }

            }

            double xErrorMax = 24;
            double normalizedErrorRot = Math.max(-1.0, Math.min(1.0, (Kprot * xError) / xErrorMax));
            double targetPositionRot = 0.43 + (normalizedErrorRot * 0.42);
            normalizedSignalRot = Math.min(0.73, Math.max(0.15, targetPositionRot));

            if(xError<=6 && xError>=1.5 ) {
                normalizedSignalRot = 0.43;

            }

            return normalizedSignalRot;

        }

        return 0;
    }


    public boolean is_detecting() {
        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid() && !result.getDetectorResults().isEmpty()) {
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
            for (LLResultTypes.DetectorResult fr : detectorResults) {

                yError = -(fr.getTargetYDegrees() - 8);
                xError = fr.getTargetXDegrees();


            }
            if ((Math.abs(yError) > YDetectlimit))
            {
                return false;
            }
            if (Math.abs(xError) > Xetectlimit)
                return false;


            return true;
        }
        return false;

    }



}


