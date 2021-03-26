package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Alpha Red", group="Autonomous")
public class Auto_Alpha_11712_RIA extends AutoTasks_11712_A{
    @Override
    public void runOpMode() throws InterruptedException{
        activate();

        waitForStart();
        resetStartTime();

        autoMain(RobotTeam.RedInside, false, 1);
    }
}
