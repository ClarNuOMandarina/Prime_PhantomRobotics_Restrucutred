package org.firstinspires.ftc.teamcode.RobotStates;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.AbstractRobotMovement.AbstractRobotMovement;
import org.firstinspires.ftc.teamcode.MecanumDrive;

public enum RobotMovement {
    STANDARD(0),
    REDUCED(1);

    private final int value;

    RobotMovement(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RobotMovement fromValue(int value) throws IllegalAccessException {
        for (RobotMovement rm : RobotMovement.values()) {
            if (rm.getValue() == value) return rm;
        }
        throw new IllegalAccessException("Invalid movement choice");
    }

    public AbstractRobotMovement getStrategy(MecanumDrive drive, Gamepad gamepad) {
        try {
            String className = this.name().charAt(0) + this.name().substring(1).toLowerCase();
            String fullClassName = "org.firstinspires.ftc.teamcode.AbstractRobotMovement." + className;

            Class<?> strategyClass = Class.forName(fullClassName);
            return (AbstractRobotMovement) strategyClass.getDeclaredConstructor(MecanumDrive.class,Gamepad.class).newInstance(drive,gamepad);

        } catch (Exception e) {
            throw new RuntimeException("Movement strategy not found for " + this.name(), e);
        }
    }
}
