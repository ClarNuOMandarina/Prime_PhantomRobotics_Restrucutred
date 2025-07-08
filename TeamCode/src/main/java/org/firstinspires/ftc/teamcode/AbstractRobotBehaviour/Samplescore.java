package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;



import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Samplescore extends AbstractRobotBehaviour{
    private boolean StrategyInitialized;
    private ElapsedTime BasicTimer = new ElapsedTime();
    private boolean ChangingState;

    public Samplescore(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

        StrategyInitialized=false;
        ChangingState=false;
        BasicTimer= new ElapsedTime();

    }

    @Override
    public RobotState UpdateBehaviour() {

        if(!StrategyInitialized) {
            teleOpActions.mecanisme.SampleScoreConfig();
            StrategyInitialized=true;
        }

        if(teleOpActions.mecanisme.intake.light.getBasketHeight()){
            teleOpActions.mecanisme.slides.HighBasket();
        }
        else {
            teleOpActions.mecanisme.slides.LowBasket();
        }

        if(gamepad.right_trigger!=0){
            teleOpActions.mecanisme.outtake.extendo.DeepBasketPosition();
        }
        else{
            teleOpActions.mecanisme.outtake.extendo.BasketPosition();
        }

        if(gamepad.circle && !ChangingState){
            ChangingState=true;
            BasicTimer.reset();
        }

        if(ChangingState)
        {
            teleOpActions.mecanisme.outtake.gripper.OpenGripper();
            if(BasicTimer.seconds()>0.8){
                teleOpActions.mecanisme.outtake.arms.Transfer();
            }
            if(BasicTimer.seconds()>1.1) {
                return RobotState.SAMPLECOLLECT;
            }

        }
        return null;
    }
}
