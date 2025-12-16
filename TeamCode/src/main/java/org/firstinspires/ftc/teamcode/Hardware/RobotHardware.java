package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;   // add this import


public class RobotHardware {
    public DcMotor leftFront, rightFront, leftBack, rightBack;
    public DcMotor launchMotor, intakeMotor, launchRotateMotor;
    public IMU imu;
    public DigitalChannel magneticSwitch;   // Magnetic Limit Switch
    public Servo rgbLight, leftLaunchServo, rightLaunchServo, middleLaunchServoLeft, middleLaunchServoRight;


    public void init(HardwareMap hwMap) {
        // Motor Names and Variables (drivetrain)
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        // Motor Names and Variables (other)
        launchMotor = hwMap.get(DcMotor.class, "launchMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        launchRotateMotor = hwMap.get(DcMotor.class, "launchRotator");
        // Servo Names and Variables
        leftLaunchServo = hwMap.get(Servo.class, "leftLaunch"); // port 0
        rightLaunchServo = hwMap.get(Servo.class, "rightLaunch"); // port 1
        middleLaunchServoLeft = hwMap.get(Servo.class, "middleLeftLaunch"); // port 2
        middleLaunchServoRight = hwMap.get(Servo.class, "middleRightLaunch"); // port 3
        // Drivetrain Motor Directions
        leftFront.setDirection(DcMotor.Direction.REVERSE);
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
        launchMotor.setDirection(DcMotor.Direction.REVERSE);
        launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        launchRotateMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchRotateMotor.setDirection(DcMotor.Direction.REVERSE);
        launchRotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        // NON-ACTUATOR COMPONENTS
        magneticSwitch = hwMap.get(DigitalChannel.class, "magneticSwitch");
        magneticSwitch.setMode(DigitalChannel.Mode.INPUT);
        // IMU sensor
        imu = hwMap.get(IMU.class, "imu");

        rgbLight = hwMap.get(Servo.class, "light1");
    }
}
