package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class RobotHardware {
    public DcMotor leftFront, rightFront, leftBack, rightBack, testMotor, rightLaunch, leftLaunch;
    public IMU imu;
    public DigitalChannel magneticSwitch;   // Magnetic Limit Switch

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

        // Motor Zero Power Behavior
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Test motor
        testMotor = hwMap.get(DcMotor.class, "testMotor");
        testMotor.setDirection(DcMotor.Direction.FORWARD);
        testMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Magnetic Limit Switch
        magneticSwitch = hwMap.get(DigitalChannel.class, "magneticSwitch");
        magneticSwitch.setMode(DigitalChannel.Mode.INPUT);

        rightLaunch = hwMap.get(DcMotor.class, "rightLaunch");
        leftLaunch = hwMap.get(DcMotor.class, "leftLaunch");

        rightLaunch.setDirection(DcMotor.Direction.FORWARD);
        leftLaunch.setDirection(DcMotor.Direction.REVERSE);
        rightLaunch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLaunch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
