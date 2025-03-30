package org.firstinspires.ftc.teamcode.user;

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

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.user.colection;
import org.firstinspires.ftc.teamcode.user.extension;
import org.firstinspires.ftc.teamcode.user.scoring;
import org.firstinspires.ftc.teamcode.user.slides;

@Autonomous(name="auto spec experimental")
public class auto_specimen_experimental extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
//        Pose2d initialPose = new Pose2d(new Vector2d(8,-60), Math.toRadians(90));
        Pose2d initialPose = new Pose2d(new Vector2d(9,-60), Math.toRadians(90));

        Limelight ll =new Limelight(hardwareMap);
        telemetry.setMsTransmissionInterval(11);

        PinpointDrive drive = new PinpointDrive(hardwareMap,initialPose);
        colection colection = new colection(hardwareMap);
        extension extension = new extension(hardwareMap);
        scoring scoring = new scoring(hardwareMap);
        slides slides = new slides(hardwareMap);
        slides.slide_init();
        colection.init_config();
        extension.extend(extension.extension_retracted);
        scoring.init_config();
        scoring.grip_transfer_grab();
        slides.reset_encoder();
        double x=0;
        TrajectoryActionBuilder start_to_score = drive.actionBuilder(initialPose)
                .afterTime(0,slides.specimen_score_high())
                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .afterTime(1,scoring.gripper_grab_max())
                .strafeTo(new Vector2d(6,-26));

        TrajectoryActionBuilder transfer_sample_1 = drive.actionBuilder(new Pose2d(new Vector2d(6,-26),Math.toRadians(90)))
                .afterTime(0,scoring.specimen_score())
                .afterTime(0.2,scoring.gripper_release())
                .afterTime(0.3,scoring.extension_retracted())
                .afterTime(0.4,scoring.specimen_collect())
                .afterTime(0.4,slides.slide_init())
                .strafeToLinearHeading(new Vector2d(20,-39),Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(50,-8),Math.toRadians(75))
                //    .splineToConstantHeading(new Vector2d(50,-14), Math.PI/8)
                .strafeToLinearHeading(new Vector2d(53,-50),Math.toRadians(90));

        TrajectoryActionBuilder transfer_sample_2 = drive.actionBuilder(new Pose2d(new Vector2d(53,-50),Math.toRadians(90)))
                .afterTime(0,scoring.sample_collect())
                .splineToConstantHeading(new Vector2d(60,-14),Math.PI/8)
                .strafeToLinearHeading(new Vector2d(61,-50),Math.toRadians(90));

        TrajectoryActionBuilder transfer_sample_3 = drive.actionBuilder(new Pose2d(new Vector2d(61,-50),Math.toRadians(90)))
                .afterTime(0, scoring.specimen_first_collect())
                .splineToConstantHeading(new Vector2d(68,-14),Math.PI/8);


        TrajectoryActionBuilder specimen_collect_pre = drive.actionBuilder(new Pose2d(new Vector2d(68,-14),Math.toRadians(90)))
                .strafeToConstantHeading(new Vector2d(65,-54));


        TrajectoryActionBuilder scoring_poz_pre = drive.actionBuilder(new Pose2d(new Vector2d(65,-54),Math.toRadians(90)))
//                .strafeToConstantHeading(new Vector2d(10,-40))
                .afterTime(1.5,colection.rotation_default())
                .afterTime(0,scoring.specimen_prepare())
                .afterTime(0,slides.specimen_score_high2())
                .afterTime(0.7,slides.specimen_score_high2())

                .afterTime(0.2,slides.specimen_score_high2())
                .afterTime(0.7,scoring.gripper_grab_max())
                .afterTime(1,slides.specimen_score_high2())
//                .setReversed(true)
//                .splineToConstantHeading(new Vector2d(0,-25),Math.toRadians(0));
//                .strafeTo(new Vector2d(-6,-35))
                .strafeTo(new Vector2d(-6,-25));
