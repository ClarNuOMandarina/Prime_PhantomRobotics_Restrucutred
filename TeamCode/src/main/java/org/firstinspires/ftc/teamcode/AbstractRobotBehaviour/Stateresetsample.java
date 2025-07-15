package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Stateresetsample extends AbstractRobotBehaviour{
    public Stateresetsample(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

    }

    @Override
    public RobotState UpdateBehaviour() {
        return RobotState.SAMPLECOLLECT;
    }
}
