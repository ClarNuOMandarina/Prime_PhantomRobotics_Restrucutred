package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeExtendo{
    Servo OuttakeExtendoServo;
    private double BasketPosition=0.30;
    private double DeepBasketPosition=0.20;
    private double RetractedPosition=0.61;
    private double TransferPosition=RetractedPosition;
    private double SpecimenScorePosition=0.12;
    private double SpecimenCollectionPosition=RetractedPosition;
    private double SpecimenCollectionFirstCyclePosition=RetractedPosition;
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
    public void SpecimenCollectionFirstCycle(){
        OuttakeExtendoServo.setPosition(SpecimenCollectionFirstCyclePosition);
    }
    public class basketScore  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            DeepBasketPosition();
            return false;
        }

    }
    public Action BasketScore(){
        return new basketScore();
    }
    public class transfer  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            Transfer();
            return false;
        }

    }
    public Action TransferAction(){
        return new transfer();
    }
}
