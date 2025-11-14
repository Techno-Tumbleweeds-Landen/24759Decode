package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.LauncherController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.SorterController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;

@TeleOp
public class TeleOpMain extends OpMode {
    // Creates instances of our subsystems
    IMUSensor gyro = new IMUSensor();
    Drivetrain drivetrain = new Drivetrain();
    TelemetryManager tel = new TelemetryManager();
    RobotHardware rob = new RobotHardware();
    IntakeController intake = new IntakeController();
    SorterController sorter = new SorterController();
    LauncherController launcher = new LauncherController();
    double heading;
    boolean fieldMovement = false;
    @Override
    public void init() {
        // Initializes our subsystems
        rob.init(hardwareMap);
        tel.init(telemetry);
        gyro.init(rob);
        drivetrain.init(rob, tel);
        intake.init(rob);
        sorter.init(rob, tel);
        launcher.init(rob, tel);
    }

    @Override
    public void loop() {

        heading = gyro.getHeading();
        // handle movement
        if (gamepad1.leftBumperWasPressed()) {fieldMovement = !fieldMovement;}
        if (fieldMovement) {
            drivetrain.fielddrive(gamepad1, heading, 0.8f);
        } else {
            drivetrain.robotDrive(gamepad1, 0.8f);
        }
        if (gamepad1.a) {
            gyro.resetIMU();
            drivetrain.resetIMU();
        }

        // handle extras
        intake.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
        sorter.setPower(gamepad2.left_stick_x);
        launcher.setPower(gamepad2.right_stick_x);
        sorter.setPos(gamepad2);






    }
}