package org.firstinspires.ftc.teamcode.FieldMap;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

public class SpecimenFieldMap {
    public SpecimenFieldMap(){

    }
    public Pose2d initialPose = new Pose2d(new Vector2d(32, -60.5), Math.toRadians(90));
    public Pose2d CollectFirstSample= new Pose2d(new Vector2d(49,-6), Math.toRadians(90));
    public Pose2d CollectSecondSample= new Pose2d(new Vector2d(59,-9), Math.toRadians(90));
    public Pose2d CollectThirdSample= new Pose2d(new Vector2d(67,-9), Math.toRadians(90));
    public Pose2d ScoreFirstSample= new Pose2d(new Vector2d(52,-50), Math.toRadians(90));
    public Pose2d ScoreSecondSample= new Pose2d(new Vector2d(61,-50), Math.toRadians(90));
    public Pose2d ScoreThirdSample= new Pose2d(new Vector2d(67,-60), Math.toRadians(90));
    public Pose2d CollectSpecimen= new Pose2d(new Vector2d(42,-58), Math.toRadians(90));
    public Pose2d CollectSpecimenFirstCycle= new Pose2d(new Vector2d(42,-61), Math.toRadians(90));
    public Pose2d CollectSubmersible= new Pose2d(new Vector2d(7,-29), Math.toRadians(90));
    public Pose2d ScoreSpecimen= new Pose2d(new Vector2d(8,-34.5), Math.toRadians(90));




}
