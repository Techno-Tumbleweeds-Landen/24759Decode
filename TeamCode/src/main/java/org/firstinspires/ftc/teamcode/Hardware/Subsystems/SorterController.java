package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

import org.firstinspires.ftc.teamcode.Software.Variables;

public class SorterController {
    RobotHardware robot;

    public SorterController(RobotHardware robot) {
        this.robot = robot;
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
                robot.testMotor.setPower(0);
            }

            if (motorPower) {
                // Set target ONCE
                robot.testMotor.setTargetPosition(position);
                // Make sure mode is correct once at init, not every loop
                robot.testMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // Keep power nonzero so PID loop can hold it
                robot.testMotor.setPower(0.25);
            }else if (!motorPower){
                robot.testMotor.setPower(0);
            }
        }

    }
