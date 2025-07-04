package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Extendo {
    Servo RightExtendo;
    Servo LeftExtendo;
    private double ExtendedPosition=1;
    private double RetractedPosition=0.69;
    private double TransferPosition=0.83;

    public Extendo(HardwareMap hardwareMap) {
        RightExtendo=hardwareMap.get(Servo.class,"RightExtendo");
        LeftExtendo=hardwareMap.get(Servo.class,"LeftExtendo");

    }
    public boolean getExtendedStatus(){
        double isExtendedTolerance = 0.05;
        if(RightExtendo.getPosition()> ExtendedPosition- isExtendedTolerance
                && RightExtendo.getPosition()< ExtendedPosition+ isExtendedTolerance)
            return true;

        return false;
    }
    private void ExtendoMovement(double poz){
        RightExtendo.setPosition(poz);
        LeftExtendo.setPosition(poz);
    }
    public void ExtendoCallibration(double x){
        ExtendoMovement(x);
    }
    public void Extend(){
        ExtendoMovement(ExtendedPosition);
    }
    public void Retracted(){
        ExtendoMovement(RetractedPosition);
    }
    public void Transfer(){
        ExtendoMovement(TransferPosition);
    }

}
