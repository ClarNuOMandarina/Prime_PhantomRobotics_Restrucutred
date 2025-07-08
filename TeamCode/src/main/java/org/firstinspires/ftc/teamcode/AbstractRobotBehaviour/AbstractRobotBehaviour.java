package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public abstract class AbstractRobotBehaviour {
    protected TeleOpActions teleOpActions;
     Gamepad gamepad;

    public AbstractRobotBehaviour(TeleOpActions teleOpActions, Gamepad gamepad) {
        this.teleOpActions= teleOpActions;
        this.gamepad= gamepad;
    }

    public abstract RobotState UpdateBehaviour();
}
