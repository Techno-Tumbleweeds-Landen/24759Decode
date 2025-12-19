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
            rob.rightLaunchServo.setPosition(var.LEFT_SERVO_UP);
        } else if (gamepad.start) {
            rob.rightLaunchServo.setPosition(gamepad.right_stick_x);
        } else {
            rob.rightLaunchServo.setPosition(var.LEFT_SERVO_DOWN);
        }
    }

}
