package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
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

    public class heightCollecting  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            HeightCollecting();
            return false;
        }

    }
    public Action HeightCollectingAction(){
        return new heightCollecting();
    }
    public class heightTransfer  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            HeightTransfer();
            return false;
        }

    }
    public Action HeightTransferAction(){
        return new heightTransfer();
    }
    public class heightDefault  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            HeightDefault();
            return false;
        }

    }
    public Action HeightDefaultAction(){
        return new heightDefault();
    }

}
