package org.firstinspires.ftc.teamcode.user;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class colection {

    public DistanceSensor senzor;

    public Servo colection_arm_left;
    public Servo colection_arm_right;
    public Servo gripper;
    public Servo gripper_rotation;
    public Servo gripper_angle;
    public double distance_to_collected_sample=2.2;
    public double colection_retracted=0.62;
    public double colection_extended=0.82;
    public double colection_extended_auto=0.82;
    public double colection_extended_auto_submersible=0.78;
    public double colection_drag=0.955;
    public double colection_default=0.76;
    public double colection_specimen=0.72;

    public double gripper_hold=0.35;
    public double gripper_release=0.58;
    public double gripper_release_auto=0.58;

    public double gripper_transfer=0.39;
    public double gripper_transfer_almost_open=0.39;

    public double gripper_rotation_score=0.7;
    public double gripper_rotation_drag=0.5;
    public double gripper_rotation_default=0.2;
    public double gripper_rotation_collect=0;

    double gripper_angle_default=0.02;
    double gripper_angle_drag=0.22;
    double gripper_angle_tranfer=0.91;
    double gripper_angle_vertical=0.22;
    double gripper_angle_auto=0.22;
    double gripper_angle_sample2=0.22;

    public colection(HardwareMap hardwareMap){
        colection_arm_left= hardwareMap.get(Servo.class,"colection_arm_left");
        colection_arm_right= hardwareMap.get(Servo.class,"colection_arm_right");
        gripper=hardwareMap.get(Servo.class,"gripper");
        gripper_rotation=hardwareMap.get(Servo.class,"gripper_rotation");
        gripper_angle=hardwareMap.get(Servo.class,"gripper_angle");
        senzor=hardwareMap.get(DistanceSensor.class,"senzor");

    }

    public void colection_arm(double x)
    {
        colection_arm_left.setPosition(x);
        colection_arm_right.setPosition(x);
    }

    public void gripper_grab()
    {
        gripper.setPosition(gripper_hold);
    }
    public void gripper_release()
    {
        gripper.setPosition(gripper_release);
    }
    public void scoring_config()
    {
        colection_arm(colection_retracted);
        gripper_rotation.setPosition(gripper_rotation_score);
        gripper_angle.setPosition(gripper_angle_tranfer);

    }
    public void collecting_config(){
        colection_arm(colection_extended);
        gripper_rotation.setPosition(gripper_rotation_collect);
        gripper.setPosition(gripper_release);


    }

    public void init_config(){
        colection_arm(colection_retracted);
        gripper.setPosition(gripper_release);
        gripper_rotation.setPosition(gripper_rotation_score);
        gripper_angle.setPosition(gripper_angle_tranfer);
    }
    public void start_config(){
        colection_arm(colection_default);
        gripper.setPosition(gripper_release);
        gripper_rotation.setPosition(gripper_rotation_collect);
        gripper_angle.setPosition(gripper_angle_default);
    }
    public void default_config(){
        colection_arm(colection_default);
        gripper.setPosition(gripper_release);
        gripper_rotation.setPosition(gripper_rotation_collect);
        gripper_angle.setPosition(gripper_angle_default);

    }
    public class AUTO_end  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(true)
            {
                init_config();

                return true;
            }
            return false;
        }

    }
    public Action auto_end(){
        return new AUTO_end();
    }
    public class Collecting_arm_collect  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            colection_arm(colection_extended);


            return false;
        }

    }
    public Action collecting_arm_collect(){
        return new Collecting_arm_collect();
    }
    public class Collecting_arm_score  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            colection_arm(colection_retracted);
            gripper.setPosition(gripper_transfer);
            gripper_angle.setPosition(gripper_angle_tranfer);
            gripper_rotation.setPosition(gripper_rotation_score);


            return false;
        }

    }
    public Action collecting_arm_score(){
        return new Collecting_arm_score();
    }
    public class Collecting_arm_default  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            colection_arm(colection_default);
            gripper.setPosition(gripper_release);
            gripper_angle.setPosition(gripper_angle_default);
            gripper_rotation.setPosition(gripper_rotation_collect);

            return false;
        }

    }
    public Action collecting_arm_default(){
        return new Collecting_arm_default();
    }
    public class Gripper_release  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {



            gripper.setPosition(gripper_release);

            return false;
        }

    }
    public Action griper_release(){
        return new Gripper_release();
    }


    public class Gripper_release_max  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {



            gripper.setPosition(gripper_release_auto);

            return false;
        }

    }
    public Action griper_release_max(){
        return new Gripper_release_max();
    }
}
