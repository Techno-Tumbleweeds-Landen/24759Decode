package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class SorterController {
    RobotHardware rob;
    TelemetryManager tel;

    public void init(RobotHardware passedRob, TelemetryManager passedTel) {
        this.rob = passedRob;
        this.tel = passedTel;
    }
    public void setPower(double power) {
        rob.sorterMotor.setPower(power);
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
            rob.sorterMotor.setPower(0);
        }

        if (motorPower) {
            // Set target ONCE
            rob.sorterMotor.setTargetPosition(position);
            // Make sure mode is correct once at init, not every loop
            rob.sorterMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // Keep power nonzero so PID loop can hold it
            rob.sorterMotor.setPower(0.25);
        }else {
            rob.sorterMotor.setPower(0);
        }
    }

    public void setPos(Gamepad gamepad) {
        if (gamepad.a) {
            rob.launchServo.setPosition(0.8);
        } else{
            rob.launchServo.setPosition(0.3);
        }
    }

}
