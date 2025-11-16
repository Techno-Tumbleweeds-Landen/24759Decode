package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;

public class LauncherController {
    RobotHardware rob;
    TelemetryManager tel;

    public void init(RobotHardware passedRob, TelemetryManager passedTel) {
        this.rob = passedRob;
        this.tel = passedTel;
    }

    public void setPower(double power){
        rob.launchMotor.setPower(power);
    }
}
