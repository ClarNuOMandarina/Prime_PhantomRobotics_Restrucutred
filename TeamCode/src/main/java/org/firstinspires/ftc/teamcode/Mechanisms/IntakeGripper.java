package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeGripper{
    private Servo IntakeGripperServo;
    private double ClosedPoz=0.88;
    private double OpenPoz=0.65;
    private double SemiOpenPoz=0.86;
    public IntakeGripper(HardwareMap hardwareMap) {
        IntakeGripperServo=hardwareMap.get(Servo.class,"IntakeGripper");
    }
    public void GripperCallibration(double x){
        IntakeGripperServo.setPosition(x);
    }
    public void ClosedGripper(){
        IntakeGripperServo.setPosition(ClosedPoz);
    }
    public void OpenGripper(){
        IntakeGripperServo.setPosition(OpenPoz);
    }
    public void AlmostOpenGripper(){
        IntakeGripperServo.setPosition(SemiOpenPoz);
    }

}