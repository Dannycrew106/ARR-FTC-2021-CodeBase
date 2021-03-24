package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public abstract class AutoMethods_11712_A extends LinearOpMode{

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private DcMotor flyWheel1, flyWheel2;
    private DcMotor collectionConveyor;
    private Servo fireServo;

    private final double FLY_WHEEL_POWER = 1, COLLECTION_POWER = 1, FIRE_SERVO_MAX = 1, FIRE_SERVO_MIN = 0;

    public enum RobotDirection{
        Forward, Backward, TurnRight, TurnLeft, Right, Left, SteerRight, SteerLeft, RSteerRight, RSteerLeft
    }
    public enum Motor{
        FlyWheels, Collection
    }
    public enum RobotTeam {
        RedOutside, RedInside, BlueOutside, BlueInside
    }
    public enum ServoPosition {
        Fire, Reset
    }

    public void activate(){
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
        collectionConveyor = hardwareMap.dcMotor.get("collection_conveyor");

        telemetry.addData("SYSTEMS", "ready.");
        telemetry.update();
    }

    public void move(RobotDirection direction, double speed){
        switch (direction) {
            case Forward:
                frontRight.setPower(speed);
                frontLeft.setPower(speed);
                backRight.setPower(speed);
                backLeft.setPower(speed);
                break;
            case Backward:
                frontRight.setPower(-speed);
                frontLeft.setPower(-speed);
                backRight.setPower(-speed);
                backLeft.setPower(-speed);
                break;
            case TurnRight:
                frontRight.setPower(-speed);
                frontLeft.setPower(speed);
                backRight.setPower(-speed);
                backLeft.setPower(speed);
                break;
            case TurnLeft:
                frontRight.setPower(speed);
                frontLeft.setPower(-speed);
                backRight.setPower(speed);
                backLeft.setPower(-speed);
                break;
            case Left:
                frontRight.setPower(speed);
                frontLeft.setPower(-speed);
                backRight.setPower(-speed);
                backLeft.setPower(speed);
                break;
            case Right:
                frontRight.setPower(-speed);
                frontLeft.setPower(speed);
                backRight.setPower(speed);
                backLeft.setPower(-speed);
                break;
            case SteerLeft:
                frontRight.setPower(speed);
                backRight.setPower(speed);
                break;
            case SteerRight:
                frontLeft.setPower(speed);
                backLeft.setPower(speed);
                break;
            case RSteerLeft:
                frontLeft.setPower(-speed);
                backLeft.setPower(-speed);
                break;
            case RSteerRight:
                frontRight.setPower(-speed);
                backRight.setPower(-speed);
                break;
        }
    }

    public void toggleMotor(Motor control) {
        switch (control) {
            case Collection:
                collectionConveyor.setPower(COLLECTION_POWER);
                break;
            case FlyWheels:
                flyWheel1.setPower(FLY_WHEEL_POWER);
                flyWheel2.setPower(FLY_WHEEL_POWER);
                break;
        }
    }

    public void fire(){
        fireServo.setPosition(FIRE_SERVO_MAX);
        collectionConveyor.setPower(1);
        stopAllAfter(1000);
    }
    public void resetFireServo(){
        fireServo.setPosition(FIRE_SERVO_MIN);
    }
    public void fireReset(){
        fire();
        collectionConveyor.setPower(1);
        sleep(1000);
        resetFireServo();
    }
    public void fireAll(){
        fireReset();
        sleep(500);
        fireReset();
        sleep(500);
        fireReset();
    }

    public void stopAll(){
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        flyWheel1.setPower(0);
        flyWheel1.setPower(0);
        collectionConveyor.setPower(0);
        sleep(300);
    }
    public void stopAllAfter(long time){
        sleep(time);
        stopAll();
    }
}
