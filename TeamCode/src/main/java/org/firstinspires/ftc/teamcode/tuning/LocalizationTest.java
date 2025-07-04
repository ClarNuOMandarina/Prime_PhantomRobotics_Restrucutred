package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drawing;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.PinpointDrive;

@Config
@TeleOp(name="localizer")
public class LocalizationTest extends LinearOpMode {
    public static int slide=0;
    public static double extendo=0.75;
    public static double OuttakeExtendo=0.61;
    public static double Arms=0.2;
    public static double IntakeGripper=0.65;
    public static double OuttakeGripper=0.35;
    public static double IntakeTurret=0.42  ;
    public static double IntakeAngle=0.52;
    public static double IntakeHeight=0.735;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        PinpointDrive drive = new PinpointDrive(hardwareMap, new Pose2d(new Vector2d(-40,-58), Math.toRadians(90)));
        Mecanisme mecanisme=new Mecanisme(hardwareMap,gamepad1);
        mecanisme.slides.ResetEncoders();
        waitForStart();

        while (opModeIsActive()) {
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));
            mecanisme.slides.SlideCalibration(slide);
            mecanisme.extendo.ExtendoCallibration(extendo);
            mecanisme.intake.angle.AngleCallibration(IntakeAngle);
            mecanisme.intake.height.HeightCallibration(IntakeHeight);
            mecanisme.intake.gripper.GripperCallibration(IntakeGripper);
            mecanisme.outtake.arms.ArmsCalibration(Arms);
            mecanisme.outtake.gripper.GripperCalibration(OuttakeGripper);
            mecanisme.outtake.extendo.ExtendoCalibration(OuttakeExtendo);
            mecanisme.intake.turret.TurretCalibration(IntakeTurret);



            telemetry.addData("LeftSlidePoz",mecanisme.slides.getLeftSlidePoz());
            telemetry.addData("RightSlidePoz",mecanisme.slides.getRightSlidePoz());
            telemetry.addData("IntakeSensorDistance",mecanisme.intake.sensor.getSensorDistance());

            drive.updatePoseEstimate();

            telemetry.addData("x", drive.pose.position.x);
            telemetry.addData("y", drive.pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(drive.pose.heading.toDouble()));
            telemetry.update();

            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), drive.pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
