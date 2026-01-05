package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class FlickerController {
    RobotHardware rob;
    TelemetryManager tel;
    Variables var;

    public FlickerController(RobotHardware passedRob, TelemetryManager passedTel, Variables passedVar) {
        this.rob = passedRob;
        this.tel = passedTel;
        this.var = passedVar;
    }


    public void controlFlickers(Gamepad gamepad) {
        if (gamepad.b) {
            rob.backRightLaunch.setPosition(0.2);
        } else if (gamepad.start) {
            rob.backRightLaunch.setPosition(gamepad.right_stick_x);
        } else {
            rob.backRightLaunch.setPosition(1);

            if (gamepad.b) {
                rob.backLeftLaunch.setPosition(0.2);
            } else if (gamepad.start) {
                rob.backLeftLaunch.setPosition(gamepad.right_stick_x);
            } else {
                rob.backLeftLaunch.setPosition(0);
            }
        }
    }

}
