package org.firstinspires.ftc.teamcode.OpModes.AUTO;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class AUTOMain extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor wheel = hardwareMap.get(DcMotor.class, "leftFront");
        waitForStart();
        wheel.setPower(0.8);
        sleep(1000);
    }
}
