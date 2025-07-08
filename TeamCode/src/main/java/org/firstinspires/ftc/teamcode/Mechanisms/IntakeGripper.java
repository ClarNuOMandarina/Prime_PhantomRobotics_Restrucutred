package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Actions.AutonomousActions;

public class IntakeGripper{
    private Servo IntakeGripperServo;
    private double ClosedPoz=0.86;
    private double OpenPoz=0.65;
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
    public class closeGripper  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            ClosedGripper();
            return false;
        }

    }
    public Action CloseGripperAction(){
        return new closeGripper();
    }
}