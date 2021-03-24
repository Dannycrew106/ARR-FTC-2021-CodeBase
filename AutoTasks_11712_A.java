package org.firstinspires.ftc.teamcode;

import java.awt.Robot;

public abstract class AutoTasks_11712_A extends AutoMethods_11712_A{

    RobotDirection forward = RobotDirection.Forward;
    RobotDirection backward = RobotDirection.Backward;
    RobotDirection right = RobotDirection.Right;
    RobotDirection left = RobotDirection.Left;

    public void autoMain(RobotTeam team, boolean delay, int area){

        if (delay){
            sleep(10000);
        }

        move(forward, 1);
        stopAllAfter(2000);

        move(forward, 0.3);
        switch(area){
            case 1:
                stopAllAfter(300);
                break;
            case 2:
                stopAllAfter(500);
                break;
            case 3:
                stopAllAfter(700);
                break;
        }
        switch(team){
            case RedInside:
                if (area == 2){
                    move(left, 0.5);
                }
                else{
                    move(right, 0.5);
                }
                break;
            case RedOutside:
                if(area == 2){
                    move(right, 0.5);
                }
                else {
                    move(right, 1);
                }
                break;
            case BlueInside:
                if (area == 2){
                    move(right, 0.5);
                }
                else{
                    move(left, 0.5);
                }
                break;
            case BlueOutside:
                if(area == 2){
                    move(left, 0.5);
                }
                else {
                    move(left, 1);
                }
                break;
        }
        sleep(500);
    }
}

