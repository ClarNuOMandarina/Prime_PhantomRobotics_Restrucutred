package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Samplecollect extends AbstractRobotBehaviour{

    private boolean StrategyInitialized;
    private boolean CollectionCheck;
    private ElapsedTime BasicTimer = new ElapsedTime();
    private boolean ChangingState;
    private boolean IsAngleChanged;
    private double TimerLag=0.2;
    public Samplecollect(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

        StrategyInitialized=false;
        CollectionCheck=false;
        ChangingState=false;
        BasicTimer= new ElapsedTime();
        IsAngleChanged=false;
    }

    @Override
    public RobotState UpdateBehaviour() {
        // Init behaviour
        if(!StrategyInitialized) {
            teleOpActions.mecanisme.SampleCollectConfig();
            StrategyInitialized=true;
            teleOpActions.mecanisme.intake.gripper.OpenGripper();
        }

        //Change state from sample to specimen collection
        if(gamepad.options){
            ChangingState=true;
            BasicTimer.reset();
        }

        if(ChangingState)
        {
            if(BasicTimer.seconds()>0.5) {
                return RobotState.SPECIMENCOLLECT;
            }

        }

        // Height control
        teleOpActions.HeightLowerControls();

        //Gripper Angle
        if(gamepad.right_bumper){
            BasicTimer.reset();
            IsAngleChanged=true;
        }

        if(IsAngleChanged) {

            if (BasicTimer.seconds() > 0.1) {

                if (teleOpActions.mecanisme.intake.angle.IsHorizontal()) {
                    teleOpActions.mecanisme.intake.angle.VerticalAngle();
                } else {
                    teleOpActions.mecanisme.intake.angle.HorizontalAngle();
                }
                IsAngleChanged = false;
            }
        }
        // Exendo control
        teleOpActions.ExtendoControl();

        //Timer reset and collection sequqnce start
        if(gamepad.square && teleOpActions.mecanisme.intake.height.IsCollecting()){
            BasicTimer.reset();
            CollectionCheck=true;
        }
        //Reset Collection after miss and switch to transfer class if collected
        if(CollectionCheck){
            teleOpActions.mecanisme.intake.gripper.ClosedGripperSample();

            if(BasicTimer.seconds()>TimerLag) {

                if (teleOpActions.mecanisme.intake.sensor.IsCollected()) {
                    if(teleOpActions.mecanisme.extendo.getExtendedStatus())
                        return RobotState.EXTENDEDTRANSFER;

                    return RobotState.RETRACTEDTRANSFER;

                }

                return RobotState.STATERESETSAMPLE;
            }

        }


        return null;
    }
}
