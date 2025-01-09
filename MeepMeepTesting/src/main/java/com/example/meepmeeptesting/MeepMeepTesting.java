package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(80, 80, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(7.2, -61, -90))
                .strafeToLinearHeading(new Vector2d(7.2,-62),Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(7.2,-37.5),Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(36,-40),Math.toRadians(20.5))
                .strafeToLinearHeading(new Vector2d(48,-41.2),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(50.5,-49),Math.toRadians(-62))
                .strafeToLinearHeading(new Vector2d(57.6,-40.7),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(56,-48),Math.toRadians(250))
                .strafeToLinearHeading(new Vector2d(56,-24),Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(57,-48),Math.toRadians(241))
                .strafeToLinearHeading(new Vector2d(40,-45),Math.toRadians(84))
                .strafeTo(new Vector2d(40,-59))

                .strafeToLinearHeading(new Vector2d(14,-32),Math.toRadians(-76))
//                .strafeToLinearHeading(new Vector2d(40,-45),Math.toRadians(90))
//                .lineToY(-59)
//                .strafeToLinearHeading(new Vector2d(12,-31),Math.toRadians(-74))
//
//                .strafeToLinearHeading(new Vector2d(40,-45),Math.toRadians(90))
//                .lineToY(-59)
//                .strafeToLinearHeading(new Vector2d(10,-32),Math.toRadians(-72))
//
//                .strafeToLinearHeading(new Vector2d(40,-45),Math.toRadians(90))
//                .lineToY(-59)
//                .strafeToLinearHeading(new Vector2d(6,-37.8),Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(39,-43),Math.toRadians(-45))



                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}