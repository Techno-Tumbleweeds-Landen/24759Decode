package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class IntakeController {
    RobotHardware rob;

    public void init(RobotHardware robot) {
        this.rob = robot;
    }

    public void setPower(double power){
        rob.intakeMotor.setPower(power);
    }


}
