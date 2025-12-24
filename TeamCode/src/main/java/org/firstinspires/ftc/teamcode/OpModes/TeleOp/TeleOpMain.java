package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.FlickerController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.LauncherController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.Limelight;
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
    Limelight limelight;
    double heading, tx, ty;
    double hoodPos = 0.1, launcherSpeed = 0.1;

    @Override
    public void init() {
        var = new Variables();
        rob = new RobotHardware(hardwareMap);
        tel = new TelemetryManager(telemetry);
        drivetrain = new Drivetrain(rob, tel, var);
        intake = new IntakeController(rob, tel, var);
        flickers = new FlickerController(rob, tel, var);
        launcher = new LauncherController(rob, tel, var);
        limelight = new Limelight(rob, tel, var);
        gyro = new IMUSensor(rob);
    }

    @Override
    public void start() {
        gyro.resetIMU();
        limelight.start(0);
    }

    @Override
    public void loop() {

        if (gamepad1.xWasPressed()) {
            hoodPos += 0.1;
        }
        if (gamepad1.yWasPressed()) {
            hoodPos -= 0.1;
        }
        if (gamepad1.bWasPressed()) {
            launcherSpeed += 0.1;
        }
        if (gamepad1.aWasPressed()) {
            launcherSpeed -= 0.1;
        }

        // GET HEADING
        heading = gyro.getHeading();

        // LIMELIGHT
        tx = limelight.getInfo().getTx();
        ty = limelight.getInfo().getTy();

        // CONTROLS SORTER
        flickers.controlFlickers(gamepad2);

        // CONTROLS LAUNCHER (flywheel, hood, & rotator)
        // flywheel control
        launcher.toggleFlywheel(gamepad2.rightBumperWasPressed());
        launcher.controlFlywheel(gamepad2, launcherSpeed);
        // hood control
        launcher.controlHood(hoodPos);
        // rotator control
        launcher.controlLaunchRotate(gamepad2, tx);

        // CONTROLS INTAKE
        intake.toggleIntake(gamepad2.startWasPressed());
        intake.controlIntake(gamepad2);

        // CONTROLS DRIVETRAIN
        drivetrain.changeMovement(gamepad1.leftBumperWasPressed());
        drivetrain.controlRobot(gamepad1, heading);
        drivetrain.resetIMU(gamepad1.a, gyro);

        // CONTROLS SORTER

        // TELEMETRY
        telemetry.addData("Launcher Speed", launcherSpeed);
        telemetry.addData("Hood Position", hoodPos);
        telemetry.addData("IMU Position", heading * 180);
        telemetry.addData("TX", tx);
        telemetry.addData("TY", ty);
        telemetry.update();
    }
}
