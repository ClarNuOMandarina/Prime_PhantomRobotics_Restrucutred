package org.firstinspires.ftc.teamcode.user;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
@TeleOp(name="Test")
public class test extends LinearOpMode {
    DigitalChannel breakbeamSensor ;
    @Override
    public void runOpMode() throws InterruptedException {
        breakbeamSensor = hardwareMap.get(DigitalChannel.class, "breakbeam"); // Update pin if needed
        breakbeamSensor.setMode(DigitalChannel.Mode.INPUT);
    waitForStart();
        while (opModeIsActive()) {
            // Read the state of the breakbeam sensor (HIGH means unbroken, LOW means broken)
            boolean isBeamBroken = !breakbeamSensor.getState();

            if (isBeamBroken) {
                telemetry.addData("Status", "Beam is broken");
            } else {
                telemetry.addData("Status", "Beam is unbroken");
            }

            telemetry.update();
        }

    }
}
