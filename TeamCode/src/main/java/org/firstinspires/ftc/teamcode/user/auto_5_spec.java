package org.firstinspires.ftc.teamcode.user;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.user.colection;
import org.firstinspires.ftc.teamcode.user.extension;
import org.firstinspires.ftc.teamcode.user.scoring;
import org.firstinspires.ftc.teamcode.user.slides;

@Autonomous(name="auto 5 spec")
public class auto_5_spec extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(new Vector2d(8,-60), Math.toRadians(90));


        PinpointDrive drive = new PinpointDrive(hardwareMap,initialPose);
        colection colection = new colection(hardwareMap);
        extension extension = new extension(hardwareMap);
        scoring scoring = new scoring(hardwareMap);
        slides slides = new slides(hardwareMap);
        slides.slide_init();
        colection.init_config();
        extension.extend(extension.extension_retracted);
        scoring.scoring_arm_score_specimen_collect();
        scoring.gripper_grab();
        slides.reset_encoder();
        TrajectoryActionBuilder start_to_score = drive.actionBuilder(initialPose)
                .afterTime(0,slides.specimen_score_high())
                .afterTime(0,scoring.specimen_prepare())
                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .strafeTo(new Vector2d(4,-27));

        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(8,-27),Math.toRadians(90)))

                .afterTime(0,slides.auto_score())
                .afterTime(0.3,scoring.gripper_release())
                .afterTime(0.5,scoring.auto_End())
                .afterTime(0.8,slides.slide_init())
                .strafeToLinearHeading(new Vector2d(20,-40),Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(50,-6),Math.toRadians(70))
            //    .splineToConstantHeading(new Vector2d(50,-14), Math.PI/8)
                .strafeToLinearHeading(new Vector2d(55,-50),Math.toRadians(90));

        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(55,-50),Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(58,-14),Math.PI/8)
                .strafeToLinearHeading(new Vector2d(62,-50),Math.toRadians(90));

        TrajectoryActionBuilder transfer_sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(62,-50),Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(65,-14),Math.PI/8)
                .strafeToConstantHeading(new Vector2d(65,-50));


        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(65,-50),Math.toRadians(90)))
                                .strafeToLinearHeading(new Vector2d(40,-62),Math.toRadians(90));

        TrajectoryActionBuilder specimen_collect_pre_cicling = drive.actionBuilder(new Pose2d(new Vector2d(2,-27),Math.toRadians(90)))
                .afterTime(0.3,scoring.gripper_release())
                .afterTime(0.4,scoring.specimen_collect())
                .afterTime(0.4,slides.slide_init())
                .splineToConstantHeading(new Vector2d(42,-62),-Math.PI/8);

//        TrajectoryActionBuilder specimen_collect = drive.actionBuilder(new Pose2d(new Vector2d(40,-58),Math.toRadians(90)))
//                .strafeToLinearHeading(new Vector2d(40,-62),Math.toRadians(90));
        TrajectoryActionBuilder scoring_poz_pre = drive.actionBuilder(new Pose2d(new Vector2d(42,-62),Math.toRadians(90)))
                .afterTime(0,scoring.specimen_prepare())

                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0,slides.specimen_score_high())

                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .strafeToConstantHeading(new Vector2d(8,-27));



        TrajectoryActionBuilder scoring_poz_pre_2 = drive.actionBuilder(new Pose2d(new Vector2d(42,-62),Math.toRadians(90)))
                .afterTime(0,slides.specimen_score_high())
                .afterTime(0,scoring.specimen_prepare())

                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .strafeToConstantHeading(new Vector2d(2,-27));



        TrajectoryActionBuilder scoring_poz_pre_3 = drive.actionBuilder(new Pose2d(new Vector2d(42,-62),Math.toRadians(90)))
                .afterTime(0,scoring.specimen_prepare())

                .afterTime(0,slides.specimen_score_high())

                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .strafeToConstantHeading(new Vector2d(0,-27));

        TrajectoryActionBuilder scoring_poz_pre_4 = drive.actionBuilder(new Pose2d(new Vector2d(42,-62),Math.toRadians(90)))
                .afterTime(0,slides.specimen_score_high())
                .afterTime(0,scoring.specimen_prepare())

                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .strafeToConstantHeading(new Vector2d(-2,-27));




        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(0,-28),Math.toRadians(90)))

                .afterTime(0.4,scoring.gripper_release())
                .afterTime(0.5,scoring.auto_End())
                .afterTime(1,slides.slide_init())
                .afterTime(1, extension.max_extension())
                .strafeToLinearHeading(new Vector2d(20,-40),Math.toRadians(44));

        scoring.gripper(scoring.gripper_hold);
        colection.gripper.setPosition(colection.gripper_release_auto);
        waitForStart();
//        slides.culisante(slides.slides_specimen_high_score);
//        scoring.scoring_arm_specimen_prepare();
        Actions.runBlocking(
                new SequentialAction(
                        start_to_score.build(),
                        scoring.specimen_score()
                        ));
        sleep(100);
                        Actions.runBlocking(
                                new SequentialAction(
                        transfer_sample_1.build(),
                        transfer_sample_2.build(),
                        transfer_sample_3.build(),
                        scoring.specimen_collect(),
                        specimen_collect_pre.build()

                       // specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre.build(),
                        scoring.specimen_score()

                ));
        sleep(100);
        Actions.runBlocking(
                new SequentialAction(
                    specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect()

                    //    specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre_2.build(),
                        scoring.specimen_score()

                ));
        sleep(100);
        Actions.runBlocking(
                new SequentialAction(
                specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect()

                    //    specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre_3.build(),
                        scoring.specimen_score()

                ));
        sleep(100);
        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect()

                     //   specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre_4.build(),
                        scoring.specimen_score()

                ));
        sleep(100);
        Actions.runBlocking(
                new SequentialAction(
                        parking.build()
                ));


        drive.updatePoseEstimate();
        telemetry.addData("pose",drive.pose);
        telemetry.update();
    }
}