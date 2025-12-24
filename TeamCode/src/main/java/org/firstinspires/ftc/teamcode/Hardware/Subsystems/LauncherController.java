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
        if (toggle) {
            isActive = !isActive;
        }
    }
    public void controlHood(Gamepad gamepad) {

        // Convert stick from [-1, 1] to [0, 1]
        double input = (-gamepad.left_stick_y + 1.0) / 2.0;

        // Clamp
        //input = Math.max(0.0, Math.min(input, var.HOOD_UP));

        // Map [0,1] → [0.38,1.0]
        double servoPos = var.HOOD_DOWN + (var.HOOD_UP - var.HOOD_DOWN) * input;

        rob.hood.setPosition(servoPos);
    }

    /**
     * @param position position from 0 to 1
     */
    public void controlHood(double position) {
        double input = position;

        // Clamp
        input = Math.max(0.0, Math.min(input, 1));

        // Map [0,1] → [0.38,1.0]
        double servoPos = var.HOOD_DOWN + (var.HOOD_UP - var.HOOD_DOWN) * input;

        rob.hood.setPosition(servoPos);

        tel.log("input", input);
        tel.log("pos", servoPos);
    }


    public void controlLaunchRotate(Gamepad gamepad, double tx) {
        if (gamepad.x) {
            if (Math.abs(rob.launchRotateMotor.getCurrentPosition()) > 300) {
                rob.launchRotateMotor.setPower(0);
            } else {
                // PID
                tx = (Math.abs(tx) > 1) ? tx : 0;
                double power = Math.pow(tx, 2);
                rob.launchRotateMotor.setPower(tx / 50);
            }
        } else {
            rob.launchRotateMotor.setPower(gamepad.left_stick_x);
        }
    }
}
