package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeGripper{
    Servo OuttakeGripper;
    private double OpenGripperPosition=0.37;
    private double ClosedGripperPosition=0.56;
    private double SemiOpenGripperPosition=0.56;
    public OuttakeGripper(HardwareMap hardwareMap) {
        OuttakeGripper=hardwareMap.get(Servo.class,"OuttakeGripper");
    }
    public void GripperCalibration(double x){
        OuttakeGripper.setPosition(x);
    }
    public void OpenGripper(){
        OuttakeGripper.setPosition(OpenGripperPosition);
    }
    public void ClosedGripper(){
        OuttakeGripper.setPosition(ClosedGripperPosition);
    }
    public void SemiOpenGripper(){
        OuttakeGripper.setPosition(SemiOpenGripperPosition);
    }
}
