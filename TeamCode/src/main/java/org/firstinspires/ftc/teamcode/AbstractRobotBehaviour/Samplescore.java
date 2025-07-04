package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;



import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Samplescore extends AbstractRobotBehaviour{
    private boolean StrategyInitialized;
    private ElapsedTime BasicTimer = new ElapsedTime();
    private boolean ChangingState;

    public Samplescore(Mecanisme mecanisme, Gamepad gamepad) {
        super(mecanisme, gamepad);
        StrategyInitialized=false;
        ChangingState=false;
        BasicTimer= new ElapsedTime();

    }

    @Override
    public RobotState UpdateBehaviour() {

        if(!StrategyInitialized) {
            mecanisme.SampleScoreConfig();
            StrategyInitialized=true;
        }

        if(mecanisme.intake.light.getBasketHeight()){
            mecanisme.slides.HighBasket();
        }
        else {
            mecanisme.slides.LowBasket();
        }

        if(gamepad.right_trigger!=0){
            mecanisme.outtake.extendo.DeepBasketPosition();
        }
        else{
            mecanisme.outtake.extendo.BasketPosition();
        }

        if(gamepad.circle && !ChangingState){
            ChangingState=true;
            BasicTimer.reset();
        }

        if(ChangingState)
        {
            mecanisme.outtake.gripper.OpenGripper();
            if(BasicTimer.seconds()>0.2){
                mecanisme.outtake.arms.Transfer();
            }
            if(BasicTimer.seconds()>0.5) {
                return RobotState.SAMPLECOLLECT;
            }

        }
        return null;
    }
}
