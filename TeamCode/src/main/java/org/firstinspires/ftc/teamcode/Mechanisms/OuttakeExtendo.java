package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeExtendo{
    Servo OuttakeExtendoServo;
    private double BasketPosition=0.25;
    private double DeepBasketPosition=0.12;
    private double RetractedPosition=0.61;
    private double TransferPosition=RetractedPosition;
    private double SpecimenScorePosition=0.12;
    private double SpecimenCollectionPosition=0.47;
    public OuttakeExtendo(HardwareMap hardwareMap) {
        OuttakeExtendoServo=hardwareMap.get(Servo.class,"OuttakeExtendo");
    }
    public void ExtendoCalibration(double x){
        OuttakeExtendoServo.setPosition(x);
    }
    public void Transfer(){
        OuttakeExtendoServo.setPosition(TransferPosition);
    }
    public void BasketPosition(){
        OuttakeExtendoServo.setPosition(BasketPosition);
    }
    public void DeepBasketPosition(){
        OuttakeExtendoServo.setPosition(DeepBasketPosition);
    }
    public void Retracted(){
        OuttakeExtendoServo.setPosition(RetractedPosition);
    }
    public void SpecimenScore(){
        OuttakeExtendoServo.setPosition(SpecimenScorePosition);
    }
    public void SpecimenCollection(){
        OuttakeExtendoServo.setPosition(SpecimenCollectionPosition);
    }
}
