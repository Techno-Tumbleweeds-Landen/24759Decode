package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;
import org.firstinspires.ftc.teamcode.Software.Subsystems.Operators;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.TelemetryManager;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class Drivetrain {
    Operators ope = new Operators();
    private TelemetryManager tel;
    private RobotHardware rob;
    private Variables var;
    double tangent, normal, rotate;
    double leftStickY, leftStickX, rightStickY, rightStickX;
    double rfPow, rbPow, lbPow, lfPow;
    double targetHeading = 0.0;
    double headingError;
    boolean isRotating = true;
    boolean fieldMovement = false ;

    // CONSTANTS
    final double DEADZONE = 0.05;
    final double STRAFE_SCALAR = -1.2;
    final double ROTATE_SCALAR = 0.5;
    double HEADING_CORRECTION = 0.5;

    /**
     * Constructor method to initialize drivetrain with robotHardware and telemetryManager
     *
     * @param passedRob RobotHardware object to be used in the class
     * @param passedTel TelemetryManager object to be used in the class
     */
    public Drivetrain(RobotHardware passedRob, TelemetryManager passedTel, Variables passedVar) {
        this.rob = passedRob;
        this.tel = passedTel;
        this.var = passedVar;
    }

    public void controlRobot(Gamepad gamepad, double heading) {
        tel.log("\nFieldMovement", fieldMovement);
        if (fieldMovement) {
            fieldDrive(gamepad, heading, var.MEDSPEED);
        } else {
            robotDrive(gamepad, var.MEDSPEED);
        }
    }
    public void resetIMU(Boolean reset, IMUSensor gyro) {
        if (reset) {
            gyro.resetIMU();
            targetHeading = 0;
        }
    }

    public void changeMovement(Boolean wasBumperPressed) {
        if (wasBumperPressed) {
            fieldMovement = !fieldMovement;
        }
    }

    public void robotDrive(Gamepad gamepad1, double motorSpeed) {
        leftStickY = gamepad1.left_stick_y;
        leftStickX = gamepad1.left_stick_x;
        rightStickY = gamepad1.right_stick_y;
        rightStickX = gamepad1.right_stick_x;

        tangent = rightStickY;
        normal = rightStickX * STRAFE_SCALAR;
        rotate = leftStickX;

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

        rob.rightFront.setPower(rfPow * motorSpeed);
        rob.rightBack.setPower(rbPow * motorSpeed);
        rob.leftBack.setPower(lbPow * motorSpeed);
        rob.leftFront.setPower(lfPow * motorSpeed);
        

    }


    public void fieldDrive(Gamepad gamepad1,
                           double heading, double motorSpeed) {

        leftStickY = gamepad1.left_stick_y;
        leftStickX = gamepad1.left_stick_x;
        rightStickY = gamepad1.right_stick_y;
        rightStickX = gamepad1.right_stick_x;        

        // Apply deadzone to all joystick inputs
        leftStickY = (Math.abs(leftStickY) < DEADZONE) ? 0.0 : leftStickY;
        leftStickX = (Math.abs(leftStickX) < DEADZONE) ? 0.0 : leftStickX;
        rightStickY = (Math.abs(rightStickY) < DEADZONE) ? 0.0 : rightStickY;
        rightStickX = (Math.abs(rightStickX) < DEADZONE) ? 0.0 : rightStickX;

        // Field-oriented fielddrive using imu heading
        tangent = Math.sin(heading) * rightStickX + Math.cos(heading) * rightStickY;
        normal = -Math.cos(heading) * rightStickX + Math.sin(heading) * rightStickY;

        // Boost strafing power to compensate for strafing being slow
        normal *= -STRAFE_SCALAR;

        // Determine if the user is trying to rotate the robot
        isRotating = leftStickX != 0;

        // Heading hold correction during pure strafing
        if (isRotating) {
            targetHeading = heading;
            rotate = leftStickX * ROTATE_SCALAR;
        } else {
            headingError = targetHeading - heading;
            rotate = headingError * HEADING_CORRECTION / 1000;
        }
        rotate = leftStickX * ROTATE_SCALAR;



        // Calculate motor powers
        rfPow = tangent - normal + rotate;
        rbPow = tangent + normal + rotate;
        lbPow = tangent - normal - rotate;
        lfPow = tangent + normal - rotate;

        // Clamp motor powers
        rfPow = ope.clamp(rfPow, -1, 1);
        rbPow = ope.clamp(rbPow, -1, 1);
        lbPow = ope.clamp(lbPow, -1, 1);
        lfPow = ope.clamp(lfPow, -1, 1);

        // Apply motor speed scaling
        rob.rightFront.setPower(rfPow * motorSpeed);
        rob.rightBack.setPower(rbPow * motorSpeed);
        rob.leftBack.setPower(lbPow * motorSpeed);
        rob.leftFront.setPower(lfPow * motorSpeed);
        tel.log("leftStickX", leftStickX);
        tel.log("isRotating", isRotating);
        tel.log("Rotate", rotate);
        tel.log("heading", heading);
        tel.log("targetHeading", targetHeading);
        tel.log("HEADING_CORRECTION", HEADING_CORRECTION);

        tel.update();
    }
}