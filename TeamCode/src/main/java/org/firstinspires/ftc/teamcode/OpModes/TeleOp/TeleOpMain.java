package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.FlickerController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.LauncherController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

@TeleOp
public class TeleOpMain extends OpMode {

    Variables var;
    RobotHardware rob;
    TelemetryManager tel ;
    Drivetrain drivetrain;
    IntakeController intake;
    FlickerController flickers;
    LauncherController launcher;
    IMUSensor gyro;
    double heading;

    @Override
    public void init() {
        var = new Variables();
        rob = new RobotHardware(hardwareMap);
        tel = new TelemetryManager(telemetry);
        drivetrain = new Drivetrain(rob, tel, var);
        intake = new IntakeController(rob, tel, var);
        flickers = new FlickerController(rob, tel, var);
        launcher = new LauncherController(rob, tel, var);
        gyro = new IMUSensor(rob);
    }

    @Override
    public void loop() {

        // GET HEADING
        heading = gyro.getHeading();

        // CONTROLS SORTER
        flickers.controlFlickers(gamepad2);

        // CONTROLS LAUNCHER (flywheel, hood, & rotator)
        // flywheel control
        launcher.toggleFlywheel(gamepad2.rightBumperWasPressed());
        launcher.controlFlywheel(gamepad2, 0.90);
        // hood control
        launcher.controlHood(gamepad2);
        // rotator control
        launcher.controlLaunchRotate(gamepad2);

        // CONTROLS INTAKE
        intake.toggleIntake(gamepad2.startWasPressed());
        intake.controlIntake(gamepad2);

        // CONTROLS DRIVETRAIN
        drivetrain.changeMovement(gamepad1.leftBumperWasPressed());
        drivetrain.controlRobot(gamepad1, heading);
        drivetrain.resetIMU(gamepad1.a, gyro);

        // CONTROLS SORTER

        // TELEMETRY
        telemetry.addData("", "");
        telemetry.addData("IMU Position", heading * 180);
        telemetry.update();
    }
}
