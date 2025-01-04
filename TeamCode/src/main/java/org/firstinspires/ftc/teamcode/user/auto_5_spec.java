package org.firstinspires.ftc.teamcode.user;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous(name="auto 5 spec")
public class auto_5_spec extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(new Vector2d(7.2,-62), Math.toRadians(-90));


        MecanumDrive drive = new MecanumDrive(hardwareMap,initialPose);
        colection colection = new colection(hardwareMap);
        extension extension = new extension(hardwareMap);
        scoring scoring = new scoring(hardwareMap);
        slides slides = new slides(hardwareMap);
        slides.slide_init();
        colection.init_config();
        extension.extend(extension.extension_retracted);
        scoring.scoring_arm_score_specimen_score();
        scoring.gripper_grab();
        slides.reset_encoder();

        TrajectoryActionBuilder start_to_score = drive.actionBuilder(initialPose)
                .afterTime(0.2,slides.auto_score())
                .lineToY(-37.5)
                .afterTime(0,scoring.specimen_score_2());

        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(7.2,-37.5),Math.toRadians(-90)))
                .afterTime(0.6,scoring.gripper_release())
                .afterTime(1.2,scoring.auto_End())
                .afterTime(1,slides.slide_init())
                .strafeToLinearHeading(new Vector2d(36,-40),Math.toRadians(20.5))
                .strafeToLinearHeading(new Vector2d(48,-41.2),Math.toRadians(90));
        TrajectoryActionBuilder transfer_sample_1_finish = drive.actionBuilder(new Pose2d(new Vector2d(48,-41.2),Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(50.5,-49),Math.toRadians(-62));



        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(50.5,-49),Math.toRadians(-62)))
                .strafeToLinearHeading(new Vector2d(57.6,-40.7),Math.toRadians(90));
        TrajectoryActionBuilder transfer_sample_2_finish = drive.actionBuilder(new Pose2d(new Vector2d(57.6,-40.7),Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(56,-48),Math.toRadians(250));
        TrajectoryActionBuilder transfer_sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(56,-48),Math.toRadians(250)))
                .strafeToLinearHeading(new Vector2d(56,-24),Math.toRadians(0));


        TrajectoryActionBuilder transfer_sample_3_finish = drive.actionBuilder(new Pose2d(new Vector2d(55,-24),Math.toRadians(0)))
                .strafeToLinearHeading(new Vector2d(57,-48),Math.toRadians(241));

        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(57,-48),Math.toRadians(246)))
                .strafeToLinearHeading(new Vector2d(40,-45),Math.toRadians(84));
        TrajectoryActionBuilder specimen_collect_pre_cicling = drive.actionBuilder(new Pose2d(new Vector2d(14,-35),Math.toRadians(-74)))
                .afterTime(0.4,scoring.gripper_release())
                .afterTime(1,slides.slide_init())
                .strafeToLinearHeading(new Vector2d(40,-45),Math.toRadians(90));
        TrajectoryActionBuilder specimen_collect = drive.actionBuilder(new Pose2d(new Vector2d(40,-45),Math.toRadians(90)))
                .lineToY(-59);

        TrajectoryActionBuilder scoring_poz_pre = drive.actionBuilder(new Pose2d(new Vector2d(40,-59),Math.toRadians(90)))
                .afterTime(0.1,slides.auto_score())
                .afterTime(0.2,scoring.specimen_prepare())
                .strafeToLinearHeading(new Vector2d(14,-32),Math.toRadians(-76))
                .afterTime(0,scoring.specimen_score_2());


        TrajectoryActionBuilder scoring_poz_pre_2 = drive.actionBuilder(new Pose2d(new Vector2d(40,-59.5),Math.toRadians(90)))

                .afterTime(0.1,slides.auto_score())
                .afterTime(0.2,scoring.specimen_prepare())
                .afterTime(0.4,slides.auto_score())
                .afterTime(1.2,slides.auto_score())

                .strafeToLinearHeading(new Vector2d(12,-31),Math.toRadians(-74))
                .afterTime(0,scoring.specimen_score_2());

        TrajectoryActionBuilder scoring_poz_pre_3 = drive.actionBuilder(new Pose2d(new Vector2d(40,-59.5),Math.toRadians(90)))
                .afterTime(0.1,slides.auto_score())
                .afterTime(0.4,slides.auto_score())
                .afterTime(1.2,slides.auto_score())

                .afterTime(0.2,scoring.specimen_prepare())
                .strafeToLinearHeading(new Vector2d(10,-32),Math.toRadians(-72))
                .afterTime(0,scoring.specimen_score_2());

        TrajectoryActionBuilder scoring_poz_pre_4 = drive.actionBuilder(new Pose2d(new Vector2d(40,-59.5),Math.toRadians(90)))

                .afterTime(0.1,slides.specimen_score_high())
                .afterTime(0.5,scoring.specimen_score())
                .strafeToLinearHeading(new Vector2d(6,-37.8),Math.toRadians(-90));


        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(6,-38),Math.toRadians(-90)))
                .afterTime(0,slides.slide_init())
                .afterTime(0,extension.max_extension())
                .afterDisp(0,scoring.auto_End())
                .afterTime(0.5,colection.auto_end())
                .strafeToLinearHeading(new Vector2d(39,-43),Math.toRadians(-45));

        scoring.gripper(scoring.gripper_hold);

        waitForStart();
        if (isStopRequested()) {
            ElapsedTime kok = new ElapsedTime();
            while(kok.seconds()<0.4)scoring.grip_transfer.setPosition(scoring.gripper_release);
            return;
        }
        scoring.scoring_arm_specimen_prepare();
        slides.culisante(slides.slides_auto_score);
        Actions.runBlocking(
                new SequentialAction(
                        start_to_score.build()
                ));
        colection.colection_arm(colection.colection_default);
        colection.default_config();
        Actions.runBlocking(
                new SequentialAction(
                        transfer_sample_1.build()
                ));
        colection.colection_arm(colection.colection_extended_auto);
        sleep(200);
        colection.gripper_grab();
        sleep(300);
        colection.colection_arm(colection.colection_default);
        Actions.runBlocking(
                new SequentialAction(
                        transfer_sample_1_finish.build()
                ));
        colection.gripper_release();
        sleep(200);
        colection.gripper_angle.setPosition(colection.gripper_angle_default);

        Actions.runBlocking(
                new SequentialAction(
                        transfer_sample_2.build()
                ));
        colection.colection_arm(colection.colection_extended_auto);
        sleep(200);
        colection.gripper_grab();
        sleep(300);
        colection.colection_arm(colection.colection_default);
        Actions.runBlocking(
                new SequentialAction(
                        transfer_sample_2_finish.build()
                ));
        colection.gripper_release();
        sleep(200);
        colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
        Actions.runBlocking(
                new SequentialAction(
                        transfer_sample_3.build()
                ));
        colection.colection_arm(colection.colection_extended_auto);
        sleep(200);
        colection.gripper_grab();
        sleep(300);
        colection.colection_arm(colection.colection_default);
        Actions.runBlocking(
                new SequentialAction(
                        transfer_sample_3_finish.build()
                ));
        colection.gripper_release();
        sleep(200);



        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre.build(),
                        scoring.specimen_collect(),

                        specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_hold);
        sleep(200);
        slides.culisante(slides.slides_auto_score+300);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre.build()
                ));
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz.build()
//                ));

        // ciclu 2



        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect(),

                        specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_hold);
        sleep(200);
        slides.culisante(slides.slides_auto_score+300);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre_2.build()
                ));
//        Actions.runBlocking(
//                new SequentialAction(
//                        scoring_poz_2.build()
//                ));

// ciclu 3

        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect(),

                        specimen_collect.build()
                ));
//        sleep(200);
        scoring.gripper(scoring.gripper_hold);
        sleep(200);
        slides.culisante(slides.slides_auto_score+300);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre_3.build()
                ));
        Actions.runBlocking(
                new SequentialAction(
                        specimen_collect_pre_cicling.build(),
                        scoring.specimen_collect(),

                        specimen_collect.build()
                ));
//        sleep(200);
        scoring.gripper(scoring.gripper_semi_hold);
        sleep(200);
        slides.culisante(slides.slides_specimen_high+300);
        Actions.runBlocking(
                new SequentialAction(
                        scoring.gripper_grab(),
                        scoring_poz_pre_4.build()
                ));
        slides.culisante(slides.slides_specimen_high_score);
        sleep(400);
        scoring.gripper(scoring.gripper_release);
        sleep(200);

        Actions.runBlocking(
                new SequentialAction(
                        parking.build()
                ));
        drive.updatePoseEstimate();
        telemetry.addData("pose",drive.pose);
        telemetry.update();
    }

}
