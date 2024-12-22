package org.firstinspires.ftc.teamcode.tuning;

public class arhiva_DE_COD {
//    public void score_config(){
//        if(extension.extension.getPosition()<extension.extension_retracted+0.1 && blockage==false
//                && slides.culisanta.getCurrentPosition()<15)
//        {
//            colection.scoring_config();
//            blockage=true;
//            timer.reset();
//        }
//        if(blockage==true) {
//            if (timer.seconds() > 2 && timer.seconds() < 2.2) {
//                scoring.scoring_arm_colect();
//            }
//            if (timer.seconds() > 3 && timer.seconds() < 3.2) {
//                colection.gripper_release();
//                scoring.grip_transfer_grab();
//
//            }
//
//            if (timer.seconds() > 4 && timer.seconds() < 4.2) {
//                scoring.scoring_arm_score();
//                colection.collecting_config();
//                blockage = false;
//            }
//        }
//    }


//     if(blockage==true) {
//        if (timer.seconds() > 2 && timer.seconds() < 2.2) {
//            colection.gripper_release();
//        }
//        if (timer.seconds() > 3 && timer.seconds() < 3.3) {
//            colection.collecting_config();
//        }
//        if (timer.seconds() > 4 && timer.seconds() < 4.2) {
//            scoring.scoring_arm_colect();
//        }
//        if (timer.seconds() > 5 && timer.seconds() < 5.2) {
//            scoring.grip_transfer_grab();
//
//        }
//        if (timer.seconds() > 6 && timer.seconds() < 6.2) {
////                scoring.scoring_arm_score();
//            blockage = false;
//        }
//    }
}

//        double x=extension.extension_retracted;
//                colection.colection_arm(colection.colection_extended_auto_submersible);
//                boolean force_stop=false;
//                while(x<extension.extension_extended && timer.seconds()<25 && !force_stop){
//            extension.extend(x);
//            sleep(500);
//            if(colection.senzor.alpha()>80){
//            colection.colection_arm(colection.colection_extended);
//            sleep(200);
//            colection.gripper_grab();
//            sleep(200);
//            if(colection.senzor.alpha()<1000){
//            colection.gripper_release();
//            sleep(200);
//            colection.colection_arm(colection.colection_extended_auto_submersible);
//            sleep(200);
//            colection.gripper_angle.setPosition(colection.gripper_angle_vertical);
//            sleep(200);
//            colection.colection_arm(colection.colection_extended);
//            sleep(200);
//            colection.gripper_grab();
//            }
//
//            if(colection.senzor.alpha()>1000){
//            colection.scoring_config();
//            force_stop=true;
//            }
//            else{
//            colection.gripper_release();
//            }
//            }
//            x+=0.05;
//            }
//            colection.scoring_config();
//            sleep(4000);