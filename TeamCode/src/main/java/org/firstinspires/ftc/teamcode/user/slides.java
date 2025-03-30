package org.firstinspires.ftc.teamcode.user;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class slides  {
    public DcMotorEx left_slide,right_slide;


// pozitii culisanta
    public int slides_init=0;
    public int slides_low_basket=525;
    public int slides_high_basket=1050;
    public int slides_specimen_high=60;
    public int slides_specimen_high_score=60 ;
    public int slide_auto_parking_new=0;

    public slides(HardwareMap hardwareMap){
        // declarari motoare si modul lor de functionare

        left_slide=hardwareMap.get(DcMotorEx.class,"left_slide");
        right_slide=hardwareMap.get(DcMotorEx.class,"right_slide");
        right_slide.setDirection(DcMotorSimple.Direction.REVERSE);
        left_slide.setDirection(DcMotorSimple.Direction.REVERSE);
        left_slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void reset_encoder(){
        left_slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public void culisante(int x)  {

        // functie generala pt uzul culisantelor

        left_slide.setTargetPosition(x);
        right_slide.setTargetPosition(x);
        left_slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_slide.setPower(1);
        right_slide.setPower(1);



    }


    public class Slide_init  implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            culisante(slides_init);


            return false;
        }

    }
    public Action slide_init(){
        return new Slide_init();
    }
    public class Slide_specimen_score  implements Action{
        // actiune pentru a misca glisierele pe durata autonomiei(pe durata traiectorilor)
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            culisante(slides_specimen_high);
            return false;
        }

    }
    public Action specimen_score_high(){
        return new Slide_specimen_score();
    }
    public class Slide_specimen_score2  implements Action{
        // actiune pentru a misca glisierele pe durata autonomiei(pe durata traiectorilor)
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            culisante(slides_specimen_high);
            return false;
        }

    }
    public Action specimen_score_high2(){
        return new Slide_specimen_score2();
    }

    public class Slide_sample  implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            culisante(slides_high_basket);


            return false;
        }

    }
    public Action slide_sample(){
        return new Slide_sample();
    }
    public class Auto_park_basket  implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

        culisante(slide_auto_parking_new);


            return false;
        }

    }
    public Action auto_park_basket() {
        return new Auto_park_basket();
    }

}