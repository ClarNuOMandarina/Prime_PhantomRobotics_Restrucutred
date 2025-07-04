package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeHeight {
     private Servo IntakeHeightServo;
     private  double Colecting=0.94;
     private double Transfer=0.385;
     private double Default=0.735;
     private double SampleSecuredPosition=Default;
    public IntakeHeight(HardwareMap hardwareMap) {
    IntakeHeightServo=hardwareMap.get(Servo.class,"IntakeHeight");
    }
    public boolean IsCollecting(){
        double isCollectingTolerance = 0.05;
        if(IntakeHeightServo.getPosition()> Colecting- isCollectingTolerance
                && IntakeHeightServo.getPosition()< Colecting+ isCollectingTolerance)
            return true;

        return false;
    }
    public void HeightCallibration(double x){
        IntakeHeightServo.setPosition(x);
    }
    public void HeightCollecting(){
        IntakeHeightServo.setPosition(Colecting);
    }
    public void HeightDefault(){
        IntakeHeightServo.setPosition(Default);
    }
    public void HeightTransfer(){
        IntakeHeightServo.setPosition(Transfer);
    }
    public void HeightSampleSecured(){
        IntakeHeightServo.setPosition(SampleSecuredPosition);
    }

}
