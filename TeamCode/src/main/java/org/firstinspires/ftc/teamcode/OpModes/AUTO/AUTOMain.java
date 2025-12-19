package org.firstinspires.ftc.teamcode.OpModes.AUTO;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.FlickerController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.FlywheelController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.FlickerController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;


import org.firstinspires.ftc.teamcode.Software.Variables;

@Autonomous
public class AUTOMain extends LinearOpMode {
    Variables var;
    RobotHardware rob;
    TelemetryManager tel ;
    Drivetrain drivetrain;
    IntakeController intake;
    FlickerController sorter;
    FlywheelController launcher;
    IMUSensor gyro;
    @Override
    public void runOpMode() {
        var = new Variables();
        rob = new RobotHardware(hardwareMap);
        tel = new TelemetryManager(telemetry);
        drivetrain = new Drivetrain(rob, tel, var);
        intake = new IntakeController(rob, tel, var);
        sorter = new FlickerController(rob, tel, var);
        launcher = new FlywheelController(rob, tel, var);
        gyro = new IMUSensor(rob);

        double heading;
        boolean fieldMovement = false;

        DcMotor wheel = hardwareMap.get(DcMotor.class, "leftFront");
        waitForStart();
        rob.leftFront.setPower(-0.1);
        rob.rightFront.setPower(-0.1);
        rob.rightBack.setPower(-0.1);
        rob.leftBack.setPower(-0.1);
        sleep(1000);
        rob.leftFront.setPower(0);
        rob.rightFront.setPower(0);
        rob.rightBack.setPower(0);
        rob.leftBack.setPower(0);

    }
}
