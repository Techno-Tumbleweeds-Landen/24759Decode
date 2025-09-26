package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

@TeleOp
public class TeleOpMain extends OpMode {
    // Creates instances of our subsystems
    IMUSensor gyr = new IMUSensor();
    Drivetrain mov = new Drivetrain();
    TelemetryManager tel = new TelemetryManager();
    RobotHardware rob = new RobotHardware();
    double heading;

    @Override
    public void init() {
        // Initializes our subsystems
        rob.init(hardwareMap);
        gyr.init(rob);
        mov.init(rob);
        tel.init(telemetry);
    }

    @Override
    public void loop() {

        heading = gyr.getHeading();
        mov.drive(gamepad1.left_stick_y, gamepad1.left_stick_x,
                gamepad1.right_stick_y, gamepad1.right_stick_x,
                heading, 0.8f);

        if (gamepad1.a) {
            gyr.resetIMU();
        }


    }
}