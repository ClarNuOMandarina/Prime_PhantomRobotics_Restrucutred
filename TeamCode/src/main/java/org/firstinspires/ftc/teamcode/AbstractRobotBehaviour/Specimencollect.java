package org.firstinspires.ftc.teamcode.AbstractRobotBehaviour;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;
import org.firstinspires.ftc.teamcode.RobotStates.RobotState;

public class Specimencollect extends AbstractRobotBehaviour{

    private boolean StrategyInitialized;
    private boolean CollectionCheck;
    private boolean AlternativeStateChange;
    private boolean IsAngleChanged;
    private ElapsedTime BasicTimer = new ElapsedTime();
    private boolean ChangingState;
    private double TimerLag=0.3;
    public Specimencollect(Mecanisme mecanisme, Gamepad gamepad) {
        super(mecanisme, gamepad);
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
            mecanisme.SpecimenCollectConfig();
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
        mecanisme.HeightLowerControls();

        //Gripper Angle
        if(gamepad.right_bumper){
            BasicTimer.reset();
            IsAngleChanged=true;
        }

        if(IsAngleChanged) {

            if (BasicTimer.seconds() > 0.1) {

                if (mecanisme.intake.angle.IsHorizontal()) {
                    mecanisme.intake.angle.VerticalAngle();
                } else {
                    mecanisme.intake.angle.HorizontalAngle();
                }
                IsAngleChanged = false;
            }
        }
        // Exendo control
        mecanisme.ExtendoControl();

        //Timer reset and collection sequqnce start
        if(gamepad.square && mecanisme.intake.height.IsCollecting()){
            BasicTimer.reset();
            CollectionCheck=true;
        }
        //Reset Collection after miss and switch to transfer class if collected
        if(CollectionCheck){
            mecanisme.intake.gripper.ClosedGripper();

            if(BasicTimer.seconds() > TimerLag) {

                if (mecanisme.intake.sensor.IsCollected()) {

                    return RobotState.SAMPLESECURE;
                }

                return RobotState.SPECIMENCOLLECT;
            }

        }

        if(gamepad.square && !mecanisme.intake.height.IsCollecting()){
            mecanisme.outtake.gripper.ClosedGripper();
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
