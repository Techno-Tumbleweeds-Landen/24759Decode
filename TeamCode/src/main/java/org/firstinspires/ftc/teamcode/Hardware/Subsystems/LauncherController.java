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

        // Invert stick so up = increase
        double stick = gamepad.left_stick_y;   // [-1, +1]

        // Map stick -> logical range [-1.0, -0.3826]
        double logicalPos =
                -0.3826 + (-1.0 + 0.38) * ((stick + 1.0) / 2.0);

        // Map logical range -> servo range [0.0, 1.0]
        double servoPos =
                (logicalPos - (-1.0)) / (-0.38 - (-1.0));

        if (gamepad.square) {
            rob.hood.setPosition(servoPos);
        }

        tel.log("stick", stick);
        tel.log("servoPos", rob.hood.getPosition());
    }

    public void controlLaunchRotate(Gamepad gamepad) {
        rob.launchRotateMotor.setPower(gamepad.left_stick_x);
    }

    public void setPower(double power){
        rob.flywheelMotor.setPower(power);
    }
}
