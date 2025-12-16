package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class SorterController {
    RobotHardware rob;
    TelemetryManager tel;
    Variables variables;
    double distToPos, armPos;

    public void init(RobotHardware passedRob, TelemetryManager passedTel) {
        this.rob = passedRob;
        this.tel = passedTel;
    }


    public void controlSorter(Gamepad gamepad) {
        if (gamepad.b) {
            rob.rightLaunchServo.setPosition(0.54);
        } else if (gamepad.start) {
            rob.rightLaunchServo.setPosition(gamepad.right_stick_x);
        } else {
            rob.rightLaunchServo.setPosition(0.26);
        }
    }

}
