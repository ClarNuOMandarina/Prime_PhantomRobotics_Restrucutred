package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Defensive extends AbstractRobotBehaviour{
    public Defensive(Mecanisme mecanisme, Gamepad gamepad) {
        super(mecanisme,gamepad);

    }

    @Override
    public RobotState UpdateBehaviour() {

        return null;
    }
}
