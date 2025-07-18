package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Specimenscore extends  AbstractRobotBehaviour{
    private boolean StrategyInitialized;
    private boolean ChangingState;
    private ElapsedTime BasicTimer;
    private double TimerLag=0.1;
    public Specimenscore(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

        StrategyInitialized=false;
        ChangingState=false;
        BasicTimer= new ElapsedTime();
    }

    @Override
    public RobotState UpdateBehaviour() {
        if(!StrategyInitialized) {

            if(BasicTimer.seconds()>TimerLag){
                teleOpActions.mecanisme.SpecimenScoreConfig();

            }

            if(BasicTimer.seconds()>0.4){
                StrategyInitialized=true;
                teleOpActions.mecanisme.outtake.gripper.ClosedGripper();
            }

        }

        if(gamepad.circle && StrategyInitialized)
        {
            teleOpActions.mecanisme.outtake.gripper.OpenGripper();
            ChangingState=true;
            BasicTimer.reset();
        }

        if(ChangingState){

            if(BasicTimer.seconds()>0.2){
                return RobotState.SPECIMENCOLLECT;
            }

        }

        return null;
    }
}
