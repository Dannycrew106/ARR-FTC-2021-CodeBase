package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="TeleOp-V1.0.0-TDS")

public class TOP11712_V100 extends OpMode {
    DcMotor frontLeft, frontRight, backLeft, backRight;

    DcMotor flyWheel;
    DcMotor conveyorMotor;
    Servo fireServo;

    double speedCoefficient = 1;

    final double FLY_WHEEL_POWER = 1, CONVEYOR_POWER = 1, WHEEL_POWER = 1, SPEED_INCREMENT = 0.2, FIRE_SERVO_MAX = 1, FIRE_SERVO_MIN = 0;

    boolean conveyor = false;
    boolean flyWheel = false;
    boolean reverse = false;
    boolean toggleConveyor = true;
    boolean toggleFlyWheel = true;
    boolean toggleSpeedUp = true;
    boolean toggleSpeedDown = true;
    boolean toggleReverse = true;
    boolean toggleFireServo = true;


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

        flyWheel = hardwareMap.dcMotor.get("fly_wheel");
        conveyorMotor = hardwareMap.dcMotor.get("conveyor_motor");
        fireServo = hardwareMap.servo.get("fire_servo");
        
        conveyorMotor.setPower(0);
        flyWheel1.setPower(0);
        flyWheel2.setPower(0);

        telemetry.addData("SYSTEMS", "ready.");
        telemetry.update();
    }

    public void loop() {

        if (gamepad1.x && toggleReverse){
            toggleReverse = false;
            reverse = !reverse;
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

        if (gamepad1.dpad_right && !reverse) {
            frontLeft.setPower(-WHEEL_POWER * speedCoefficient);
            backLeft.setPower(WHEEL_POWER * speedCoefficient);
            frontRight.setPower(WHEEL_POWER * speedCoefficient);
            backRight.setPower(-WHEEL_POWER * speedCoefficient);
        }
        if (gamepad1.dpad_right && reverse) {
            frontLeft.setPower(WHEEL_POWER * speedCoefficient);
            backLeft.setPower(-WHEEL_POWER * speedCoefficient);
            frontRight.setPower(-WHEEL_POWER * speedCoefficient);
            backRight.setPower(WHEEL_POWER * speedCoefficient);
        }
        if (gamepad1.dpad_left && !reverse) {
            frontLeft.setPower(WHEEL_POWER * speedCoefficient);
            backLeft.setPower(-WHEEL_POWER * speedCoefficient);
            frontRight.setPower(-WHEEL_POWER * speedCoefficient);
            backRight.setPower(WHEEL_POWER * speedCoefficient);
        }
        if (gamepad1.dpad_left && reverse) {
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

        if (gamepad1.a && toggleConveyor){
            toggleConveyor = false;
            if (!conveyor){
                conveyor = true;
                conveyorMotor.setPower(CONVEYOR_POWER);

                telemetry.addData("Conveyor Motor: ", "ON");
            }
            else {
                conveyor = false;
                conveyorMotor.setPower(0);

                telemetry.addData("Conveyor Motor: ", "ON");
            }
            telemetry.update();
        }
        else if (!gamepad1.a){
            toggleConveyor = true;
        }


        if (gamepad1.y && toggleFlyWheel){
            toggleFlyWheel = false;
            if (!flyWheel){
                flyWheel = true;
                flyWheel.setPower(FLY_WHEEL_POWER);

                telemetry.addData("FlyWheel: ", "ON");
            }
            else {
                flyWheel = false;
                flyWheel.setPower(0);

                telemetry.addData("FlyWheel: ", "OFF");
            }
            telemetry.update();
        }
        else if (!gamepad1.y){
            toggleFlyWheel = true;
        }
        
        if (gamepad.b && toggleFireServo){
            toggleFireServo = false;
            fireServo.setPosition(FIRE_SERVO_MAX);
            sleep(500);
            fireServo.setPosition(FIRE_SERVO_MIN);
            telemetry.addData("READY TO", " FIRE");
            telemetry.update();
        }
        else if (!gamepad.b){
            toggleFireServo = true;
        }

    }
}
