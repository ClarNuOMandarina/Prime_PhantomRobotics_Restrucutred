package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Init extends AbstractRobotBehaviour {

    public Init(Mecanisme mecanisme, Gamepad gamepad) {
        super(mecanisme,gamepad);
    }

    @Override
    public RobotState UpdateBehaviour() {
    mecanisme.InitConfig();
        return null;
    }

}
