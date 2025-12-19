package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class LauncherController {
    RobotHardware rob;
    TelemetryManager tel;
    Variables var;
    Boolean isActive = false;

    public LauncherController(RobotHardware passedRob, TelemetryManager passedTel, Variables passedVar) {
        this.rob = passedRob;
        this.tel = passedTel;
        this.var = passedVar;
    }

    public void controlFlywheel(Gamepad gamepad, double power) {
        if (isActive) {
            rob.flywheelMotor.setPower(power);
        } else {
            rob.flywheelMotor.setPower(gamepad.right_stick_y);
        }
    }

    public void toggleFlywheel(Boolean toggle) {
        tel.log("Launcher Active", isActive);
        if (toggle) {
            isActive = !isActive;
        }
    }
    public void controlHood(Gamepad gamepad) {
        rob.hood.setPosition(gamepad.left_stick_y);
    }
    public void controlLaunchRotate(Gamepad gamepad) {
        rob.launchRotateMotor.setPower(gamepad.left_stick_x);
    }

    public void setPower(double power){
        rob.flywheelMotor.setPower(power);
    }
}
