package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class LauncherController {
    RobotHardware rob;
    TelemetryManager tel;
    Variables var;
    Boolean flywheelIsActive = false;
    Boolean focusIsActive = false;
    double lastTx;

    /**
     * Constructor
     * @param passedRob passed robot from teleop
     * @param passedTel passed telemetryManager from teleop
     * @param passedVar passed variables from teleop
     */
    public LauncherController(RobotHardware passedRob, TelemetryManager passedTel, Variables passedVar) {
        this.rob = passedRob;
        this.tel = passedTel;
        this.var = passedVar;
    }

    // ================================================================ Flywheel
    public void controlFlywheel(Gamepad gamepad, double power) {
        if (flywheelIsActive) {
            rob.flywheelMotor.setPower(power);
        } else {
            rob.flywheelMotor.setPower(gamepad.right_stick_y);
        }
    }

    public void toggleFlywheel(Boolean toggle) {
        if (toggle) {
            flywheelIsActive = !flywheelIsActive;
        }
    }
    // ================================================================ Hood
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
    }

    // ================================================================ Launch Rotate
    public void controlLaunchRotate(Gamepad gamepad, LLResult llInfo) {
        double tx = llInfo.getTx();
        double ta = llInfo.getTa();
        tel.log("Launcher Focus Status: ", focusIsActive? "Active" : "Inactive");
        if (focusIsActive) {
            // Handles when tag goes out of frame
            if (ta > 0) {
                lastTx = tx;
            } else {
                tx = lastTx;
            }
            // handles when turret is past the coord length
            if (Math.abs(rob.launchRotateMotor.getCurrentPosition()) > var.LAUNCH_ROTATE_MAX) {
                rob.launchRotateMotor.setPower(0);
                tel.log("Launcher out of range", "");
            } else {
                // 'PID' LOGIC
                tx = (Math.abs(tx) > 1) ? tx : 0;
                double power = Math.pow(tx, 2);
                rob.launchRotateMotor.setPower(tx / 45);
            }
        } else {
            rob.launchRotateMotor.setPower(gamepad.left_stick_x);
        }
        tel.log("Rotate Position", rob.launchRotateMotor.getCurrentPosition());
        tel.log("RotatePower", rob.launchRotateMotor.getPower());
    }

    public void toggleFocus(Boolean toggle) {
        if (toggle) {
            focusIsActive = !focusIsActive;
        }
    }

}
