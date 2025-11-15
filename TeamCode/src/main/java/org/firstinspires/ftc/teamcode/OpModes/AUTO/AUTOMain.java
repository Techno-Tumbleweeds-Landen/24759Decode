package org.firstinspires.ftc.teamcode.OpModes.AUTO;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;

@Autonomous
public class AUTOMain extends LinearOpMode {
    RobotHardware rob = new RobotHardware();   // REQUIRED
    @Override
    public void runOpMode() {
        rob.init(hardwareMap);

        waitForStart();
        rob.leftFront.setPower(-0.1);
        rob.rightFront.setPower(-0.1);
        rob.rightBack.setPower(-0.1);
        rob.leftBack.setPower(-0.1);
        sleep(1000);
        rob.leftFront.setPower(0);
        rob.rightFront.setPower(0);
        rob.rightBack.setPower(0);
        rob.leftBack.setPower(0);

    }
}
