package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

import java.lang.Math;

@TeleOp
public class Lights extends OpMode {

    RobotHardware robot = new RobotHardware();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        /*
        // A = Red
        if (gamepad1.a) {
            robot.rgbLight.setPosition(0.6);//Blue //White = (1)
        }
        // B = Green
        else if (gamepad1.b) {
            robot.rgbLight.setPosition(0.68);//Purple
        }
        // X = Blue
        else if (gamepad1.x) {
            robot.rgbLight.setPosition(0.279);//Red  //Green = 0.5
        }
        // Y = White
        else if (gamepad1.y) {
            robot.rgbLight.setPosition(0.5 + (abs(gamepad1.right_stick_y) / 2));
        }
         */

        robot.rgbLight.setPosition(0.5 + (gamepad1.right_stick_y) / 2);

        telemetry.addData("Light Pos", robot.rgbLight.getPosition());
        telemetry.update();
    }
}
