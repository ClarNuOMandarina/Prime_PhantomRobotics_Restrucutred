package org.firstinspires.ftc.teamcode.Mechanisms;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Mecanisme {
    public Intake intake;
    public Outtake outtake;
    public Extendo extendo;
    public Slides slides;


    public Mecanisme(HardwareMap hardwareMap) {

        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);
        extendo = new Extendo(hardwareMap);
        slides = new Slides(hardwareMap);

    }

    public void InitConfig() {

        intake.InitConfig();
        outtake.InitConfig();
        extendo.Retracted();
        slides.InitSlides();
    }
    public void DefenseConfig(){
        intake.DefenseConfig();
        outtake.DefenseConfig();
        slides.InitSlides();
        extendo.Retracted();
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
    public void AutoSampleScoreConfig() {
        intake.AutoSampleScoreConfig();
        outtake.AutoSampleScoreConfig();
        extendo.Retracted();
    }
    public void SampleScoreAutoConfig() {
        intake.SampleScoreConfig();
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
    public void AutoInitSample(){
        intake.AutoInitSampleConfig();
        outtake.AutoInitSampleConfig();
        extendo.Retracted();
        slides.Transfer();
    }
    public void AutoThirdSampleConfig(){
        intake.AutoThirdSampleConfig();
        outtake.TransferConfig();
        extendo.Extend();
        slides.Transfer();
    }

}
