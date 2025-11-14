package org.firstinspires.ftc.teamcode.Software.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class Sorter_Automation {

    private RobotHardware rob;
    private final double MOVE_POWER = 0.25;
    private final int INCREMENT = 470;        // ticks per press
    private final int TOLERANCE = 20;        // motor stops within this range

    private boolean xWasPressed = false;
    private boolean bWasPressed = false;

    private int pos = 0;

    // Initialize with hardware reference
    public void init(RobotHardware passedRob) {
        this.rob = passedRob;
        rob.sorterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rob.sorterMotor.setPower(0);
        pos = rob.sorterMotor.getCurrentPosition();
    }

    // Call every loop
    public void update(Gamepad gamepad) {

        if (gamepad.x && !xWasPressed) {
            pos -= INCREMENT;
            moveToPosition(pos);
        }

        if (gamepad.b && !bWasPressed) {
            pos += INCREMENT;
            moveToPosition(pos);
        }

        // Stop motor when target reached
        if (rob.sorterMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION && !rob.sorterMotor.isBusy()) {
            rob.sorterMotor.setPower(0);
            rob.sorterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            pos = rob.sorterMotor.getCurrentPosition();
        }

        xWasPressed = gamepad.x;
        bWasPressed = gamepad.b;
    }

    private void moveToPosition(int target) {
        rob.sorterMotor.setTargetPosition(target);
        rob.sorterMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rob.sorterMotor.setPower(MOVE_POWER); // always positive
    }

}
