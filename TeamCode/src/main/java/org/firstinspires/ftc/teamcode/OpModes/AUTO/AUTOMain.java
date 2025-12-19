package org.firstinspires.ftc.teamcode.OpModes.AUTO;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.FlywheelController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.SorterController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;


import org.firstinspires.ftc.teamcode.Software.Variables;

@Autonomous
public class AUTOMain extends LinearOpMode {
    Variables var = new Variables();
    RobotHardware rob = new RobotHardware(hardwareMap);
    TelemetryManager tel = new TelemetryManager(telemetry);
    Drivetrain drivetrain = new Drivetrain(rob, tel, var);
    IntakeController intake = new IntakeController(rob, tel, var);
    FlywheelController launcher = new FlywheelController(rob, tel, var);
    SorterController sorter = new SorterController(rob, tel, var);
    IMUSensor gyro = new IMUSensor(rob);
    @Override
    public void runOpMode() {

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
