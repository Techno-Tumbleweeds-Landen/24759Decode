package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.RotateMotor;

@TeleOp
public class MotorTrackingTeleOp extends OpMode {
    // Subsystems
    IMUSensor gyro = new IMUSensor();
    RobotHardware robot = new RobotHardware();
    Drivetrain move = new Drivetrain();
    RotateMotor rotate;

    @Override
    public void init() {
        // Init hardware
        gyro.init(hardwareMap);
        robot.init(hardwareMap);

        // Pass initialized robot to RotateMotor
        rotate = new RotateMotor(robot);
    }

    //boolean lastA = false;

    @Override
    public void loop() {
        /*
        if (gamepad1.a && !lastA) {   // button just pressed
            rotate.rotateSorter(1);
        } else if (gamepad1.b && lastA) {  // button just released
            rotate.rotateSorter(1);
        }else if (gamepad1.x && !lastA) {
            rotate.rotateSorter(2);
        } else {
            rotate.rotateSorter(4);
        }

        //lastA = gamepad1.a;
         */

        if (gamepad1.a) {
            rotate.rotateSorter(0);
        } else if (gamepad1.b) {
            rotate.rotateSorter(1);
        } else if (gamepad1.x) {
            rotate.rotateSorter(2);
        } else {
            rotate.rotateSorter(4 ); // stop motor
        }

        telemetry.addData("Position", robot.testMotor.getCurrentPosition());
        telemetry.addData("Busy?", robot.testMotor.isBusy());
    }

}
