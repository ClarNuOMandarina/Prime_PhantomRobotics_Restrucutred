package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Init extends AbstractRobotBehaviour {

    public Init(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

    }

    @Override
    public RobotState UpdateBehaviour() {
        teleOpActions.mecanisme.InitConfig();
        return null;
    }

}
