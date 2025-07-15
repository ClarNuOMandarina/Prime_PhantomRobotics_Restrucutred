package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class IntakeGripper{
    private Servo IntakeGripperServo;
    private double ClosedPozSample=0.86;
    private double ClosedPozSpecimen=0.86;
    private double OpenPoz=0.65;
    public IntakeGripper(HardwareMap hardwareMap) {
        IntakeGripperServo=hardwareMap.get(Servo.class,"IntakeGripper");
    }
    public void GripperCallibration(double x){
        IntakeGripperServo.setPosition(x);
    }
    public void ClosedGripperSample(){
        IntakeGripperServo.setPosition(ClosedPozSample);
    }
    public void ClosedGripperSpecimen(){
        IntakeGripperServo.setPosition(ClosedPozSpecimen);
    }
    public void OpenGripper(){
        IntakeGripperServo.setPosition(OpenPoz);
    }

    public class openGripper  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            OpenGripper();
            return false;
        }

    }
    public Action OpenGripperAction(){
        return new openGripper();
    }
    public class closeGripperSpecimen  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            ClosedGripperSpecimen();
            return false;
        }

    }
    public Action CloseGripperSpecimenAction(){
        return new closeGripperSpecimen();
    }
    public class closeGripperSample  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            ClosedGripperSample();
            return false;
        }

    }
    public Action CloseGripperSampleAction(){
        return new closeGripperSample();
    }
}