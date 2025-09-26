package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Software.Subsystems.Operators;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;

public class Drivetrain {
    Operators ope = new Operators();
    RobotHardware rob = new RobotHardware();

    double tangent, normal, rotate;
    double rfPow, rbPow, lbPow, lfPow;
    double targetHeading = 0.0;
    double headingError;
    boolean isStrafingOnly;

    final double DEADZONE = 0.05;
    final double STRAFE_SCALAR = 1.2;
    final double HEADING_KP = 0.5;

    public void init(HardwareMap hwMap) {
        rob.init(hwMap);
    }

    public void drive(double LeftStickY, double LeftStickX,
                      double RightStickY, double RightStickX,
                      double heading, double motorSpeed) {

        // 🧼 Apply deadzone to all joystick inputs
        LeftStickY = (Math.abs(LeftStickY) < DEADZONE) ? 0.0 : LeftStickY;
        LeftStickX = (Math.abs(LeftStickX) < DEADZONE) ? 0.0 : LeftStickX;
        RightStickY = (Math.abs(RightStickY) < DEADZONE) ? 0.0 : RightStickY;
        RightStickX = (Math.abs(RightStickX) < DEADZONE) ? 0.0 : RightStickX;

        // 🧭 Field-oriented drive using provided heading
        tangent = Math.sin(heading) * RightStickX + Math.cos(heading) * RightStickY;
        normal = -Math.cos(heading) * RightStickX + Math.sin(heading) * RightStickY;
        rotate = LeftStickX;

        // ⚙️ Boost strafing power to compensate for strafing being slow
        normal *= STRAFE_SCALAR;

        // 🛡️ Heading hold correction during pure strafing
        isStrafingOnly = rotate == 0.0 && LeftStickY == 0.0 && (RightStickX != 0.0 || RightStickY != 0.0);

        if (!isStrafingOnly) {
            targetHeading = heading;
        }

        headingError = targetHeading - heading;
        if (isStrafingOnly) {
            rotate = headingError * HEADING_KP;
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
    }
}