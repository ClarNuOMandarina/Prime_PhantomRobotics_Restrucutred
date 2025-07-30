
package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import static java.lang.Math.pow;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.SampleAutonomous.AutonomousSampleActions;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;
import org.firstinspires.ftc.teamcode.PinpointDrive;

import java.util.List;

@Autonomous(name= "Sample Autonomous")
public class SampleAutonomous extends LinearOpMode {

    private double objectwidth = 0.0;
    private double Actualobjectwidth = 0.0;
    private double xError = 0.0;
    private double yError = 0.0;

    public static double KpTurret = -0.3;
    public static double KpTurretRightOffset = -0.7;

    public static double KpExtendo = 0.15;


    public static double extendo=0.68;



    public static double ObjWithXMod=1.52;
    public static double ObjWithYMod=0.73;
    public static double A=2;

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
                    Kprot*=A*1/Math.abs(xError);
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
    @Override
    public void runOpMode() throws InterruptedException {

        AutonomousSampleActions autonomousActions = new AutonomousSampleActions(hardwareMap, gamepad1);
        autonomousActions.actionBuilder.mecanisme.slides.ResetEncoders();
        autonomousActions.actionBuilder.mecanisme.AutoInitSample();
        PinpointDrive drive = new PinpointDrive(hardwareMap, autonomousActions.sampleFieldMap.initialPose);
        ElapsedTime BasicTimer= new ElapsedTime();
        LimeLight limeLight= new LimeLight(hardwareMap);

        waitForStart();

        autonomousActions.PreloadScore(drive);
        sleep(600);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(200);
        autonomousActions.CollectFirstSample(drive);
        autonomousActions.actionBuilder.mecanisme.slides.SlideCalibration(-30);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreFirstSample(drive);
        sleep(400);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(100);
        autonomousActions.CollectSecondSample(drive);
        autonomousActions.actionBuilder.mecanisme.slides.SlideCalibration(-30);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreSecondSample(drive);
        sleep(400);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(100);

        autonomousActions.CollectThirdSample(drive);
        autonomousActions.actionBuilder.mecanisme.slides.SlideCalibration(-30);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreThirdSample(drive);
        sleep(400);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(100);
        double y = -2;

        autonomousActions.SubmersibleCollectFirstCycle(drive,y);
        boolean isCollected =false;
        while (!isCollected && opModeIsActive()) {

            BasicTimer.reset();
            while(BasicTimer.seconds()<0.8 ){
                if(limeLight.is_detecting()) {
                    autonomousActions.actionBuilder.mecanisme.intake.angle.AngleCallibration(AngleMovement(limeLight));
                    autonomousActions.actionBuilder.mecanisme.intake.turret.TurretCalibration(TurretMovement(limeLight));
                    autonomousActions.actionBuilder.mecanisme.extendo.ExtendoCallibration(ExtendoMovement(limeLight));

                    if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                        isCollected = true;
                    }
                }
                telemetry.addData("is detecting",limeLight.is_detecting());
                telemetry.addData("x Error", xError);
                telemetry.addData("y Error", yError);
                telemetry.addData("Object Width", objectwidth);
                telemetry.addData("Actual Object Width", Actualobjectwidth);
                telemetry.addData("IsHorizontal", Actualobjectwidth<125);
                telemetry.update();
            }

            if(!isCollected) {
                y += 3;

                autonomousActions.SubmersibleSearch(drive,y);

            }
            if(y>18)y=-2;
        }
        isCollected = false;
        autonomousActions.SubmersibleScore(drive,y);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(300);

        autonomousActions.SubmersibleCollect(drive,y);

        while (!isCollected && opModeIsActive()) {
            boolean condition=false;
            BasicTimer.reset();

            while(BasicTimer.seconds()<0.8 && !condition){
                if(limeLight.is_detecting()){
                    condition=true;
                }
            }
            if (condition) {
                double anglepoz=0.52;
                double turretpoz=0.43;
                double extendopoz=0.69;
                BasicTimer.reset();
                while(BasicTimer.seconds()<0.2){
                    anglepoz=limeLight.AngleMovement(limeLight);
                    turretpoz=limeLight.TurretMovement(limeLight);
                    extendopoz=limeLight.ExtendoMovement(limeLight);
                }
                autonomousActions.actionBuilder.mecanisme.intake.angle.AngleCallibration(anglepoz);
                autonomousActions.actionBuilder.mecanisme.intake.turret.TurretCalibration(turretpoz);
                autonomousActions.actionBuilder.mecanisme.extendo.ExtendoCallibration(extendopoz);
                if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                    isCollected = true;
                }

            }
            else {
                y += 3;

                autonomousActions.SubmersibleSearch(drive,y);

            }
            if(y>18)y=-2;
        }
        isCollected = false;
        autonomousActions.SubmersibleScore(drive,y);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(300);
        autonomousActions.SubmersibleCollect(drive,y);

        BasicTimer.reset();
        while (!isCollected && opModeIsActive()) {
            boolean condition=false;
            BasicTimer.reset();
            while(BasicTimer.seconds()<0.8 && !condition){
                if(limeLight.is_detecting()){
                    condition=true;
                }
            }
            if (condition) {
                double anglepoz=0.52;
                double turretpoz=0.43;
                double extendopoz=0.69;
                BasicTimer.reset();
                while(BasicTimer.seconds()<0.2){
                    anglepoz=limeLight.AngleMovement(limeLight);
                    turretpoz=limeLight.TurretMovement(limeLight);
                    extendopoz=limeLight.ExtendoMovement(limeLight);
                }
                autonomousActions.actionBuilder.mecanisme.intake.angle.AngleCallibration(anglepoz);
                autonomousActions.actionBuilder.mecanisme.intake.turret.TurretCalibration(turretpoz);
                autonomousActions.actionBuilder.mecanisme.extendo.ExtendoCallibration(extendopoz);
                if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                    isCollected = true;
                }

            } else {
                y += 3;

                autonomousActions.SubmersibleSearch(drive,y);

            }
            if(y>18)y=-2;
        }
        isCollected = false;
        autonomousActions.SubmersibleScore(drive,y);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(300);
        sleep(400);

        autonomousActions.Reset(drive);
    }

}
