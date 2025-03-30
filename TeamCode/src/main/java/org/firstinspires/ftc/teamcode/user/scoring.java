package org.firstinspires.ftc.teamcode.user;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class scoring {
    // declarare motoare utilizate
    public Servo scoring_arm_left;
    public Servo scoring_arm_right;
    public Servo scoring_arm_extension;
    public Servo grip_transfer;
    public DistanceSensor senzor;
    // declarare/memorare pozitii utilizate
    public double senzor_colected=3;
    public double extension_retracted=0.61;
    public double extension_specimen_collect=0.47;
    public double extension_extended=0.25;
    public double extension_extended_specimen=0.15;
    public double extension_extended_max=0.12;

    public double gripper_hold=0.57;
    public double gripper_semi_hold=0.53;
    public double gripper_release=0.35  ;
    public double gripper_release_speci=0.42  ;
    public double scoring_arm_colect=0.21;
    public double scoring_arm_default=0.21;
    public double scoring_arm_basket=0.71;
    public double scoring_arm_basket_deep=0.79;
    public double scoring_arm_specimen_collect_first_cycle=0.88;
    public double scoring_arm_specimen_score=0.54 ;
    public double scoring_arm_specimen_first_cycle=0 ;
    public double scoring_arm_specimen_prepare=0.44 ;

    public double scoring_arm_auto_end_init=0.87 ;
    public double scoring_arm_auto_park=0;
    public double scoring_arm_specimen_collect=0.89
            ;
 public double scoring_arm_auto_init=0;
    public double scoring_arm_auto_park_basket=0 ;

    public scoring(HardwareMap hardwareMap){
        // detalierea modului de functionare a mecanismelor
        scoring_arm_left=hardwareMap.get(Servo.class,"scoring_arm_left");
        scoring_arm_right=hardwareMap.get(Servo.class,"scoring_arm_right");
        grip_transfer=hardwareMap.get(Servo.class,"grip_transfer");
         scoring_arm_extension=hardwareMap.get(Servo.class,"arm_extend");
     senzor=hardwareMap.get(DistanceSensor.class,"senzorsc");
    }
    public void score(double x){
        scoring_arm_left.setPosition(x);
        scoring_arm_right.setPosition(x);
    }
    public void init_auto_arms(){
        scoring_arm_left.setPosition(scoring_arm_auto_init);
        scoring_arm_right.setPosition(scoring_arm_auto_init);
    }
    public void park_basket(){
        scoring_arm_left.setPosition(scoring_arm_auto_park_basket);
        scoring_arm_right.setPosition(scoring_arm_auto_park_basket);
        grip_transfer.setPosition(gripper_hold);
    }
    public void scoring_arm_colect(){
        scoring_arm_left.setPosition(scoring_arm_colect);
        scoring_arm_right.setPosition(scoring_arm_colect);
    }
    public void scoring_arm_auto_init_end(){
        scoring_arm_left.setPosition(scoring_arm_auto_end_init);
        scoring_arm_right.setPosition(scoring_arm_auto_end_init);
    }
    public void scoring_arm_specimen_prepare(){
        scoring_arm_left.setPosition(scoring_arm_specimen_prepare);
        scoring_arm_right.setPosition(scoring_arm_specimen_prepare);
        scoring_arm_extension.setPosition(extension_extended_specimen);
    }

    public void scoring_arm_init(){
        scoring_arm_left.setPosition(scoring_arm_colect);
        scoring_arm_right.setPosition(scoring_arm_colect);
    }
    public void scoring_arm_default(){
        scoring_arm_left.setPosition(scoring_arm_default);
        scoring_arm_right.setPosition(scoring_arm_default);
    }
    public void scoring_arm_score_basket(){
        scoring_arm_left.setPosition(scoring_arm_basket);
        scoring_arm_right.setPosition(scoring_arm_basket);
    }
    public void scoring_arm_score_basket_deep(){
        scoring_arm_left.setPosition(scoring_arm_basket_deep);
        scoring_arm_right.setPosition(scoring_arm_basket_deep);
    }
    public void scoring_arm_score_specimen_score(){
        scoring_arm_left.setPosition(scoring_arm_specimen_score);
        scoring_arm_right.setPosition(scoring_arm_specimen_score);
    }
    public void scoring_arm_score_specimen_collect(){
        scoring_arm_left.setPosition(scoring_arm_specimen_collect);
        scoring_arm_right.setPosition(scoring_arm_specimen_collect);
        scoring_arm_extension.setPosition(extension_specimen_collect);
    }
    public void gripper(double x){
        grip_transfer.setPosition(x);
    }
    public void grip_transfer_grab(){
        grip_transfer.setPosition(gripper_hold);
    }
    public void grip_transfer_release(){
        grip_transfer.setPosition(gripper_release);
    }
    public void init_config(){
        scoring_arm_extension.setPosition(extension_retracted);
        scoring_arm_left.setPosition(scoring_arm_colect);
        scoring_arm_right.setPosition(scoring_arm_colect);
        grip_transfer.setPosition(gripper_release);


    }
    public class Init_config  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(true)

                init_config();

            return false;
        }

    }
    public class Gripper_grab  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            grip_transfer_grab();


            return false;
        }

    }
    public Action gripper_grab(){
        return new Gripper_grab();
    }
    public class Gripper_grab2  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            grip_transfer.setPosition(gripper_hold);


            return false;
        }

    }
    public Action gripper_grab_max(){
        return new Gripper_grab2();
    }
    public class Gripper_release  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            grip_transfer.setPosition(gripper_release);


            return false;
        }

    }
    public Action gripper_release(){
        return new Gripper_release();
    }
    public class Extension_retracted  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            scoring_arm_extension.setPosition(extension_specimen_collect);


            return false;
        }

    }
    public Action extension_retracted(){
        return new Extension_retracted();
    }
    public class Specimen_collect  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

                scoring_arm_score_specimen_collect();


            return false;
        }

    }
    public Action specimen_collect(){
        return new Specimen_collect();
    }
    public class Specimen_prepare  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


                scoring_arm_specimen_prepare();
            return false;
        }

    }
    public Action specimen_prepare(){
        return new Specimen_prepare();
    }
    public class AUTO_RESET  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                scoring_arm_auto_init_end();
                grip_transfer_release();
                scoring_arm_extension.setPosition(extension_retracted);


            return false;
        }

    }
    public Action auto_End(){
        return new scoring.AUTO_RESET();
    }
    public class Specimen_score  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
             scoring_arm_score_specimen_score();
            return false;
        }

    }
    public Action specimen_score(){
        return new Specimen_score();
    }


    public class Sample_collect  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

             scoring_arm_colect();
             scoring_arm_extension.setPosition(extension_retracted);
            return false;
        }

    }
    public Action sample_collect(){
        return new Sample_collect();
    }
    public class Sample_score  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

             scoring_arm_score_basket();
            scoring_arm_extension.setPosition(extension_extended);


            return false;
        }

    }
    public Action sample_score(){
        return new Sample_score();
    }
    public class Sample_score_auto  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

             scoring_arm_score_basket();
            scoring_arm_extension.setPosition(extension_extended_max);


            return false;
        }

    }
    public Action sample_score_auto(){
        return new Sample_score_auto();
    }
    public class Sample_score_retracted  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

             scoring_arm_score_basket();
            scoring_arm_extension.setPosition(extension_retracted);


            return false;
        }

    }
    public Action sample_score_retracted(){
        return new Sample_score_retracted();
    }
    public class speci_FIRST_collect  implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

             score(scoring_arm_specimen_collect_first_cycle);
        scoring_arm_extension.setPosition(extension_extended_max);


            return false;
        }

    }
    public Action specimen_first_collect(){
        return new speci_FIRST_collect();
    }
}
