//package org.firstinspires.ftc.teamcode.user;
//
//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//public class automation {
//    boolean blockage;
//    colection colection = new colection(hardwareMap);
//    extension extension = new extension(hardwareMap);
//    scoring scoring = new scoring(hardwareMap);
//    slides slides = new slides(hardwareMap);
//    ElapsedTime timer = new ElapsedTime(0);
//    public automation(HardwareMap hardwareMap){
//blockage=false;
//timer.reset();
//    }
//
//
//            public void score_config(){
//        if(extension.left_extension.getPosition()<extension.extension_retracted+0.1 && blockage==false
//                && slides.right_slide.getCurrentPosition()<15)
//        {
//            colection.scoring_config();
//            blockage=true;
//            timer.reset();
//        }
//        if(blockage==true) {
//            if (timer.seconds() > 2 && timer.seconds() < 2.2) {
//                colection.gripper.setPosition(colection.gripper_transfer);
//            }
//            if (timer.seconds() > 3 && timer.seconds() < 3.2) {
//                scoring.grip_transfer_grab();
//
//            } if (timer.seconds() > 4 && timer.seconds() < 4.2) {
//                colection.gripper.setPosition(colection.gripper_release);
//                colection.colection_arm(colection.colection_extended);
//
//            }
//            if (timer.seconds() > 5 && timer.seconds() < 5.2) {
//                colection.collecting_config();
//                blockage = false;
//            }
//        }
//    }
//
//    public void init(){
//        colection.init_config();
//        scoring.init_config();
//        slides.culisante(slides.slides_init);
//        extension.extend(extension.extension_retracted);
//
//    }
//
//    public void reset(){
//        colection.collecting_config();
//        colection.gripper.setPosition(colection.gripper_release);
//        scoring.init_config();
//        slides.culisante(slides.slides_init);
//        extension.extend(extension.extension_retracted);
//    }
//
//    public void auto_init(){
//        colection.init_config();
//        scoring.init_config();
//        slides.culisante(slides.slides_init);
//        extension.extend(extension.extension_retracted);
//    }
//
//}
