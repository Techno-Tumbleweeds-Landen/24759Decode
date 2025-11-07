package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class SorterController {
    RobotHardware robot;
    TelemetryManager tel;

    public SorterController(RobotHardware robot, TelemetryManager passedTelemetry) {
        this.robot = robot;
        this.tel = passedTelemetry;
    }

    public void rotateSorter(int targetPos) {
        int position = 5;
        boolean motorPower = false;

        if (targetPos == 0) {
            motorPower = true;
            position = Variables.intake1;
        } else if (targetPos == 1) {
            motorPower = true;
            position = Variables.intake2;
        } else if (targetPos == 2) {
            motorPower = true;
            position = Variables.intake3;
        } else {
            motorPower = false;
            robot.launchMotor.setPower(0);
        }

        if (motorPower) {
            // Set target ONCE
            robot.launchMotor.setTargetPosition(position);
            // Make sure mode is correct once at init, not every loop
            robot.launchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // Keep power nonzero so PID loop can hold it
            robot.launchMotor.setPower(0.25);
        }else if (!motorPower){
            robot.launchMotor.setPower(0);
        }
    }
}
