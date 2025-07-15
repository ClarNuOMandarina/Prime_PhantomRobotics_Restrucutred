package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actions.SpecimenAutonomous.AutonomousSpecimenActions;
import org.firstinspires.ftc.teamcode.PinpointDrive;

public class SpecimenAutonomous extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AutonomousSpecimenActions autonomousActions = new AutonomousSpecimenActions(hardwareMap, gamepad1);
        autonomousActions.actionBuilder.mecanisme.slides.ResetEncoders();
        autonomousActions.actionBuilder.mecanisme.AutoInitSample();
        PinpointDrive drive = new PinpointDrive(hardwareMap, autonomousActions.FieldMap.initialPose);
        autonomousActions.actionBuilder.mecanisme.SpecimenAutoInitConfig();
        waitForStart();
        autonomousActions.PreloadScore(drive);
        autonomousActions.IntermediaryPosition(drive);
        autonomousActions.ScoreFirstSample(drive);
        autonomousActions.ScoreSecondSample(drive);
        autonomousActions.ScoreSecondSample(drive);
    }
}
