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

    public void PreloadScore(MecanumDrive drive){
        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .afterTime(0,actionBuilder.ScoreSpecimenAction())
                .strafeToLinearHeading(
                        FieldMap.ScorePreload.position,
                        FieldMap.ScorePreload.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void IntermediaryPosition(MecanumDrive drive){
        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .afterTime(0,actionBuilder.CollectSpecimenAction())
                .strafeToLinearHeading(
                        FieldMap.IntermediaryPosition.position,
                        FieldMap.IntermediaryPosition.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void ScoreFirstSample(MecanumDrive drive){
        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .strafeToLinearHeading(
                        FieldMap.CollectFirstSample.position,
                        FieldMap.CollectFirstSample.heading
                )
                .strafeToLinearHeading(
                        FieldMap.ScoreFirstSample.position,
                        FieldMap.ScoreFirstSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void ScoreSecondSample(MecanumDrive drive){
        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .strafeToLinearHeading(
                        FieldMap.CollectSecondSample.position,
                        FieldMap.CollectSecondSample.heading
                )
                .strafeToLinearHeading(
                        FieldMap.ScoreSecondSample.position,
                        FieldMap.ScoreSecondSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
    public void ScoreThirdSample(MecanumDrive drive){
        TrajectoryActionBuilder Traj = drive.actionBuilder( drive.pose)
                .afterTime(0, actionBuilder.CollectFirstSpecimenAction())
                .strafeToLinearHeading(
                        FieldMap.CollectThirdSample.position,
                        FieldMap.CollectThirdSample.heading
                )
                .strafeToLinearHeading(
                        FieldMap.ScoreThirdSample.position,
                        FieldMap.ScoreThirdSample.heading
                );
        Actions.runBlocking(
                new SequentialAction(
                        Traj.build()
                ));
    }
}
