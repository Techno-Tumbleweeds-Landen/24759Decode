package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;


@TeleOp
public class Launcher extends OpMode {
    RobotHardware robot = new RobotHardware();
    TelemetryManager tel = new TelemetryManager();
    IMUSensor gyr = new IMUSensor();
    Drivetrain mov = new Drivetrain(robot, tel);


    double heading;

    @Override
    public void init() {
        robot.init(hardwareMap);  // initialize hardware
        tel.init(telemetry);
        gyr.init(robot);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            robot.flywheelMotor.setPower(0.5);
            //robot.leftLaunch.setPower(1);
        } else if (gamepad1.b) {
            robot.flywheelMotor.setPower(0.35);
        }
        else {
            robot.flywheelMotor.setPower(gamepad1.left_trigger); // stop when button not pressed
            tel.log("speed",robot.flywheelMotor.getPower());

            //robot.leftLaunch.setPower(0);
        }
    }
}
