package org.firstinspires.ftc.teamcode.Software.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Software.Sorter;
import org.firstinspires.ftc.teamcode.Software.Sorter_Positions;

@TeleOp(name="Sorter Automation", group="TeleOp")
public class Sorter_Automation extends OpMode {

    private Sorter sorter;

    @Override
    public void init() {
        sorter = new Sorter();
        telemetry.addLine("Sorter Automation Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Check buttons to set different orders
        if (gamepad1.a) {
            // Example: all GREEN
            sorter.setSlot(0, Sorter_Positions.GREEN);
            sorter.setSlot(1, Sorter_Positions.GREEN);
            sorter.setSlot(2, Sorter_Positions.GREEN);
        }

        if (gamepad1.b) {
            // Example: 2 GREEN, 1 PURPLE
            sorter.setSlot(0, Sorter_Positions.GREEN);
            sorter.setSlot(1, Sorter_Positions.GREEN);
            sorter.setSlot(2, Sorter_Positions.PURPLE);
        }

        if (gamepad1.x) {
            // Example: 1 GREEN, 2 PURPLE
            sorter.setSlot(0, Sorter_Positions.GREEN);
            sorter.setSlot(1, Sorter_Positions.PURPLE);
            sorter.setSlot(2, Sorter_Positions.PURPLE);
        }

        if (gamepad1.y) {
            // Example: all PURPLE
            sorter.setSlot(0, Sorter_Positions.PURPLE);
            sorter.setSlot(1, Sorter_Positions.PURPLE);
            sorter.setSlot(2, Sorter_Positions.PURPLE);
        }

        // Display the current slots on the Driver Hub
        telemetry.addLine("Current Sorter Slots:");
        for (int i = 0; i < sorter.getAllSlots().length; i++) {
            telemetry.addData("Slot " + i, sorter.getSlot(i));
        }

        telemetry.update();
    }
}
