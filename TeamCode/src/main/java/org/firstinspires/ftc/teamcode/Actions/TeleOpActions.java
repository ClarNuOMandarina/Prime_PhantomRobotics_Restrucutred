package org.firstinspires.ftc.teamcode.Actions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;

public class TeleOpActions {
    private final Gamepad gamepad;
    public Mecanisme mecanisme;

    public TeleOpActions(Gamepad gamepad, HardwareMap hardwareMap) {
        this.gamepad = gamepad;
        this.mecanisme= new Mecanisme(hardwareMap);
    }
    public void HeightLowerControls() {

        if(gamepad.right_trigger!=0){
            mecanisme.intake.height.HeightCollecting();
        }
        else {
            mecanisme.intake.height.HeightDefault();
        }
    }
    public void AngleControl(boolean isAngleChanged, ElapsedTime BasicTimer){

        if(gamepad.right_bumper){
            BasicTimer.reset();
            isAngleChanged=true;
        }

        if(isAngleChanged){

            if(BasicTimer.seconds()>0.3){

                if(mecanisme.intake.angle.IsHorizontal()){
                    mecanisme.intake.angle.VerticalAngle();
                }
                else {
                    mecanisme.intake.angle.HorizontalAngle();
                }
                isAngleChanged=false;
            }

        }

    }
    public void ExtendoControl(){

        if(gamepad.left_trigger!=0) {
           mecanisme.extendo.Extend();
        }
        else{
            mecanisme.extendo.Retracted();
        }


    }
}
