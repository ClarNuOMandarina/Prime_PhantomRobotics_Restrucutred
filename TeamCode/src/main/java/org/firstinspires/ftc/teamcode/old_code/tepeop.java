//package org.firstinspires.ftc.teamcode.user;
//
//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.PoseVelocity2d;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//
//@TeleOp(name="tepeop_slides_fucked_up")
//public class tepeop extends LinearOpMode {
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        MecanumDrive drive = new MecanumDrive(hardwareMap,new Pose2d(new Vector2d(0,0),Math.toRadians(0)));
//        colection colection = new colection(hardwareMap);
//        extension extension = new extension(hardwareMap);
//        scoring scoring = new scoring(hardwareMap);
//        slides slides = new slides(hardwareMap);
//        boolean hang=false;
//        boolean collecting=false;
//        boolean blockage=false;
//        ElapsedTime timer = new ElapsedTime(0);
//        ElapsedTime timer2 = new ElapsedTime(0);
//        ElapsedTime timer_score= new ElapsedTime(0);
//        boolean to_score=false;
//        boolean grab=false;
//        boolean alt_transfer=false;
//        ElapsedTime timer_release= new ElapsedTime(0);
//        boolean timer_no=false;
//        boolean scramble=false;
//        boolean scramble_single_shot=false;
//        boolean scramble_intermediary=false;
//        ElapsedTime scrambler_time= new ElapsedTime(0);
//        colection.init_config();
//        scoring.init_config();
//        boolean specimen_cycling=false;
//        slides.culisante(slides.slides_init);
//        extension.extend(extension.extension_retracted);
//        boolean manual=true;
//        boolean transfer_extend=false;
//        boolean transfer_extend_counter=false;
//        boolean transfer_retracted=false;
//        boolean transfer_retracted_counter=false;
//
//        while(!opModeIsActive()){
//            slides.culisante(0);
//            if(!opModeInInit())break;
//        }
//        waitForStart();
//        colection.start_config();
//        scoring.init_config();
//        slides.reset_encoder();
//        while(opModeIsActive() && !isStopRequested()){
//            drive.setDrivePowers(new PoseVelocity2d(
//                    new Vector2d(
//                            -gamepad1.left_stick_y,
//                            -gamepad1.left_stick_x
//                    ),
//                    -gamepad1.right_stick_x
//            ));
//            if(extension.left_extension.getPosition()>extension.extension_retracted+0.1) {
//                drive.setDrivePowers(new PoseVelocity2d(
//                        new Vector2d(
//                                -gamepad1.left_stick_y,
//                                -gamepad1.left_stick_x
//                        ),
//                        -gamepad1.right_stick_x * 0.5
//                ));
//            }
//
//            else
//                drive.setDrivePowers(new PoseVelocity2d(
//                        new Vector2d(
//                                -gamepad1.left_stick_y,
//                                -gamepad1.left_stick_x
//                        ),
//                        -gamepad1.right_stick_x
//                ));
//            // driver 2
//
//            if(blockage==false)
//            {
//                if(gamepad2.cross)manual=false;
//                if(gamepad2.square)manual=true;
//                if(gamepad2.dpad_up) {
//                    slides.culisante(slides.slides_high_basket);
//                    scoring.scoring_arm_score_basket();
//                }
//                if(gamepad2.dpad_left) {
//                    slides.culisante(slides.slides_low_basket);
//                    scoring.scoring_arm_score_basket();
//
//                }
//                if(gamepad2.dpad_right) {
//                    slides.culisante(slides.slides_specimen_high);
//                    scoring.scoring_arm_score_specimen_score();                }
//                if(gamepad2.dpad_down) {
//                    slides.culisante(slides.slides_specimen_low);
//                    scoring.scoring_arm_score_specimen_score();                }
//                if(gamepad2.share){
//                    alt_transfer=true;
//                    slides.culisante(slides.slides_init);
//                    scoring.scoring_arm_score_specimen_collect();
//                }
//                if(gamepad2.triangle){
//                    slides.culisante(slides.slides_init);
//                    scoring.scoring_arm_default();
//                }
//                if(gamepad2.right_bumper)specimen_cycling=true;
//                if(gamepad2.left_bumper)specimen_cycling=false;
//                if(gamepad1.left_trigger==0) {
//                    if (transfer_extend == true) {
//                        timer.reset();
//                        transfer_extend_counter = true;
//                        transfer_extend = false;
//                    }
//                    if (transfer_retracted == true) {
//                        timer.reset();
//                        transfer_retracted_counter = true;
//                        transfer_retracted = false;
//                    }
//                }
//                if(gamepad2.touchpad || (transfer_retracted_counter && timer.seconds()>0.8) ||(transfer_extend_counter==true && timer.seconds()>0.9)){
//                    transfer_extend_counter=false;
//                    transfer_retracted_counter=false;
//                    if ( blockage == false
//                            && slides.right_slide.getCurrentPosition() < 15) {
//                        alt_transfer=false;
//                        colection.scoring_config();
//                        scoring.scoring_arm_default();
//                        colection.gripper.setPosition(colection.gripper_transfer);
//
//                        blockage = true;
//                        timer.reset();
//                    }
//
//                }
//
//
//                if(gamepad2.left_trigger!=0){
//                    extension.extend(extension.extension_hang);
//                    slides.culisante(slides.slides_high_basket);
//                    hang=true;
//                }
//
//                if(gamepad2.right_trigger!=0){
//                    scramble_single_shot=false;
//
//                    colection.default_config();
//                    scoring.init_config();
//                    slides.culisante(0);
//                    collecting=false;
//                    hang=false;
//                }
//
//
//
//
//                // driver 1
////                if(gamepad1.x)
////                {
////                    colection.colection_arm(colection.colection_extended);
////                    colection.gripper_angle.setPosition(colection.gripper_angle_default);
////                    colection.gripper_rotation.setPosition(colection.gripper_rotation_collect);
////                    timer_release.reset();
////                    timer_no=true;
////                }
//////                if(timer_no==true)
//////                    if(timer_release.seconds()>)
////                if(gamepad1.touchpad)colection.gripper_release();
//                if(gamepad1.share){
//                    slides.culisante(slides.slides_hang);
//                }
//                if(gamepad1.right_trigger!=0)
//                {
//                    colection.colection_arm(colection.colection_extended);
//                    colection.gripper_rotation.setPosition(colection.gripper_rotation_collect);
//                    colection.gripper_angle.setPosition(colection.gripper_angle_default);
//                    scramble=true;
//                    scrambler_time.reset();
//                    scramble_single_shot=true;
//                }
////                else{
////                    scramble_single_shot=false;
////                }
//                if(scramble==true&&!(gamepad1.right_trigger!=0))
//                {
////                    if(scramble_single_shot==false)
////                        colection.gripper.setPosition(colection.gripper_release);
//                    if(scrambler_time.seconds()>0.3)
//                    {
//                        colection.colection_arm(colection.colection_default);
//                        scramble=false;
//                    }
//                }
//
//                if(gamepad1.b){
//                    to_score=true;
//                    timer_score.reset();
//                    slides.culisante(slides.slides_specimen_high_score_tepeop);
//                }
//                if(to_score==true) {
//                    if (timer_score.seconds() > 0.5) {
//                        scoring.gripper_release();
//
//                    }
//                    if(timer_score.seconds()>1){
//                        scoring.init_config();
//                        to_score=false;
//                    }
//                }
//                if(gamepad1.dpad_left) colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
//                if(gamepad1.dpad_right)colection.gripper_angle.setPosition(colection.gripper_angle_default);
//                if( (gamepad1.x)){
//                    colection.collecting_config();
//                    grab=true;
//                    timer2.reset();
//                }
//                if(timer2.seconds()>0.1 && grab==true) {
//                    colection.gripper_grab();
//                }
//                if(timer2.seconds()>0.5 &&grab ==true)
//                {
//                    colection.colection_arm(colection.colection_default);
//                    if(timer2.seconds()>0.7) {
//                        if (specimen_cycling == false) {
//
//                            if (colection.senzor.alpha() > 600) {
//                                colection.gripper.setPosition(colection.gripper_transfer);
//                                colection.scoring_config();
//                                if(manual==true) {
//                                    if (gamepad1.left_trigger != 0) transfer_extend = true;
//                                    else transfer_retracted = true;
//                                }
//                            } else {
//                                colection.default_config();
//                            }
//                        } else {
//                            if(manual==true) {
//                                if (colection.senzor.alpha() > 600) {
//                                    colection.colection_arm(colection.colection_specimen);
//                                } else {
//                                    colection.default_config();
//                                }
//                            }
//                            else colection.colection_arm(colection.colection_specimen);
//                        }
//                        grab = false;
//                    }
//                }
//
//
//                if(gamepad1.right_bumper){
//                    colection.gripper_release();
//                    colection.colection_arm(colection.colection_default);
//                }
//                // expansion and retraction
//                if( gamepad1.left_trigger!=0){
//                    extension.extend(extension.extension_extended);
//                }
//
//                if( gamepad1.left_trigger==0 && hang==false)
//                {
//                    extension.extend(extension.extension_retracted);
//                }
//                // specimen colection
//                if(gamepad1.triangle){
////                    if(scoring.scoring_arm_left.getPosition()>0.28)
//                    if(alt_transfer==true)
//                    {
//                        scoring.grip_transfer_grab();
//                    }
//                    else scoring.grip_transfer_release();
//                }
//
//            }
//
//            if(blockage==true) {
//                extension.extend(extension.extension_forced);
//                if ( timer.seconds() < 0.2) {
//                    scoring.scoring_arm_colect();
//                }
//
//                if (timer.seconds() > 0.2 && timer.seconds() < 0.4) {
//                    scoring.grip_transfer_grab();
//
//                } if (timer.seconds() >0.4  && timer.seconds() < 0.5) {
//                    colection.gripper.setPosition(colection.gripper_release);
//
//                }
//                if (timer.seconds() > 0.6 ) {
//                    colection.default_config();
//                    extension.extend(extension.extension_retracted);
//                    blockage = false;
//                }
//            }
//
//            telemetry.addData("Cycling_specimen",specimen_cycling);
//            telemetry.addData("Color",colection.senzor.alpha());
//            telemetry.addData("automations",manual);
//            telemetry.update();
//
//        }
//
//    }}
//
