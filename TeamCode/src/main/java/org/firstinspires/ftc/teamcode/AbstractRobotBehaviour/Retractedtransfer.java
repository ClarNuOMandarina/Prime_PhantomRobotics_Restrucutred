package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Retractedtransfer extends AbstractRobotBehaviour{
    private ElapsedTime BasicTimer;
    private boolean IsInitialized;
    private boolean IsExtendReady;
    public Retractedtransfer(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

        BasicTimer= new ElapsedTime();
        IsInitialized=false;
        IsExtendReady=false;
    }

    @Override
    public RobotState UpdateBehaviour() {
        // initalizing all local variables
        if(!IsInitialized){
            teleOpActions.mecanisme.Transfer();
            IsInitialized=true;
            BasicTimer.reset();
        }
        //Extendo in transfer pozition

        if(!IsExtendReady) {

            if (BasicTimer.seconds() < 0.35) {
                teleOpActions.mecanisme.extendo.Extend();
            }

            if(BasicTimer.seconds()>0.4) {
                teleOpActions.mecanisme.extendo.Transfer();
            }

            if(BasicTimer.seconds()>0.5){
                IsExtendReady=true;
                BasicTimer.reset();
            }



        }

        if(IsExtendReady){

            teleOpActions.mecanisme.outtake.gripper.ClosedGripper();

            if(BasicTimer.seconds()>0.2){
                teleOpActions.mecanisme.intake.gripper.OpenGripper();
            }

            if(BasicTimer.seconds()>0.3) {
                return RobotState.SAMPLESCORE;
            }

        }





        return null;
    }
}
