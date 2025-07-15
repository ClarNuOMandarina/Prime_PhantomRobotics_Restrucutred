package org.firstinspires.ftc.teamcode.FieldMap;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TangentPath;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SampleFieldMap {
    public SampleFieldMap(){
    }
   public Pose2d initialPose= new Pose2d(new Vector2d(-40,-58), Math.toRadians(90));
    public Pose2d PreloadScorePosition= new Pose2d(new Vector2d(-57,-52), Math.toRadians(70));
    public Pose2d CollectFirstSample = new Pose2d(new Vector2d(-55,-46), Math.toRadians(70));
    public Pose2d CollectSecondSample= new Pose2d(new Vector2d(-57,-46.5), Math.toRadians(90));
    public Pose2d CollectThirdSample = new Pose2d(new Vector2d(-63,-45.5), Math.toRadians(90));
    public Pose2d ScoreFirstSample= new Pose2d(new Vector2d(-61,-49), Math.toRadians(80));
    public Pose2d ScoreSecondSample=new Pose2d(new Vector2d(-63,-49), Math.toRadians(90));
    public Pose2d ScoreThirdSample= ScoreSecondSample;
    public Pose2d CollectSubmersibleSample= (new Pose2d(new Vector2d(-20.5,-2), Math.toRadians(0)));
    public Pose2d SearchSubmersibleSample= (new Pose2d(new Vector2d(-20.5,-2), Math.toRadians(0)));
    public Pose2d ScoreSubmersibleSample= new Pose2d(new Vector2d(-56,-49), Math.toRadians(47));
}
