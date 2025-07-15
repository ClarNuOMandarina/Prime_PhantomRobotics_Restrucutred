package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Outtake{
    private ElapsedTime BasicTimer = new ElapsedTime();
    public OuttakeExtendo extendo;
    public OuttakeGripper gripper;
    public OuttakeArms arms;

    public Outtake(HardwareMap hardwareMap){
        extendo= new OuttakeExtendo(hardwareMap);
        gripper= new OuttakeGripper(hardwareMap);
        arms= new OuttakeArms(hardwareMap);
    }
    public void InitConfig(){
        extendo.Retracted();
        gripper.OpenGripper();
        arms.Transfer();
    }
    public void DefenseConfig(){
        SampleCollectConfig();
    }

    public void SampleCollectConfig(){
        extendo.Retracted();
        arms.Transfer();
        gripper.OpenGripper();
    }
    public void SampleScoreConfig(){
        arms.BasketScore();

    }
    public void AutoSampleScoreConfig(){
//        arms.BasketScore();

    }
    public void SpecimenScoreConfig(){
        arms.SpecimenScore();
        extendo.SpecimenScore();
        gripper.ClosedGripper();
    }
    public void SpecimenCollectConfig(){
        arms.SpecimenCollection();
        extendo.SpecimenCollection();

    }

   public void TransferConfig(){
        gripper.OpenGripper();
        arms.Transfer();
        extendo.Transfer();
   }
   public void SecureSampleConfig(){
        SpecimenCollectConfig();
   }
    public void AutoInitSampleConfig(){
        extendo.Retracted();
        gripper.ClosedGripper();
        arms.AutoInitSample();
    }

}
