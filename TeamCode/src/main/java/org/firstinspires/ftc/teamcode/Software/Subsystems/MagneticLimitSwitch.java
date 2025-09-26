package org.firstinspires.ftc.teamcode.Software.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class MagneticLimitSwitch {
    private DcMotor motor;
    private DigitalChannel limitSwitch;

    public MagneticLimitSwitch(DcMotor motor, DigitalChannel limitSwitch) {
        this.motor = motor;
        this.limitSwitch = limitSwitch;
    }

    public void checkAndReset() {
        // On REV hardware, false = pressed
        if (!limitSwitch.getState()) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public boolean isPressed() {
        return !limitSwitch.getState();
    }
}
