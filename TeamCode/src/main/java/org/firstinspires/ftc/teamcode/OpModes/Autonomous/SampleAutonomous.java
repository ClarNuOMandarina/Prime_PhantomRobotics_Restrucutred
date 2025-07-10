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
        sleep(200);
        autonomousActions.CollectFirstSample(drive);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreFirstSample(drive);
        sleep(200);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();

        sleep(200);
        autonomousActions.CollectSecondSample(drive);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreSecondSample(drive);
        sleep(500);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(200);

        autonomousActions.CollectThirdSample(drive);
        autonomousActions.actionBuilder.CollectSample();
        autonomousActions.ScoreThirdSample(drive);
        sleep(500);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(200);

        autonomousActions.SubmersibleCollect(drive);
        boolean isCollected = true;
        double y = -2;
        //public Pose2d ScoreSubmersibleSample= new Pose2d(new Vector2d(-58,-50.7), Math.toRadians(65));

        while (isCollected && opModeIsActive()) {
            sleep(200);
            if (autonomousActions.actionBuilder.limeLight.is_detecting()) {
                autonomousActions.actionBuilder.SampleCollectUsingLimelight();
                sleep(400);
                if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                    isCollected = false;
                }

            } else {
                y += 1;
                Pose2d SearchSubmersibleSample = (new Pose2d(new Vector2d(-19.5, y), Math.toRadians(0)));

                TrajectoryActionBuilder SearchSubmersibleSampleTraj = drive.actionBuilder(new Pose2d(new Vector2d(-19.5, y - 1), Math.toRadians(0)))
                        .strafeToLinearHeading(
                                SearchSubmersibleSample.position,
                                SearchSubmersibleSample.heading
                        );
                Actions.runBlocking(
                        new SequentialAction(
                                SearchSubmersibleSampleTraj.build()
                        ));
                sleep(200);
            }

        }
        isCollected = false;
            autonomousActions.SubmersibleScore(drive,y);
            autonomousActions.actionBuilder.mecanisme.intake.gripper.OpenGripper();
            sleep(300);

    }
}
