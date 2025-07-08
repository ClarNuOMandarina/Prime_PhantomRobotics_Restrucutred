package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Defensive extends AbstractRobotBehaviour{
    public Defensive(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

    }

    @Override
    public RobotState UpdateBehaviour() {

        teleOpActions.mecanisme.DefenseConfig();
        return null;
    }
}
