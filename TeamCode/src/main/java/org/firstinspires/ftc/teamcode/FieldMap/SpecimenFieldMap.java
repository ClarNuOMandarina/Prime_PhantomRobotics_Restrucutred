package org.firstinspires.ftc.teamcode.FieldMap;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

public class SpecimenFieldMap {
    public SpecimenFieldMap(){

    }
    public Pose2d initialPose = new Pose2d(new Vector2d(32, -60.5), Math.toRadians(90));
    public Pose2d CollectFirstSample= new Pose2d(new Vector2d(50,-5), Math.toRadians(90));
    public Pose2d CollectSecondSample= new Pose2d(new Vector2d(57,-9), Math.toRadians(90));
    public Pose2d CollectThirdSample= new Pose2d(new Vector2d(66,-9), Math.toRadians(90));

    public Pose2d ScoreFirstSample= new Pose2d(new Vector2d(54,-50), Math.toRadians(90));
    public Pose2d ScoreSecondSample= new Pose2d(new Vector2d(65,-50), Math.toRadians(90));
    public Pose2d ScoreThirdSample= new Pose2d(new Vector2d(71,-56), Math.toRadians(90));
    public Pose2d ScoreFirstSpecimen= new Pose2d(new Vector2d(10,-33), Math.toRadians(90));
    public Pose2d CollectSpecimen= new Pose2d(new Vector2d(-40,-58), Math.toRadians(90));
    public Pose2d CollectSubmersible= new Pose2d(new Vector2d(-40,-58), Math.toRadians(90));

    public Pose2d ScoreSpecimen= new Pose2d(new Vector2d(10,-33), Math.toRadians(90));




}
