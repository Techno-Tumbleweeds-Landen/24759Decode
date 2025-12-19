package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

@TeleOp
public class Testing extends OpMode {
    RobotHardware robot;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        robot = new RobotHardware(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
        robot.leftFront.setPower(0.69);
    }
}
