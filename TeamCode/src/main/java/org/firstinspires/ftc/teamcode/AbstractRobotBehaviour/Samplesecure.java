package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Samplesecure extends AbstractRobotBehaviour{
    private boolean StrategyInitialized;
    private ElapsedTime BasicTimer = new ElapsedTime();
    private boolean ChangingState;
    private double TimerLag=0.2;
    public Samplesecure(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

        StrategyInitialized=false;
        ChangingState=false;
        BasicTimer= new ElapsedTime();

    }

    @Override
    public RobotState UpdateBehaviour() {
        if(!StrategyInitialized) {
            teleOpActions.mecanisme.SecureSampleConfig();
            StrategyInitialized=true;
        }

        if(gamepad.circle){
            teleOpActions.mecanisme.intake.gripper.OpenGripper();
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
