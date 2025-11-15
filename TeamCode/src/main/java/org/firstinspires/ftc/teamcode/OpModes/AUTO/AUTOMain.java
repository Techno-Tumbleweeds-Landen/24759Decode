package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.LauncherController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.SorterController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.Sorter_Automation;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;


@Autonomous
public class AUTOMain extends LinearOpMode {
    @Override
    public void runOpMode() {
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

        DcMotor wheel = hardwareMap.get(DcMotor.class, "leftFront");
        waitForStart();
        wheel.setPower(0.8);
        sleep(1000);
    }
}
