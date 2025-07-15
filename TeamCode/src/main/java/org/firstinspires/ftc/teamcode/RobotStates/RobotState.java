package org.firstinspires.ftc.teamcode.RobotStates;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.AbstractRobotBehaviour.AbstractRobotBehaviour;
import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;

public enum RobotState {
    INIT(0),
    SPECIMENCOLLECT(1),
    SAMPLESECURE(2),
    SPECIMENSCORE(3),
    SAMPLECOLLECT(4),
    SAMPLESCORE(5),
    DEFENSIVE(6),
    RETRACTEDTRANSFER(7),
    EXTENDEDTRANSFER(8),
    STATERESETSAMPLE(9),
    STATERESETSPECIMEN(10);
    private final int value;

    RobotState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RobotState fromValue(int value) throws IllegalAccessException {
        for (RobotState state : RobotState.values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        throw new IllegalAccessException("Invalid state choice");
    }

    public AbstractRobotBehaviour getStrategy(TeleOpActions teleOpActions, Gamepad gamepad) {
        try {
            String className = this.name().charAt(0) + this.name().substring(1).toLowerCase();
            String fullClassName = "org.firstinspires.ftc.teamcode.AbstractRobotBehaviour." + className;

            Class<?> strategyClass = Class.forName(fullClassName);
            return (AbstractRobotBehaviour) strategyClass.getDeclaredConstructor(TeleOpActions.class,Gamepad.class).newInstance(teleOpActions,gamepad);

        } catch (Exception e) {
            throw new RuntimeException("Behavior strategy not found for " + this.name(), e);
        }
    }

}
