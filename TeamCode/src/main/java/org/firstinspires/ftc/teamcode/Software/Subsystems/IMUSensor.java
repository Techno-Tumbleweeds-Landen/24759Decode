package org.firstinspires.ftc.teamcode.Software.Subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class IMUSensor {
    private RobotHardware rob;

    public IMUSensor(RobotHardware passedRob) {
        this.rob = passedRob;

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
        );
        rob.imu.initialize(new IMU.Parameters(RevOrientation));
    }

    public double getHeading() {
        return rob.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public void resetIMU() {
        rob.imu.resetYaw();
    }

}
