package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class IntakeSensor {
    private DistanceSensor sensor;
    public double SampleCollectedRange=1.7;
    public IntakeSensor(HardwareMap hardwareMap){
        sensor=hardwareMap.get(DistanceSensor.class,"IntakeSensor");
    }
    public double getSensorDistance(){
        return sensor.getDistance(DistanceUnit.CM);
    }
    public boolean IsCollected(){
        if(sensor.getDistance(DistanceUnit.CM)<SampleCollectedRange) return true;
        return false;
    }
}
