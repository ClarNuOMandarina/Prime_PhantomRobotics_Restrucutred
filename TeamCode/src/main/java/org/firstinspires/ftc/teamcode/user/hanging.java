package org.firstinspires.ftc.teamcode.user;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class hanging {
    public Servo right_hang,left_hang;
    public double hanged=1;
    public double unhanged=1;
    public double stop=0.5;

    public void hang(double x){
        right_hang.setPosition(x);
        left_hang.setPosition(x);
    }

    public hanging(HardwareMap hardwareMap){
        right_hang=hardwareMap.get(Servo.class,"rhang");
        left_hang=hardwareMap.get(Servo.class,"lhang");
    }




}
