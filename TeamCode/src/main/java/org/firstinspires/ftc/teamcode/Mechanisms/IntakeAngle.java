package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeAngle{
    private Servo IntakeAngleServo;
    private double HorizontalPoz=0.52;
    private double VerticalPoz=0.25;
    private double AutoThirdSamplePosition=0.2;


    public IntakeAngle(HardwareMap hardwareMap) {
        IntakeAngleServo=hardwareMap.get(Servo.class,"IntakeAngle");
    }
    public boolean IsHorizontal(){
        double isHorizontalTolerance = 0.05;
        if(IntakeAngleServo.getPosition()> HorizontalPoz- isHorizontalTolerance
                && IntakeAngleServo.getPosition()< HorizontalPoz+ isHorizontalTolerance)
            return true;

        return false;
    }
    public void AngleCallibration(double x){
        IntakeAngleServo.setPosition(x);
    }

    public void HorizontalAngle(){
        IntakeAngleServo.setPosition(HorizontalPoz);
    }
    public void VerticalAngle(){
        IntakeAngleServo.setPosition(VerticalPoz);
    }
    public void AutoThirdSample(){
        IntakeAngleServo.setPosition(AutoThirdSamplePosition);
    }
}
