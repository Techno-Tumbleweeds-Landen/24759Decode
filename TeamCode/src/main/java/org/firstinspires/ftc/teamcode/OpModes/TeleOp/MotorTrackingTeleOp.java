package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.SorterController;
import org.firstinspires.ftc.teamcode.Software.Subsystems.MagneticLimitSwitch;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;

@TeleOp
public class MotorTrackingTeleOp extends OpMode {
    // Subsystems
    IMUSensor gyro = new IMUSensor();
    RobotHardware robot = new RobotHardware();
    TelemetryManager tel = new TelemetryManager();
    Drivetrain move = new Drivetrain(robot, tel);
    SorterController rotate;
    MagneticLimitSwitch limitReset;

    @Override
    public void init() {
        // Init hardware
        robot.init(hardwareMap);
        tel.init(telemetry);
        gyro.init(robot);

        // Pass initialized robot to RotateMotor
        rotate = new SorterController();
        rotate.init(robot,tel);

        // Create limit reset helper
        limitReset = new MagneticLimitSwitch(robot.flywheelMotor, robot.magneticSwitch);
    }

    @Override
    public void loop() {
        // Check the magnetic limit switch each loop
        limitReset.checkAndReset();




        telemetry.addData("Position", robot.flywheelMotor.getCurrentPosition());
        telemetry.addData("Busy?", robot.flywheelMotor.isBusy());
        telemetry.addData("Limit Pressed", limitReset.isPressed());
        telemetry.update();
    }
}
