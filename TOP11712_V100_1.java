package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="TeleOp-V1.0.0-TTS")

public class TOP11712_V100_1 extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor flyWheel1;
    DcMotor flyWheel2;

    DcMotor conveyorMotor;
    DcMotor collectionMotor;

    double speedCoefficient = 1;

    final double FLY_WHEEL_POWER = 1, CONVEYOR_POWER = 1, COLLECTION_POWER = 1, WHEEL_POWER = 1, SPEED_INCREMENT = 0.2;

    boolean collector = false;
    boolean flyWheels = false;
    boolean reverse = false;
    boolean toggleCollector = true;
    boolean toggleFlyWheels = true;
    boolean toggleSpeedUp = true;
    boolean toggleSpeedDown = true;
    boolean toggleReverse = true;


    @Override
    public void init(){
        telemetry.addData("SYSTEMS", "initializing... Standby");
        telemetry.update();

        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        flyWheel1 = hardwareMap.dcMotor.get("fly_wheel_1");
        flyWheel2 = hardwareMap.dcMotor.get("fly_wheel_2");
        flyWheel2.setDirection(DcMotorSimple.Direction.REVERSE);
        conveyorMotor = hardwareMap.dcMotor.get("conveyor_motor");
        collectionMotor = hardwareMap.dcMotor.get("collection_motor");

        telemetry.addData("SYSTEMS", "ready.");
        telemetry.update();
    }

    public void loop() {
        conveyorMotor.setPower(0);
        collectionMotor.setPower(0);
        flyWheel1.setPower(0);
        flyWheel2.setPower(0);

        if (gamepad1.x && toggleReverse){
            toggleReverse = false;
            reverse = !reverse;
            if (reverse) {
                telemetry.addData("Reverse: ", "On");
            }
            else {
                telemetry.addData("Reverse: ", "Off");
            }
            telemetry.update();
        }
        else if (!gamepad1.x) {
            toggleReverse = true;
        }

        if (!reverse) {
            frontLeft.setPower(gamepad1.left_stick_y * speedCoefficient);
            backLeft.setPower(gamepad1.left_stick_y * speedCoefficient);
            frontRight.setPower(gamepad1.right_stick_y * speedCoefficient);
            backRight.setPower(gamepad1.right_stick_y * speedCoefficient);
        }
        else if (reverse) {
            frontLeft.setPower(-gamepad1.left_stick_y * speedCoefficient);
            backLeft.setPower(-gamepad1.left_stick_y * speedCoefficient);
            frontRight.setPower(-gamepad1.right_stick_y * speedCoefficient);
            backRight.setPower(-gamepad1.right_stick_y * speedCoefficient);
        }

        if (gamepad1.right_bumper && !reverse) {
            frontLeft.setPower(-WHEEL_POWER * speedCoefficient);
            backLeft.setPower(WHEEL_POWER * speedCoefficient);
            frontRight.setPower(WHEEL_POWER * speedCoefficient);
            backRight.setPower(-WHEEL_POWER * speedCoefficient);
        }
        if (gamepad1.right_bumper && reverse) {
            frontLeft.setPower(WHEEL_POWER * speedCoefficient);
            backLeft.setPower(-WHEEL_POWER * speedCoefficient);
            frontRight.setPower(-WHEEL_POWER * speedCoefficient);
            backRight.setPower(WHEEL_POWER * speedCoefficient);
        }
        if (gamepad1.left_bumper && !reverse) {
            frontLeft.setPower(WHEEL_POWER * speedCoefficient);
            backLeft.setPower(-WHEEL_POWER * speedCoefficient);
            frontRight.setPower(-WHEEL_POWER * speedCoefficient);
            backRight.setPower(WHEEL_POWER * speedCoefficient);
        }
        if (gamepad1.left_bumper && reverse) {
            frontLeft.setPower(WHEEL_POWER * speedCoefficient);
            backLeft.setPower(-WHEEL_POWER * speedCoefficient);
            frontRight.setPower(-WHEEL_POWER * speedCoefficient);
            backRight.setPower(WHEEL_POWER * speedCoefficient);
        }

        if (gamepad1.dpad_up && speedCoefficient < 1 && toggleSpeedUp){
            speedCoefficient = speedCoefficient + SPEED_INCREMENT;

            telemetry.addData("Speed: ", speedCoefficient * 100);
            telemetry.update();
        }
        else if (!gamepad1.dpad_up){
            toggleSpeedUp = true;
        }
        if (gamepad1.dpad_down && speedCoefficient > 0.2 && toggleSpeedDown) {
            speedCoefficient = speedCoefficient - SPEED_INCREMENT;

            telemetry.addData("Speed: ", speedCoefficient * 100);
            telemetry.update();
        }
        else if (!gamepad1.dpad_down){
            toggleSpeedDown = true;
        }

        if ((gamepad1.y && toggleFlyWheels && reverse) || (gamepad1.a && toggleFlyWheels && !reverse)){
            toggleCollector = false;
            if (!collector){
                collector = true;
                collectionMotor.setPower(COLLECTION_POWER);

                telemetry.addData("Collection Motors: ", "ON");
            }
            else {
                collector = false;
                collectionMotor.setPower(0);

                telemetry.addData("Collection Motors: ", "OFF");
            }
            telemetry.update();
        }
        else if ((!gamepad1.y && !reverse) || (!gamepad1.a && reverse)){
            toggleCollector = true;
        }


        if ((gamepad1.y && toggleFlyWheels && !reverse) || (gamepad1.a && toggleFlyWheels && reverse)){
            toggleFlyWheels = false;
            if (!flyWheels){
                flyWheels = true;
                flyWheel1.setPower(FLY_WHEEL_POWER);
                flyWheel2.setPower(-FLY_WHEEL_POWER);

                telemetry.addData("FlyWheels: ", "ON");
            }
            else {
                flyWheels = false;
                flyWheel1.setPower(0);
                flyWheel2.setPower(0);

                telemetry.addData("FlyWheels: ", "OFF");
            }
            telemetry.update();
        }
        else if ((!gamepad1.y && !reverse) || (!gamepad1.a && reverse)){
            toggleFlyWheels = true;
        }

        while (gamepad1.b) {conveyorMotor.setPower(CONVEYOR_POWER);}
    }
}
