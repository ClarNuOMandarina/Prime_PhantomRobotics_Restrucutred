package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actions.AutonomousSampleActions;
import org.firstinspires.ftc.teamcode.FieldMap.SampleFieldMap;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;
@Autonomous(name= "Sample Autonomous")
public class SampleAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        AutonomousSampleActions autonomousActions = new AutonomousSampleActions(hardwareMap, gamepad1);
        autonomousActions.actionBuilder.mecanisme.slides.ResetEncoders();
        autonomousActions.actionBuilder.mecanisme.AutoInitSample();
        PinpointDrive drive = new PinpointDrive(hardwareMap, autonomousActions.sampleFieldMap.initialPose);

        waitForStart();

        autonomousActions.PreloadScore(drive);
        sleep(200);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(300);
        autonomousActions.CollectFirstSample(drive);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreFirstSample(drive);
        sleep(400);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(100);
        autonomousActions.CollectSecondSample(drive);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreSecondSample(drive);
        sleep(400);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(100);

        autonomousActions.CollectThirdSample(drive);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreThirdSample(drive);
        sleep(400);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(100);

        autonomousActions.SubmersibleCollectFirstCycle(drive);
        boolean isCollected =false;
        double y = -2;
        sleep(400);
        while (!isCollected && opModeIsActive()) {
            sleep(400);
            if (autonomousActions.actionBuilder.limeLight.is_detecting()) {
                sleep(400);
                autonomousActions.actionBuilder.SampleCollectUsingLimelight();
                sleep(400);
                if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                    isCollected = true;
                }

            } else {
                y += 5;

                autonomousActions.SubmersibleSearch(drive,y);

            }
            if(y>18)y=-2;
        }
        isCollected = false;
            autonomousActions.SubmersibleScore(drive,y);
            autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
            sleep(300);

        autonomousActions.SubmersibleCollect(drive);
        sleep(400);
        while (!isCollected && opModeIsActive()) {
            sleep(200);
            if (autonomousActions.actionBuilder.limeLight.is_detecting()) {
                sleep(200);
                autonomousActions.actionBuilder.SampleCollectUsingLimelight();
                sleep(400);
                if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                    isCollected = true;
                }

            } else {
                y += 5;
                autonomousActions.SubmersibleSearch(drive,y);
            }
            if(y>18)y=-2;
        }
        isCollected = false;
        autonomousActions.SubmersibleScore(drive,y);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(300);


        autonomousActions.Reset(drive);
    }
}
