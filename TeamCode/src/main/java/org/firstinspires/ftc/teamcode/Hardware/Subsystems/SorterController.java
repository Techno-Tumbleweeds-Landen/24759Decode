package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class SorterController {
    RobotHardware rob;
    TelemetryManager tel;
    double distToPos, armPos;

    public void init(RobotHardware passedRob, TelemetryManager passedTel) {
        this.rob = passedRob;
        this.tel = passedTel;
        this.armPos = rob.launchRotateMotor.getCurrentPosition();
        this.distToPos = 0;
    }
    public void setPower(double power) {
        rob.launchRotateMotor.setPower(power);
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
            rob.launchRotateMotor.setPower(0);
        }

        if (motorPower) {
            // Set target ONCE
            rob.launchRotateMotor.setTargetPosition(position);
            // Make sure mode is correct once at init, not every loop
            rob.launchRotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // Keep power nonzero so PID loop can hold it
            rob.launchRotateMotor.setPower(0.25);
        }else {
            rob.launchRotateMotor.setPower(0);
        }
    }

    public void PIDSorter(Gamepad gamepad) {
        if (gamepad.y) {
            this.armPos += 1;
        } else if (gamepad.a) {
            this.armPos -= 1;
        }
        this.distToPos = this.armPos - rob.launchRotateMotor.getCurrentPosition();
        rob.launchRotateMotor.setPower(distToPos * -0.5);
        tel.log("Sorter Motor Power: ", this.distToPos * -0.05);
        tel.log("distToPos: ", this.distToPos);
        tel.log("armPos", armPos);
        tel.log("position", rob.launchRotateMotor.getCurrentPosition());

    }

    public void setPos(Gamepad gamepad) {
        if (gamepad.a) {
            rob.middleLaunchServoRight.setPosition(0.8);
        } else{
            rob.middleLaunchServoRight.setPosition(0.3);
        }
    }

}
