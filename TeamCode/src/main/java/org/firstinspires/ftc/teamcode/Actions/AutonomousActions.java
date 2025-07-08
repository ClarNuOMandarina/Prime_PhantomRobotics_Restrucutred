package org.firstinspires.ftc.teamcode.Actions;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.FieldMap.SampleFieldMap;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;

public class AutonomousActions {
    public SampleFieldMap sampleFieldMap;
    private Gamepad gamepad;
    public Mecanisme mecanisme;
    public LimeLight limeLight;
    public AutonomousActions(HardwareMap hardwareMap, Gamepad gamepad){
        this.gamepad=gamepad;
        this.mecanisme= new Mecanisme(hardwareMap);
        sampleFieldMap= new SampleFieldMap();
        this.limeLight= new LimeLight(hardwareMap);
    }

    public class sampleCollectConfig  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.SampleCollectConfig();
            mecanisme.extendo.Extend();
            mecanisme.slides.Transfer();
           return false;
        }

    }
    public void LimeLightCollect(boolean opModeIsActive) throws InterruptedException {
        while(opModeIsActive) {
            sleep(200);
            if(limeLight.is_detecting()){
            mecanisme.extendo.ExtendoCallibration(limeLight.ExtendoMovement());
            mecanisme.intake.turret.TurretCalibration(limeLight.TurretMovement());
            }
        }
    }
    public Action SampleCollectConfig(){
        return new sampleCollectConfig();
    }
    public class sampleScoreConfig  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.AutoSampleScoreConfig();
            mecanisme.slides.HighBasket();
           return false;
        }

    }
    public Action SampleScoreConfig(){
        return new sampleScoreConfig();
    }

    public class collectThirdSample  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
           mecanisme.AutoThirdSampleConfig();
            return false;
        }

    }
    public Action CollectThirdSample(){
        return new collectThirdSample();
    }
    public class sampleCollectSubmersibleConfig  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.SampleCollectConfig();
            mecanisme.slides.Transfer();
            return false;
        }

    }
    public Action SampleCollectSubmersibleConfig(){
        return new sampleCollectSubmersibleConfig();
    }

    public void PreloadScore(MecanumDrive drive){
        TrajectoryActionBuilder PreloadScore = drive.actionBuilder( sampleFieldMap.initialPose)
                .afterTime(0.2,SampleScoreConfig())
                .afterTime(0.4,mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.6,mecanisme.outtake.extendo.BasketScore())
                .strafeToLinearHeading(
                        sampleFieldMap.PreloadScorePosition.position,
                        sampleFieldMap.PreloadScorePosition.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        PreloadScore.build()
                ));
    }
    public void CollectFirstSample(MecanumDrive drive){
        TrajectoryActionBuilder CollectFirstSample = drive.actionBuilder( sampleFieldMap.PreloadScorePosition)
                .afterTime(0.1,SampleCollectConfig())
                .strafeToLinearHeading(
                        sampleFieldMap.FirstSample.position,
                        sampleFieldMap.FirstSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        CollectFirstSample.build()
                ));
    }
public void ScoreFirstSample(MecanumDrive drive){
        TrajectoryActionBuilder ScoreFirstSample = drive.actionBuilder( sampleFieldMap.FirstSample)
                .afterTime(0,mecanisme.outtake.gripper.CloseGripperAction())
                .afterTime(0.2,mecanisme.intake.gripper.OpenGripperAction())
                .afterTime(0.3,SampleScoreConfig())
                .afterTime(0.9,mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.8,mecanisme.outtake.extendo.BasketScore())

                .strafeToLinearHeading(
                        sampleFieldMap.FirstSampleScore.position,
                        sampleFieldMap.FirstSampleScore.heading
                );
    Actions.runBlocking(
            new SequentialAction(
                    ScoreFirstSample.build()
            ));
    }
    public void CollectSecondSample(MecanumDrive drive){
        TrajectoryActionBuilder CollectSecondSample = drive.actionBuilder( sampleFieldMap.FirstSampleScore)
                .afterTime(0.1,SampleCollectConfig())
                .strafeToLinearHeading(
                        sampleFieldMap.SecondSample.position,
                        sampleFieldMap.SecondSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        CollectSecondSample.build()
                ));
    }
public void ScoreSecondSample(MecanumDrive drive){
        TrajectoryActionBuilder ScoreSecondSample = drive.actionBuilder( sampleFieldMap.SecondSample)
                .afterTime(0,mecanisme.outtake.gripper.CloseGripperAction())
                .afterTime(0.2,mecanisme.intake.gripper.OpenGripperAction())
                .afterTime(0.3,SampleScoreConfig())
                .afterTime(0.9,mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.8,mecanisme.outtake.extendo.BasketScore())
                .strafeToLinearHeading(
                        sampleFieldMap.SecondSampleScore.position,
                        sampleFieldMap.SecondSampleScore.heading
                );
    Actions.runBlocking(
            new SequentialAction(
                    ScoreSecondSample.build()
            ));
    }
    public void CollectThirdSample(MecanumDrive drive){
        TrajectoryActionBuilder CollectThirdSample = drive.actionBuilder( sampleFieldMap.SecondSampleScore)
                .afterTime(0.1,CollectThirdSample())
                .strafeToLinearHeading(
                        sampleFieldMap.ThirdSample.position,
                        sampleFieldMap.ThirdSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        CollectThirdSample.build()
                ));
    }
public void ScoreThirdSample(MecanumDrive drive){
        TrajectoryActionBuilder ScoreThirdSample = drive.actionBuilder( sampleFieldMap.ThirdSample)
                .afterTime(0,mecanisme.outtake.gripper.CloseGripperAction())
                .afterTime(0.2,mecanisme.intake.gripper.OpenGripperAction())
                .afterTime(0.3,SampleScoreConfig())
                .afterTime(0.9,mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.8,mecanisme.outtake.extendo.BasketScore())

                .strafeToLinearHeading(
                        sampleFieldMap.ThirdSampleScore.position,
                        sampleFieldMap.ThirdSample.heading
                );
    Actions.runBlocking(
            new SequentialAction(
                    ScoreThirdSample.build()
            ));
    }
    public void SubmersibleCollect(MecanumDrive drive){
        TrajectoryActionBuilder SubmersbleCollect = drive.actionBuilder( sampleFieldMap.ThirdSampleScore)
                .afterTime(0.1,SampleCollectSubmersibleConfig())
                .splineToLinearHeading(
                        sampleFieldMap.SubmersibleSampleCollect,
                        Math.toRadians(0)
                );
    Actions.runBlocking(
            new SequentialAction(
                    SubmersbleCollect.build()
            ));
    }



    public void CollectSample() throws InterruptedException {
        sleep(200);
        mecanisme.intake.height.HeightCollecting();
        sleep(200);
        mecanisme.intake.gripper.ClosedGripper();
        sleep(200);
        mecanisme.intake.angle.HorizontalAngle();
        mecanisme.intake.turret.TurretDefault();
        mecanisme.intake.height.HeightTransfer();
        sleep(300);
        mecanisme.extendo.Transfer();
        sleep(200);
    }


}
