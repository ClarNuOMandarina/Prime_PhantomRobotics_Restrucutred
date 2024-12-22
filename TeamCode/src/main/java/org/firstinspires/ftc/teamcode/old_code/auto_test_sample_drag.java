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
//@Autonomous(name="auto_drag")
//public class auto_test_sample_drag extends LinearOpMode {
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
//                .lineToY(-38);
//        TrajectoryActionBuilder score_to_transfer = drive.actionBuilder(new Pose2d(new Vector2d(7.2,-38),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(32,-38));
//        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(31,-38),Math.toRadians(-90)))
//                .strafeToLinearHeading(new Vector2d(31,-39.1),Math.toRadians(70));
//        TrajectoryActionBuilder transfer_sample_1_finish = drive.actionBuilder(new Pose2d(new Vector2d(31,-39.1),Math.toRadians(70)))
///*
//                .afterTime(0.4, extension.max_extension())
//*/
//                .turnTo(Math.toRadians(-35));
//
//
//        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(31  ,-39.1),Math.toRadians(-35)))
//                .turnTo(Math.toRadians(47.5));
//        TrajectoryActionBuilder transfer_sample_2_inter = drive.actionBuilder(new Pose2d(new Vector2d(32  ,-39.1),Math.toRadians(47.5)))
//                .strafeTo(new Vector2d(31,-39.1));
//
//        TrajectoryActionBuilder transfer_sample_2_finish = drive.actionBuilder(new Pose2d(new Vector2d(32,-39.1),Math.toRadians(47.5)))
//                .turnTo(Math.toRadians(-32.5));
//        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(36,-42.3),Math.toRadians(-34.5)))
//                .strafeToLinearHeading(new Vector2d(38,-38),Math.toRadians(84));
//        TrajectoryActionBuilder specimen_collect = drive.actionBuilder(new Pose2d(new Vector2d(38,-45.5),Math.toRadians(84)))
//                .lineToY(-58);
////-62
//        TrajectoryActionBuilder scoring_poz_pre = drive.actionBuilder(new Pose2d(new Vector2d(38,-62),Math.toRadians(84)))
//                .strafeToSplineHeading(new Vector2d(0,-38),Math.toRadians(-93.85));
//        TrajectoryActionBuilder scoring_poz = drive.actionBuilder(new Pose2d(new Vector2d(0,-38),Math.toRadians(-93.85)))
//                .lineToY(-38);
//        TrajectoryActionBuilder scoring_poz_pre_2 = drive.actionBuilder(new Pose2d(new Vector2d(26.5,-59.5),Math.toRadians(84)))
//                .strafeToSplineHeading(new Vector2d(-3,-38),Math.toRadians(-93.85));
//        TrajectoryActionBuilder scoring_poz_2 = drive.actionBuilder(new Pose2d(new Vector2d(-3,-38),Math.toRadians(-93.85)))
//                .lineToY(-38);
//
//        TrajectoryActionBuilder scoring_poz_pre_3 = drive.actionBuilder(new Pose2d(new Vector2d(26.5,-59.5),Math.toRadians(84)))
//                .strafeToSplineHeading(new Vector2d(1,-38),Math.toRadians(-93.85));
//        TrajectoryActionBuilder scoring_poz_3 = drive.actionBuilder(new Pose2d(new Vector2d(-3,-38),Math.toRadians(-93.85)))
//                .lineToY(-38);
//        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(0,-35.5),Math.toRadians(-93.85)))
//                .afterDisp(0.1,scoring.auto_End())
//                .afterDisp(0.1,colection.auto_End())
//                .afterDisp(0.1,slides.slide_init())
//                .strafeToLinearHeading(new Vector2d(38,-58),Math.toRadians(-55));
//
//
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
//        colection.default_config();
//        slides.culisante(slides.slides_specimen_high_score);
//        sleep(700);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        slides.culisante(slides.slides_init);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_1.build()
//                ));
//        extension.extend(extension.sample_drag);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_sample_drag);
//        sleep(400);
//        colection.colection_arm(colection.colection_sample_drag);
//        sleep(500);
////        extension.extend(extension.extension_extended);
////        sleep(500);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_1_finish.build()
//
//                ));
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_2.build(),
//                        transfer_sample_2_inter.build()
//                ));
//        sleep(200);
//        extension.extend(extension.extension_extended);
//        sleep(400);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_2_finish.build()
//                ));
//        sleep(200);
//        colection.scoring_config();
//        extension.extend(extension.extension_retracted);
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect_pre.build()
//                ));
//        scoring.scoring_arm_score_specimen_collect();
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_hold);
//        sleep(700);
//        slides.culisante(slides.slides_specimen_high+300);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre.build()
//                ));
//        scoring.scoring_arm_score_specimen_score();
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
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect_pre.build()
//                ));
//        slides.culisante(slides.slides_init);
//        scoring.scoring_arm_score_specimen_collect();
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect.build()
//
//                ));
//        scoring.gripper(scoring.gripper_hold);
//        sleep(700);
//        slides.culisante(slides.slides_specimen_high+300);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre_2.build()
//                ));
//        scoring.scoring_arm_score_specimen_score();
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
//        //ciclu 3
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect_pre.build()
//                ));
//        slides.culisante(slides.slides_init);
//        scoring.scoring_arm_score_specimen_collect();
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect.build()
//
//                ));
//        scoring.gripper(scoring.gripper_hold);
//        sleep(700);
//        slides.culisante(slides.slides_specimen_high+300);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre_3.build()
//                ));
//        scoring.scoring_arm_score_specimen_score();
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_3.build()
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
//        scoring.scoring_arm_auto_init_end();
//        scoring.grip_transfer_release();
//        slides.culisante(slides.slides_init);
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
