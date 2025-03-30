package org.firstinspires.ftc.teamcode.user;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

@Config
@TeleOp(name="camera calibration")
public class camera_calibration extends LinearOpMode {

    public static double Kpang = -0.5;
    public static double KprotBase = -0.32;
    public static double Kp = 0.175;
    public static double KprotBaseinstant = -0.06;
    public static double Kpinstant = 0.1;

    // Tunable distance-based scaling factors
    public static double distanceFactorRotBase = 1.9;
    public static double distanceFactorAngBasex = 1;
    public static double distanceFactorExtBase = 0.06;

    private double objectwidth = 0.0;
    private double xError = 0.0;
    private double yError = 0.0;

    // Threshold for ignoring small yError when applying xError scaling
    public static double xErrorThreshold = 10002; // In degrees
    public static double yErrorThreshold = 10002; // In degrees
    double coral=0;


    @Override
    public void runOpMode() throws InterruptedException {
        Limelight3A limelight = hardwareMap.get(Limelight3A.class, "limelight");
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        colection colection = new colection(hardwareMap);
        colection.gripper_height.setPosition(colection.height_default);
        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
        extension extension = new extension(hardwareMap);
        extension.extend(extension.extension_retracted);
        colection.detectio_light();
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(1);
        limelight.start();
        boolean coco=false;
        boolean locked_on=false;
        ElapsedTime extensiontimer= new ElapsedTime();
        boolean sync=false;
        ElapsedTime corection= new ElapsedTime();
        boolean corectionz=false;
        waitForStart();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid() && !result.getDetectorResults().isEmpty() ) {
                List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
                if(!locked_on) {

                    for (LLResultTypes.DetectorResult fr : detectorResults) {
                        telemetry.addData("Detection",
                                "Class: %s, Confidence: %.2f, X°: %.2f, Y°: %.2f",
                                fr.getClassName(), fr.getConfidence(), fr.getTargetXDegrees(), fr.getTargetYDegrees());

                        xError = fr.getTargetXDegrees();
                        yError = -(fr.getTargetYDegrees() - 8);

                        double corner1 = fr.getTargetCorners().get(0).get(0);
                        double corner2 = fr.getTargetCorners().get(3).get(0);
                        double corner3 = fr.getTargetCorners().get(2).get(0);
                        double corner4 = fr.getTargetCorners().get(1).get(0);

                        double leftmostX = Math.min(Math.min(corner1, corner2), Math.min(corner3, corner4));
                        double rightmostX = Math.max(Math.max(corner1, corner2), Math.max(corner3, corner4));

                        objectwidth = rightmostX - leftmostX - 100;
                        telemetry.addData("Object Width", objectwidth);
                    }

                }
                // **Dynamic distance-based scaling**
                // Apply scaling for x and y separately
                double distanceFactorRot = distanceFactorRotBase * Math.abs(xError + 0.01);
                double distanceFactorExt = distanceFactorExtBase * Math.abs(xError + 0.01);
                double objectwidthx=objectwidth;
                if(Math.abs(xError)>2)
                 objectwidthx*=(distanceFactorAngBasex*Math.abs(xError + 0.01));
                telemetry.addData("Object Widthx", objectwidthx);
                // **Reduce the influence of xError if yError is small**
                    distanceFactorRot = 1;  // Do not apply the xError correction if yError is small
                    telemetry.addData("X Error Scaling", "Disabled due to small Y Error");

                if (distanceFactorExtBase==1|| Math.abs(xError)<15) {
                    distanceFactorExt = 1;  // Do not apply the xError correction if yError is small
                    telemetry.addData("X Error Scaling", "Disabled due to small Y Error");
                }
                telemetry.addData("X Error Sc", Math.abs(xError));

                // **Apply Distance Factors**
                double Kprot = KprotBase * distanceFactorRot;
                double KpangScaled = Kpang;
                double KpScaled = Kp * distanceFactorExt;

                // **Rotation Correction (X-axis)**
                double xErrorMax = 24;
                double normalizedErrorRot = Math.max(-1.0, Math.min(1.0, (Kprot * xError) / xErrorMax));
                double targetPositionRot = colection.gripper_rotation.getPosition() + (normalizedErrorRot * 0.42);
                double normalizedSignalRot = Math.min(0.73, Math.max(0.15, targetPositionRot));

                // **Rotation Correction (X-axis) instant**
//                double xErrorMax = 24;
                double normalizedErrorRotinst = Math.max(-1.0, Math.min(1.0, (KprotBaseinstant * xError) / xErrorMax));
                double targetPositionRotinst = colection.gripper_rotation.getPosition() + (normalizedErrorRotinst * 0.42);
                double normalizedSignalRotinst = Math.min(0.73, Math.max(0.15, targetPositionRotinst));

                // **Extension Correction (Y-axis)**
                double yErrorMax = 26;
                double normalizedErrorExt = Math.max(-1, Math.min(1, (KpScaled * yError) / yErrorMax));
                double targetPositionExt = extension.left_extension.getPosition()+ (normalizedErrorExt * 0.8);
                double normalizedSignalExt = Math.min(1, Math.max(0.69, targetPositionExt));


                double normalizedErrorExtinst = Math.max(-1, Math.min(1, (Kpinstant * yError) / yErrorMax));
                double targetPositionExtinst = extension.left_extension.getPosition()+ (normalizedErrorExtinst * 0.8);
                double normalizedSignalExtinst = Math.min(1, Math.max(0.69, targetPositionExtinst));


                // **Angle Correction (Using Object Width)**
                double maxWidth = 325 - 100;
                double normalizedErrorAng = Math.max(-1, Math.min(1.0, KpangScaled * objectwidth / maxWidth));
                double targetPositionAng = 0.52 - (normalizedErrorAng * 1.1);
                double normalizedSignalAng = Math.min(0.52, Math.max(0.25, targetPositionAng));
                if(Math.abs(xError)>8.5
                        //&& yError>-2.5
                )
                {
                    if(objectwidth<120)normalizedSignalAng =0.52;
                    else normalizedSignalAng =0.25;
                }
                else {
                    if (objectwidth < 190) normalizedSignalAng = 0.52;
                    else normalizedSignalAng = 0.25;
                }
                // **Debugging Info**
                telemetry.addData("x Error", xError);
                telemetry.addData("y Error", yError);
                telemetry.addData("Object Width", objectwidth);
                telemetry.addData("Normalized Rotation Error", normalizedErrorRot);
                telemetry.addData("Normalized Extension Error", normalizedErrorExt);
                telemetry.addData("Normalized Angle Error", normalizedErrorAng);
                telemetry.addData("Target Rotation", targetPositionRot);
                telemetry.addData("Target Extension", targetPositionExt);
                telemetry.addData("Target Angle", targetPositionAng);
                telemetry.update();

                if(corectionz){
                    colection.gripper_angle.setPosition(normalizedSignalAng);
                    sleep(200);
                    colection.gripper_height.setPosition(colection.height_collecting);
                    sleep(300);
                    colection.gripper_grab();
                    sleep(400);
                    colection.gripper_height.setPosition(colection.height_default);
                    sleep(5000);
                    corectionz=false;
                }

                if(gamepad2.right_trigger!=0){

                        colection.gripper_angle.setPosition(normalizedSignalAng);
                        colection.gripper_rotation.setPosition(normalizedSignalRot);
                        extension.extend(normalizedSignalExt);
                        colection.gripper_release();
                        colection.gripper_height.setPosition(colection.height_scanning);
                        sleep(500);
//                        sleep(100);
//                        colection.gripper_rotation.setPosition(normalizedSignalRotinst);
//                        colection.gripper_angle.setPosition(normalizedErrorAng);
//                        sleep(100);
//                        colection.gripper_rotation.setPosition(normalizedSignalRotinst);
//                        colection.gripper_angle.setPosition(normalizedErrorAng);
//                        sleep(100);
                        corectionz=true;


                }


                if(gamepad2.left_trigger!=0)
                {





                    colection.gripper_height.setPosition(colection.height_scanning);
                    colection.gripper_angle.setPosition(normalizedSignalAng);
                    colection.gripper_rotation.setPosition(normalizedSignalRotinst);
                    extension.extend(normalizedSignalExtinst);
                    sleep(100);
                }
                // **Apply Mov
                // ements**
                if (gamepad1.circle &&!coco) {
                    colection.gripper_angle.setPosition(normalizedSignalAng);
                    colection.gripper_rotation.setPosition(normalizedSignalRot);
                    extension.extend(normalizedSignalExt);
                    colection.gripper_release();
                    sleep(500);
                    coco=true;
                }

//                if(coco && !locked_on){
//                    if (( Math.abs( xError) > 0.5 ||Math.abs( yError) > 0.5)) {
//                    colection.gripper_height.setPosition(colection.height_scanning);
//                    colection.gripper_angle.setPosition(normalizedSignalAng);
//                    colection.gripper_rotation.setPosition(normalizedSignalRotinst);
//                    //extension.extend(normalizedSignalExtinst);
//                    sleep(100);
//                }
//                    else locked_on=true;
//                }
//                else if(locked_on ) {
//                    locked_on = false;
//                    coco=false;
//                    colection.gripper_height.setPosition(colection.height_collecting);
//                    sleep(200);
//                    colection.gripper_grab();
//                    sleep(500);
//                    colection.gripper_height.setPosition(colection.height_default);
//                }





//                if(!locked_on) {
//                    if (gamepad1.dpad_up &&  ( Math.abs( xError) > 0.5 ||Math.abs( yError) > 0.5)) {
//                        if( Math.abs( xError) > 0.5) {
//                            colection.gripper_angle.setPosition(normalizedSignalAng);
//                            colection.gripper_rotation.setPosition(normalizedSignalRot);
//                        }
//                        if(Math.abs( yError) > 0.5)
//                            extension.extend(normalizedSignalExt);
//
//                        colection.gripper_release();
//                        sleep(60);
//                        if (Math.abs( xError) < 0.5 ||Math.abs( yError) < 0.5) locked_on = true;
//                        coco=true;
//                    }
//                }
//                else{
//                    if(coco) {
//                        colection.gripper_height.setPosition(colection.height_collecting);
//                        sleep(200);
//                        colection.gripper_grab();
//                        sleep(500);
//                        colection.gripper_height.setPosition(colection.height_default);
//                    }
//                    coco=false;
//
//                }
//                    if(gamepad1.circle);
//                    {
//                        sync=true;
//                        extensiontimer.reset();
//                    }
//                    if(sync){
//                        colection.gripper_angle.setPosition(normalizedSignalAng);
//                        colection.gripper_rotation.setPosition(normalizedSignalRot);
//                        extension.extend(normalizedSignalExt);
//                        colection.gripper_release();
//                        sleep(60);


            }

            telemetry.addData("timer",(int)(extensiontimer.seconds()*1000));
            if (gamepad1.dpad_down) {

                colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
                colection.gripper_angle.setPosition(0.385);
                extension.extend(extension.extension_retracted);
                sync=false;
                locked_on=false;
                colection.gripper_release();

            }
//            if(!locked_on) {
//                if (gamepad1.right_trigger != 0) {
//                    colection.gripper_height.setPosition(colection.height_collecting);
//
//                } else {
//                    colection.gripper_height.setPosition(colection.height_default);
//
//                }
//            }

            if(gamepad1.square){
                colection.gripper_grab();
            }

            telemetry.update();
        }
    }
}

