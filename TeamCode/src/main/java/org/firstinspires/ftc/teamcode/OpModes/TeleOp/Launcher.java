package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;


@TeleOp
public class Launcher extends OpMode {
    IMUSensor gyr = new IMUSensor();
    Drivetrain mov = new Drivetrain();
    RobotHardware robot = new RobotHardware();

    TelemetryManager tel = new TelemetryManager();


    double heading;

    @Override
    public void init() {
        robot.init(hardwareMap);  // initialize hardware
        tel.init(telemetry);
        gyr.init(robot);
        mov.init(robot, tel);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            robot.launchMotor.setPower(0.5);
            //robot.leftLaunch.setPower(1);
        } else if (gamepad1.b) {
            robot.launchMotor.setPower(0.99);
        }
        else {
            robot.launchMotor.setPower(0); // stop when button not pressed
            //robot.leftLaunch.setPower(0);
        }
    }
}
