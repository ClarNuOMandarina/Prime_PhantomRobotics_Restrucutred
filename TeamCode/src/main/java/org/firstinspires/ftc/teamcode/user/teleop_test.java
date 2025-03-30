package org.firstinspires.ftc.teamcode.user;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;

@TeleOp(name="teleop test")
public class teleop_test extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initpose = new Pose2d(new Vector2d(50, -62), Math.toRadians(90));
        colection colection = new colection(hardwareMap);

        colection.init_config();
        scoring scoring = new scoring(hardwareMap);
        slides slides = new slides(hardwareMap);
        extension extension = new extension(hardwareMap);
        PinpointDrive drive = new PinpointDrive(hardwareMap, initpose);
        hanging hanging = new hanging(hardwareMap);
        hanging.hang(hanging.stop);
        boolean blockage = false;
        ElapsedTime timer = new ElapsedTime(0);
        ElapsedTime timer_colection = new ElapsedTime(0);
        ElapsedTime timer_extension = new ElapsedTime(0);
        ElapsedTime timer2 = new ElapsedTime(0);
        boolean colectng=false;
        boolean colectng_intermediary=false;
        boolean is_extending=false;
        boolean extension_timer=false;
        boolean manual = false;
        boolean is_collecting = false;
        boolean specimen_cycling = false;
        boolean specimen_cycling_timer = false;
        boolean specimen_cycling_intermediary = false;
        boolean transfer_extend = false;
        boolean transfer_extend_counter = false;
        boolean transfer_retracted = false;
        boolean transfer_retracted_counter = false;
        boolean colection_check=false;
        boolean basket_score=false;
        boolean basket_reset=false;
        boolean to_score=false;
        boolean specimen_preparing=false;
        boolean sample_to_observation=false;
        boolean specimen_scoring=false;
        boolean high_basket=true;
        boolean high_basket_intermediary=false;
        boolean basket_scoring=false;
        boolean is_collecting_check=false;
        boolean specimen_collected=false;
        boolean tohang=false;
        int slidezz=0;
        boolean auto_hang=false;
        boolean specimen_automatization=true;
        boolean automatization_intermediary=false;
        boolean automatization_running=false;
        boolean stop_code=false;
        boolean automatization_finish=false;
        extension.extend(extension.extension_retracted);
        scoring.init_config();
        boolean is_collected = false;
        TrajectoryActionBuilder scoring_poz = drive.actionBuilder(initpose)
                .afterTime(0,slides.specimen_score_high())
                .afterTime(0,scoring.specimen_prepare())

                .afterTime(0.7,slides.specimen_score_high())
                .afterTime(0.2,slides.specimen_score_high())
                .afterTime(1,slides.specimen_score_high())
                .afterTime(2,scoring.gripper_grab_max())
                .strafeToConstantHeading(new Vector2d(6,-25));

        while (!isStarted() && !isStopRequested() ) {
            slides.culisante(0);
            telemetry.addData("Status", "Waiting for start...");
            telemetry.update();
        }
        waitForStart();
