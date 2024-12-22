//package org.firstinspires.ftc.teamcode.user;
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
//import org.firstinspires.ftc.teamcode.user.slides;
//
//@Autonomous(name="auto_test")
//public class red_TEST_v1 extends LinearOpMode {
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(0));
//        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
//        slides slides = new slides(hardwareMap);
//        TrajectoryActionBuilder tr1 = drive.actionBuilder(initialPose)
//                .lineToXSplineHeading(20, Math.toRadians(-90))
//                .strafeTo(new Vector2d(20, 40));
//
//        waitForStart();
//        if (isStopRequested()) return;
//
//        Actions.runBlocking(
//                new SequentialAction(
//
//                ));
////            Actions.runBlocking(
////                    new SequentialAction(
////                            st1.build(),
////                        new ParallelAction(
////                          st2.build(),
////                                slides.slide_low()
////                        ),
////                            st3.build(),
////                            slides.slide_zero()
////                    ));
////            Actions.runBlocking(
////                    new SequentialAction(
////                            tr1.build(),
////                        new ParallelAction(
////                          tr2.build(),
////                                slides.slide_low()
////                        ),
////                            slides.slide_zero()
////                    )
////            );
//
//    }
//}