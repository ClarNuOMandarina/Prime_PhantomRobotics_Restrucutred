package org.firstinspires.ftc.teamcode.user;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
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

@Autonomous(name="auto 5 spec push")
public class auto_5_spec_push extends LinearOpMode {


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

                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .strafeTo(new Vector2d(4,-27))
                .afterTime(0,slides.auto_score());

        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(8,-27),Math.toRadians(90)))

                .afterTime(0,slides.auto_score())
                .afterTime(0.2,scoring.gripper_release())
                .afterTime(0.5,scoring.auto_End())
                .afterTime(0.8,slides.slide_init())
                .strafeToLinearHeading(new Vector2d(10,-40),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(40,-10),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(55,-55),Math.toRadians(90));

        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(55,-55),Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(50,-10),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(57,-55),Math.toRadians(90));

        TrajectoryActionBuilder transfer_sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(55,-55),Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(57,-10),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(60,-55),Math.toRadians(90));
        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(60,-55),Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(60,-55),Math.toRadians(90));
        TrajectoryActionBuilder specimen_collect_pre2 = drive.actionBuilder(new Pose2d(new Vector2d(60,-56),Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(60,-62),Math.toRadians(90));

        TrajectoryActionBuilder specimen_collect_pre_cicling = drive.actionBuilder(new Pose2d(new Vector2d(2,-27),Math.toRadians(90)))
                .afterTime(0.4,scoring.gripper_release())
                .afterTime(1,slides.slide_init())
                .strafeToLinearHeading(new Vector2d(40,-58),Math.toRadians(90));
        TrajectoryActionBuilder specimen_collect = drive.actionBuilder(new Pose2d(new Vector2d(40,-58),Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(40,-62),Math.toRadians(90));

        TrajectoryActionBuilder scoring_poz_pre = drive.actionBuilder(new Pose2d(new Vector2d(60,-62),Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(40,-50),Math.toRadians(90))

                .afterTime(0.1,slides.specimen_score_high())
                .afterTime(0.5,slides.specimen_score_high())
                .strafeToLinearHeading(new Vector2d(6,-28),Math.toRadians(90))
                .afterTime(0,slides.auto_score());


        TrajectoryActionBuilder scoring_poz_pre_2 = drive.actionBuilder(new Pose2d(new Vector2d(40,-62),Math.toRadians(90)))

                .afterTime(0.1,slides.specimen_score_high())
                .afterTime(0.5,slides.specimen_score_high())
                .strafeToLinearHeading(new Vector2d(4,-28),Math.toRadians(90))
                .afterTime(0,slides.auto_score());


        TrajectoryActionBuilder scoring_poz_pre_3 = drive.actionBuilder(new Pose2d(new Vector2d(40,-62),Math.toRadians(90)))


                .afterTime(0.1,slides.specimen_score_high())
                .afterTime(0.5,slides.specimen_score_high())
                .strafeToLinearHeading(new Vector2d(2,-28),Math.toRadians(90))
                .afterTime(0,slides.auto_score());

        TrajectoryActionBuilder scoring_poz_pre_4 = drive.actionBuilder(new Pose2d(new Vector2d(40,62),Math.toRadians(90)))

                .afterTime(0.1,slides.specimen_score_high())
                .afterTime(0.5,slides.specimen_score_high())
                .strafeToLinearHeading(new Vector2d(0,-28),Math.toRadians(90))
                .afterTime(0,slides.auto_score());




        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(0,-28),Math.toRadians(90)))

                .afterTime(0.4,scoring.gripper_release())
                .afterTime(0.5,scoring.auto_End())
                .afterTime(1,slides.slide_init())
                .afterTime(0, extension.max_extension())
                .strafeToLinearHeading(new Vector2d(-40,-100),Math.toRadians(90));

        scoring.gripper(scoring.gripper_hold);
        colection.gripper.setPosition(colection.gripper_release_auto);
        waitForStart();
        slides.culisante(slides.slides_specimen_high_score);
        scoring.scoring_arm_specimen_score_auto();
        Actions.runBlocking(
                new SequentialAction(
                        start_to_score.build(),
                        transfer_sample_1.build(),
                        transfer_sample_2.build(),
                        transfer_sample_3.build()
                ));

            scoring.scoring_arm_score_specimen_collect();
        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre.build(),
                        scoring.specimen_collect(),

                        specimen_collect_pre2.build()
                ));
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        slides.culisante(slides.slides_specimen_high_score);
        scoring.scoring_arm_score_specimen_score();
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre.build()
                ));

        // ciclu 2



        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect(),

                        specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        slides.culisante(slides.slides_specimen_high_score);
        scoring.scoring_arm_score_specimen_score();
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre_2.build()
                ));
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_2.build()
//                ));

// ciclu 3

        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect(),

                        specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        slides.culisante(slides.slides_specimen_high_score);
        scoring.scoring_arm_score_specimen_score();
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre_3.build()
                ));
        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect(),

                        specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        slides.culisante(slides.slides_specimen_high_score);
        scoring.scoring_arm_score_specimen_score();
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre_4.build(),
                        parking.build()
                ));


        drive.updatePoseEstimate();
        telemetry.addData("pose",drive.pose);
        telemetry.update();
    }

}
