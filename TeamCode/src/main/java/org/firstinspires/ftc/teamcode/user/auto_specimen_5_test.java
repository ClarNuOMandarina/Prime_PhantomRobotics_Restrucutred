//package org.firstinspires.ftc.teamcode.user;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.ProfileAccelConstraint;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.SleepAction;
//import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
//import com.acmerobotics.roadrunner.TrajectoryBuilder;
//import com.acmerobotics.roadrunner.TranslationalVelConstraint;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.PinpointDrive;
//import org.firstinspires.ftc.teamcode.user.colection;
//import org.firstinspires.ftc.teamcode.user.extension;
//import org.firstinspires.ftc.teamcode.user.scoring;
//import org.firstinspires.ftc.teamcode.user.slides;
//
//@Autonomous(name="auto 5 spec test")
//public class auto_specimen_5_test extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        Pose2d initialPose = new Pose2d(new Vector2d(8,-60), Math.toRadians(90));
//
//
//        PinpointDrive drive = new PinpointDrive(hardwareMap,initialPose);
//        colection colection = new colection(hardwareMap);
//        extension extension = new extension(hardwareMap);
//        scoring scoring = new scoring(hardwareMap);
//        slides slides = new slides(hardwareMap);
//        slides.slide_init();
//        colection.init_config();
//        extension.extend(extension.extension_retracted);
//        scoring.init_config();
//        scoring.grip_transfer_grab();
//        slides.reset_encoder();
//        TrajectoryActionBuilder start_to_score = drive.actionBuilder(initialPose)
//                .afterTime(0,slides.specimen_score_high())
//                .afterTime(0.7,slides.specimen_score_high())
//                .afterTime(0.2,slides.specimen_score_high())
//                .afterTime(1,slides.specimen_score_high())
//                .afterTime(1,scoring.gripper_grab_max())
//
//                .strafeTo(new Vector2d(6,-24));
//
//        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(6,-24),Math.toRadians(90)))
//                .afterTime(0.2,slides.slide_init())
//                .afterTime(0.2,scoring.gripper_release())
//                .afterTime(0.5,scoring.specimen_collect())
//                .afterTime(0.8,slides.slide_init())
//                .strafeToLinearHeading(new Vector2d(22,-39),Math.toRadians(0))
//                .strafeToLinearHeading(new Vector2d(50,-8),Math.toRadians(75))
//                //    .splineToConstantHeading(new Vector2d(50,-14), Math.PI/8)
//                .strafeToLinearHeading(new Vector2d(52,-50),Math.toRadians(90));
//
//        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(52,-50),Math.toRadians(90)))
//                .afterTime(0,scoring.sample_collect())
//                .splineToConstantHeading(new Vector2d(62,-14),Math.PI/8)
//                .strafeToLinearHeading(new Vector2d(61,-50),Math.toRadians(90));
//
//        TrajectoryActionBuilder transfer_sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(61,-50),Math.toRadians(90)))
//                .afterTime(0, scoring.specimen_first_collect())
//                .splineToConstantHeading(new Vector2d(66.5,-14),Math.PI/8);
//
//
//        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(66.5,-14),Math.toRadians(90)))
//                .strafeToConstantHeading(new Vector2d(65,-58));
//
//
//        TrajectoryActionBuilder scoring_poz_pre = drive.actionBuilder(new Pose2d(new Vector2d(65,-58),Math.toRadians(90)))
////                .strafeToConstantHeading(new Vector2d(10,-40))
//                .afterTime(0,colection.rotation_observation())
//                .afterTime(0,scoring.specimen_prepare())
//                .afterTime(0,slides.specimen_score_high())
//                .afterTime(0.7,slides.specimen_score_high())
//
//                .afterTime(0.2,slides.specimen_score_high())
//            .afterTime(1,scoring.gripper_grab_max())
//                .afterTime(1,slides.specimen_score_high())
//                .strafeToConstantHeading(new Vector2d(0,-25));
//
//        TrajectoryActionBuilder specimen_collect_pre_cicling = drive.actionBuilder(new Pose2d(new Vector2d(4,-25),Math.toRadians(90)))
//                .afterTime(0.2,scoring.gripper_release())
//                .afterTime(0.4,scoring.specimen_collect())
//                .afterTime(0.4,slides.slide_init())
//                .strafeToConstantHeading(new Vector2d(44, -61));
////        TrajectoryActionBuilder specimen_dragging = drive.actionBuilder(new Pose2d(new Vector2d(4,-25),Math.toRadians(90)))
////                .strafeToConstantHeading(new Vector2d(0,-25));
//
//
//        TrajectoryActionBuilder scoring_poz = drive.actionBuilder(new Pose2d(new Vector2d(44,-61),Math.toRadians(90)))
//                .afterTime(0,slides.specimen_score_high())
//                .afterTime(0,scoring.specimen_prepare())
//
//                .afterTime(0.7,slides.specimen_score_high())
//                .afterTime(0.2,slides.specimen_score_high())
//                .afterTime(1,slides.specimen_score_high())
//                .afterTime(2,scoring.gripper_grab_max())
//
//                .strafeToConstantHeading(new Vector2d(4,-25));
//
//
//
//
//        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(4,-25),Math.toRadians(90)))
//
//                .afterTime(0.3,scoring.gripper_release())
//                .afterTime(0.5,scoring.auto_End())
//                .afterTime(1,slides.slide_init())
//                .strafeToLinearHeading(new Vector2d(45,-60),Math.toRadians(90));
//
//        scoring.gripper(scoring.gripper_hold);
//        colection.gripper.setPosition(colection.gripper_release);
//        waitForStart();
//        slides.culisante(slides.slides_specimen_high_score);
//        scoring.scoring_arm_specimen_prepare();
////        scoring.scoring_arm_specimen_prepare();
//        scoring.grip_transfer.setPosition(scoring.gripper_hold);
//        Actions.runBlocking(
//                new SequentialAction(
//                        start_to_score.build(),
//                        scoring.specimen_score()
//                ));
//
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_1.build(),
//                        colection.rotation_default(),
//                        transfer_sample_2.build(),
//                        transfer_sample_3.build(),
//                        specimen_collect_pre.build()
//                        // specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(200);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre.build(),
//                        scoring.specimen_score()
//
//                ));
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
////                        specimen_dragging.build(),
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect()
//
//                        //    specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(200);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build(),
//                        scoring.specimen_score()
//
//                ));
//
//        sleep(100);
//
//        Actions.runBlocking(
//                new SequentialAction(
////                        specimen_dragging.build(),
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect()
//
//                        //    specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(200);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build(),
//                        scoring.specimen_score()
//
//                ));
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
////                        specimen_dragging.build(),
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect()
//
//                        //   specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(200);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build(),
//                        scoring.specimen_score()
//
//                ));
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
//                        parking.build()
//                ));
//
//
//        drive.updatePoseEstimate();
//        telemetry.addData("pose",drive.pose);
//        telemetry.update();
//    }
//}