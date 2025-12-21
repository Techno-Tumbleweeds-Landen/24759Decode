package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Software.Subsystems.ColorSensing;

@TeleOp
public class ColorSensor extends OpMode {
    ColorSensing color = new ColorSensing();
    ColorSensing.DetectedColor detectedColor;


    @Override
    public void init(){
        color.init(hardwareMap);
    }

    public void loop(){
        detectedColor = color.getColor(telemetry);
        telemetry.addData("Detected Color", detectedColor);
    }

}
