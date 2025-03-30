//package org.firstinspires.ftc.teamcode.user;
//
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.ParallelAction;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.PoseVelocity2d;
//import com.acmerobotics.roadrunner.ProfileAccelConstraint;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.SleepAction;
//import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
//import com.acmerobotics.roadrunner.TrajectoryBuilder;
//import com.acmerobotics.roadrunner.TranslationalVelConstraint;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.VelConstraint;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.qualcomm.hardware.limelightvision.LLResult;
//import com.qualcomm.hardware.limelightvision.LLResultTypes;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.PinpointDrive;
//
//import java.util.List;
//
//@Autonomous(name="auto_basket limelight")
//public class auto_basket_camera extends LinearOpMode {
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
////        Pose2d initialPose = new Pose2d(new Vector2d(-40,-59), Math.toRadians(90));
//        Pose2d initialPose = new Pose2d(new Vector2d(-40,-58), Math.toRadians(90));
//
//        Limelight ll= new Limelight(hardwareMap);
//        telemetry.setMsTransmissionInterval(11);
//
//        ElapsedTime timerr= new ElapsedTime();
//        PinpointDrive drive = new PinpointDrive(hardwareMap,initialPose);
//        colection colection = new colection(hardwareMap);
//        extension extension = new extension(hardwareMap);
//        scoring scoring = new scoring(hardwareMap);
//        slides slides = new slides(hardwareMap);
//        slides.slide_init();
//        colection.init_config();
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        extension.extend(extension.extension_retracted);
//        scoring.scoring_arm_default();
//        slides.reset_encoder();
//        scoring.gripper_grab();
//        ElapsedTime timer= new ElapsedTime();
//        double samp3=0;
//        double samp4=0;
//        boolean transferz=false;
//        double x=-4;
//        colection.light.setPosition(colection.detection_light);
//        TrajectoryActionBuilder start_to_score = drive.actionBuilder( initialPose)
//
//                .afterTime(0,slides.slide_sample())
//                .afterTime(0.4,slides.slide_sample())
//                .afterTime(0.6,scoring.sample_score_retracted())
//                .afterTime(0.8,colection.collecting_arm_default())
//                .strafeToLinearHeading(new Vector2d(-59,-51),Math.toRadians(69));
//
//        TrajectoryActionBuilder sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(-59,-51),Math.toRadians(69)))
//                .afterTime(0.4,slides.slide_init())
//                .afterTime(0.7,slides.slide_init())
//                .afterTime(0.1,scoring.sample_collect())
//                .strafeToLinearHeading(new Vector2d(-54.5,-44.5),Math.toRadians(68));
//        TrajectoryActionBuilder sample_finish = drive.actionBuilder(new Pose2d(new Vector2d(-54.5,-44.5),Math.toRadians(68)))
//                .afterTime(0, scoring.gripper_grab())
//                .afterTime(0.2, colection.griper_release())
//                .afterTime(0.3,slides.slide_sample())
//                .afterTime(0.8,scoring.sample_score_auto())
//                .strafeToLinearHeading(new Vector2d(-62.5,-51),Math.toRadians(80));
//
//        TrajectoryActionBuilder sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(-62.5,-51),Math.toRadians(80)))
//                .afterTime(0.4,slides.slide_init())
//                .afterTime(0.7,slides.slide_init())
//                .afterTime(0.5,colection.collecting_arm_default())
//                .strafeToLinearHeading(new Vector2d(-61.5,-44.5),Math.toRadians(83));
//
//        TrajectoryActionBuilder sample_finish_2 = drive.actionBuilder(new Pose2d(new Vector2d(-61.5 ,-44.5),Math.toRadians(83)))
//                .afterTime(0, scoring.gripper_grab())
//                .afterTime(0.2, colection.griper_release())
//                .afterTime(0.3,slides.slide_sample())
//                .afterTime(0.7,scoring.sample_score_auto())
//                .strafeToLinearHeading(new Vector2d(-64,-52.5),Math.toRadians(87));
//
//        TrajectoryActionBuilder sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(-64,-52.5),Math.toRadians(87)))
//                .afterTime(0.4,slides.slide_init())
//                .afterTime(0.7,slides.slide_init())
//                .afterTime(0.6,colection.third_sample())
//
//                .strafeToLinearHeading(new Vector2d(-63,-44),Math.toRadians(93));
////        TrajectoryActionBuilder sample_3_end = drive.actionBuilder(new Pose2d(new Vector2d(-49,-26),Math.toRadians(-180)))
////                .strafeTo(new Vector2d(-42,-26));
//        TrajectoryActionBuilder sample_finish_3 = drive.actionBuilder(new Pose2d(new Vector2d(-63,-44),Math.toRadians(93)))
//                .afterTime(0, scoring.gripper_grab())
//                .afterTime(0.2, colection.griper_release())
//                .afterTime(0.3,slides.slide_sample())
//                .afterTime(0.8,scoring.sample_score_auto())
//                .strafeToLinearHeading(new Vector2d(-64,-49.5),Math.toRadians(87));
//        TrajectoryActionBuilder sample_4 = drive.actionBuilder(new Pose2d(new Vector2d(-64,-49.5),Math.toRadians(87)))
//                .afterTime(0.4,slides.slide_init())
//                .afterTime(0.7,slides.slide_init())
//                .afterTime(0.5,colection.collecting_arm_default())
////                .setTangent(Math.toRadians(41))
////                .splineToConstantHeading(new Vector2d(-44,-20.5),Math.toRadians(41))
//                .splineToLinearHeading(new Pose2d(-20, -4, 0), Math.toRadians(0));
//
////        TrajectoryActionBuilder sample_finish_4 = drive.actionBuilder(new Pose2d(new Vector2d(-20,-4),Math.toRadians(0)))
////                .afterTime(0.2, scoring.gripper_grab())
////                .afterTime(0.4, colection.griper_release())
////                .afterTime(0.5,slides.slide_sample())
////                .afterTime(0.7,scoring.sample_score_auto())
//////                .splineToLinearHeading(new Pose2d(-40, -20, 40), 180 )
//////                .setReversed(true)
//////                .splineToLinearHeading(new Pose2d(-54, -46, Math.toRadians(60)), Math.toRadians(180));
////                .strafeToLinearHeading(new Vector2d(-62.5,-48.5),Math.toRadians(90));
//
//
//
//
////        TrajectoryActionBuilder sample_finish_5 = drive.actionBuilder(new Pose2d(new Vector2d(-10,-2.5),Math.toRadians(0)))
////                .afterTime(0, scoring.gripper_grab())
////                .afterTime(0.2, colection.griper_release())
////                .afterTime(0.3,slides.slide_sample())
////                .afterTime(0.7,scoring.sample_score_auto())
////                .strafeToLinearHeading(new Vector2d(-58,-47),Math.toRadians(65.7));
//
//        TrajectoryActionBuilder parking_pre = drive.actionBuilder(new Pose2d(new Vector2d(-57.5,-57),Math.toRadians(30)))
//                .afterTime(1,slides.auto_park_basket())
//                .afterTime(1,scoring.specimen_collect())
//                .strafeToLinearHeading(new Vector2d(-43,-13),Math.toRadians(0));
//
//        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(-43,-13),Math.toRadians(0)))
//                .afterTime(0.2,slides.auto_park_basket())
//                .afterTime(0.2,scoring.specimen_collect())
//                .strafeTo(new Vector2d(-25,-11));
//
//
//        scoring.grip_transfer.setPosition(scoring.gripper_hold);
//        waitForStart();
//        slides.culisante(slides.slides_high_basket);
//        timerr.reset();
//        if (isStopRequested()) return;
//        Actions.runBlocking(
//                new SequentialAction(
//                        start_to_score.build()
//                ));
//        extension.extend(extension.extension_extended);
//        sleep(100);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_1.build()
//                ));
//        sleep(100);
//        ll.rot_detect(colection.gripper_rotation);
//        colection.gripper_release();
//        sleep(100);
//        colection.gripper_height.setPosition(colection.height_collecting);
//        sleep(200);
//        colection.gripper.setPosition(colection.gripper_transfer);
//        sleep(200);
//        colection.gripper_height.setPosition(colection.height_transfer);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//        extension.extend(extension.extension_transfer+0.08);
//        sleep(400);
//        extension.extend(extension.extension_transfer);
//        ;
////        sleep(5000);
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_finish.build()
//                ));
//        scoring.scoring_arm_extension.setPosition(scoring.extension_extended_max);
//        extension.extend(extension.extension_extended);
//        sleep(500);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
//        scoring.scoring_arm_default();
//        colection.default_config();
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_2.build()
//                ));
//        sleep(100);
//        ll.rot_detect(colection.gripper_rotation);
////        ll.extend_detect(extension.left_extension,extension.right_extension);
//        colection.gripper_release();
//        sleep(100);
//        colection.gripper_height.setPosition(colection.height_collecting);
//        sleep(200);
//        colection.gripper.setPosition(colection.gripper_transfer);
//        sleep(200);
//        colection.gripper_height.setPosition(colection.height_transfer);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//
//        extension.extend(extension.extension_transfer+0.08);
//        sleep(500);
//        extension.extend(extension.extension_transfer);
//        ;
////        sleep(5000);
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_finish_2.build()
//                ));
//        extension.extend(extension.extension_extended);
//        scoring.scoring_arm_extension.setPosition(scoring.extension_extended_max);
//        sleep(500);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        scoring.scoring_arm_default();
//
//        scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
//        colection.default_config();
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_3.build()
//                ));
//        sleep(100);
//        ll.rot_detect(colection.gripper_rotation);
////        ll.extend_detect(extension.left_extension,extension.right_extension);
//        colection.gripper_release();
//        colection.gripper_angle.setPosition(0.72);
//        sleep(100);
//        colection.gripper_height.setPosition(colection.height_collecting);
//        sleep(300);
//        colection.gripper.setPosition(colection.gripper_transfer);
//        sleep(200);
//        colection.gripper_height.setPosition(colection.height_transfer);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//
//        extension.extend(extension.extension_transfer+0.08);
//        sleep(500);
//        extension.extend(extension.extension_transfer);
//        ;
////        sleep(5000);
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_finish_3.build()
//                ));
//
//        extension.extend(extension.extension_retracted);
//        scoring.scoring_arm_extension.setPosition(scoring.extension_extended_max);
//
//        sleep(500);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        scoring.scoring_arm_default();
//
//        scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
//        colection.default_config();
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//        LLResult result = ll.limelight.getLatestResult();
//        ElapsedTime taim = new ElapsedTime();
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_4.build()
//                ));
//
//        while(opModeIsActive()) {
//            sleep(200);
////        ll.angle_detect(colection.gripper_angle);
//            if(ll.is_detecting()) {
//                ll.rot_detect(colection.gripper_rotation);
//                ll.extend_detect(extension.left_extension, extension.right_extension);
//                colection.gripper_release();
//                colection.gripper_height.setPosition(colection.height_scanning);
//                taim.reset();
//                while (taim.seconds() < 0.4 && opModeIsActive()) {
////
//
//                    telemetry.addData("samp4", samp4);
//                    telemetry.update();
//                    ll.angle_detect(colection.gripper_angle);
//                }
//                colection.gripper_height.setPosition(colection.height_collecting);
//                sleep(200);
//                colection.gripper.setPosition(colection.gripper_transfer);
//                sleep(300);
//                if (colection.senzor.getDistance(DistanceUnit.CM) < colection.distance_to_collected_sample) {
//                    extension.extend(extension.extension_transfer+0.08);
//
//                    break;
//                }
//                colection.gripper_height.setPosition(colection.height_default);
//                colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//                colection.gripper.setPosition(colection.gripper_release);
//                extension.extend(extension.extension_retracted);
//            }
//            else{
//                drive.updatePoseEstimate();
//                TrajectoryActionBuilder sample_collect1 = drive.actionBuilder(drive.pose)
//                        .strafeToLinearHeading(new Vector2d(-20,x),Math.toRadians(0));
//                Actions.runBlocking(
//                        new SequentialAction(
//                                sample_collect1.build()
//                        ));
//                x+=2.5;
////                extension.extend(extension.extension_retracted+x);
////                sleep(400);
////                ll.rot_detect(colection.gripper_rotation);
////                ll.extend_detect(extension.left_extension, extension.right_extension);
////                colection.gripper_release();
////                colection.gripper_height.setPosition(colection.height_scanning);
////                x+=0.05;
//            }
//
//
//            extension.extend(extension.extension_retracted);
//
//            colection.gripper_height.setPosition(colection.height_default);
//            colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//            colection.gripper.setPosition(colection.gripper_release);
//            sleep(100);
//
//        }
//        colection.gripper_height.setPosition(colection.height_transfer);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//
//        extension.extend(extension.extension_transfer+0.08);
//        sleep(400);
//        extension.extend(extension.extension_transfer);
//        TrajectoryActionBuilder sample_finish_4 = drive.actionBuilder(drive.pose)
//                .afterTime(0.2, scoring.gripper_grab())
//                .afterTime(0.4, colection.griper_release())
//                .afterTime(0.5,slides.slide_sample())
//                .afterTime(0.7,scoring.sample_score_auto())
////                .splineToLinearHeading(new Pose2d(-40, -20, 40), 180 )
////                .setReversed(true)
////                .splineToLinearHeading(new Pose2d(-54, -46, Math.toRadians(60)), Math.toRadians(180));
//                .strafeToLinearHeading(new Vector2d(-62.5,-49.5),Math.toRadians(80));
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_finish_4.build()
//                ));
//        extension.extend(extension.extension_retracted);
//        scoring.scoring_arm_extension.setPosition(scoring.extension_extended_max);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        scoring.scoring_arm_default();
//
//        scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
//        colection.default_config();
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//        drive.updatePoseEstimate();
//        TrajectoryActionBuilder sample_5 = drive.actionBuilder(drive.pose)
//                .afterTime(0.4,slides.slide_init())
//                .afterTime(0.7,slides.slide_init())
//                .afterTime(0.5,colection.collecting_arm_default())
//                .splineToLinearHeading(new Pose2d(-20, -2, 0), 0);
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_4.build()
//                ));
//        while(opModeIsActive()) {
//            sleep(200);
////        ll.angle_detect(colection.gripper_angle);
//            if(ll.is_detecting()) {
//                ll.rot_detect(colection.gripper_rotation);
//                ll.extend_detect(extension.left_extension, extension.right_extension);
//                colection.gripper_release();
//                colection.gripper_height.setPosition(colection.height_scanning);
//                taim.reset();
//                while (taim.seconds() < 0.4 && opModeIsActive()) {
////
//
//                    telemetry.addData("samp4", samp4);
//                    telemetry.update();
//                    ll.angle_detect(colection.gripper_angle);
//                }
//                colection.gripper_height.setPosition(colection.height_collecting);
//                sleep(200);
//                colection.gripper.setPosition(colection.gripper_transfer);
//                sleep(300);
//                if (colection.senzor.getDistance(DistanceUnit.CM) < colection.distance_to_collected_sample) {
//                    extension.extend(extension.extension_transfer+0.08);
//
//                    break;
//                }
//                colection.gripper_height.setPosition(colection.height_default);
//                colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//                colection.gripper.setPosition(colection.gripper_release);
//                extension.extend(extension.extension_retracted);
//
//            }
//            else{
//                drive.updatePoseEstimate();
//                TrajectoryActionBuilder sample_collect1 = drive.actionBuilder(drive.pose)
//                        .strafeToLinearHeading(new Vector2d(-20,x),Math.toRadians(0));
//                Actions.runBlocking(
//                        new SequentialAction(
//                                sample_collect1.build()
//                        ));
//                x+=2.5;
////                extension.extend(extension.extension_retracted+x);
////                sleep(400);
////                ll.rot_detect(colection.gripper_rotation);
////                ll.extend_detect(extension.left_extension, extension.right_extension);
////                colection.gripper_release();
////                colection.gripper_height.setPosition(colection.height_scanning);
////                x+=0.05;
//            }
//        }
//        colection.gripper_height.setPosition(colection.height_transfer);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//
//        extension.extend(extension.extension_transfer+0.08);
//        sleep(200);
//        extension.extend(extension.extension_transfer);
//        TrajectoryActionBuilder sample_finish_5 = drive.actionBuilder(drive.pose)
//                .afterTime(0.2, scoring.gripper_grab())
//                .afterTime(0.4, colection.griper_release())
//                .afterTime(0.5,slides.slide_sample())
//                .afterTime(0.7,scoring.sample_score_auto())
////                .splineToLinearHeading(new Pose2d(-40, -20, 40), 180 )
////                .setReversed(true)
////                .splineToLinearHeading(new Pose2d(-54, -46, Math.toRadians(60)), Math.toRadians(180));
//                .strafeToLinearHeading(new Vector2d(-62.5,-49.5),Math.toRadians(80));
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_finish_5.build()
//                ));
//        extension.extend(extension.extension_retracted);
//        scoring.scoring_arm_extension.setPosition(scoring.extension_extended_max);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        scoring.scoring_arm_default();
//
//        scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
//        colection.default_config();
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//        drive.updatePoseEstimate();
//        TrajectoryActionBuilder sample_6 = drive.actionBuilder(drive.pose)
//                .afterTime(0.4,slides.slide_init())
//                .afterTime(0.7,slides.slide_init())
//                .afterTime(0.5,colection.collecting_arm_default())
//                .splineToLinearHeading(new Pose2d(-20, 0, 0), 0);
//        ;Actions.runBlocking(
//                new SequentialAction(
//                        sample_4.build()
//                ));
//        while(opModeIsActive()) {
//            sleep(200);
////        ll.angle_detect(colection.gripper_angle);
//            if (ll.is_detecting()) {
//                ll.rot_detect(colection.gripper_rotation);
//                ll.extend_detect(extension.left_extension, extension.right_extension);
//                colection.gripper_release();
//                colection.gripper_height.setPosition(colection.height_scanning);
//                taim.reset();
//                while (taim.seconds() < 0.4 && opModeIsActive()) {
////
//
//                    telemetry.addData("samp4", samp4);
//                    telemetry.update();
//                    ll.angle_detect(colection.gripper_angle);
//                }
//                colection.gripper_height.setPosition(colection.height_collecting);
//                sleep(200);
//                colection.gripper.setPosition(colection.gripper_transfer);
//                sleep(300);
//                if (colection.senzor.getDistance(DistanceUnit.CM) < colection.distance_to_collected_sample) {
//                    extension.extend(extension.extension_transfer + 0.08);
//
//                    break;
//                }
//                colection.gripper_height.setPosition(colection.height_default);
//                colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//                colection.gripper.setPosition(colection.gripper_release);
//                extension.extend(extension.extension_retracted);
//
//
//            } else {
//                drive.updatePoseEstimate();
//                TrajectoryActionBuilder sample_collect1 = drive.actionBuilder(drive.pose)
//                        .strafeToLinearHeading(new Vector2d(-20, x), Math.toRadians(0));
//                Actions.runBlocking(
//                        new SequentialAction(
//                                sample_collect1.build()
//                        ));
//                x += 2.5;
////                extension.extend(extension.extension_retracted+x);
////                sleep(400);
////                ll.rot_detect(colection.gripper_rotation);
////                ll.extend_detect(extension.left_extension, extension.right_extension);
////                colection.gripper_release();
////                colection.gripper_height.setPosition(colection.height_scanning);
////                x+=0.05;
//            }
//        }
//        colection.gripper_height.setPosition(colection.height_transfer);
//        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//
//        extension.extend(extension.extension_transfer+0.08);
//        sleep(200);
//        extension.extend(extension.extension_transfer);
//        TrajectoryActionBuilder sample_finish_6 = drive.actionBuilder(drive.pose)
//                .afterTime(0.2, scoring.gripper_grab())
//                .afterTime(0.4, colection.griper_release())
//                .afterTime(0.5,slides.slide_sample())
//                .afterTime(0.7,scoring.sample_score_auto())
////                .splineToLinearHeading(new Pose2d(-40, -20, 40), 180 )
////                .setReversed(true)
////                .splineToLinearHeading(new Pose2d(-54, -46, Math.toRadians(60)), Math.toRadians(180));
//                .strafeToLinearHeading(new Vector2d(-62.5,-49.5),Math.toRadians(80));
//        Actions.runBlocking(
//                new SequentialAction(
//                        sample_finish_6.build()
//                ));
//        extension.extend(extension.extension_retracted);
//        scoring.scoring_arm_extension.setPosition(scoring.extension_extended_max);
//
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        scoring.scoring_arm_default();
//
//        scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
//        colection.default_config();
//        colection.gripper_angle.setPosition(colection.gripper_angle_default);
//        sleep(200);
//
//        telemetry.update();
//    }
//}
