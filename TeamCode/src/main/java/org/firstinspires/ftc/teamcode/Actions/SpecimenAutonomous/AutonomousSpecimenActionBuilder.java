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
    public LimeLight limeLight;
    public AutonomousSpecimenActionBuilder( HardwareMap hardwareMap){
        mecanisme= new Mecanisme(hardwareMap);
        limeLight= new LimeLight(hardwareMap);

    }
    public void SampleCollectUsingLimelight() throws InterruptedException {
        mecanisme.intake.angle.AngleCallibration(limeLight.AngleMovement());
        mecanisme.intake.turret.TurretCalibration(limeLight.TurretMovement());
        mecanisme.extendo.ExtendoCallibration(limeLight.ExtendoMovement());
        sleep(400);
        CollectSample();
    }
    public void InitConfig(){
        mecanisme.SpecimenAutoInitConfig();
    }
    public void CollectSpecimenConfig(){
        mecanisme.SpecimenCollectAutoConfig();
    }
    public void ScoreSpecimen(){
        mecanisme.SpecimenScoreConfig();
    }
    public void CollectSpecimen(){
        mecanisme.outtake.gripper.SemiClosedGripper();

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
            mecanisme.outtake.extendo.SpecimenCollectionFirstCycle();
            return false;
        }

    }

    public Action CollectFirstSpecimenAction(){
        return new collectFirstSpecimen();
    }
    public class scoreSpecimen  implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            ScoreSpecimen();
            return false;
        }

    }

    public Action ScoreSpecimenAction(){
        return new scoreSpecimen();
    }

    public void CollectSample() throws InterruptedException {
        sleep(200);
        mecanisme.intake.height.HeightCollecting();
        sleep(200);
        mecanisme.intake.gripper.ClosedGripperSample();
        sleep(200);
        mecanisme.intake.SecureSampleConfig();
        mecanisme.extendo.Retracted();
    }
}
