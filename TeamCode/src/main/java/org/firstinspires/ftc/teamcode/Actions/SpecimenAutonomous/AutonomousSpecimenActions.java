package org.firstinspires.ftc.teamcode.Actions.SpecimenAutonomous;

import static java.lang.Thread.sleep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Actions.SampleAutonomous.AutonomousSampleActionBuilder;
import org.firstinspires.ftc.teamcode.FieldMap.SampleFieldMap;
import org.firstinspires.ftc.teamcode.FieldMap.SpecimenFieldMap;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.lang.reflect.Field;

public class AutonomousSpecimenActions {
    public SpecimenFieldMap FieldMap;
    private Gamepad gamepad;
    public  AutonomousSpecimenActionBuilder actionBuilder;
    public AutonomousSpecimenActions(HardwareMap hardwareMap, Gamepad gamepad){
        this.gamepad=gamepad;
        FieldMap= new SpecimenFieldMap();
        actionBuilder=new AutonomousSpecimenActionBuilder(hardwareMap);
    }

    public void ScoreFirstSample(MecanumDrive drive){
        drive.updatePoseEstimate();

        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .strafeToConstantHeading(
                        FieldMap.CollectFirstSample.position,
                        null,
                        new ProfileAccelConstraint(-90.0, 140)
        )

                .strafeToConstantHeading(
                        FieldMap.ScoreFirstSample.position
                        ,
                        null,
                        new ProfileAccelConstraint(-90.0, 140)
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void ScoreSecondSample(MecanumDrive drive){
        drive.updatePoseEstimate();

        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .splineToConstantHeading(
                        FieldMap.CollectSecondSample.position,
                        Math.PI/8
                        ,
                        null,
                        new ProfileAccelConstraint(-90.0, 140)
                )
                .strafeToConstantHeading(
                        FieldMap.ScoreSecondSample.position
                        ,
                        null,
                        new ProfileAccelConstraint(-90.0, 140)
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void ScoreThirdSample(MecanumDrive drive) {
        drive.updatePoseEstimate();

        TrajectoryActionBuilder Traj = drive.actionBuilder(drive.pose)
                .afterTime(0, actionBuilder.CollectFirstSpecimenAction())
                .splineToConstantHeading(
                        FieldMap.CollectThirdSample.position,
                        Math.PI/8
                        ,
                        null,
                        new ProfileAccelConstraint(-90.0, 140)
                )
                .strafeToConstantHeading(
                        FieldMap.ScoreThirdSample.position
                        ,
                        null,
                        new ProfileAccelConstraint(-90.0, 140)
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }

    public void CollectSpecimen(MecanumDrive drive) throws InterruptedException {
        drive.updatePoseEstimate();

        TrajectoryActionBuilder Traj = drive.actionBuilder(drive.pose)
                .afterTime(0.05, actionBuilder.CollectSampleConfig())
                .setReversed(true)
                .splineToConstantHeading(
                        FieldMap.CollectSpecimen.position,
                        -Math.PI/4
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
        sleep(100);
        actionBuilder.mecanisme.outtake.gripper.SemiClosedGripper();
        actionBuilder.mecanisme.intake.gripper.OpenGripper();
        sleep(100);

        TrajectoryActionBuilder Traj2 = drive.actionBuilder(drive.pose)
                .afterTime(0, actionBuilder.ScoreSpecimenFirstSeqAction())
                .afterTime(0.2, actionBuilder.ScoreSpecimenSecondAction())
                .strafeToLinearHeading(
                        FieldMap.ScoreSpecimen.position,
                        FieldMap.ScoreSpecimen.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj2.build()
                ));
    }
    public void CollectSpecimenSubmersibleCollect(MecanumDrive drive) throws InterruptedException {
        drive.updatePoseEstimate();
        TrajectoryActionBuilder Traj = drive.actionBuilder(drive.pose)
                .afterTime(0.4, actionBuilder.CollectSpecimenConfigAction())
                .setReversed(true)
                .splineToConstantHeading(
                        FieldMap.CollectSpecimenFirstCycle.position,
                        -Math.PI/4
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
        sleep(100);
        actionBuilder.mecanisme.outtake.gripper.SemiClosedGripper();
        actionBuilder .mecanisme.intake.gripper.OpenGripper();
        sleep(100);
        TrajectoryActionBuilder Traj2 = drive.actionBuilder(drive.pose)
                .afterTime(0, actionBuilder.ScoreSpecimenFirstSeqAction())
                .afterTime(0.2, actionBuilder.ScoreSpecimenSecondAction())
                .strafeToLinearHeading(
                        FieldMap.ScoreSpecimen.position,
                        FieldMap.ScoreSpecimen.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj2.build()
                ));
    }
    public void CollectSubmersible(MecanumDrive drive) throws InterruptedException {
        drive.updatePoseEstimate();

        TrajectoryActionBuilder Traj = drive.actionBuilder(drive.pose)
                .afterTime(0, actionBuilder.ScoreSpecimenFirstSeqAction())
                .afterTime(0.2, actionBuilder.ScoreSpecimenSecondAction())
                .strafeToLinearHeading(
                        FieldMap.ScoreSpecimen.position,
                        FieldMap.ScoreSpecimen.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build(),
                        actionBuilder.mecanisme.outtake.gripper.OpenGripperAction()
                ));

        TrajectoryActionBuilder Traj2 = drive.actionBuilder(drive.pose)
                .afterTime(0, actionBuilder.CollectSpecimenConfigAction())
                .strafeToLinearHeading(
                        FieldMap.CollectSubmersible.position,
                        FieldMap.CollectSubmersible.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj2.build()
                ));
        actionBuilder.mecanisme.SpecimenCollectConfig();
    }

    public void SubmersibleSearch(MecanumDrive drive, double x) throws InterruptedException {
        drive.updatePoseEstimate();

        Pose2d SearchSubmersibleSample = (new Pose2d(new Vector2d(x, -29), Math.toRadians(90)));

        TrajectoryActionBuilder SearchSubmersibleSampleTraj = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(
                        SearchSubmersibleSample.position,
                        SearchSubmersibleSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        SearchSubmersibleSampleTraj.build()
                ));
    }
        public void Reset(MecanumDrive drive) throws InterruptedException {

            TrajectoryActionBuilder SearchSubmersibleSampleTraj = drive.actionBuilder(drive.pose)
                    .strafeToLinearHeading(
                            FieldMap.initialPose.position,
                            FieldMap.initialPose.heading
                    );
            actionBuilder.mecanisme.InitConfig();
            Actions.runBlocking(
                    new SequentialAction(
                            SearchSubmersibleSampleTraj.build()
                    ));

        }


}
