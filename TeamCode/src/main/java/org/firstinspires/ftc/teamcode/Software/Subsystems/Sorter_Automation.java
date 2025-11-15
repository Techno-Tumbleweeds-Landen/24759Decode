package org.firstinspires.ftc.teamcode.Software.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class Sorter_Automation {

    private RobotHardware rob;
    TelemetryManager tel;
    private final double MOVE_POWER = 0.25;
    private final int INCREMENT = 470;        // ticks per press
    private final int TOLERANCE = 20;        // motor stops within this range

    private int pos = 0;

    // Initialize with hardware reference
    public void init(RobotHardware passedRob, TelemetryManager passedTel) {
        this.rob = passedRob;
        this.tel = passedTel;
        rob.sorterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rob.sorterMotor.setPower(0.25);
        //pos = rob.sorterMotor.getCurrentPosition();
    }

    // Call every loop
    public void update(Gamepad gamepad) {

        if (gamepad.xWasPressed()) {
            pos += INCREMENT;
            moveToPosition(pos);
            tel.log("X Was Pressed", "yes");
        }

        if (gamepad.bWasPressed()) {
            pos -= INCREMENT;
            moveToPosition(pos);
            tel.log("X Was Pressed", "yes");
        }
        tel.log("pos", pos);
        tel.log("motorPower", rob.sorterMotor.getPower());
        tel.log("targetPosition", rob.sorterMotor.getTargetPosition());

        // Stop motor when target reached

        if (rob.sorterMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION && !rob.sorterMotor.isBusy()) {
           rob.sorterMotor.setPower(0);
    rob.sorterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    //pos = rob.sorterMotor.getCurrentPosition();
        }



    }

    private void moveToPosition(int target) {
        rob.sorterMotor.setPower(0.1); // always positive
        tel.log("RUNNING TO POSITION", target * -1);
        rob.sorterMotor.setTargetPosition(target * -1);
        rob.sorterMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
