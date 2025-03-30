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
//
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.PinpointDrive;
//import org.firstinspires.ftc.teamcode.user.colection;
//import org.firstinspires.ftc.teamcode.user.extension;
//import org.firstinspires.ftc.teamcode.user.scoring;
//import org.firstinspires.ftc.teamcode.user.slides;
//
//@Autonomous(name="auto spec limelight")
//public class auto_specimen_limelight extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
////        Pose2d initialPose = new Pose2d(new Vector2d(8,-60), Math.toRadians(90));
//        Pose2d initialPose = new Pose2d(new Vector2d(9,-60), Math.toRadians(90));
//
//        Limelight ll =new Limelight(hardwareMap);
//        telemetry.setMsTransmissionInterval(11);
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
//
//        TrajectoryActionBuilder start_to_score = drive.actionBuilder(initialPose)
//                .afterTime(0,slides.specimen_score_high())
//                .afterTime(0.7,slides.specimen_score_high())
//                .afterTime(0.2,slides.specimen_score_high())
//                .afterTime(1,slides.specimen_score_high())
//                .afterTime(1,scoring.gripper_grab_max())
//
//                .strafeTo(new Vector2d(6,-24));
//        TrajectoryActionBuilder submersible_score = drive.actionBuilder(new Pose2d(new Vector2d(6,-24),Math.toRadians(90)))
//                .afterTime(0.2,slides.slide_init())
//                .afterTime(0.2,scoring.gripper_release())
//                .afterTime(0.3,scoring.extension_retracted())
//                .afterTime(0.4,scoring.specimen_collect())
//                .afterTime(0.3,slides.slide_init())
//                .setReversed(true)
//                .splineToConstantHeading(new Vector2d(58, -59), Math.toRadians(0));
////                .strafeToLinearHeading(new Vector2d(61,-60),Math.toRadians(75));
//
//        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(58,-59),Math.toRadians(90)))
//                .afterTime(0,colection.first_sample_spec())
//                .strafeToLinearHeading(new Vector2d(68,-47.8),Math.toRadians(85.5));
//        TrajectoryActionBuilder transfer_sample_1_score = drive.actionBuilder(new Pose2d(new Vector2d(68,-47.2),Math.toRadians(85.5)))
////                .afterTime(0.45,colection.griper_release())
//                .strafeToLinearHeading(new Vector2d(59,-60),Math.toRadians(85.5));
//
//        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(59,-60),Math.toRadians(85.5)))
//                .strafeToLinearHeading(new Vector2d(62,-48.5),Math.toRadians(90));
//        TrajectoryActionBuilder transfer_sample_2_score = drive.actionBuilder(new Pose2d(new Vector2d(62,-48.5),Math.toRadians(90)))
////                .afterTime(0.2,colection.griper_release())
//                .strafeToLinearHeading(new Vector2d(53,-60),Math.toRadians(90));
//
//        TrajectoryActionBuilder transfer_sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(53,-60),Math.toRadians(90)))
//                .strafeToLinearHeading(new Vector2d(51.5,-48.5),Math.toRadians(90));
//
//        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(51.5,-48.5),Math.toRadians(90)))
////                .afterTime(0.1,colection.griper_release())
//                .strafeToLinearHeading(new Vector2d(50,-62), Math.toRadians(90));
//
//
//        TrajectoryActionBuilder scoring_poz_pre = drive.actionBuilder(new Pose2d(new Vector2d(50,-62),Math.toRadians(90)))
////                .strafeToConstantHeading(new Vector2d(10,-40))
//                .afterTime(0,colection.rotation_observation())
//                .afterTime(0,scoring.specimen_prepare())
//                .afterTime(0,slides.specimen_score_high())
//                .afterTime(0.7,slides.specimen_score_high())
//
//                .afterTime(0.2,slides.specimen_score_high())
//                .afterTime(1,scoring.gripper_grab_max())
//                .afterTime(1,slides.specimen_score_high())
//                .strafeToConstantHeading(new Vector2d(0,-25));
//        TrajectoryActionBuilder specimen_collect_pre_cicling_pre = drive.actionBuilder(new Pose2d(new Vector2d(0,-25),Math.toRadians(90)))
//                .afterTime(0,scoring.specimen_score())
//                .afterTime(0.2,scoring.gripper_release())
//                .afterTime(0.3,scoring.extension_retracted())
//                .afterTime(0.4,scoring.specimen_collect())
//                .afterTime(0.4,slides.slide_init())
//                .strafeToConstantHeading(new Vector2d(42, -61));
//        TrajectoryActionBuilder specimen_collect_pre_cicling = drive.actionBuilder(new Pose2d(new Vector2d(4,-25),Math.toRadians(90)))
//                .afterTime(0,scoring.specimen_score())
//                .afterTime(0.2,scoring.gripper_release())
//                .afterTime(0.3,scoring.extension_retracted())
//                .afterTime(0.4,scoring.specimen_collect())
//                .afterTime(0.4,slides.slide_init())
//                .strafeToConstantHeading(new Vector2d(42, -61));
//
////        TrajectoryActionBuilder specimen_dragging = drive.actionBuilder(new Pose2d(new Vector2d(4,-25),Math.toRadians(90)))
////                .strafeToConstantHeading(new Vector2d(0,-25));
//
//
//        TrajectoryActionBuilder scoring_poz = drive.actionBuilder(new Pose2d(new Vector2d(42,-61),Math.toRadians(90)))
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
//        colection.light.setPosition(colection.detection_light);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        slides.culisante(slides.slides_specimen_high_score);
//        scoring.scoring_arm_specimen_prepare();
////        scoring.scoring_arm_specimen_prepare();
//        scoring.grip_transfer.setPosition(scoring.gripper_hold);
//        Actions.runBlocking(
//                new SequentialAction(
//                        start_to_score.build()
//                ));
//        scoring.scoring_arm_score_specimen_score();
//        ElapsedTime taim = new ElapsedTime();
//        ElapsedTime taim2 = new ElapsedTime();
//        taim2.reset();
//      // while(opModeIsActive()) {
//            sleep(200);
//
////        ll.angle_detect(colection.gripper_angle);
//             ll.rot_detect(colection.gripper_rotation);
//                ll.extend_detect(extension.left_extension, extension.right_extension);
//                colection.gripper_release();
//                colection.gripper_height.setPosition(colection.height_scanning);
//
//            taim.reset();
//            while (taim.seconds() < 0.3 && opModeIsActive()) {
////
//                telemetry.update();
//                ll.angle_detect(colection.gripper_angle);
//            }
//            colection.gripper_height.setPosition(colection.height_collecting);
//            sleep(200);
//            colection.gripper.setPosition(colection.gripper_hold);
//            sleep(200);
////            if (colection.senzor.getDistance(DistanceUnit.CM) < colection.distance_to_collected_sample)
////                break;
////            extension.extend(extension.extension_retracted);
////            colection.gripper_height.setPosition(colection.height_default);
////            colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
////            colection.gripper.setPosition(colection.gripper_release);
//
//     //   }
//        extension.extend(extension.extension_retracted);
//        colection.gripper_height.setPosition(colection.height_default);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_score_sample);
//        colection.gripper_angle.setPosition(colection.gripper_angle_sample_observation);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        submersible_score.build()
//                ));
//        colection.gripper.setPosition(colection.gripper_release);
//        sleep(100);
//
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
////        sleep(100);
//        extension.extend(extension.extension_extended);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_1.build()
//                ));
//        colection.gripper_release();
//        sleep(100);
//        colection.gripper_height.setPosition(colection.height_collecting);
//        sleep(200);
//        colection.gripper.setPosition(colection.gripper_hold);
//        sleep(200);
//        colection.gripper_height.setPosition(colection.height_default);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_score_sample);
//        colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
//        extension.extend(extension.extension_retracted);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_1_score.build()
//                ));
//        colection.gripper_release();
//        sleep(100);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
////        sleep(100);
//        extension.extend(extension.extension_extended);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_2.build()
//                ));
//        sleep(200);
//        ll.rot_detect(colection.gripper_rotation);
//        colection.gripper_release();
////        sleep(100);
//        colection.gripper_height.setPosition(colection.height_collecting);
//        sleep(200);
//        colection.gripper.setPosition(colection.gripper_hold);
//        sleep(200);
//        extension.extend(extension.extension_retracted);
//        colection.gripper_height.setPosition(colection.height_default);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_score_sample);
//        colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_2_score.build()
//                ));
//        colection.gripper_release();
//        sleep(100);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
////sleep(100);
//        extension.extend(extension.extension_extended);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        Actions.runBlocking(
//                new SequentialAction(
//                        transfer_sample_3.build()
//                ));
//        sleep(200);
//        ll.rot_detect(colection.gripper_rotation);
//        colection.gripper_release();
//        sleep(100);
//        colection.gripper_height.setPosition(colection.height_collecting);
//        sleep(200);
//        colection.gripper.setPosition(colection.gripper_hold);
//        sleep(200);
//        colection.gripper_height.setPosition(colection.height_default);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_score_sample);
//        colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
//        extension.extend(extension.extension_retracted);
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_collect_pre.build()
//
//                ));
//        colection.gripper_release();
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(100);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_pre.build(),
//
////                ));
////        sleep(100);
////        Actions.runBlocking(
////                new SequentialAction(
////                        specimen_dragging.build(),
//                        specimen_collect_pre_cicling_pre.build(),
//                        scoring.specimen_collect()
//
//                        //    specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build(),
////                        scoring.specimen_score(),
//
////                ));
////
////        sleep(100);
////
////        Actions.runBlocking(
////                new SequentialAction(
////                        specimen_dragging.build(),
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect()
//
//                        //    specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build(),
////                        scoring.specimen_score(),
//
////                ));
////        sleep(100);
////        Actions.runBlocking(
////                new SequentialAction(
////                        specimen_dragging.build(),
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect()
//
//                        //   specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build(),
////                        scoring.specimen_score(),
//
////                ));
////        sleep(100);
////        Actions.runBlocking(
////                new SequentialAction(
////                        specimen_dragging.build(),
//                        specimen_collect_pre_cicling.build(),
//                        scoring.specimen_collect()
//
//                        //   specimen_collect.build()
//                ));
//        scoring.gripper(scoring.gripper_semi_hold);
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build(),
//                        scoring.specimen_score(),
//
////                ));
////        sleep(100);
////        Actions.runBlocking(
////                new SequentialAction(
//                        parking.build()
//                ));
//
//
//        drive.updatePoseEstimate();
//        telemetry.addData("pose",drive.pose);
//        telemetry.update();
//    }
//}