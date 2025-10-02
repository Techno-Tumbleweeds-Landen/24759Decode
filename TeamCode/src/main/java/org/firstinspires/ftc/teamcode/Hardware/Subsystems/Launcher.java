package org.firstinspires.ftc.teamcode.Hardware.Subsystems;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Variables;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;


@TeleOp
public class Launcher extends OpMode {
    IMUSensor gyr = new IMUSensor();
    Drivetrain mov = new Drivetrain();
    RobotHardware robot = new RobotHardware();  // create instance

    double heading;

    @Override
    public void init() {
        robot.init(hardwareMap);  // initialize hardware
        gyr.init(hardwareMap);
        mov.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            robot.rightLaunch.setPower(1);
            robot.leftLaunch.setPower(1);
        } else {
            robot.rightLaunch.setPower(0); // stop when button not pressed
            robot.leftLaunch.setPower(0);
        }
    }
}
