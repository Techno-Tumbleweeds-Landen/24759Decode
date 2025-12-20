package org.firstinspires.ftc.teamcode.Software.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class ColorSensing {
    public enum DetectedColor {
        PURPLE,
        GREEN,
        NONE
    }
    RobotHardware robot;

    public void init(HardwareMap hardwareMap) {
        robot = new RobotHardware(hardwareMap);
        robot.colorSensor.setGain(10);
    }


    public DetectedColor getColor(Telemetry telemetry) {
        NormalizedRGBA colors = robot.colorSensor.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed= colors.red/colors.alpha;
        normGreen= colors.green/colors.alpha;
        normBlue=colors.blue/colors.alpha;

        telemetry.addData("Red", normRed);
        telemetry.addData("Green", normGreen);
        telemetry.addData("Blue", normBlue);

        //return DetectedColor.NONE;

        /*
            PURPLE, GREEN

            PURPLE = < 0.2, < 0.2, > 0.4
            GREEN = < 0.17, > 0.76, < 0.5

         */

        if (normRed > 0.15 && normGreen > 0.3 && normBlue > 0.45) {
            return DetectedColor.PURPLE;
        } else if (normRed < 0.2 && normGreen > 0.5 && normBlue < 0.6) {
            return DetectedColor.GREEN;
        }else{
            return DetectedColor.NONE;
        }

    }
}
