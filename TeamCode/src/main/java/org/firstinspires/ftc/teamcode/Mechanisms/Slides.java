package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private DcMotorEx RightSlideMotor;
    private DcMotorEx LeftSlideMotor;
    private int InitPosition=0;
    private int HighBasketPosition=770;
    private int LowBasketPosition=390;
    private int SpecimenScorePosition=440;
    private int SpecimenCollectionPosition=0;
    private int TransferPosition=0;

    public Slides(HardwareMap hardwareMap){
        RightSlideMotor=hardwareMap.get(DcMotorEx.class,"RightSlideMotor");
        LeftSlideMotor=hardwareMap.get(DcMotorEx.class,"LeftSlideMotor");
        RightSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    protected void SlideMovement(int x){
        RightSlideMotor.setTargetPosition(x);
        LeftSlideMotor.setTargetPosition(x);
        RightSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightSlideMotor.setPower(1);
        LeftSlideMotor.setPower(1);
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

}