//        TrajectoryActionBuilder specimen_collect_pre_cicling_pre = drive.actionBuilder(new Pose2d(new Vector2d(-6,-26),Math.toRadians(90)))
//                .afterTime(0.1,scoring.specimen_score())
//                .afterTime(0.2,scoring.gripper_release())
//                .afterTime(0.3,scoring.extension_retracted())
//                .afterTime(0.4,scoring.specimen_collect())
//                .afterTime(0.4,slides.slide_init())
//                .afterTime(1.4,scoring.gripper_release() )
//                .strafeToConstantHeading(new Vector2d(42, -63));

        TrajectoryActionBuilder specimen_collect_pre_cicling = drive.actionBuilder(new Pose2d(new Vector2d(6,-25),Math.toRadians(90)))
                .afterTime(0,scoring.specimen_score())
                .afterTime(0.2,scoring.gripper_release())
                .afterTime(0.3,scoring.extension_retracted())
                .afterTime(0.4,scoring.specimen_collect())
                .afterTime(0.4,slides.slide_init())
                .strafeToConstantHeading(new Vector2d(6, -30))
                .strafeToConstantHeading(new Vector2d(38, -60));

//        TrajectoryActionBuilder specimen_dragging = drive.actionBuilder(new Pose2d(new Vector2d(4,-25),Math.toRadians(90)))
//                .strafeToConstantHeading(new Vector2d(0,-25));


        TrajectoryActionBuilder scoring_poz = drive.actionBuilder(new Pose2d(new Vector2d(38,-60),Math.toRadians(90)))
                .afterTime(0,slides.specimen_score_high())
                .afterTime(0,scoring.specimen_prepare())

                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .afterTime(1,scoring.gripper_grab_max())

                .strafeToConstantHeading(new Vector2d(4,-26));




        TrajectoryActionBuilder parking = drive.actionBuilder(new Pose2d(new Vector2d(4,-27),Math.toRadians(90)))

                .afterTime(0.3,scoring.gripper_release())
                .afterTime(0.5,scoring.auto_End())
                .afterTime(1,slides.slide_init())
                .strafeToLinearHeading(new Vector2d(45,-60),Math.toRadians(90));

        scoring.gripper(scoring.gripper_hold);
        colection.gripper.setPosition(colection.gripper_release);
        waitForStart();
        colection.light.setPosition(colection.detection_light);
        slides.culisante(slides.slides_specimen_high_score);
        scoring.scoring_arm_specimen_prepare();
