package org.firstinspires.ftc.teamcode.Actions.SpecimenAutonomous;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Actions.SampleAutonomous.AutonomousSampleActionBuilder;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;
import org.firstinspires.ftc.teamcode.Mechanisms.Mecanisme;

public class AutonomousSpecimenActionBuilder {

    public Mecanisme mecanisme;
    public AutonomousSpecimenActionBuilder( HardwareMap hardwareMap){
        mecanisme= new Mecanisme(hardwareMap);

    }
    public void InitConfig(){
        mecanisme.SpecimenAutoInitConfig();
    }
    public void CollectSpecimenConfig(){
        mecanisme.SpecimenCollectAutoConfig();
    }
    public void ScoreSpecimenFirstSequence(){
        mecanisme.intake.SpecimenScoreAuto();
        mecanisme.slides.SpecimenScore();
    }
    public void ScoreSpecimenSecondSequence(){
        mecanisme.outtake.arms.SpecimenScore();
        mecanisme.outtake.gripper.ClosedGripper();
        mecanisme.outtake.extendo.SpecimenScore();
    }
    public void CollectSpecimen(){
        mecanisme.outtake.gripper.SemiClosedGripper();
        mecanisme.intake.gripper.OpenGripper();

    }
    public class collectSpecimen  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            CollectSpecimenConfig();
            return false;
        }

    }

    public Action CollectSpecimenConfigAction(){
        return new collectSpecimen();
    }

    public class collectFirstSpecimen  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            CollectSpecimenConfig();
            mecanisme.outtake.gripper.OpenGripper();
            mecanisme.outtake.extendo.SpecimenCollectionFirstCycle();
            mecanisme.intake.turret.TurretDefault();
            return false;
        }

    }

    public Action CollectFirstSpecimenAction(){
        return new collectFirstSpecimen();
    }
    public class scoreSpecimenFirstSequence  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            ScoreSpecimenFirstSequence();
            return false;
        }

    }

    public Action ScoreSpecimenFirstSeqAction(){
        return new scoreSpecimenFirstSequence();
    }
    public class scoreSpecimenSecondSequence  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            ScoreSpecimenSecondSequence();
            return false;
        }

    }

    public Action ScoreSpecimenSecondAction(){
        return new scoreSpecimenSecondSequence();
    }
    public class CollectSampleConfig  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mecanisme.intake.SampleCollectConfig();
            mecanisme.outtake.SpecimenCollectConfig();
            mecanisme.slides.SpecimenCollect();
            mecanisme.outtake.gripper.OpenGripper();
            return false;
        }

    }

    public Action CollectSampleConfig(){
        return new CollectSampleConfig();
    }

    public boolean CollectSample() throws InterruptedException {
        sleep(500);
        mecanisme.intake.height.HeightCollecting();
        sleep(300);
        mecanisme.intake.gripper.ClosedGripperSample();
        sleep(300);
        if(mecanisme.intake.sensor.IsCollected()) {
            mecanisme.intake.SecureSampleConfig();
            mecanisme.extendo.Retracted();
            return true;
        }

        mecanisme.intake.height.HeightDefault();
        mecanisme.intake.angle.HorizontalAngle();
        mecanisme.intake.turret.TurretDefault();
        mecanisme.intake.gripper.OpenGripper();
        mecanisme.extendo.Retracted();
        return false;
    }
}
