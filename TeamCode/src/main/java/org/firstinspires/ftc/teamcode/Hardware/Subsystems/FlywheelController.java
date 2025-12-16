package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;

public class FlywheelController {
    RobotHardware rob;
    TelemetryManager tel;
    Boolean isActive = false;

    public void init(RobotHardware passedRob, TelemetryManager passedTel) {
        this.rob = passedRob;
        this.tel = passedTel;
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

    public void setPower(double power){
        rob.flywheelMotor.setPower(power);
    }
}
