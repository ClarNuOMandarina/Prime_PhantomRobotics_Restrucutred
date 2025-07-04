package org.firstinspires.ftc.teamcode.AbstractRobotMovement;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public abstract class AbstractRobotMovement {
    protected MecanumDrive drive;
    Gamepad gamepad;

    public AbstractRobotMovement(MecanumDrive drive, Gamepad gamepad) {
        this.drive = drive;
        this.gamepad=gamepad;
    }

    public abstract void update();
}
