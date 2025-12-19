package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class FlickerController {
    RobotHardware rob;
    TelemetryManager tel;
    Variables var;
    double distToPos, armPos;

    public FlickerController(RobotHardware passedRob, TelemetryManager passedTel, Variables passedVar) {
        this.rob = passedRob;
        this.tel = passedTel;
        this.var = passedVar;
    }


    public void controlFlickers(Gamepad gamepad) {
        if (gamepad.b) {
            rob.rightLaunchServo.setPosition(0.54);
        } else if (gamepad.start) {
            rob.rightLaunchServo.setPosition(gamepad.right_stick_x);
        } else {
            rob.rightLaunchServo.setPosition(0.26);
        }
    }

}
