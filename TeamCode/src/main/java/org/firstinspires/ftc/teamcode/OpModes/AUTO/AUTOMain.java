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


import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;

@Autonomous
public class AUTOMain extends LinearOpMode {
    RobotHardware rob = new RobotHardware();   // REQUIRED
    @Override
    public void runOpMode() {
<<<<<<< HEAD
        rob.init(hardwareMap);

=======
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
>>>>>>> 22ea1c84155d2fccaa0754e440a18d50cfb4e825
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
