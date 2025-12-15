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
    double motorSpeed = 0.8;
    boolean fieldMovement = false;
    boolean manualSorter = true;
    boolean launcherActive = false;

    boolean intakeActive = false;

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
        cycler.init(rob, tel);          // now valid
        sorterMotor = rob.sorterMotor;
    }

    @Override
    public void loop() {

        heading = gyro.getHeading();
        if (gamepad2.leftBumperWasPressed()) {
            manualSorter = !manualSorter;
        }
        if (gamepad2.rightBumperWasPressed() || gamepad1.rightBumperWasPressed()) {
            launcherActive = !launcherActive;
        }

        if (gamepad2.startWasPressed()) {
            intakeActive = !intakeActive;
        }

        if (launcherActive) {
            launcher.setPower(1);
        } else {
            launcher.setPower(0);
        }
/*
        if (intakeActive) {
            rob.intakeMotor.setPower(0.75);
        } else {
            rob.intakeMotor.setPower(0);
        }

 */

        if (manualSorter) {
            sorter.setPower(gamepad2.left_stick_x / 2);
        } else {
            //sorter.PIDSorter(gamepad2);
            cycler.update(gamepad2);
        }

        drivetrain.changeMovement(gamepad1.leftBumperWasPressed()); // 
        drivetrain.driveRobot(gamepad1, motorSpeed, heading);

        if (gamepad1.a) {
            gyro.resetIMU();
            drivetrain.resetIMU();
        }

        if (intakeActive){
            rob.intakeMotor.setPower(0.95);
        } else {
            intake.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
        }

        if (gamepad1.rightBumperWasPressed()) {
            intakeActive = !intakeActive;
        }

        if (intakeActive) {
            rob.intakeMotor.setPower(0.75);
        }else{
            rob.intakeMotor.setPower(0);
        }

        intake.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
        //sorter.setPower(gamepad2.left_stick_x);
        sorter.setPos(gamepad2);

        telemetry.addData("Sorter Position", sorterMotor.getCurrentPosition());
        telemetry.addData("", "");
        telemetry.addData("IMU Position", heading * 180);
        telemetry.update();
    }
}
