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
//@Autonomous(name="auto 5 spec experimental")
//public class auto_5_specimen_experimental extends LinearOpMode {
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
//        slides.reset_encoder();
//        TrajectoryActionBuilder start_to_score = drive.actionBuilder(initialPose)
//                .afterTime(0.2,slides.auto_score())
//                .lineToY(-37)
//                .afterTime(0,scoring.specimen_score_2());
////        TrajectoryActionBuilder score_to_transfer = drive.actionBuilder(new Pose2d(new Vector2d(7.2,-37),Math.toRadians(-90)))
////                .afterTime(0.3,scoring.gripper_release())
////                .afterTime(0.8,scoring.auto_End())
////                .afterTime(1,slides.slide_init())
////                .strafeToLinearHeading(new Vector2d(32,-40),Math.toRadians(90));
//        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(7.2,-37),Math.toRadians(-90)))
//                .afterTime(0.8,scoring.gripper_release())
//                .afterTime(1.2,scoring.auto_End())
//                .afterTime(1,slides.slide_init())
//                .strafeToLinearHeading(new Vector2d(36,-40),Math.toRadians(20.5))
//                .strafeToLinearHeading(new Vector2d(36.7,-32),Math.toRadians(20.5));
//        TrajectoryActionBuilder transfer_sample_1_finish = drive.actionBuilder(new Pose2d(new Vector2d(51,-40),Math.toRadians(20.5)))
//                .strafeToLinearHeading(new Vector2d(42.7,-52),Math.toRadians(-40));
//
//
//
//        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(42.7,-52),Math.toRadians(-40)))
//                .strafeToLinearHeading(new Vector2d(62,-40),Math.toRadians(90));
//        TrajectoryActionBuilder transfer_sample_2_finish = drive.actionBuilder(new Pose2d(new Vector2d(62,-40),Math.toRadians(90)))
//                .strafeToLinearHeading(new Vector2d(56,-48),Math.toRadians(250));
//        TrajectoryActionBuilder transfer_sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(56,-48),Math.toRadians(250)))
//                .strafeToLinearHeading(new Vector2d(56,-23.3),Math.toRadians(0));
////        TrajectoryActionBuilder transfer_sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(57,-48),Math.toRadians(241)))
////                        .strafeToLinearHeading(new Vector2d(55.5,-26),Math.toRadians(0));
//
//        TrajectoryActionBuilder transfer_sample_3_finish = drive.actionBuilder(new Pose2d(new Vector2d(56,-25),Math.toRadians(0)))
//                .strafeToLinearHeading(new Vector2d(57,-48),Math.toRadians(241));
//
//        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(57,-48),Math.toRadians(246)))
//                .strafeToLinearHeading(new Vector2d(38,-45),Math.toRadians(84));
//        TrajectoryActionBuilder specimen_collect_pre_cicling = drive.actionBuilder(new Pose2d(new Vector2d(10,-33),Math.toRadians(-67)))
//                .afterTime(0.8,scoring.gripper_release())
//                .afterTime(1,slides.slide_init())
//                .strafeToLinearHeading(new Vector2d(40,-45),Math.toRadians(84));
//        TrajectoryActionBuilder specimen_collect = drive.actionBuilder(new Pose2d(new Vector2d(40,-45.5),Math.toRadians(84)))
//                .lineToY(-62);
//
//        TrajectoryActionBuilder scoring_poz_pre = drive.actionBuilder(new Pose2d(new Vector2d(38,-62),Math.toRadians(84)))
//                .afterTime(0.1,slides.auto_score())
//                .afterTime(0.2,scoring.specimen_prepare())
//                .strafeToLinearHeading(new Vector2d(16,-33),Math.toRadians(-71))
//                .afterTime(0,scoring.specimen_score_2());
//
//
//        TrajectoryActionBuilder scoring_poz_pre_2 = drive.actionBuilder(new Pose2d(new Vector2d(26.5,-59.5),Math.toRadians(84)))
//
//                .afterTime(0.1,slides.auto_score())
//                .afterTime(0.2,scoring.specimen_prepare())
//                .afterTime(0.4,slides.auto_score())
//                .afterTime(1.2,slides.auto_score())
//
//                .strafeToLinearHeading(new Vector2d(14,-37),Math.toRadians(-90))
//                .strafeToLinearHeading(new Vector2d(18,-35),Math.toRadians(-90))
//                .afterTime(0,scoring.specimen_score_2());
//
//        TrajectoryActionBuilder scoring_poz_pre_3 = drive.actionBuilder(new Pose2d(new Vector2d(26.5,-59.5),Math.toRadians(84)))
//                .afterTime(0.1,slides.auto_score())
//                .afterTime(0.4,slides.auto_score())
//                .afterTime(1.2,slides.auto_score())
//
//                .afterTime(0.2,scoring.specimen_prepare())
//                .strafeToLinearHeading(new Vector2d(14,-37),Math.toRadians(-90))
//                .strafeToLinearHeading(new Vector2d(18,-35),Math.toRadians(-90))
//                .afterTime(0,scoring.specimen_score_2());
//
//        TrajectoryActionBuilder scoring_poz_pre_4 = drive.actionBuilder(new Pose2d(new Vector2d(26.5,-59.5),Math.toRadians(84)))
//                .afterTime(0.1,slides.auto_score())
//                .afterTime(0.4,slides.auto_score())
//                .afterTime(1.2,slides.auto_score())
//
//                .afterTime(0.2,scoring.specimen_prepare())
//                .strafeToLinearHeading(new Vector2d(14,-37),Math.toRadians(-90))
//                .strafeToLinearHeading(new Vector2d(18,-35),Math.toRadians(-90))
//                .afterTime(0,scoring.specimen_score_2());
//
//
//        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(18,-33),Math.toRadians(-75)))
//                .afterTime(1,scoring.gripper_release())
//                .afterTime(1.4,slides.slide_init())
//                .afterTime(0.1,extension.max_extension())
//                .afterDisp(1.2,scoring.auto_End())
//                .afterDisp(0.5,colection.auto_end())
//                .afterDisp(1.2,slides.slide_init())
//                .strafeToLinearHeading(new Vector2d(26,-40),Math.toRadians(-29));
//
//        scoring.gripper(scoring.gripper_hold);
//
//        waitForStart();
//        if (isStopRequested()) return;
//        scoring.scoring_arm_specimen_prepare();
//        slides.culisante(slides.slides_auto_score);
//        Actions.runBlocking(
//                new SequentialAction(
//                        start_to_score.build()
//                ));
//        colection.colection_arm(colection.colection_default);
//        colection.default_config();
//        colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
//        Actions.runBlocking(
//                new SequentialAction(
////                        score_to_transfer.build(),
//                        transfer_sample_1.build()
//                ));
//        colection.colection_arm(colection.colection_extended_auto);
//        sleep(200);
//        colection.gripper_grab();
//        sleep(300);
//        colection.colection_arm(colection.colection_default);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_1_finish.build()
//                ));
//        colection.gripper_release();
//        sleep(200);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_2.build()
//                ));
//        colection.colection_arm(colection.colection_extended_auto);
//        sleep(200);
//        colection.gripper_grab();
//        sleep(300);
//        colection.colection_arm(colection.colection_default);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_2_finish.build()
//                ));
//        colection.gripper_release();
//        sleep(200);
//
//        colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_3.build()
//                ));
//        colection.colection_arm(colection.colection_extended_auto);
//        sleep(200);
//        colection.gripper_grab();
//        sleep(300);
//        colection.colection_arm(colection.colection_default);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_3_finish.build()
//                ));
//        colection.gripper_release();
//        sleep(200);
//
//
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect_pre.build(),
//                        scoring.specimen_collect(),
//
//                        specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_hold);
//        sleep(200);
//        slides.culisante(slides.slides_auto_score+300);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre.build()
//                ));
////        Actions.runBlocking(
////                new SequentialAction(
////                        scoring_poz.build()
////                ));
//
//        // ciclu 2
//
//
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect(),
//
//                        specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_hold);
//        sleep(200);
//        slides.culisante(slides.slides_auto_score+300);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre_2.build()
//                ));
////        Actions.runBlocking(
////                new SequentialAction(
////                        scoring_poz_2.build()
////                ));
//
//// ciclu 3
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect(),
//
//                        specimen_collect.build()
//                ));
////        sleep(200);
//        scoring.gripper(scoring.gripper_hold);
//        sleep(200);
//        slides.culisante(slides.slides_auto_score+300);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre_3.build()
//                ));
////        Actions.runBlocking(
////                new SequentialAction(
////                        scoring_poz_3.build()
////                ));
//
//
//
//        // ciclu 4
//
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect(),
//
//                        specimen_collect.build()
//                ));
////        sleep(200);
//        scoring.gripper(scoring.gripper_hold);
//        sleep(200);
//        slides.culisante(slides.slides_auto_score+300);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre_4.build()
//                ));
////        Actions.runBlocking(
////                new SequentialAction(
////                        scoring_poz_4.build()
////                ));
//
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        parking.build()
//                ));
//        while(!isStopRequested()){
//            scoring.scoring_arm_auto_init_end();
//            slides.culisante(slides.slides_init);
//            colection.init_config();
//        }
//
//
//        telemetry.update();
//    }
//}
