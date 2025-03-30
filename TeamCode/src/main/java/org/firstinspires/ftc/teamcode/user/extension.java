package org.firstinspires.ftc.teamcode.user;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class extension {
    public Servo left_extension;
    public Servo right_extension;
    public double extension_extended=1;
    public double extension_retracted=0.69;
    public double extension_forced=0.69;
    public double extension_hang=0.87;
    public double extension_transfer=0.83;
    public double sample_1=0.865;
    public double sample_drag=0.9;
    ElapsedTime timer = new ElapsedTime(0);

    public extension(HardwareMap hardwareMap)
    {

        left_extension =hardwareMap.get(Servo.class,"left_extension");
        right_extension =hardwareMap.get(Servo.class,"right_extension");
    }

    public void extend(double poz){
        right_extension.setPosition(poz);
        left_extension.setPosition(poz);

    }

    public class Max_extension  implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


                extend(extension_extended);

                return true;

        }

    }
    public Action max_extension(){
        return new Max_extension();
    }
    public class Extension_transfer  implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


                extend(extension_transfer);

                return true;

        }

    }
    public Action extension_transfer(){
        return new Extension_transfer();
    }
    public class Retracted_forced  implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


                extend(extension_transfer);

                return true;

        }

    }
    public Action retracted_forced(){
        return new Retracted_forced();
    }
 public class Retracted  implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


                extend(extension_transfer+0.07);

                return true;

        }

    }
    public Action retracted(){
        return new Retracted();
    }

}
