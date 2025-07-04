package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeArms{

    Servo LeftOuttakeArmServo;
    Servo RightOuttakeArmServo;
    private double TransferPosition=0.21;
    private double BasketPosition=0.71;
    private double SpecimenCollectionPosition=0.89;
    private double SpecimenScorePosition=TransferPosition;
    private double SpecimenAutoInitPosition=0;

    public OuttakeArms(HardwareMap hardwareMap) {
        LeftOuttakeArmServo=hardwareMap.get(Servo.class,"OuttakeArmLeft");
        RightOuttakeArmServo=hardwareMap.get(Servo.class,"OuttakeArmRight");
    }
    private void ArmMovement(double x){
        LeftOuttakeArmServo.setPosition(x);
        RightOuttakeArmServo.setPosition(x);
    }
    public void ArmsCalibration(double x){
        ArmMovement(x);
    }
    public void Transfer(){
       ArmMovement(TransferPosition);
    }
    public void BasketScore(){
        ArmMovement(BasketPosition);
    }
    public void SpecimenCollection(){
       ArmMovement(SpecimenCollectionPosition);
    }
    public void SpecimenScore(){
       ArmMovement(SpecimenScorePosition);
    }
    public void SpecimenAutoInit(){
        ArmMovement(SpecimenAutoInitPosition);
    }
}
