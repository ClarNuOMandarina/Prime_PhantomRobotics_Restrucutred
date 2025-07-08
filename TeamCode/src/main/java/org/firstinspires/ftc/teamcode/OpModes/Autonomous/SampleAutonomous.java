package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actions.AutonomousActions;
import org.firstinspires.ftc.teamcode.FieldMap.SampleFieldMap;
import org.firstinspires.ftc.teamcode.PinpointDrive;
@Autonomous(name= "Sample Autonomous")
public class SampleAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        AutonomousActions autonomousActions=new AutonomousActions(hardwareMap,gamepad1);
        autonomousActions.mecanisme.slides.ResetEncoders();
        autonomousActions.mecanisme.AutoInitSample();
        PinpointDrive drive= new PinpointDrive(hardwareMap,autonomousActions.sampleFieldMap.initialPose );
        waitForStart();
        autonomousActions.PreloadScore(drive);
        sleep(200);
        autonomousActions.mecanisme.outtake.gripper.OpenGripper();
        sleep(200);
        autonomousActions.CollectFirstSample(drive);
        autonomousActions.CollectSample();
        autonomousActions.ScoreFirstSample(drive);
        sleep(200);
        autonomousActions.mecanisme.outtake.gripper.OpenGripper();

        sleep(200);
        autonomousActions.CollectSecondSample(drive);
        autonomousActions.CollectSample();
        autonomousActions.ScoreSecondSample(drive);
        sleep(500);
        autonomousActions.mecanisme.outtake.gripper.OpenGripper();
        sleep(200);

        autonomousActions.CollectThirdSample(drive);
        autonomousActions.CollectSample();
        autonomousActions.ScoreThirdSample(drive);
        sleep(500);
        autonomousActions.mecanisme.outtake.gripper.OpenGripper();
        sleep(200);

        autonomousActions.SubmersibleCollect(drive);


    }
}
