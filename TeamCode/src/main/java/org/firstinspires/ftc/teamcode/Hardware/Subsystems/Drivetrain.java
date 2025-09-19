package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import java.lang.Math;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Subsystems.IMUSensor;

import java.util.Vector;

public class Drivetrain {
    RobotHardware rob = new RobotHardware();
    double x;
    double y;
    double tangent;
    double normal;

    public void drive(double LeftStickY, double LeftStickX, double RightStickY, double RightStickX, double heading, double motorSpeed){
        x = Math.cos(heading);
        y = Math.sin(heading);

        tangent = Math.sin(heading) * RightStickY - Math.cos(heading) * RightStickX;
        normal = -Math.cos(heading) * RightStickY - Math.sin(heading) * RightStickX;

        rob.rightFront.setPower((( - RightStickY - LeftStickX) - (normal)) * motorSpeed);
        rob.rightBack.setPower((( - RightStickY - LeftStickX) + (normal)) * motorSpeed);
        rob.leftBack.setPower((( - RightStickY + LeftStickX) - (normal)) * motorSpeed);
        rob.leftFront.setPower((( - RightStickY + LeftStickX) + (normal)) * motorSpeed);



    }
}
