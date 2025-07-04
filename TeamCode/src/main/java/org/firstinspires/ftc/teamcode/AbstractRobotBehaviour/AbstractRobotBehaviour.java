package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public abstract class AbstractRobotBehaviour {
    protected Mecanisme mecanisme;
     Gamepad gamepad;

    public AbstractRobotBehaviour(Mecanisme mecanisme, Gamepad gamepad) {
        this.mecanisme = mecanisme;
        this.gamepad= gamepad;
    }

    public abstract RobotState UpdateBehaviour();
}
