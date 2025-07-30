package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private DcMotorEx RightSlideMotor;
    private DcMotorEx LeftSlideMotor;
    private int InitPosition=0;
    private int HighBasketPosition=1060;
    private int LowBasketPosition=540;
    private int SpecimenScorePosition=570;
    private int SpecimenCollectionPosition=0;
    private int TransferPosition=0;
    private int SlideKillerThreshold=30;
    public boolean isSlideKIller;

    public Slides(HardwareMap hardwareMap){
        RightSlideMotor=hardwareMap.get(DcMotorEx.class,"RightSlideMotor");
        LeftSlideMotor=hardwareMap.get(DcMotorEx.class,"LeftSlideMotor");
        RightSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        isSlideKIller=false;
    }
    protected void SlideMovement(int x){
        RightSlideMotor.setTargetPosition(x);
        LeftSlideMotor.setTargetPosition(x);
//        if(x>30 || (x<30 &&RightSlideMotor.getCurrentPosition()>SlideKillerThreshold)){
            RightSlideMotor.setPower(1);
            LeftSlideMotor.setPower(1);
        RightSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        }
//        else {
//            LeftSlideMotor.setPower(0.2);
//            RightSlideMotor.setPower(0.2);
//        }
    }
    public void ResetEncoders(){
        RightSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public int getRightSlidePoz(){
        return RightSlideMotor.getCurrentPosition();
    }
    public int getLeftSlidePoz(){
        return LeftSlideMotor.getCurrentPosition();
    }
    public void SlideCalibration(int x){
        SlideMovement(x);
    }


    public void InitSlides()
    {
        SlideMovement(InitPosition);

    }

    public void SpecimenScore()
    {
        SlideMovement(SpecimenScorePosition);

    }
    public void SpecimenCollect()
    {
        SlideMovement(SpecimenCollectionPosition);

    }
    public void HighBasket(){
        SlideMovement(HighBasketPosition);
    }
    public void LowBasket(){
        SlideMovement(LowBasketPosition);
    }
    public void Transfer(){
        SlideMovement(TransferPosition);
    }
    public void SlideManualReset(float RightTrigger,float LeftTrigger,boolean condition){

        if(RightTrigger!=0){
            RightSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            LeftSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RightSlideMotor.setPower(-1);
            LeftSlideMotor.setPower(-1);

        }
        else if(LeftTrigger!=0){
            RightSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            LeftSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            LeftSlideMotor.setPower(1);
            RightSlideMotor.setPower(1);

        }
         if(condition){
            LeftSlideMotor.setPower(0);
            RightSlideMotor.setPower(0);
            RightSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            ResetEncoders();
        }
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
    public class ResetEncoder  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
ResetEncoders();
return false;
        }

    }
    public Action ResetEncoder(){
        return new ResetEncoder();
    }
    public class highBasket  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            HighBasket();
            return false;
        }

    }
    public Action HighBasketAction(){
        return new highBasket();
    }
}
