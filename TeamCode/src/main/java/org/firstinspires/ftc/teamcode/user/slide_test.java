package org.firstinspires.ftc.teamcode.user;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
// leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
@TeleOp(name="slide test")
public class slide_test extends LinearOpMode {
    DcMotorEx left_slide,right_slide;

    @Override
    public void runOpMode() throws InterruptedException {
        left_slide=hardwareMap.get(DcMotorEx.class,"left_slide");
        right_slide=hardwareMap.get(DcMotorEx.class,"right_slide");
        left_slide.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        right_slide.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        right_slide.setDirection(DcMotorEx.Direction.REVERSE);
        left_slide.setDirection(DcMotorEx.Direction.REVERSE);

        left_slide.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        right_slide.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.dpad_up){
                left_slide.setTargetPosition(300);
                right_slide.setTargetPosition(300);
                left_slide.setPower(1);
                right_slide.setPower(1);
                left_slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                right_slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            }
            if(gamepad1.dpad_down){
                left_slide.setTargetPosition(0);
                right_slide.setTargetPosition(0);
                left_slide.setPower(1);
                right_slide.setPower(1);
                left_slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                right_slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            }
            if (gamepad1.dpad_left) {

                left_slide.setPower(0);
                right_slide.setPower(0);
            }

            telemetry.addData("culisante",left_slide.getCurrentPosition());
            telemetry.addData("culisante2",right_slide.getCurrentPosition());
            telemetry.addData("culisante",left_slide.getVelocity());
            telemetry.addData("culisante2",right_slide.getVelocity());
            telemetry.update();
        }
    }
}
