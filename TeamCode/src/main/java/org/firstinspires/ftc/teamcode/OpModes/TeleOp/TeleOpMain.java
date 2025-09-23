package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Variables;

@TeleOp
public class TeleOpMain extends OpMode {
    // Creates instances of our subsystems
    IMUSensor gyr = new IMUSensor();
    RobotHardware rob  = new RobotHardware();
    Drivetrain mov = new Drivetrain();
    Variables var = new Variables();

    @Override
    public void init() {
        // Initializes our subsystems
        gyr.init(hardwareMap);
        rob.init(hardwareMap);
    }

    @Override
    public void loop() {
        // Tests two Front Motors
        // double LeftStickY, double LeftStickX, double RightStickY, double RightStickX, double heading,
        // gamepad1.left_stick_y, gamepad1.left_stick_x,
        //                gamepad1.right_stick_y, gamepad1.right_stick_x,
        //                gyr.getHeading(),
        mov.drive(0.8f);

        //rob.rightFront.setPower((( - gamepad1.right_stick_y - gamepad1.left_stick_x) - (gamepad1.right_stick_x)));
        //rob.rightBack.setPower((( - gamepad1.right_stick_y - gamepad1.left_stick_x) + (gamepad1.right_stick_x)));
        //rob.leftBack.setPower((( - gamepad1.right_stick_y + gamepad1.left_stick_x) - (gamepad1.right_stick_x)));
        //rob.leftFront.setPower((( - gamepad1.right_stick_y + gamepad1.left_stick_x) + (gamepad1.right_stick_x)));

        // Tests IMU on button press
        if (gamepad1.a) {
            telemetry.addData("Heading", gyr.getHeading());

        }
    }
}