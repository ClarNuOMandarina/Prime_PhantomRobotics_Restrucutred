//package org.firstinspires.ftc.teamcode.user;
//
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.ParallelAction;
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
//
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//
//@Autonomous(name="auto_test_4_specimen")
//public class auto_test_4_sample extends LinearOpMode {
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        Pose2d initialPose = new Pose2d(new Vector2d(7.2,-62), Math.toRadians(-90));
//
//
//        MecanumDrive drive = new MecanumDrive(hardwareMap,initialPose);
//        colection colection = new colection(hardwareMap);
//        extension extension = new extension(hardwareMap);
//        scoring scoring = new scoring(hardwareMap);
//        slides slides = new slides(hardwareMap);
//        slides.slide_init();
//        colection.init_config();
//        extension.extend(extension.extension_retracted);
//        scoring.scoring_arm_score_specimen_score();
//        scoring.gripper_grab();
//        TrajectoryActionBuilder start_to_score = drive.actionBuilder(initialPose)
//                .lineToY(-36);
//        TrajectoryActionBuilder score_to_transfer = drive.actionBuilder(new Pose2d(new Vector2d(7.2,-36),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(32,-37));
//        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(32,-37),Math.toRadians(-90)))
//                .splineToConstantHeading(new Vector2d(45,-12.5),Math.toRadians(-90));
//        TrajectoryActionBuilder transfer_sample_1_finish = drive.actionBuilder(new Pose2d(new Vector2d(45,-12.5),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(56,-45));
////        TrajectoryActionBuilder transfer_sample_2_pre = drive.actionBuilder(new Pose2d(new Vector2d(49,-52),Math.toRadians(-90)))
////                .strafeTo(new Vector2d(54,-14));
//
//
//        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(52,-52),Math.toRadians(-90)))
//                .splineToConstantHeading(new Vector2d(50,-12),Math.toRadians(-90))
//                .splineToConstantHeading(new Vector2d(55,-12),Math.toRadians(-90));
//        TrajectoryActionBuilder transfer_sample_2_finish = drive.actionBuilder(new Pose2d(new Vector2d(53,-12),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(64,-50));
//
////        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(55.5,-45),Math.toRadians(-90)))
////                .strafeToLinearHeading(new Vector2d(38,-45),Math.toRadians(84));
//        TrajectoryActionBuilder specimen_collect = drive.actionBuilder(new Pose2d(new Vector2d(0,-38),Math.toRadians(-90)))
//                .afterTime(0.3,slides.slide_init())
//                .afterTime(0.3,scoring.specimen_collect())
//                .strafeToSplineHeading(new Vector2d(38,-50),Math.toRadians(84))
//                .strafeToSplineHeading(new Vector2d(38,-62),Math.toRadians(84));
//        TrajectoryActionBuilder specimen_collect_pre_cicling= drive.actionBuilder(new Pose2d(new Vector2d(64,-45),Math.toRadians(-90)))
//
//                .strafeToSplineHeading(new Vector2d(38,-50),Math.toRadians(84))
//                .strafeToSplineHeading(new Vector2d(38,-62),Math.toRadians(84));
//
//
//        TrajectoryActionBuilder scoring_poz = drive.actionBuilder(new Pose2d(new Vector2d(38,-62),Math.toRadians(84)))
//                .afterTime(0.2,scoring.specimen_score())
//                .strafeToLinearHeading(new Vector2d(0,-38),Math.toRadians(-98))
//                .lineToY(-32);
//        TrajectoryActionBuilder scoring_poz_2 = drive.actionBuilder(new Pose2d(new Vector2d(38,-62),Math.toRadians(84)))
//                .afterTime(0.2,scoring.specimen_score())
//                .strafeToLinearHeading(new Vector2d(3,-38),Math.toRadians(-98))
//                .lineToY(-32);
//
//        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(0,-35.5),Math.toRadians(-90)))
//                .strafeToLinearHeading(new Vector2d(38,-58),Math.toRadians(-55));
//
//        scoring.gripper(scoring.gripper_hold);
//
//        waitForStart();
//        if (isStopRequested()) return;
//        scoring.specimen_score();
//        slides.culisante(slides.slides_specimen_high);
//        Actions.runBlocking(
//                new SequentialAction(
//                        start_to_score.build()
//                ));
//        slides.culisante(slides.slides_specimen_high_score);
//        sleep(600);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        slides.culisante(slides.slides_init);
//        Actions.runBlocking(
//                new SequentialAction(
//                        score_to_transfer.build(),
//                        transfer_sample_1.build(),
//                        transfer_sample_1_finish.build(),
//                        scoring.specimen_collect(),
//                        transfer_sample_2.build(),
//                        transfer_sample_2_finish.build(),
//                        specimen_collect_pre_cicling.build()
//                ));
//        sleep(200);
//        scoring.gripper(scoring.gripper_hold);
//        sleep(500);
//        slides.culisante(slides.slides_specimen_high+300);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build()
//                ));
//        telemetry.addData("CULI",slides.left_slide.getCurrentPosition());
//        telemetry.addData("CULI2",slides.right_slide.getCurrentPosition());
//        telemetry.addData("extend",extension.left_extension.getPosition());
//        telemetry.update();
//        slides.culisante(slides.slides_specimen_high_score);
//        sleep(400);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//
//        // ciclu 2
//
//
//
//
////        slides.culisante(slides.slides_init);
//       // scoring.scoring_arm_score_specimen_collect();
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect.build()
//
//                ));
//        sleep(200);
//        scoring.gripper(scoring.gripper_hold);
//        sleep(500);
//        slides.culisante(slides.slides_specimen_high+300);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_2.build()
//                ));
//        telemetry.addData("CULI",slides.left_slide.getCurrentPosition());
//        telemetry.addData("CULI2",slides.right_slide.getCurrentPosition());
//        telemetry.addData("extend",extension.left_extension.getPosition());
//        telemetry.update();
//        slides.culisante(slides.slides_specimen_high_score);
//        sleep(400);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        parking.build()
//                ));
//        while(!isStopRequested()){
//            scoring.scoring_arm_auto_init_end();
//            scoring.grip_transfer_release();
//            slides.culisante(slides.slides_init);
//            colection.init_config();
//        }
////        Actions.runBlocking(
////                new SequentialAction(
////                        start_to_score.build(),
////                        scoring.gripper_release(),
////                        slides.slide_init(),
////                        scoring.specimen_collect(),
////
////
////                        score_to_transfer.build(),
////                        transfer_sample_1.build(),
////                        transfer_sample_1_finish.build(),
////                        transfer_sample_2_pre.build(),
////                        transfer_sample_2.build(),
////                        transfer_sample_2_finish.build(),
////
////                        //first cycle
////
////                        specimen_collect.build(),
////                        scoring.gripper_grab(),
////                        slides.specimen_score(),
////
////                        scoring_poz.build(),
////                        scoring.gripper_release(),
////                        slides.slide_init(),
////                        scoring.specimen_collect(),
////
////
////                        specimen_collect.build(),
////                        scoring.gripper_grab(),
////                        slides.specimen_score(),
////
////                        scoring_poz.build(),
////                        scoring.gripper_release(),
////                        slides.slide_init(),
////                        scoring.init_config_auto(),
////
////                        parking.build()
////                ));
//
//        telemetry.update();
//    }
//}
