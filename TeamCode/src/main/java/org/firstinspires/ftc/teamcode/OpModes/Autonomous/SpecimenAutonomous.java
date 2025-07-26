package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actions.SpecimenAutonomous.AutonomousSpecimenActions;
import org.firstinspires.ftc.teamcode.PinpointDrive;
@Autonomous(name="Specimen Autonomous")
public class SpecimenAutonomous extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AutonomousSpecimenActions autonomousActions = new AutonomousSpecimenActions(hardwareMap, gamepad1);
        autonomousActions.actionBuilder.mecanisme.slides.ResetEncoders();
        autonomousActions.actionBuilder.mecanisme.AutoInitSample();
        PinpointDrive drive = new PinpointDrive(hardwareMap, autonomousActions.FieldMap.initialPose);
        autonomousActions.actionBuilder.mecanisme.SpecimenAutoInitConfig();
        waitForStart();

        autonomousActions.ScoreFirstSample(drive);
        autonomousActions.actionBuilder.mecanisme.intake.turret.TurretDefault();
        autonomousActions.ScoreSecondSample(drive);
        autonomousActions.ScoreThirdSample(drive);
        autonomousActions.actionBuilder.mecanisme.intake.turret.TurretAlternative();
        autonomousActions.actionBuilder.CollectSpecimen();

        autonomousActions.CollectSubmersible(drive);
        autonomousActions.actionBuilder.mecanisme.SpecimenCollectConfig();
        sleep(1000);
        autonomousActions.actionBuilder.SampleCollectUsingLimelight();

        autonomousActions.CollectSpecimenSubmersibleCollect(drive);

        while(opModeIsActive()) {
            autonomousActions.CollectSpecimen(drive);
        }
        autonomousActions.Reset(drive);

    }
}
