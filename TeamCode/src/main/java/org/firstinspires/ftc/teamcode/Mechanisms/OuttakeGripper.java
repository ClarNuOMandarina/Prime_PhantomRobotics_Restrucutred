package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeGripper{
    Servo OuttakeGripper;
    private double OpenGripperPosition=0.37;
    private double SemiClosedGripperPosition=0.52;
    private double ClosedGripperPosition=0.56;
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
    } public void SemiClosedGripper(){
        OuttakeGripper.setPosition(SemiClosedGripperPosition);
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
    public class SemiCloseGripper  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            ClosedGripper();
            return false;
        }

    }
    public Action SemiCloseGripperAction(){
        return new SemiCloseGripper();
    }
}
