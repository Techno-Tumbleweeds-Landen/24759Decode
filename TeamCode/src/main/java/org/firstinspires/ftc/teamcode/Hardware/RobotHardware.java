package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;   // add this import


public class RobotHardware {
    public DcMotor leftFront, rightFront, leftBack, rightBack, launchMotor;
    public IMU imu;
    public DigitalChannel magneticSwitch;   // Magnetic Limit Switch
    public Servo rgbLight;                  // GoBilda RGB Indicator Light


    public void init(HardwareMap hwMap) {
        // IMU sensor
        imu = hwMap.get(IMU.class, "imu");

        // Motor Names and Variables
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");

        // Motor Directions
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        // Encoder Settings
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Motor Zero Power Behavior
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Test motor
        launchMotor = hwMap.get(DcMotor.class, "launchMotor");
        launchMotor.setDirection(DcMotor.Direction.FORWARD);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Magnetic Limit Switch
        magneticSwitch = hwMap.get(DigitalChannel.class, "magneticSwitch");
        magneticSwitch.setMode(DigitalChannel.Mode.INPUT);

        /*
        rightLaunch = hwMap.get(DcMotor.class, "rightLaunch");
        leftLaunch = hwMap.get(DcMotor.class, "leftLaunch");

        rightLaunch.setDirection(DcMotor.Direction.FORWARD);
        leftLaunch.setDirection(DcMotor.Direction.REVERSE);
        rightLaunch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLaunch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

         */

        rgbLight = hwMap.get(Servo.class, "light1");
    }
}