//        scoring.scoring_arm_specimen_prepare();
        scoring.grip_transfer.setPosition(scoring.gripper_hold);
        Actions.runBlocking(
                new SequentialAction(
                        start_to_score.build(),
                        transfer_sample_1.build(),
                        colection.rotation_default(),
                        transfer_sample_2.build(),
                        transfer_sample_3.build(),
                        specimen_collect_pre.build()
                        // specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_hold);
        sleep(200);
        scoring.gripper(scoring.gripper_semi_hold);

        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz_pre.build()
                ));
        scoring.scoring_arm_score_specimen_score();
        ElapsedTime taim = new ElapsedTime();
        ElapsedTime taim2 = new ElapsedTime();
        taim2.reset();
        taim.reset();
        while(opModeIsActive() && taim2.seconds()<3) {
            sleep(200);
            scoring.grip_transfer.setPosition(scoring.gripper_release);

//        ll.angle_detect(colection.gripper_angle);
            if (ll.is_detecting()) {
                ll.rot_detect(colection.gripper_rotation);
                ll.extend_detect(extension.left_extension, extension.right_extension);
                colection.gripper_release();
                colection.gripper_height.setPosition(colection.height_scanning);
                taim.reset();
                while (taim.seconds() < 0.4 && opModeIsActive()) {
//

                    telemetry.update();
                    ll.angle_detect(colection.gripper_angle);
                }
                colection.gripper_height.setPosition(colection.height_collecting);
                sleep(200);
                colection.gripper.setPosition(colection.gripper_transfer);
                sleep(300);
                if (colection.senzor.getDistance(DistanceUnit.CM) < colection.distance_to_collected_sample) {
                    extension.extend(extension.extension_retracted);
                    colection.gripper_height.setPosition(colection.height_default);
                    colection.gripper_rotation.setPosition(colection.gripper_rotation_score_sample);
                    colection.gripper_angle.setPosition(colection.gripper_angle_sample_observation);
                    break;
                }
                colection.gripper_height.setPosition(colection.height_default);
                colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
                colection.gripper.setPosition(colection.gripper_release);
                extension.extend(extension.extension_retracted);
            } else {
//                drive.updatePoseEstimate();
//                TrajectoryActionBuilder sample_collect1 = drive.actionBuilder(drive.pose)
//                        .strafeToLinearHeading(new Vector2d(-20, x), Math.toRadians(0));
//                Actions.runBlocking(
//                        new SequentialAction(
//                                sample_collect1.build()
//                        ));
                extension.extend(extension.extension_retracted+x);
                x += 0.02;
//                extension.extend(extension.extension_retracted+x);
//                sleep(400);
//                ll.rot_detect(colection.gripper_rotation);
//                ll.extend_detect(extension.left_extension, extension.right_extension);
//                colection.gripper_release();
//                colection.gripper_height.setPosition(colection.height_scanning);
//                x+=0.05;
            }
        }
        extension.extend(extension.extension_retracted);
        colection.gripper_height.setPosition(colection.height_default);
        colection.gripper_rotation.setPosition(colection.gripper_rotation_score_sample);
        colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
        TrajectoryActionBuilder specimen_collect_pre_cicling_coelcting = drive.actionBuilder(drive.pose)
                .afterTime(0,scoring.specimen_score())
                .afterTime(0.2,scoring.gripper_release())
                .afterTime(0.3,scoring.extension_retracted())
                .afterTime(0.4,scoring.specimen_collect())
                .afterTime(0.4,slides.slide_init())
                .strafeToConstantHeading(new Vector2d(-6, -30))
                .strafeToConstantHeading(new Vector2d(38, -60))
                .afterTime(0,colection.griper_release() )
                ;

                        Actions.runBlocking(
                                new SequentialAction(
                        specimen_collect_pre_cicling_coelcting.build(),
                        colection.griper_release()
                        //    specimen_collect.build()
                ));
                        sleep(200);
        scoring.gripper(scoring.gripper_hold);
        sleep(200);
        scoring.gripper(scoring.gripper_semi_hold);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz.build(),
                        specimen_collect_pre_cicling.build()

                        //    specimen_collect.build()
                ));
        colection.gripper_release();
        scoring.gripper(scoring.gripper_hold);
        sleep(200);
        scoring.gripper(scoring.gripper_semi_hold);

        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz.build(),
//                        scoring.specimen_score(),

//                ));
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_dragging.build(),
                        specimen_collect_pre_cicling.build()

                        //   specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_hold);
        sleep(200);
        scoring.gripper(scoring.gripper_semi_hold);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz.build(),
//                        scoring.specimen_score(),

//                ));
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
//                        specimen_dragging.build(),
                        specimen_collect_pre_cicling.build()

                        //   specimen_collect.build()
                ));
        scoring.gripper(scoring.gripper_hold);
        sleep(200);
        scoring.gripper(scoring.gripper_semi_hold);
        Actions.runBlocking(
                new SequentialAction(
                        scoring_poz.build(),
                        scoring.specimen_score(),

//                ));
//        sleep(100);
//        Actions.runBlocking(
//                new SequentialAction(
                        parking.build()
                ));


        drive.updatePoseEstimate();
        telemetry.addData("pose",drive.pose);
        telemetry.update();
    }
}