package org.firstinspires.ftc.teamcode.Mechanisms;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Mecanisme {
    public Intake intake;
    public Outtake outtake;
    public Extendo extendo;
    public Slides slides;
    private final Gamepad gamepad;


    public Mecanisme(HardwareMap hardwareMap, Gamepad gamepad) {

        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);
        extendo = new Extendo(hardwareMap);
        slides = new Slides(hardwareMap);

        this.gamepad = gamepad;
    }

    public void InitConfig() {

        intake.InitConfig();
        outtake.InitConfig();
        extendo.Retracted();
        slides.InitSlides();
    }

    public void SampleCollectConfig() {
        intake.SampleCollectConfig();
        outtake.SampleCollectConfig();
        slides.InitSlides();
    }

    public void SampleScoreConfig() {
        intake.SampleScoreConfig();
        outtake.SampleScoreConfig();
        extendo.Retracted();
    }

    public void SpecimenScoreConfig() {
        intake.SpecimenScore();
        outtake.SpecimenScoreConfig();
        slides.SpecimenScore();
    }

    public void Transfer() {
        intake.TransferConfig();
        outtake.TransferConfig();
        slides.Transfer();
    }
    public void SpecimenCollectConfig(){
        SampleCollectConfig();
        outtake.SpecimenCollectConfig();
        slides.SpecimenCollect();
    }
    public void SecureSampleConfig(){

        intake.SecureSampleConfig();
        outtake.SecureSampleConfig();
        slides.InitSlides();
        extendo.Retracted();
    }

    public void HeightLowerControls() {

        if(gamepad.right_trigger!=0){
            intake.height.HeightCollecting();
        }
        else {
            intake.height.HeightDefault();
        }
    }
    public void AngleControl(boolean isAngleChanged, ElapsedTime BasicTimer){

        if(gamepad.right_bumper){
            BasicTimer.reset();
            isAngleChanged=true;
        }

        if(isAngleChanged){

            if(BasicTimer.seconds()>0.3){

                if(intake.angle.IsHorizontal()){
                    intake.angle.VerticalAngle();
                }
                else {
                    intake.angle.HorizontalAngle();
                }
                isAngleChanged=false;
            }

        }

    }
    public void ExtendoControl(){

        if(gamepad.left_trigger!=0) {
            extendo.Extend();
        }
        else{
                extendo.Retracted();
            }


    }


}
