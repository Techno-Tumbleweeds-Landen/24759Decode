package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

@TeleOp
public class HoodControl extends OpMode {
    RobotHardware rob;
    @Override
    public void init() {
        rob.init(hardwareMap);
    }
    @Override
    public void loop() {
        //0.3826
        rob.hood.setPosition();
    }

}
