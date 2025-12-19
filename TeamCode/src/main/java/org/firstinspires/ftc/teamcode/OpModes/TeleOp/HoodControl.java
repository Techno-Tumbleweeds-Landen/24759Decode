package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

@TeleOp
public class HoodControl extends OpMode {
    RobotHardware rob;
    @Override
    public void init() {
        rob = new RobotHardware(hardwareMap);
    }

    @Override
    public void loop() {
        double stick = -gamepad2.right_stick_y;

        double targetPosition =
                0.3826 + (1.0 - 0.3826) * ((stick + 1.0) / 2.0);

        rob.hood.setPosition(targetPosition);
    }


}
