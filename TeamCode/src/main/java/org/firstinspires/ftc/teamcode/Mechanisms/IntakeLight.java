package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeLight {
    private Servo Light;
    private double DetectionLightPosition=1;
    private double IsHighBasketScoring=1;
    private double IsLowBasketScoring=0.2;
    public IntakeLight(HardwareMap hardwareMap){
        Light= hardwareMap.get(Servo.class,"Light");
    }
    public void SetDetectionLight(){
        Light.setPosition(DetectionLightPosition);
    }
    public void SetHighBasket(){
        SetDetectionLight();
    }
    public void SetLowBasket(){
        Light.setPosition(IsLowBasketScoring);
    }
    public boolean getBasketHeight(){

        if(Light.getPosition()>DetectionLightPosition-0.1) {
            return true;
        }
        else return false;

    }
    public void LightCalibration(double x){
        Light.setPosition(x);
    }
}
