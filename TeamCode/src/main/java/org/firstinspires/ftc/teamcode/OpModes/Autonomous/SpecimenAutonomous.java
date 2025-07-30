package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import static java.lang.Math.pow;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.SpecimenAutonomous.AutonomousSpecimenActions;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;
import org.firstinspires.ftc.teamcode.PinpointDrive;

import java.util.List;

@Autonomous(name="Specimen Autonomous")
public class SpecimenAutonomous extends LinearOpMode {
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
        AutonomousSpecimenActions autonomousActions = new AutonomousSpecimenActions(hardwareMap, gamepad1);
        autonomousActions.actionBuilder.mecanisme.slides.ResetEncoders();
        LimeLight limeLight= new LimeLight(hardwareMap);
        autonomousActions.actionBuilder.mecanisme.AutoInitSample();
        PinpointDrive drive = new PinpointDrive(hardwareMap, autonomousActions.FieldMap.initialPose);
        autonomousActions.actionBuilder.mecanisme.SpecimenAutoInitConfig();
        waitForStart();

        autonomousActions.ScoreFirstSample(drive);
        autonomousActions.actionBuilder.mecanisme.intake.turret.TurretDefault();
        autonomousActions.ScoreSecondSample(drive);
        autonomousActions.ScoreThirdSample(drive);
        autonomousActions.actionBuilder.mecanisme.intake.turret.TurretAlternative();
        autonomousActions.actionBuilder.CollectSpecimen();

        autonomousActions.CollectSubmersible(drive);
        ElapsedTime BasicTimer = new ElapsedTime();
        BasicTimer.reset();
        boolean IsCollected=false;
        double x=7;
        double CollectAttempts=0;
        while (!IsCollected && opModeIsActive()) {
            BasicTimer.reset();
            while (BasicTimer.seconds() < 1) {
                if(limeLight.is_detecting()) {
                    autonomousActions.actionBuilder.mecanisme.intake.angle.AngleCallibration(AngleMovement(limeLight));
                    autonomousActions.actionBuilder.mecanisme.intake.turret.TurretCalibration(TurretMovement(limeLight));
                    autonomousActions.actionBuilder.mecanisme.extendo.ExtendoCallibration(ExtendoMovement(limeLight));

                    if (autonomousActions.actionBuilder.CollectSample()) {

                        IsCollected = true;
                    }
                }

                telemetry.update();

                if (!IsCollected) {
                    x -= 3;

                    autonomousActions.SubmersibleSearch(drive, x);
                    CollectAttempts+=1;
                }
                if (x < -6) x = 7;
            }
            if(CollectAttempts>=2)IsCollected=true;
        }
        autonomousActions.CollectSpecimenSubmersibleCollect(drive);

        while(opModeIsActive()) {
            autonomousActions.CollectSpecimen(drive);
        }
        autonomousActions.Reset(drive);

    }
}
