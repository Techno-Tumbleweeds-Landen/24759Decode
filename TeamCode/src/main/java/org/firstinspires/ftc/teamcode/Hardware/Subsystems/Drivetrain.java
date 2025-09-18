package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class Drivetrain {
    RobotHardware robot  = new RobotHardware();

    public void drive(double LeftStickY, double LeftStickX, double RightStickY, double RightStickX, double heading, double motorSpeed){
        
        robot.rightFront.setPower((( - RightStickY - LeftStickX) - (RightStickX)) * motorSpeed);
        robot.rightBack.setPower((( - RightStickY - LeftStickX) + (RightStickX)) * motorSpeed);
        robot.leftBack.setPower((( - RightStickY + LeftStickX) - (RightStickX)) * motorSpeed);
        robot.leftFront.setPower((( - RightStickY + LeftStickX) + (RightStickX)) * motorSpeed);

    }
}
