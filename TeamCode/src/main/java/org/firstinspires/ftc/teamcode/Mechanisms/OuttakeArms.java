package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeArms{

    Servo LeftOuttakeArmServo;
    Servo RightOuttakeArmServo;
    private double TransferPosition=0.23;
    private double BasketPosition=0.73;
    private double SpecimenCollectionPosition=0.91;
    private double SpecimenScorePosition=0.21;
    private double AutoInitSamplePosition=TransferPosition;
    private double AutoInitSpecimenPosition=SpecimenCollectionPosition;

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
    public void AutoInitSample(){
        ArmMovement(AutoInitSamplePosition);
    }
    public void AutoInitSpecimen(){
        ArmMovement(AutoInitSpecimenPosition);
    }
    public class basketScore  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            BasketScore();
            return false;
        }

    }
    public Action BasketScoreAction(){
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
