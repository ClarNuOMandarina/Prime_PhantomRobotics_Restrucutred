package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Samplesecure extends AbstractRobotBehaviour{
    private boolean StrategyInitialized;
    private ElapsedTime BasicTimer = new ElapsedTime();
    private boolean ChangingState;
    private double TimerLag=0.2;
    public Samplesecure(Mecanisme mecanisme, Gamepad gamepad)
    {
        super(mecanisme, gamepad);
        StrategyInitialized=false;
        ChangingState=false;
        BasicTimer= new ElapsedTime();

    }

    @Override
    public RobotState UpdateBehaviour() {
        if(!StrategyInitialized) {
            mecanisme.SecureSampleConfig();
            StrategyInitialized=true;
        }

        if(gamepad.circle){
            mecanisme.intake.gripper.OpenGripper();
            BasicTimer.reset();
            ChangingState=true;
        }

        if(ChangingState)
        {
            if(BasicTimer.seconds() > TimerLag)
            {
                return RobotState.SPECIMENCOLLECT;
            }

        }

        return null;

    }
}
