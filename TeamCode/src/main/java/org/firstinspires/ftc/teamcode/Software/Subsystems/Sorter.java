package org.firstinspires.ftc.teamcode.Software.Subsystems;

import org.firstinspires.ftc.teamcode.Software.Sorter_Positions;

public class Sorter {
    // Private array of slots
    private Sorter_Positions[] slots = {
            Sorter_Positions.PURPLE,
            Sorter_Positions.PURPLE,
            Sorter_Positions.GREEN
    };

    // Getter for a single slot
    public Sorter_Positions getSlot(int index) {
        return slots[index];
    }

    // Setter for a single slot
    public void setSlot(int index, Sorter_Positions color) {
        slots[index] = color;
    }

    // Get a copy of all slots
    public Sorter_Positions[] getAllSlots() {
        return slots.clone(); // safe copy
    }

    // Flip a slot between GREEN and PURPLE
    public void toggleSlot(int index) {
        if (slots[index] == Sorter_Positions.GREEN) {
            slots[index] = Sorter_Positions.PURPLE;
        } else {
            slots[index] = Sorter_Positions.GREEN;
        }
    }
}
