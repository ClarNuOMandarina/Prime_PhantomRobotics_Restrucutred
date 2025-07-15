package org.firstinspires.ftc.teamcode.Actions;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;

public class AutonomousSampleActionBuilder {

    public Mecanisme mecanisme;
    public LimeLight limeLight;
    public AutonomousSampleActionBuilder( HardwareMap hardwareMap){
        mecanisme= new Mecanisme(hardwareMap);
        limeLight= new LimeLight(hardwareMap);

    }
    public void SampleCollectUsingLimelight(){
        mecanisme.intake.angle.AngleCallibration(limeLight.AngleMovement());
        mecanisme.intake.turret.TurretCalibration(limeLight.TurretMovement());
        mecanisme.extendo.ExtendoCallibration(limeLight.ExtendoMovement());
    }
    public class sampleCollectConfig  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.SampleCollectConfig();
            mecanisme.extendo.Extend();
            mecanisme.slides.Transfer();
            return false;
        }

    }

    public Action SampleCollectConfig(){
        return new sampleCollectConfig();
    }
    public class Extend  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.extendo.Extend();
            return false;
        }

    }

    public Action Extend(){
        return new Extend();
    }
    public class sampleCollectConfigFirstSample  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.SampleCollectConfig();
            mecanisme.slides.Transfer();
            return false;
        }

    }

    public Action SampleCollectConfigFirstSample(){
        return new sampleCollectConfigFirstSample();
    }
    public class sampleScoreConfig  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.AutoSampleScoreConfig();
            mecanisme.slides.HighBasket();
            return false;
        }

    }
    public Action SampleScoreConfig(){
        return new sampleScoreConfig();
    }

    public class collectThirdSample  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.AutoThirdSampleConfig();
            return false;
        }

    }
    public Action CollectThirdSample(){
        return new collectThirdSample();
    }
    public class sampleCollectSubmersibleConfig  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.SampleCollectConfig();
            mecanisme.slides.Transfer();
            return false;
        }

    }
    public Action SampleCollectSubmersibleConfig(){
        return new sampleCollectSubmersibleConfig();
    }
    public boolean CollectSampleSubmersible() throws InterruptedException {
        sleep(200);
        mecanisme.intake.height.HeightCollecting();
        sleep(200);
        mecanisme.intake.gripper.ClosedGripperSample();
        sleep(200);
        if(mecanisme.intake.sensor.IsCollected()) {
            mecanisme.intake.angle.HorizontalAngle();
            mecanisme.intake.turret.TurretDefault();
            mecanisme.intake.height.HeightTransfer();
            sleep(200);
            mecanisme.extendo.Extend();
            sleep(200);
            mecanisme.extendo.Transfer();
            sleep(200);
            if(mecanisme.intake.sensor.IsCollected()) {
                return true;
            }
        }

            mecanisme.intake.height.HeightDefault();
            mecanisme.intake.angle.HorizontalAngle();
            mecanisme.intake.turret.TurretDefault();
            mecanisme.intake.gripper.OpenGripper();
            mecanisme.extendo.Retracted();
            return false;

    }
    public void CollectSample() throws InterruptedException {
        sleep(200);
        mecanisme.intake.height.HeightCollecting();
        sleep(200);
        mecanisme.intake.gripper.ClosedGripperSample();
        sleep(200);
        mecanisme.intake.angle.HorizontalAngle();
        mecanisme.intake.turret.TurretDefault();
        mecanisme.intake.height.HeightTransfer();
        sleep(300);
        mecanisme.extendo.Transfer();
        sleep(200);
    }

}
