package org.firstinspires.ftc.teamcode.user;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class extension {
    public Servo left_extension;
    public Servo right_extension;
    public double extension_extended=1;
    public double extension_retracted=0.75;
    public double extension_forced=0.7;
    public double extension_hang=0.87;
    public double sample_1=0.865;
    public double sample_drag=0.9;
    ElapsedTime timer = new ElapsedTime(0);

    public extension(HardwareMap hardwareMap)
    {

        left_extension =hardwareMap.get(Servo.class,"left_extension");
        right_extension =hardwareMap.get(Servo.class,"right_extension");
    }

    public void extend(double poz){
        right_extension.setPosition(poz);
        left_extension.setPosition(poz);

    }
    public void extend_forced(boolean param){
        timer.reset();
        right_extension.setPosition(extension_forced);
        left_extension.setPosition(extension_forced);
        param=true;
    }
    public void extend_forced_cond(boolean param){
        if(param) {
            if (timer.seconds() > 0.2) {
                extend(extension_extended);
                param = false;
            }
        }
    }
    public class Max_extension  implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


                extend(extension_extended);

                return true;

        }

    }
    public Action max_extension(){
        return new Max_extension();
    }

}
