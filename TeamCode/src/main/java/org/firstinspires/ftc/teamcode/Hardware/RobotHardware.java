package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;   // add this import


public class RobotHardware {
    public DcMotor leftFront, rightFront, leftBack, rightBack;
    public DcMotor flywheelMotor, intakeMotor, launchRotateMotor;
    public IMU imu;
    public DigitalChannel magneticSwitch;   // Magnetic Limit Switch
    public Servo rgbLight, frontRightLaunch, frontLeftLaunch, backRightLaunch, backLeftLaunch, hood;

    public Limelight3A limelight;

    public     NormalizedColorSensor colorSensor;

    public RobotHardware(HardwareMap hwMap) {
        // Motor Names and Variables (drivetrain)
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        // Motor Names and Variables (other)
        flywheelMotor = hwMap.get(DcMotor.class, "launchMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        launchRotateMotor = hwMap.get(DcMotor.class, "launchRotator");
        // Servo Names and Variables
        backLeftLaunch = hwMap.get(Servo.class, "backLeftLaunch"); // port 0
        backRightLaunch = hwMap.get(Servo.class, "backRightLaunch"); // port 1
        frontLeftLaunch = hwMap.get(Servo.class, "frontLeftLaunch"); // port 2
        frontRightLaunch = hwMap.get(Servo.class, "frontRightLaunch"); // port 3
        hood = hwMap.get(Servo.class, "hood"); // port 4

        //colorSensor = hwMap.get(NormalizedColorSensor.class, "colorSensor"); //I2C Port 1


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

        // drivetrain zero power behavior
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Flywheel motor
        flywheelMotor.setDirection(DcMotor.Direction.REVERSE);
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        launchRotateMotor.setDirection(DcMotor.Direction.REVERSE);
        launchRotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // NON-ACTUATOR COMPONENTS
        //magneticSwitch = hwMap.get(DigitalChannel.class, "magneticSwitch");
        //magneticSwitch.setMode(DigitalChannel.Mode.INPUT);
        // IMU sensor
        imu = hwMap.get(IMU.class, "imu");

        // limelight
        limelight = hwMap.get(Limelight3A.class, "limelight");
       // rgbLight = hwMap.get(Servo.class, "light1");
    }
}
