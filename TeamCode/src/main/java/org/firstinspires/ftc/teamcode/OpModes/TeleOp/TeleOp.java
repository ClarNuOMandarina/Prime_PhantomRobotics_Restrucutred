package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.AbstractRobotBehaviour.AbstractRobotBehaviour;
import org.firstinspires.ftc.teamcode.AbstractRobotMovement.AbstractRobotMovement;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.RobotStates.RobotMovement;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Mecanisme mecanisme= new Mecanisme(hardwareMap,gamepad1);

        PinpointDrive drive = new PinpointDrive(hardwareMap,new Pose2d(new Vector2d(0,0),Math.toRadians(0)));
        AbstractRobotMovement movement;
        RobotState currentState = RobotState.SAMPLECOLLECT;
        AbstractRobotBehaviour behaviour = currentState.getStrategy(mecanisme,gamepad1);
        mecanisme.intake.light.SetHighBasket();
        while(!isStarted() && !isStopRequested()){
            mecanisme.slides.InitSlides();
        }
        waitForStart();


        while(opModeIsActive()){
            // select high or low basket scoring
            if(gamepad1.dpad_up)mecanisme.intake.light.SetHighBasket();

            if(gamepad1.dpad_down)mecanisme.intake.light.SetLowBasket();
            // movement state update
            if(mecanisme.extendo.getExtendedStatus()) movement=RobotMovement.REDUCED.getStrategy(drive,gamepad1);
            else movement=RobotMovement.STANDARD.getStrategy(drive,gamepad1);

            movement.update();

            // Robot behaviour update

             RobotState newState = behaviour.UpdateBehaviour();

            if (newState != null && newState != currentState) {
                currentState = newState;
                behaviour = currentState.getStrategy(mecanisme,gamepad1);
            }

            telemetry.addData("Intake sensor distance to object",mecanisme.intake.sensor.getSensorDistance());
            telemetry.update();
        }
    }
}
