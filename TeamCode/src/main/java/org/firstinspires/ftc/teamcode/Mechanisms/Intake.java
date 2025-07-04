package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake{
    public IntakeAngle angle;
    public IntakeTurret turret;
    public IntakeGripper gripper;
    public IntakeHeight height;
    public IntakeSensor sensor;
    public IntakeLight light;

    public Intake(HardwareMap hardwareMap) {
        angle = new IntakeAngle(hardwareMap);
        turret = new IntakeTurret(hardwareMap);
        gripper = new IntakeGripper(hardwareMap);
        height = new IntakeHeight(hardwareMap);
        sensor = new IntakeSensor(hardwareMap);
        light = new IntakeLight(hardwareMap);
    }

    public void InitConfig(){
        light.SetHighBasket();
        angle.VerticalAngle();
        turret.TurretAlternative();
        gripper.OpenGripper();
        height.HeightDefault();

    }
    public void DefenseConfig(){
        turret.TurretAlternative();
        gripper.OpenGripper();
        height.HeightSampleSecured();
    }
    public void SampleCollectConfig(){
        angle.HorizontalAngle();
        turret.TurretDefault();
        gripper.OpenGripper();
        height.HeightDefault();
    }
    public void TransferConfig(){
        angle.HorizontalAngle();
        height.HeightTransfer();
        turret.TurretDefault();
    }
    public void SpecimenCollectConfig(){
       SampleCollectConfig();
    }
    public void SecureSampleConfig(){
        turret.TurretAlternative();
        height.HeightSampleSecured();
        angle.VerticalAngle();
    }

    public void SampleScoreConfig(){
        DefenseConfig();
    }
    public void SpecimenScore(){
        DefenseConfig();
    }



}
