package org.firstinspires.ftc.teamcode.AbstractRobotMovement;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class Standard extends AbstractRobotMovement {

    public Standard(MecanumDrive drive, Gamepad gamepad) {
        super(drive,gamepad);
    }

    @Override
    public void update() {

        drive.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        -gamepad.left_stick_y,
                        -gamepad.left_stick_x
                ),
                -gamepad.right_stick_x
        ));
    }
}
