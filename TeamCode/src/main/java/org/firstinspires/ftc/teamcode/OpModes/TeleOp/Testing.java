package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

@TeleOp
public class Testing extends OpMode {
//    RobotHardware robot;

    DcMotor test;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
//        robot = new RobotHardware(hardwareMap);
        test = hardwareMap.get(DcMotor.class, "launchRotator");
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
//        robot.leftFront.setPower(0.69);
        test.setPower(gamepad2.right_trigger);
    }
}
