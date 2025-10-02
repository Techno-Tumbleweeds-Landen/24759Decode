package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class LauncherController {
    RobotHardware rob;

    public void init(RobotHardware robot) {
        this.rob = robot;
    }

    public void launch(double power){
        rob.rightLaunch.setPower(power);
        rob.leftLaunch.setPower(power);

    }
}
