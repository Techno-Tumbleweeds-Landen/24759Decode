package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Software.Subsystems.Operators;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;

public class Drivetrain {
    Operators ope = new Operators();
    private TelemetryManager tel;
    private RobotHardware rob;
    double tangent, normal, rotate;
    double rfPow, rbPow, lbPow, lfPow;
    double targetHeading = 0.0;
    double headingError;
    boolean isRotating = true;

    // CONSTANTS
    final double DEADZONE = 0.05;
    final double STRAFE_SCALAR = 1.2;
    final double ROTATE_SCALAR = 1;
    final double HEADING_CORRECTION = -0.04;

    public void init(RobotHardware passedRob, TelemetryManager passedTel) {
        this.rob = passedRob;
        this.tel = passedTel;
    }

    public void drive(double LeftStickY, double LeftStickX,
                      double RightStickY, double RightStickX,
                      double heading, double motorSpeed) {

        // 🧼 Apply deadzone to all joystick inputs
        LeftStickY = (Math.abs(LeftStickY) < DEADZONE) ? 0.0 : LeftStickY;
        LeftStickX = (Math.abs(LeftStickX) < DEADZONE) ? 0.0 : LeftStickX;
        RightStickY = (Math.abs(RightStickY) < DEADZONE) ? 0.0 : RightStickY;
        RightStickX = (Math.abs(RightStickX) < DEADZONE) ? 0.0 : RightStickX;

        // 🧭 Field-oriented drive using imu heading
        tangent = Math.sin(heading) * RightStickX + Math.cos(heading) * RightStickY;
        normal = -Math.cos(heading) * RightStickX + Math.sin(heading) * RightStickY;

        // ⚙️ Boost strafing power to compensate for strafing being slow
        normal *= STRAFE_SCALAR;

        // Determine if the user is trying to rotate the robot
        isRotating = LeftStickX != 0;

        // 🛡️ Heading hold correction during pure strafing
        if (isRotating) {
            tel.log("isRotating", "true");
            targetHeading = heading;
            rotate = LeftStickX * ROTATE_SCALAR;
        } else {
            tel.log("isRotating", "false");
            headingError = targetHeading - heading;
            rotate = headingError * HEADING_CORRECTION;
        }


        // 🧮 Calculate motor powers
        rfPow = tangent - normal + rotate;
        rbPow = tangent + normal + rotate;
        lbPow = tangent - normal - rotate;
        lfPow = tangent + normal - rotate;

        // 🧼 Clamp motor powers
        rfPow = ope.clamp(rfPow, -1, 1);
        rbPow = ope.clamp(rbPow, -1, 1);
        lbPow = ope.clamp(lbPow, -1, 1);
        lfPow = ope.clamp(lfPow, -1, 1);

        // 🚀 Apply motor speed scaling
        rob.rightFront.setPower(rfPow * motorSpeed);
        rob.rightBack.setPower(rbPow * motorSpeed);
        rob.leftBack.setPower(lbPow * motorSpeed);
        rob.leftFront.setPower(lfPow * motorSpeed);
        tel.log("hallo", LeftStickX);
        tel.update();
    }
}