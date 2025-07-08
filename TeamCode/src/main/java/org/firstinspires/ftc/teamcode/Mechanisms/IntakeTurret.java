package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeTurret{
    Servo IntakeTurretServo;
    private double DefaultPosition=0.405;
    private double AlternativePosition=0.8;
    private double AutoThirdSamplePosition=0.25;
    public IntakeTurret(HardwareMap hardwareMap) {
    IntakeTurretServo=hardwareMap.get(Servo.class,"IntakeTurret");
    }
    public void TurretCalibration(double x){
        IntakeTurretServo.setPosition(x);
    }
    public void TurretDefault(){
        IntakeTurretServo.setPosition(DefaultPosition);
    }
    public void TurretAlternative(){
        IntakeTurretServo.setPosition(AlternativePosition);
    }
    public void AutoThirdSampleCollect(){
        IntakeTurretServo.setPosition(AutoThirdSamplePosition);
    }
}
