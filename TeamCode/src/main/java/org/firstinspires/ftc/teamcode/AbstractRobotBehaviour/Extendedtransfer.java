package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Extendedtransfer extends AbstractRobotBehaviour{
    private ElapsedTime BasicTimer;
    private boolean IsInitialized;
    private boolean IsExtendReady;
    public Extendedtransfer(Mecanisme mecanisme, Gamepad gamepad) {
        super(mecanisme, gamepad);
        BasicTimer= new ElapsedTime();
        IsInitialized=false;
        IsExtendReady=false;
    }

    @Override
    public RobotState UpdateBehaviour() {
    // initalizing all local variables
    if(!IsInitialized){
        mecanisme.Transfer();
        IsInitialized=true;
        BasicTimer.reset();
    }
    //Extendo in transfer pozition

    if(!IsExtendReady) {
        if(BasicTimer.seconds()>0.4){
            mecanisme.extendo.Transfer();

        }

            if(BasicTimer.seconds()>0.6){
                IsExtendReady=true;
                BasicTimer.reset();
            }



    }

    if(IsExtendReady){

            mecanisme.outtake.gripper.ClosedGripper();

            if(BasicTimer.seconds()>0.2){
                mecanisme.intake.gripper.OpenGripper();
            }

            if(BasicTimer.seconds()>0.3) {
                    return RobotState.SAMPLESCORE;
                }

            }





        return null;
    }
}
