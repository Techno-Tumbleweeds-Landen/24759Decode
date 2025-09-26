package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.SorterController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.MagneticLimitSwitch;

@TeleOp
public class MotorTrackingTeleOp extends OpMode {
    // Subsystems
    IMUSensor gyro = new IMUSensor();
    RobotHardware robot = new RobotHardware();
    Drivetrain move = new Drivetrain();
    SorterController rotate;
    MagneticLimitSwitch limitReset;

    @Override
    public void init() {
        // Init hardware
        robot.init(hardwareMap);
        gyro.init(robot);

        // Pass initialized robot to RotateMotor
        rotate = new SorterController(robot);

        // Create limit reset helper
        limitReset = new MagneticLimitSwitch(robot.testMotor, robot.magneticSwitch);
    }

    @Override
    public void loop() {
        // Check the magnetic limit switch each loop
        limitReset.checkAndReset();

        if (gamepad1.a) {
            rotate.rotateSorter(0);
        } else if (gamepad1.b) {
            rotate.rotateSorter(1);
        } else if (gamepad1.x) {
            rotate.rotateSorter(2);
        } else {
            rotate.rotateSorter(4); // stop motor
        }

        telemetry.addData("Position", robot.testMotor.getCurrentPosition());
        telemetry.addData("Busy?", robot.testMotor.isBusy());
        telemetry.addData("Limit Pressed", limitReset.isPressed());
        telemetry.update();
    }
}
