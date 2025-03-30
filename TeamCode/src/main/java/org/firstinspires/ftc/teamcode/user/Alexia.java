//package org.firstinspires.ftc.teamcode.user;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.PoseVelocity2d;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.PinpointDrive;
//
//@TeleOp(name = "test")
//public class Alexia extends LinearOpMode {
//
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        PinpointDrive drive = new PinpointDrive(hardwareMap, new Pose2d(new Vector2d(-40,-58), Math.toRadians(90)));
//
//waitForStart();
//while (opModeIsActive()) {
//    drive.setDrivePowers(new PoseVelocity2d(
//            new Vector2d(
//                    -gamepad1.left_stick_y,
//                    -gamepad1.left_stick_x
//            ),
//            -gamepad1.right_stick_x*0.7
//
//    ));
//}
//    }
//}