//        hanging.hang(hanging.unhanged);f
//        sleep(100);
//        hanging.hang(hanging.stop);

        slides.reset_encoder();
        scoring.init_config();
        colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
        while (opModeIsActive()) {
            if (!automatization_running ) {

                if (extension.left_extension.getPosition() > extension.extension_retracted + 0.1 && !is_collected) {
                    drive.setDrivePowers(new PoseVelocity2d(
                            new Vector2d(
                                    -gamepad1.left_stick_y,
                                    -gamepad1.left_stick_x*0.7
                            ),
                            -gamepad1.right_stick_x * 0.5
                    ));
                } else
                    drive.setDrivePowers(new PoseVelocity2d(
                            new Vector2d(
                                    -gamepad1.left_stick_y,
                                    -gamepad1.left_stick_x
                            ),
                            -gamepad1.right_stick_x
                    ));
                if (!tohang) {
                    slides.culisante(slidezz);

                    if (!blockage) {
                        if (gamepad1.options && gamepad1.share) {
                            timer.reset();
                            scoring.scoring_arm_score_specimen_collect();

                            tohang = true;
                        }
                        if (!transfer_extend_counter && !transfer_retracted_counter && !transfer_extend && !transfer_retracted) {
                            if (gamepad1.left_trigger == 0) {
                                colection.gripper_angle.setPosition(colection.gripper_angle_default);
                            } else {
                                colection.gripper_angle.setPosition(colection.gripper_angle_vertical);

                            }
                        }
                        if (gamepad1.dpad_left && !high_basket_intermediary) {
                            if(basket_score && slides.right_slide.getCurrentPosition()<800)slidezz=slides.slides_high_basket;
                            else if(basket_score && slides.right_slide.getCurrentPosition()>800)slidezz=slides.slides_low_basket;

                            if(!basket_score) {
                                high_basket_intermediary = true;
                                if (high_basket) {
                                    high_basket = false;
                                } else high_basket = true;
                                timer_extension.reset();
                            }
                            else{
                                high_basket_intermediary = true;
                                timer_extension.reset();

                            }
                        }
                        if (high_basket_intermediary) {

                            if (timer_extension.seconds() > 0.3) {

                                high_basket_intermediary = false;

                            }

                        }

                        if (gamepad1.right_bumper && !extension_timer) {

                            extension_timer = true;
                            if (is_extending) {
                                is_extending = false;
                            } else is_extending = true;
                            timer_extension.reset();

                        }
                        if (extension_timer) {

                            if (timer_extension.seconds() > 0.3) {

                                extension_timer = false;

                            }

                        }

                        if (!transfer_extend_counter && !transfer_retracted_counter && !transfer_extend && !transfer_retracted) {


                            if (is_extending) extension.extend(extension.extension_extended);
                            else extension.extend(extension.extension_retracted);
                        }


                        if (gamepad1.left_bumper && !specimen_cycling_timer) {

                            specimen_cycling_timer = true;
                            timer.reset();

                        }
                        if (specimen_cycling_timer) {

                            if (timer.seconds() > 0.5) {

                                specimen_cycling_timer = false;
                                if (specimen_cycling) {
                                    specimen_cycling = false;
                                } else specimen_cycling = true;

                                specimen_cycling_intermediary = true;

                            }

                        }
                        if (specimen_cycling && specimen_cycling_intermediary) {
                            scoring.scoring_arm_score_specimen_collect();
                            scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
                            colection.default_config();
                            specimen_cycling_intermediary = false;
                            colection.gripper.setPosition(colection.gripper_release);
                            scoring.gripper(scoring.gripper_release);
                        } else if (!specimen_cycling && specimen_cycling_intermediary) {
                            scoring.scoring_arm_default();
                            scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
                            colection.default_config();
                            specimen_cycling_intermediary = false;
                            colection.gripper.setPosition(colection.gripper_release);
                            scoring.gripper(scoring.gripper_release);

                        }
                        if (!basket_scoring) {
                            if (!colection_check && !is_collected) {
                                if (gamepad1.right_trigger != 0) {
                                    colection.gripper_height.setPosition(colection.height_collecting);
                                    is_collecting = true;

                                }
                                if (gamepad1.right_trigger == 0) {
                                    colection.gripper_height.setPosition(colection.height_default);
                                    is_collecting = false;
                                }
                            }
                        } else {
                            if (gamepad1.right_trigger != 0) {
                                scoring.scoring_arm_extension.setPosition(scoring.extension_extended_specimen);
                            } else {
                                scoring.scoring_arm_extension.setPosition(scoring.extension_extended);
                            }
                        }
                        if (is_collecting) is_collecting_check = true;
                        else is_collecting_check = false;
                        if (specimen_preparing && specimen_cycling) {

                            if (!is_collected) {
                                if (timer_colection.seconds() > 0.2) {
                                    scoring.grip_transfer.setPosition(scoring.gripper_semi_hold);
                                    slidezz = slides.slides_specimen_high_score;
                                    scoring.score(scoring.scoring_arm_specimen_prepare);
                                    scoring.scoring_arm_extension.setPosition(scoring.extension_extended_specimen);


                                }
                                if (timer_colection.seconds() > 1.5) {
                                    scoring.grip_transfer.setPosition(scoring.gripper_hold);

                                    scoring.scoring_arm_extension.setPosition(scoring.extension_extended_specimen);
                                    specimen_preparing = false;
                                    specimen_collected = true;

                                }
                            }


                        }
                        if (gamepad1.square && !colectng && !to_score) {
                            colectng = true;
                            colectng_intermediary = true;
                            timer2.reset();
                        }
                        if (colectng_intermediary) {
                            colectng_intermediary = false;
                            if (is_collecting && !is_collected) {

                                is_collecting = false;

                                colection.gripper.setPosition(colection.gripper_transfer);
                                colection_check = true;
                                timer_colection.reset();

                            } else if (!is_collecting_check && specimen_cycling) {
                                if (is_collected) {
                                    is_collected = false;
                                    colection.gripper.setPosition(colection.gripper_release);
                                    sample_to_observation = true;
                                    timer_colection.reset();
                                }
                                if(!manual) {
                                    if (scoring.senzor.getDistance(DistanceUnit.CM) < scoring.senzor_colected) {
                                        specimen_scoring = true;
                                        scoring.gripper(scoring.gripper_hold);
                                        specimen_preparing = true;
                                        timer_colection.reset();
                                        specimen_collected = true;
                                    }
                                }
                                else{
                                    specimen_scoring = true;
                                    scoring.gripper(scoring.gripper_hold);
                                    specimen_preparing = true;
                                    timer_colection.reset();
                                    specimen_collected = true;
                                }

                            }
                        }

                        if (colectng && timer2.seconds() > 0.2) {
                            colectng = false;
                        }


                        if (colection_check) {

                            if (specimen_cycling) {
                                if (manual) {
                                    if (timer_colection.seconds() > 0.1) colection.default_config();
                                    colection.default_config();
                                    colection.gripper_rotation.setPosition(colection.gripper_rotation_score_sample);
                                    is_collected = true;
                                    colection_check = false;
                                    is_extending = false;

                                } else {
                                    if (timer_colection.seconds() > 0.4) {
//                                    if (true) {
                                        if (colection.senzor.getDistance(DistanceUnit.CM) <= colection.distance_to_collected_sample) {
                                            if (automatization_intermediary) {
                                                automatization_running = true;
                                                automatization_intermediary = false;
                                            }
                                            colection.default_config();
                                            colection.gripper_rotation.setPosition(colection.gripper_rotation_score_sample);
                                            colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
                                            is_collected = true;
                                            colection_check = false;
                                            is_extending = false;

                                        } else {

                                            colection.gripper_release();
                                            colection.gripper_height  .setPosition(colection.height_default);

                                            colection_check = false;

                                        }
                                    }
                                }
                            } else {
                                if (manual) {

                                    if (timer_colection.seconds() > 0.4) {
                                        colection.scoring_config();
                                        extension.extend(extension.extension_transfer);
                                        if (!is_extending) transfer_retracted = true;
                                        else transfer_extend = true;
                                        is_collected = true;
                                        colection_check = false;
                                    }

                                } else {
                                    if (timer_colection.seconds() > 0.3) {
                                        if (colection.senzor.getDistance(DistanceUnit.CM) <= colection.distance_to_collected_sample) {

                                            colection.scoring_config();
                                            extension.extend(extension.extension_transfer);
                                            if (!is_extending) transfer_retracted = true;
                                            else transfer_extend = true;
                                            is_collected = true;
                                            colection_check = false;


                                        } else {
                                            colection.gripper_release();
                                            colection.gripper_height.setPosition(colection.height_default);

                                            colection_check = false;

                                        }
                                    }

                                }


                            }


                        }


                        if (!specimen_cycling && is_collected) {
                            if (transfer_extend) {
                                colection.scoring_config();
                                extension.extend(extension.extension_transfer+0.05);
                                timer.reset();
                                transfer_extend_counter = true;
                                transfer_extend = false;

                            }
                            if (transfer_retracted && is_collected) {
                                extension.extend(extension.extension_extended);
                                colection.scoring_config();
                                timer.reset();
                                transfer_retracted_counter = true;
                                transfer_retracted = false;
                            }
                        }


                        if ((transfer_retracted_counter && timer.seconds() > 0.4) || (transfer_extend_counter == true && timer.seconds() > 0.3)) {
                            transfer_extend_counter = false;
                            transfer_retracted_counter = false;
                            if (colection.senzor.getDistance(DistanceUnit.CM) < colection.distance_to_collected_sample) {
                                if (blockage == false
                                        && slides.right_slide.getCurrentPosition() < 15) {
                                    colection.gripper_rotation.setPosition(colection.gripper_angle_default);
                                    colection.scoring_config();
                                    scoring.scoring_arm_default();
                                    extension.extend(extension.extension_transfer);
                                    blockage = true;
                                    timer.reset();
                                    basket_score = true;
                                }
                            }
                        else {
                                is_collected = false;
                                is_extending = false;
                                scoring.score(scoring.scoring_arm_colect);
                                scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
                                colection.default_config();
                                colection.gripper_release();
                            }

                        }

                        if (sample_to_observation) {
                            if (timer_colection.seconds() > 0.3) {
                                colection.default_config();
                                sample_to_observation = false;
                            }
                        }

                        if (gamepad1.circle) {


                            if (basket_score) {
                                scoring.gripper(scoring.gripper_release);
                                basket_score = false;
                                basket_reset = true;
                                timer.reset();
                                is_collected = false;
                                basket_scoring = false;
                            }

                            if (specimen_scoring && specimen_cycling && specimen_collected && !specimen_preparing) {
                                to_score = true;
                                timer_colection.reset();
                                scoring.scoring_arm_score_specimen_score();
                            }


                        }
                        if (basket_reset) {
                            if (timer.seconds() > 1) {
                                scoring.score(scoring.scoring_arm_colect);
                                scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
                                slidezz = slides.slides_init;
                            }
                            if (timer.seconds() > 1.5) {

                                basket_reset = false;
                            }
                        }
                        if (to_score == true) {
                            specimen_scoring = false;
                            if (timer_colection.seconds() > 0.4) {
                                scoring.gripper(scoring.gripper_release);
                            }
                            if (timer_colection.seconds() > 0.5) {
                                scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);


                            }
                            if (timer_colection.seconds() > 0.6) {
                                slidezz = slides.slides_init;
                                scoring.scoring_arm_score_specimen_collect();
                                scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
                                to_score = false;


                            }
                        }

                    }
                    if (blockage == true) {

                        if (timer.seconds() > 0.1 && timer.seconds() < 0.2) {
                            scoring.grip_transfer.setPosition(scoring.gripper_hold);

                        }
                        if (timer.seconds() > 0.2 && timer.seconds() < 0.3) {
                            colection.gripper.setPosition(colection.gripper_release);

                        }
                        if (timer.seconds() > 0.4) {
                            colection.default_config();
                            extension.extend(extension.extension_retracted);
                            if (high_basket) slidezz = slides.slides_high_basket;
                            else slidezz = slides.slides_low_basket;

                        }
                        if (timer.seconds() > 0.8) {
                            scoring.scoring_arm_score_basket();
                            scoring.scoring_arm_extension.setPosition(scoring.extension_extended);
                            is_extending = false;
                            blockage = false;
                            basket_scoring = true;

                        }
                    }
                } else {
                    if (timer.seconds() < 0.1) {
                        slidezz=1000;
                        slides.culisante(slidezz);
                    }
                    if (timer.seconds() > 0.6 && timer.seconds() < 0.7) {
                        hanging.hang(hanging.hanged);
                    }
                    if (timer.seconds() > 0.75 && timer.seconds() < 0.8) {
                        hanging.hang(hanging.stop);
                        slidezz=0;
                        slides.culisante(slidezz);
                    }

                    if (gamepad1.dpad_right) tohang = false;
                    if (gamepad1.right_bumper) {
                        hanging.hang(hanging.hanged);
                        auto_hang = true;
                        timer_colection.reset();
                    }
                    if (timer_colection.seconds() < 1 && auto_hang) {
                        hanging.hang(hanging.hanged);

                    }
                    if ( timer_colection.seconds() > 3.7 && auto_hang) {
                        hanging.hang(hanging.stop);
                        auto_hang=false;
                    }
                    if(!auto_hang && timer.seconds()>1){
                        hanging.hang(hanging.stop);
                    }


                }
                telemetry.addData("specimen_cyclng", specimen_cycling);
                telemetry.addData("culisante", slides.left_slide.getCurrentPosition());
                telemetry.addData("culisante2", slides.right_slide.getCurrentPosition());
                telemetry.addData("Using sensor?", !manual);
                telemetry.addData("senzor", colection.senzor.getDistance(DistanceUnit.CM));
                telemetry.addData("senzor2", scoring.senzor.getDistance(DistanceUnit.CM));

                telemetry.update();
            }
            else{
                    if (gamepad1.right_trigger != 0) {
                        automatization_running = false;
                        automatization_intermediary = false;
                    }
                    if (!is_collected) {
                        if(automatization_running) {
                            Actions.runBlocking(
                                    new SequentialAction(
                                            scoring_poz.build()
                                    ));
                            colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
                            scoring.scoring_arm_score_specimen_score();
                            extension.extend(extension.extension_extended);
                            sleep(300);
                            slidezz = 0;
                            slides.culisante(slidezz);
                            scoring.grip_transfer.setPosition(scoring.gripper_release);
                            specimen_collected = false;
                            colection.gripper_rotation.setPosition(colection.gripper_rotation_default);
                            is_extending = true;
                            automatization_running = false;
                            automatization_intermediary = true;
                            scoring.scoring_arm_extension.setPosition(scoring.extension_retracted);
                            sleep(100);
                            scoring.scoring_arm_score_specimen_collect();
                        }
                    } else {
                        if (automatization_running){
                            is_extending = false;
                        TrajectoryActionBuilder specimen_collect_pre_cicling = drive.actionBuilder(drive.pose)
                                .afterTime(0.2, scoring.gripper_release())
                                .afterTime(0.4, scoring.specimen_collect())
                                .afterTime(0.4, slides.slide_init())
                                .strafeToConstantHeading(new Vector2d(44, -61));
                        extension.extend(extension.extension_retracted);
                        Actions.runBlocking(
                                new SequentialAction(
                                        colection.rotation_observation(),
                                        specimen_collect_pre_cicling.build()
                                ));
                        colection.gripper_release();
                        scoring.gripper(scoring.gripper_semi_hold);
                        sleep(200);
                        is_collected = false;
                    }
                    }


            }
            drive.updatePoseEstimate();
telemetry.update();
        }

    }
}

