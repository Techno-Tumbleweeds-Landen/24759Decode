package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.LauncherController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.SorterController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.Sorter_Automation;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;

import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOpMain extends OpMode {

    IMUSensor gyro = new IMUSensor();
    Drivetrain drivetrain = new Drivetrain();
    TelemetryManager tel = new TelemetryManager();

    RobotHardware rob = new RobotHardware();   // only once

    IntakeController intake = new IntakeController();
    SorterController sorter = new SorterController();
    LauncherController launcher = new LauncherController();
    Sorter_Automation cycler = new Sorter_Automation();
    double heading;
    boolean fieldMovement = false;

    public DcMotor sorterMotor;

    @Override
    public void init() {
        rob.init(hardwareMap);
        tel.init(telemetry);
        gyro.init(rob);
        drivetrain.init(rob, tel);
        intake.init(rob);
        sorter.init(rob, tel);
        launcher.init(rob, tel);
        cycler.init(rob);          // now valid
        sorterMotor = rob.sorterMotor;
    }

    @Override
    public void loop() {

        heading = gyro.getHeading();

        if (gamepad1.leftBumperWasPressed()) {
            fieldMovement = !fieldMovement;
        }

        if (fieldMovement) {
            drivetrain.fielddrive(gamepad1, heading, 0.8f);
        } else {
            drivetrain.robotDrive(gamepad1, 0.8f);
        }

        if (gamepad1.a) {
            gyro.resetIMU();
            drivetrain.resetIMU();
        }

        intake.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
        //sorter.setPower(gamepad2.left_stick_x);
        launcher.setPower(gamepad2.right_stick_x);
        //sorter.setPos(gamepad2);
        cycler.update(gamepad2);

        telemetry.addData("Sorter Position", sorterMotor.getCurrentPosition());
        telemetry.update();
    }
}
