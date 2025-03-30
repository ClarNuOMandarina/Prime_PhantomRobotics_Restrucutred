package org.firstinspires.ftc.teamcode.user;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="config")
@Config
public class config extends LinearOpMode {

double kp=0;
    double ki=0;
    double kd=0;
    ElapsedTime timer =new ElapsedTime();
    double integralSum=0;
    public double lastError=0;
     public static int slidez=0;
     public static double extendz=0.5;
     public static double scoring_arm=0.5;
     public static double colecting_arms=0.5;
     public static double gripz=0.5;
     public static double transfer_gripz=0.5;
     public static double gripz_rotation=0.5;
     public static double gripz_angle=0.5;
     public static double extend_armzz=0.5;
     public static double heightzzzz=0.5;
     public static double hangz=0.5;
     public static double lightz=0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        slides slides=new slides(hardwareMap);
        colection colection = new colection(hardwareMap);
        scoring scoring = new scoring(hardwareMap);
        extension extension = new extension(hardwareMap);
        boolean blockage=false;
        ElapsedTime timer =new ElapsedTime(0);
        boolean extend=false;
        hanging hanging =new hanging(hardwareMap);

        waitForStart();
        while( opModeIsActive())
        {

            if(gamepad1.dpad_up)
            {
                slides.culisante(slidez);
            }
            if(gamepad1.dpad_left){
                extension.extend(extendz);
            }
            if(gamepad1.dpad_right){
                scoring.score(scoring_arm);
            }
            if(gamepad1.dpad_down){
                scoring.scoring_arm_extension.setPosition(extend_armzz);
            }
            if(gamepad2.dpad_right)
            {
                colection.gripper.setPosition(gripz);
            }
            if(gamepad2.dpad_left)scoring.grip_transfer.setPosition(transfer_gripz);
            if(gamepad2.dpad_up)colection.gripper_rotation.setPosition(gripz_rotation);
            if(gamepad2.dpad_down)colection.gripper_angle.setPosition(gripz_angle);
            if(gamepad1.right_bumper)colection.scoring_config();
           if(gamepad1.touchpad) {
                    hanging.hang(hangz);

                }
            if(gamepad1.triangle){
                colection.gripper_height.setPosition(heightzzzz);
            }
            
            if(gamepad1.share)colection.light.setPosition(lightz);

            telemetry.addData("CULI",slides.left_slide.getCurrentPosition());
            telemetry.addData("CULI2",slides.right_slide.getCurrentPosition());

            telemetry.addData("extend",extension.left_extension.getPosition());
            telemetry.addData("sensor",colection.senzor.getDistance(DistanceUnit.CM));
            telemetry.addData("sensor",colection.senzor.getConnectionInfo());

            telemetry.update();
        }
    }
}
