package org.firstinspires.ftc.teamcode.Actions.SpecimenAutonomous;

import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
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

//    public void PreloadScore(MecanumDrive drive){
//        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
//                .afterTime(0,actionBuilder.ScoreSpecimenAction())
//                .strafeToLinearHeading(
//                        FieldMap.ScorePreload.position,
//                        FieldMap.ScorePreload.heading
//                );
//        Actions.runBlocking(
//                new SequentialAction(
//                        Traj.build()
//                ));
//    }
//    public void IntermediaryPosition(MecanumDrive drive){
//        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
//                .afterTime(0,actionBuilder.CollectSpecimenAction())
//                .strafeToLinearHeading(
//                        FieldMap.IntermediaryPosition.position,
//                        FieldMap.IntermediaryPosition.heading
//                );
//        Actions.runBlocking(
//                new SequentialAction(
//                        Traj.build()
//                ));
//    }
    public void ScoreFirstSample(MecanumDrive drive){
        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .strafeToConstantHeading(
                        FieldMap.CollectFirstSample.position
                )
                .strafeToConstantHeading(
                        FieldMap.ScoreFirstSample.position
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void ScoreSecondSample(MecanumDrive drive){
        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .splineToConstantHeading(
                        FieldMap.CollectSecondSample.position,
                        Math.PI/8
                )
                .strafeToConstantHeading(
                        FieldMap.ScoreSecondSample.position
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void ScoreThirdSample(MecanumDrive drive) {
        TrajectoryActionBuilder Traj = drive.actionBuilder(drive.pose)
                .afterTime(0, actionBuilder.CollectFirstSpecimenAction())
                .splineToConstantHeading(
                        FieldMap.CollectThirdSample.position,
                        Math.PI/8
                )
                .strafeToConstantHeading(
                        FieldMap.ScoreThirdSample.position
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void ScoreSpecimen(MecanumDrive drive) {
        TrajectoryActionBuilder Traj = drive.actionBuilder(drive.pose)
                .afterTime(0.4, actionBuilder.ScoreSpecimenAction())
                .strafeToLinearHeading(
                        FieldMap.ScoreSpecimen.position,
                        FieldMap.ScoreSpecimen.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void CollectSpecimen(MecanumDrive drive) {
        TrajectoryActionBuilder Traj = drive.actionBuilder(drive.pose)
                .afterTime(0, actionBuilder.CollectSpecimenConfigAction())
                .strafeToLinearHeading(
                        FieldMap.CollectSpecimen.position,
                        FieldMap.CollectSpecimen.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
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
