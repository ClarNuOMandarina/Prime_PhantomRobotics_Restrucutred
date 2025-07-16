package org.firstinspires.ftc.teamcode.Actions.SampleAutonomous;

import static java.lang.Thread.sleep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.FieldMap.SampleFieldMap;
import org.firstinspires.ftc.teamcode.MecanumDrive;

public class AutonomousSampleActions {
    public SampleFieldMap sampleFieldMap;
    private Gamepad gamepad;
    public AutonomousSampleActionBuilder actionBuilder;
    public AutonomousSampleActions(HardwareMap hardwareMap, Gamepad gamepad){
        this.gamepad=gamepad;
        sampleFieldMap= new SampleFieldMap();
        actionBuilder=new AutonomousSampleActionBuilder(hardwareMap);
    }


    public void PreloadScore(MecanumDrive drive){
        TrajectoryActionBuilder PreloadScore = drive.actionBuilder( drive.pose)
                .afterTime(0.1,actionBuilder.SampleScoreConfig())
                .afterTime(0.4,actionBuilder.mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.3,actionBuilder.mecanisme.outtake.extendo.BasketScore())
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
        TrajectoryActionBuilder CollectFirstSample = drive.actionBuilder(drive.pose)
                .afterTime(0,actionBuilder.SampleCollectConfig())
                .strafeToLinearHeading(
                        sampleFieldMap.CollectFirstSample.position,
                        sampleFieldMap.CollectFirstSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        CollectFirstSample.build()
                ));
    }
public void ScoreFirstSample(MecanumDrive drive){
        TrajectoryActionBuilder ScoreFirstSample = drive.actionBuilder( drive.pose)
                .afterTime(0,actionBuilder.mecanisme.outtake.gripper.CloseGripperAction())
                .afterTime(0.2,actionBuilder.mecanisme.intake.gripper.OpenGripperAction())
                .afterTime(0.3,actionBuilder.SampleScoreConfig())
                .afterTime(0.7,actionBuilder.mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.6,actionBuilder.mecanisme.outtake.extendo.BasketScore())

                .strafeToLinearHeading(
                        sampleFieldMap.ScoreFirstSample.position,
                        sampleFieldMap.ScoreFirstSample.heading
                );
    Actions.runBlocking(
            new SequentialAction(
                    ScoreFirstSample.build()
            ));
    }
    public void CollectSecondSample(MecanumDrive drive){
        TrajectoryActionBuilder CollectSecondSample = drive.actionBuilder( drive.pose)
                .afterTime(0.1,actionBuilder.SampleCollectConfig())
                .strafeToLinearHeading(
                        sampleFieldMap.CollectSecondSample.position,
                        sampleFieldMap.CollectSecondSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        CollectSecondSample.build()
                ));
    }
public void ScoreSecondSample(MecanumDrive drive){
        TrajectoryActionBuilder ScoreSecondSample = drive.actionBuilder( drive.pose)
                .afterTime(0,actionBuilder.mecanisme.outtake.gripper.CloseGripperAction())
                .afterTime(0.2,actionBuilder.mecanisme.intake.gripper.OpenGripperAction())
                .afterTime(0.3,actionBuilder.SampleScoreConfig())
                .afterTime(0.7,actionBuilder.mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.6,actionBuilder.mecanisme.outtake.extendo.BasketScore())
                .strafeToLinearHeading(
                        sampleFieldMap.ScoreSecondSample.position,
                        sampleFieldMap.ScoreSecondSample.heading
                );
    Actions.runBlocking(
            new SequentialAction(
                    ScoreSecondSample.build()
            ));
    }
    public void CollectThirdSample(MecanumDrive drive){
        TrajectoryActionBuilder CollectThirdSample = drive.actionBuilder( drive.pose)
                .afterTime(0.1,actionBuilder.CollectThirdSample())
                .strafeToLinearHeading(
                        sampleFieldMap.CollectThirdSample.position,
                        sampleFieldMap.CollectThirdSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        CollectThirdSample.build()
                ));
    }
public void ScoreThirdSample(MecanumDrive drive){
        TrajectoryActionBuilder ScoreThirdSample = drive.actionBuilder( drive.pose)
                .afterTime(0,actionBuilder.mecanisme.outtake.gripper.CloseGripperAction())
                .afterTime(0.2,actionBuilder.mecanisme.intake.gripper.OpenGripperAction())
                .afterTime(0.3,actionBuilder.SampleScoreConfig())
                .afterTime(0.7,actionBuilder.mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.6,actionBuilder.mecanisme.outtake.extendo.BasketScore())

                .strafeToLinearHeading(
                        sampleFieldMap.ScoreThirdSample.position,
                        sampleFieldMap.ScoreThirdSample.heading
                );
    Actions.runBlocking(
            new SequentialAction(
                    ScoreThirdSample.build()
            ));
    }
    public void SubmersibleCollectFirstCycle(MecanumDrive drive){
        TrajectoryActionBuilder SubmersbleCollect = drive.actionBuilder( drive.pose)
                .afterTime(0.1,actionBuilder.SampleCollectSubmersibleConfig())
                .splineToLinearHeading(
                        sampleFieldMap.CollectSubmersibleSample,
                        Math.toRadians(0)
                );
    Actions.runBlocking(
            new SequentialAction(
                    SubmersbleCollect.build()
            ));
    }
    public void SubmersibleCollect(MecanumDrive drive){
        TrajectoryActionBuilder SubmersbleCollect = drive.actionBuilder( drive.pose)
                .afterTime(0.1,actionBuilder.SampleCollectSubmersibleConfig())
                .strafeToLinearHeading(
                        sampleFieldMap.CollectSubmersibleSample.position,
                        sampleFieldMap.CollectSubmersibleSample.heading
                );
    Actions.runBlocking(
            new SequentialAction(
                    SubmersbleCollect.build()
            ));
    }

    public void SubmersibleScore(MecanumDrive drive, double y){
        TrajectoryActionBuilder SubmersibleScore = drive.actionBuilder( drive.pose)
                .afterTime(0,actionBuilder.mecanisme.outtake.gripper.CloseGripperAction())
                .afterTime(0.2,actionBuilder.mecanisme.intake.gripper.OpenGripperAction())
                .afterTime(0.5,actionBuilder.SampleScoreConfig())
                .afterTime(0.8,actionBuilder.mecanisme.outtake.arms.BasketScoreAction())
                .afterTime(0.7,actionBuilder.mecanisme.outtake.extendo.BasketScore())
                .strafeToLinearHeading(
                        sampleFieldMap.ScoreSubmersibleSample.position,
                        sampleFieldMap.ScoreSubmersibleSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        SubmersibleScore.build()
                ));
    }

    public void SubmersibleSearch(MecanumDrive drive, double y) throws InterruptedException {
        Pose2d SearchSubmersibleSample = (new Pose2d(new Vector2d(-20.5, y), Math.toRadians(0)));

        TrajectoryActionBuilder SearchSubmersibleSampleTraj = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(
                        SearchSubmersibleSample.position,
                        SearchSubmersibleSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        SearchSubmersibleSampleTraj.build()
                ));
        sleep(400);

    }
    public void Reset(MecanumDrive drive) throws InterruptedException {

        TrajectoryActionBuilder SearchSubmersibleSampleTraj = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(
                       sampleFieldMap.initialPose.position,
                        sampleFieldMap.initialPose.heading
                );
        actionBuilder.mecanisme.InitConfig();

        Actions.runBlocking(
                new SequentialAction(
                        SearchSubmersibleSampleTraj.build()
                ));

    }
}
