//package org.firstinspires.ftc.teamcode.old_code;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.user.colection;
//import org.firstinspires.ftc.teamcode.user.extension;
//import org.firstinspires.ftc.teamcode.user.scoring;
//import org.firstinspires.ftc.teamcode.user.slides;
//
//@Autonomous(name="auto_basket_simple")
//public class auto_basket extends LinearOpMode {
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        Pose2d initialPose = new Pose2d(new Vector2d(-16.5,-62), Math.toRadians(-90));
//
//
//        MecanumDrive drive = new MecanumDrive(hardwareMap,initialPose);
//        colection colection = new colection(hardwareMap);
//        extension extension = new extension(hardwareMap);
//        scoring scoring = new scoring(hardwareMap);
//        slides slides = new slides(hardwareMap);
//        slides.slide_init();
//        slides.reset_encoder();
//        colection.init_config();
//        extension.extend(extension.extension_retracted);
//        scoring.scoring_arm_score_specimen_score();
//        scoring.gripper_grab();
//        TrajectoryActionBuilder start_to_score_pre = drive.actionBuilder(initialPose)
//                .strafeTo(new Vector2d(-4,-50));
//        TrajectoryActionBuilder start_to_score = drive.actionBuilder(new Pose2d(new Vector2d(-4,-50),Math.toRadians(-90)))
//                .lineToY(-36);
//        TrajectoryActionBuilder score_to_transfer = drive.actionBuilder(new Pose2d(new Vector2d(-4,-36),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(-34,-37));
//        TrajectoryActionBuilder sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(-34,-37),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(-40,-10))
//                .strafeTo(new Vector2d(-46,-10));
//        TrajectoryActionBuilder sample_1_finish = drive.actionBuilder(new Pose2d(new Vector2d(-52,-32),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(-52,-54));
//        TrajectoryActionBuilder sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(-52,-56),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(-40,-56))
//                .strafeTo(new Vector2d(-40,-10))
//                .strafeTo(new Vector2d(-56,-10));
//        TrajectoryActionBuilder sample_2_finish = drive.actionBuilder(new Pose2d(new Vector2d(-51.5,-32),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(-51.5,-54));
//        TrajectoryActionBuilder sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(-51.5,-62),Math.toRadians(-90)))
//
//                .strafeTo(new Vector2d(-55,-10))
//                .strafeTo(new Vector2d(-60,-10));
//        TrajectoryActionBuilder sample_3_finish = drive.actionBuilder(new Pose2d(new Vector2d(-60,-32),Math.toRadians(-90)))
//                .strafeTo(new Vector2d(-60,-52));
//        TrajectoryActionBuilder parking_pre = drive.actionBuilder(new Pose2d(new Vector2d(-58,-61.5),Math.toRadians(-90)))
//                .strafeToLinearHeading(new Vector2d(-43,-13),Math.toRadians(0));
//        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(-43,-13),Math.toRadians(0)))
//                .strafeTo(new Vector2d(-28,-11));
//
//
//
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
//                        start_to_score_pre.build(),
//                        start_to_score.build()
//                ));
//        slides.culisante(slides.slides_specimen_high_score);
//        sleep(700);
//        scoring.gripper(scoring.gripper_release);
//        sleep(200);
//        slides.culisante(slides.slides_init);
//        Actions.runBlocking(
//                new SequentialAction(
//                        score_to_transfer.build(),
//                        sample_1.build(),
//                        sample_1_finish.build(),
//                        sample_2.build(),
//                        sample_2_finish.build(),
//                        sample_3.build(),
//                        sample_3_finish.build(),
//                        parking_pre.build(),
//                        parking.build()
//                ));
//        while(!isStopRequested()){
//            scoring.scoring_arm_auto_init_end();
//            scoring.grip_transfer_release();
//            slides.culisante(slides.slides_init);
//            colection.init_config();
//        }
//        telemetry.update();
//    }
//}
