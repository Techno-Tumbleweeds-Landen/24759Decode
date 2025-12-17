package org.firstinspires.ftc.teamcode.OpModes.AUTO;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.FlywheelController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.IntakeController;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.SorterController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.Sorter_Automation;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;


import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;

@Autonomous
public class AUTOMain extends LinearOpMode {
    RobotHardware rob = new RobotHardware();
    TelemetryManager tel = new TelemetryManager();
    Drivetrain drivetrain = new Drivetrain(rob, tel);

    @Override
    public void runOpMode() {
        rob.init(hardwareMap);

        IMUSensor gyro = new IMUSensor();

        RobotHardware rob = new RobotHardware();   // only once

        IntakeController intake = new IntakeController();
        SorterController sorter = new SorterController();
        FlywheelController launcher = new FlywheelController();
        Sorter_Automation cycler = new Sorter_Automation();
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
