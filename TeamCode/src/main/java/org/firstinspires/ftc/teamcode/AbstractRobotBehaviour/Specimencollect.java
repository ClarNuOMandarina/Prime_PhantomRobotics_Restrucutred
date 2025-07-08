package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Actions.TeleOpActions;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Specimencollect extends AbstractRobotBehaviour{

    private boolean StrategyInitialized;
    private boolean CollectionCheck;
    private boolean AlternativeStateChange;
    private boolean IsAngleChanged;
    private ElapsedTime BasicTimer = new ElapsedTime();
    private boolean ChangingState;
    private double TimerLag=0.3;
    public Specimencollect(TeleOpActions teleOpActions, Gamepad gamepad) {
        super(teleOpActions,gamepad);

        StrategyInitialized=false;
        CollectionCheck=false;
        ChangingState=false;
        AlternativeStateChange=false;
        BasicTimer= new ElapsedTime();
        IsAngleChanged=false;

    }

    @Override
    public RobotState UpdateBehaviour() {
// Init behaviour
        if(!StrategyInitialized) {
            teleOpActions.mecanisme.SpecimenCollectConfig();
            StrategyInitialized=true;
        }

        //Change state from sample to specimen collection
        if(gamepad.options){
            ChangingState=true;
            BasicTimer.reset();
        }

        if(ChangingState)
        {
            if(BasicTimer.seconds() > 0.5) {
                return RobotState.SAMPLECOLLECT;
            }

        }
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
            teleOpActions.mecanisme.intake.gripper.ClosedGripper();

            if(BasicTimer.seconds() > TimerLag) {

                if (teleOpActions.mecanisme.intake.sensor.IsCollected()) {

                    return RobotState.SAMPLESECURE;
                }

                return RobotState.SPECIMENCOLLECT;
            }

        }

        if(gamepad.square && !teleOpActions.mecanisme.intake.height.IsCollecting()){
            teleOpActions.mecanisme.outtake.gripper.ClosedGripper();
            AlternativeStateChange=true;
            BasicTimer.reset();
        }

        if(AlternativeStateChange){

            if(BasicTimer.seconds() > TimerLag){
                return RobotState.SPECIMENSCORE;
            }

        }

        return null;
    }
}
