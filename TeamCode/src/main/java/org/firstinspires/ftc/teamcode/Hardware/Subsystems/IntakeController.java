package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class IntakeController {
    RobotHardware rob;
    Boolean intakeActive = false;
    TelemetryManager tel;


    public void init(RobotHardware robot, TelemetryManager passedTel) {
        this.rob = robot;
        this.tel = passedTel;
    }

    public void controlIntake(Gamepad gamepad, Variables variables) {
        tel.log("Intake Active", intakeActive);
        if (intakeActive) {
            rob.intakeMotor.setPower(variables.intakeSpeed);
        } else {
            rob.intakeMotor.setPower(gamepad.right_trigger-gamepad.left_trigger);
        }
    }

    public void toggleIntake(Boolean toggle) {
        if (toggle) {
            intakeActive = !intakeActive;
        }
    }

    public void setPower(double power){
        rob.intakeMotor.setPower(power);
    }


}
