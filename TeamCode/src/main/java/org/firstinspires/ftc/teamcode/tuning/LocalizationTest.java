package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drawing;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.PinpointDrive;

import java.util.List;

@Config
@TeleOp(name="localizer")
public class LocalizationTest extends LinearOpMode {

//17
    // y 10
    private double objectwidth = 0.0;
    private double xError = 0.0;
    private double yError = 0.0;

    public static double KprotBaseClose = -0.32;
    public static double KprotBaseCloseY = -0.32;
    public static double KprotBaseFarY = -0.5;
    public static double KprotBaseFarthestY = -0.5;
    public static double KprotBaseMaxY = -0.5;



    public static double KpClose = 0.195;
    public static double KpCloseY = 0.21;
    public static double KpFarY = 0.2;
    public static double KpFarthestY = 0.21;
    public static double KpMaxY = 0.23;


    public static int slide=0;
    public static double extendo=0.68;
    public static double OuttakeExtendo=0.61;
    public static double Arms=0.2;
    public static double IntakeGripper=0.65;
    public static double OuttakeGripper=0.35;
    public static double IntakeTurret=0.42  ;

        public static double IntakeAngle=0.52;

    public static double IntakeHeight=0.735;
    public static boolean UseLimelight=false;
    public static boolean Movement=false;
    public static int HorizontalSampleClose=120;
    public static int HorizontalSampleMedium=70;
    public static int HorizontalSampleFar=30;
    public static int HorizontalSampleLimit=30;

    public static double Ylimit=10;
    public static double Xlimit=17;
    public static double YFar=17;
    public static double YFarthest=28;
    public static double YMax=29.5;



    public static double LightPoz=1;
    public double ExtendoMovement(LimeLight limeLight) {
        LLResult result = limeLight.limelight.getLatestResult();

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
    public double TurretMovement(LimeLight limeLight) {
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
    @Override
    public void runOpMode() throws InterruptedException {
        LimeLight limeLight= new LimeLight(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        PinpointDrive drive = new PinpointDrive(hardwareMap, new Pose2d(new Vector2d(32, -60.5), Math.toRadians(90)));
        Mecanisme mecanisme=new Mecanisme(hardwareMap);
        mecanisme.slides.ResetEncoders();
        mecanisme.intake.light.SetDetectionLight();

        waitForStart();


        while (opModeIsActive()) {

            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));

//            if(!UseLimelight)
//            {
//            }
//            else{
//                mecanisme.intake.angle.HorizontalAngle();
//                mecanisme.intake.turret.TurretDefault();
//                mecanisme.extendo.Retracted();
//                mecanisme.intake.height.HeightDefault();
//            }
//
//            if(Movement && limeLight.is_detecting()){
//                mecanisme.intake.angle.AngleCallibration(AngleMovement(limeLight));
//                mecanisme.intake.turret.TurretCalibration(TurretMovement(limeLight));
//                mecanisme.extendo.ExtendoCallibration(ExtendoMovement(limeLight));
//                Movement=false;
//                sleep(500);
//                mecanisme.intake.height.HeightCollecting();
//
//            }

            mecanisme.intake.light.LightCalibration(LightPoz);
            mecanisme.slides.SlideCalibration(slide);

            mecanisme.extendo.ExtendoCallibration(extendo);
            mecanisme.intake.angle.AngleCallibration(IntakeAngle);
            mecanisme.intake.height.HeightCallibration(IntakeHeight);
            mecanisme.intake.gripper.GripperCallibration(IntakeGripper);
            mecanisme.outtake.arms.ArmsCalibration(Arms);
            mecanisme.outtake.gripper.GripperCalibration(OuttakeGripper);
            mecanisme.outtake.extendo.ExtendoCalibration(OuttakeExtendo);
            //   mecanisme.intake.turret.TurretCalibration(IntakeTurret);
            LLResult result = limeLight.limelight.getLatestResult();

            if (result != null && result.isValid() && !result.getDetectorResults().isEmpty() ) {
                List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
                if (limeLight.is_detecting()) {

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
            }
            telemetry.addData("is detecting",limeLight.is_detecting());
            telemetry.addData("extendoPoz",mecanisme.extendo.getExtendoPosition());
            telemetry.addData("x Error", xError);
            telemetry.addData("y Error", yError);
            telemetry.addData("Object Width", objectwidth);
            telemetry.addData("LeftSlidePoz",mecanisme.slides.getLeftSlidePoz());
            telemetry.addData("RightSlidePoz",mecanisme.slides.getRightSlidePoz());
            telemetry.addData("IntakeSensorDistance",mecanisme.intake.sensor.getSensorDistance());

            drive.updatePoseEstimate();

            telemetry.addData("x", drive.pose.position.x);
            telemetry.addData("y", drive.pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(drive.pose.heading.toDouble()));
            telemetry.update();

            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), drive.pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
