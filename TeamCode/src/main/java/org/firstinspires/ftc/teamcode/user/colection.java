package org.firstinspires.ftc.teamcode.user;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class colection {

    public DistanceSensor senzor;
    public Servo gripper;
    public Servo light;
    public Servo gripper_rotation;
    public Servo gripper_angle;
    public Servo gripper_height;
    public double detection_light=1;
    public double height_collecting=0.94;
    public double height_collecting_retracted=0.925;
    public double height_scanning=0.8;
    public double height_safe=0.6;
    public double height_default=0.735;
    public double height_transfer=0.385;
  //  public double height_observation=0.79;
    public double distance_to_collected_sample=3;

    public double gripper_hold=0.88;
    public double gripper_release=0.65;
    public double gripper_transfer=0.86;
    public double gripper_rotation_score=0.42;
    public double gripper_rotation_default=0.42;
    public double gripper_rotation_collect=0.42;
    public double gripper_rotation_score_sample=0.8;
    public double gripper_rotation_sample3=0.28;
    public double gripper_rotation_sample1_specimen=0.55;

    public double gripper_angle_default=0.52;
    double gripper_angle_tranfer=0.52;
    public double gripper_angle_vertical=0.25;
    public double gripper_angle_sample_observation=0.15;

    public colection(HardwareMap hardwareMap){
        gripper=hardwareMap.get(Servo.class,"gripper");
        gripper_height=hardwareMap.get(Servo.class,"gripper_height");
        gripper_angle=hardwareMap.get(Servo.class,"gripper_angle");
        gripper_rotation=hardwareMap.get(Servo.class,"gripper_rotation");
        light=hardwareMap.get(Servo.class,"light");
        senzor=hardwareMap.get(DistanceSensor.class,"senzor");

    }

public void detectio_light(){
        light.setPosition(detection_light);

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
        gripper_height.setPosition(height_transfer);
        gripper_rotation.setPosition(gripper_rotation_score);
        gripper_angle.setPosition(gripper_angle_tranfer);


    }
    public void collecting_config(){
        gripper_height.setPosition(height_collecting);

        gripper_rotation.setPosition(gripper_rotation_collect);
        gripper_angle.setPosition(gripper_angle_default);



    }

    public void init_config(){
        gripper_height.setPosition(height_default);
        gripper.setPosition(gripper_release);
        gripper_rotation.setPosition(gripper_rotation_score_sample);
        gripper_angle.setPosition(gripper_angle_tranfer);
    }

    public void default_config(){
        gripper_height.setPosition(height_default);
        gripper_rotation.setPosition(gripper_rotation_collect);
        gripper_angle.setPosition(gripper_angle_default);

    }




    public class Gripper_release_max  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {



            gripper.setPosition(gripper_release);

            return false;
        }

    }
    public Action griper_release_max(){
        return new Gripper_release_max();
    }
    public class Gripper_release  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {



            gripper.setPosition(gripper_release);

            return false;
        }

    }
    public Action griper_release(){
        return new Gripper_release_max();
    }
    public class third_Sample  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {



            gripper_angle.setPosition(0.72);
            gripper_rotation.setPosition(gripper_rotation_sample3);

            return false;
        }

    }
    public Action third_sample(){
        return new third_Sample();
    }
    public class first_Sample  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            gripper_angle.setPosition(0.38);
            gripper_rotation.setPosition(gripper_rotation_sample1_specimen);

            return false;
        }

    }
    public Action first_sample_spec(){
        return new first_Sample();
    }
    public class Rotation_default  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            gripper_rotation.setPosition(gripper_rotation_default);

            return false;
        }

    }
    public Action rotation_default(){
        return new Rotation_default();
    }
    public class Rotation_observation  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            gripper_angle.setPosition(gripper_angle_sample_observation);
            gripper_rotation.setPosition(gripper_rotation_score_sample);

            return false;
        }

    }
    public Action rotation_observation(){
        return new Rotation_observation();
    }


    public class Collecting_arm_score  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            gripper.setPosition(gripper_transfer);
            gripper_angle.setPosition(gripper_angle_tranfer);
            gripper_rotation.setPosition(gripper_rotation_score);
            gripper_height.setPosition(height_transfer);

            return false;
        }

    }
    public Action collecting_arm_score(){
        return new Collecting_arm_score();
    }
    public class Collecting_arm_default  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            gripper.setPosition(gripper_release);
            gripper_angle.setPosition(gripper_angle_default);
            gripper_rotation.setPosition(gripper_rotation_collect);
            gripper_height.setPosition(height_default);
            return false;
        }

    }
    public Action collecting_arm_default(){
        return new Collecting_arm_default();
    }
}
