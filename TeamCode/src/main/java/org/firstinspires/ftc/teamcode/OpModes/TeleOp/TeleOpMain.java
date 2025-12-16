package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.FlywheelController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.SorterController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.Sorter_Automation;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOpMain extends OpMode {

    IMUSensor gyro = new IMUSensor();
    Drivetrain drivetrain = new Drivetrain();
    TelemetryManager tel = new TelemetryManager();
    RobotHardware rob = new RobotHardware();   // only once
    IntakeController intake = new IntakeController();
    SorterController sorter = new SorterController();
    FlywheelController launcher = new FlywheelController();
    Sorter_Automation cycler = new Sorter_Automation();
    Variables variables = new Variables();
    double heading;
    double motorSpeed = 0.8;
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
        intake.init(rob, tel);
        sorter.init(rob, tel);
        launcher.init(rob, tel);
        cycler.init(rob, tel);          // now valid
    }

    @Override
    public void loop() {

        // GET HEADING
        heading = gyro.getHeading();

        // CONTROLS SORTER
        sorter.controlSorter(gamepad2);

        // CONTROLS FLYWHEEL
        launcher.toggleFlywheel(gamepad2.rightBumperWasPressed());
        launcher.controlFlywheel(gamepad2, 0.90);

        // CONTROLS INTAKE
        intake.toggleIntake(gamepad2.startWasPressed());
        intake.controlIntake(gamepad2, variables);

        // CONTROLS DRIVETRAIN
        drivetrain.changeMovement(gamepad1.leftBumperWasPressed());
        drivetrain.controlRobot(gamepad1, variables, heading);
        drivetrain.resetIMU(gamepad1.a, gyro);

        // CONTROLS SORTER)

        // TELEMETRY
        telemetry.addData("", "");
        telemetry.addData("IMU Position", heading * 180);
        telemetry.update();
    }
}
