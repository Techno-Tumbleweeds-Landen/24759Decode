package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;

@TeleOp
public class TeleOpMain extends OpMode {
    // Creates instances of our subsystems
    IMUSensor gyro = new IMUSensor();
    RobotHardware robot  = new RobotHardware();
    Drivetrain move = new Drivetrain();

    @Override
    public void init() {
        // Initializes our subsystems
        gyro.init(hardwareMap);
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        // Tests two Front Motors
        move.drive(gamepad1.left_stick_y, gamepad1.left_stick_x,
                gamepad1.right_stick_y, gamepad1.right_stick_x,
                gyro.getHeading(), 0.8f);

        // Tests IMU on button press
        if (gamepad1.a) {
            telemetry.addData("Heading", gyro.getHeading());

        }
    }
}