package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.SampleAutonomous.AutonomousSampleActions;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;
import org.firstinspires.ftc.teamcode.PinpointDrive;
@Autonomous(name= "Sample Autonomous")
public class SampleAutonomous extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        AutonomousSampleActions autonomousActions = new AutonomousSampleActions(hardwareMap, gamepad1);
        autonomousActions.actionBuilder.mecanisme.slides.ResetEncoders();
        autonomousActions.actionBuilder.mecanisme.AutoInitSample();
        PinpointDrive drive = new PinpointDrive(hardwareMap, autonomousActions.sampleFieldMap.initialPose);
        ElapsedTime BasicTimer= new ElapsedTime();


        waitForStart();

        autonomousActions.PreloadScore(drive);
        sleep(600);
        autonomousActions.actionBuilder.mecanisme.outtake.gripper.OpenGripper();
        sleep(200);
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
        BasicTimer.reset();
        while (!isCollected && opModeIsActive()) {
            boolean condition=false;
            while(BasicTimer.seconds()<0.4 && !condition){
                if(autonomousActions.actionBuilder.limeLight.is_detecting()){
                    condition=true;
                }
            }
            if (condition) {
                double anglepoz=0.52;
                double turretpoz=0.43;
                double extendopoz=0.69;
                BasicTimer.reset();
                while(BasicTimer.seconds()<0.2){
                    anglepoz=autonomousActions.actionBuilder.limeLight.AngleMovement(autonomousActions.actionBuilder.limeLight);
                    turretpoz=autonomousActions.actionBuilder.limeLight.TurretMovement(autonomousActions.actionBuilder.limeLight);
                    extendopoz=autonomousActions.actionBuilder.limeLight.ExtendoMovement(autonomousActions.actionBuilder.limeLight);
                }
                autonomousActions.actionBuilder.mecanisme.intake.angle.AngleCallibration(anglepoz);
                autonomousActions.actionBuilder.mecanisme.intake.turret.TurretCalibration(turretpoz);
                autonomousActions.actionBuilder.mecanisme.extendo.ExtendoCallibration(extendopoz);
                if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                    isCollected = true;
                }

            } else {
                y += 3;

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
        BasicTimer.reset();
        while (!isCollected && opModeIsActive()) {
            boolean condition=false;
            while(BasicTimer.seconds()<0.4 && !condition){
                if(autonomousActions.actionBuilder.limeLight.is_detecting()){
                    condition=true;
                }
            }
            if (condition) {
                double anglepoz=0.52;
                double turretpoz=0.43;
                double extendopoz=0.69;
                BasicTimer.reset();
                while(BasicTimer.seconds()<0.2){
                    anglepoz=autonomousActions.actionBuilder.limeLight.AngleMovement(autonomousActions.actionBuilder.limeLight);
                    turretpoz=autonomousActions.actionBuilder.limeLight.TurretMovement(autonomousActions.actionBuilder.limeLight);
                    extendopoz=autonomousActions.actionBuilder.limeLight.ExtendoMovement(autonomousActions.actionBuilder.limeLight);
                }
                autonomousActions.actionBuilder.mecanisme.intake.angle.AngleCallibration(anglepoz);
                autonomousActions.actionBuilder.mecanisme.intake.turret.TurretCalibration(turretpoz);
                autonomousActions.actionBuilder.mecanisme.extendo.ExtendoCallibration(extendopoz);
                if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                    isCollected = true;
                }

            } else {
                y += 3;

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

        BasicTimer.reset();
        while (!isCollected && opModeIsActive()) {
            boolean condition=false;
            while(BasicTimer.seconds()<0.4 && !condition){
                if(autonomousActions.actionBuilder.limeLight.is_detecting()){
                    condition=true;
                }
            }
            if (condition) {
                double anglepoz=0.52;
                double turretpoz=0.43;
                double extendopoz=0.69;
                BasicTimer.reset();
                while(BasicTimer.seconds()<0.2){
                    anglepoz=autonomousActions.actionBuilder.limeLight.AngleMovement(autonomousActions.actionBuilder.limeLight);
                    turretpoz=autonomousActions.actionBuilder.limeLight.TurretMovement(autonomousActions.actionBuilder.limeLight);
                    extendopoz=autonomousActions.actionBuilder.limeLight.ExtendoMovement(autonomousActions.actionBuilder.limeLight);
                }
                autonomousActions.actionBuilder.mecanisme.intake.angle.AngleCallibration(anglepoz);
                autonomousActions.actionBuilder.mecanisme.intake.turret.TurretCalibration(turretpoz);
                autonomousActions.actionBuilder.mecanisme.extendo.ExtendoCallibration(extendopoz);
                if (autonomousActions.actionBuilder.CollectSampleSubmersible()) {
                    isCollected = true;
                }

            } else {
                y += 3;

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

        autonomousActions.Reset(drive);
    }

}
