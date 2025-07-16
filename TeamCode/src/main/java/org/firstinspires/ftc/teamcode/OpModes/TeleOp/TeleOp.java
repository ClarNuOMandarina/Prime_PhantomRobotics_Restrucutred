package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AbstractRobotBehaviour.AbstractRobotBehaviour;
import org.firstinspires.ftc.teamcode.AbstractRobotMovement.AbstractRobotMovement;
import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.RobotStates.RobotMovement;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        TeleOpActions teleOpActions= new TeleOpActions(gamepad1,hardwareMap);

        PinpointDrive drive = new PinpointDrive(hardwareMap,new Pose2d(new Vector2d(0,0),Math.toRadians(0)));
        AbstractRobotMovement movement;
        RobotState currentState = RobotState.SAMPLECOLLECT;
        boolean DefenseActive=false;
        ElapsedTime DefenseSwitch = new ElapsedTime();
        AbstractRobotBehaviour behaviour = currentState.getStrategy(teleOpActions,gamepad1);
        teleOpActions.mecanisme.intake.light.SetHighBasket();
        while(!isStarted() && !isStopRequested()){
            teleOpActions.mecanisme.slides.InitSlides();
        }
        waitForStart();
        teleOpActions.mecanisme.slides.ResetEncoders();

        while(opModeIsActive()){
            // select high or low basket scoring
            if(gamepad1.dpad_up)teleOpActions.mecanisme.intake.light.SetHighBasket();

            if(gamepad1.dpad_down)teleOpActions.mecanisme.intake.light.SetLowBasket();
            // movement state update
            if(teleOpActions.mecanisme.extendo.getExtendedStatus()) movement=RobotMovement.REDUCED.getStrategy(drive,gamepad1);
            else movement=RobotMovement.STANDARD.getStrategy(drive,gamepad1);

            movement.update();

            // Robot behaviour update

            RobotState newState = behaviour.UpdateBehaviour();

            if (newState != null && newState != currentState) {
                currentState = newState;
                behaviour = currentState.getStrategy(teleOpActions,gamepad1);
            }

            // Robot safety mode
            if(gamepad1.share && !DefenseActive){

                if(currentState==RobotState.DEFENSIVE) {
                    currentState = RobotState.SAMPLECOLLECT;
                }
                else {
                    currentState = RobotState.DEFENSIVE;
                }
                behaviour = currentState.getStrategy(teleOpActions,gamepad1);

                DefenseActive=true;
                DefenseSwitch.reset();
            }

            if(DefenseActive){

                if(DefenseSwitch.seconds()>0.3)DefenseActive=false;

            }

            teleOpActions.mecanisme.slides.SlideKiller();
            telemetry.addData("isSlideKillerOn",teleOpActions.mecanisme.slides.getLeftSlidePoz()<10);
            telemetry.addData("Intake sensor distance to object",teleOpActions.mecanisme.intake.sensor.getSensorDistance());
            telemetry.addData("Current State",currentState);
            telemetry.addData("HighBasket Scoring",teleOpActions.mecanisme.intake.light.getBasketHeight());
            telemetry.update();
        }
    }
}
