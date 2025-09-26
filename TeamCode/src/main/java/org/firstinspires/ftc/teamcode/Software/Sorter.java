package org.firstinspires.ftc.teamcode.Software;

public class Sorter {
    // Private array of slots
    private Sorter_Positions[] slots = {
            Sorter_Positions.PURPLE,
            Sorter_Positions.PURPLE,
            Sorter_Positions.GREEN
    };

    // Getter for a single slot
    public Sorter_Positions getSlot(int index) {
        if (index < 0 || index >= slots.length) {
            throw new IndexOutOfBoundsException("Slot index out of range");
        }
        return slots[index];
    }

    // Setter for a single slot
    public void setSlot(int index, Sorter_Positions color) {
        if (index < 0 || index >= slots.length) {
            throw new IndexOutOfBoundsException("Slot index out of range");
        }
        slots[index] = color;
    }

    // Get a copy of all slots
    public Sorter_Positions[] getAllSlots() {
        return slots.clone(); // safe copy
    }

    // Flip a slot between GREEN and PURPLE
    public void toggleSlot(int index) {
        if (index < 0 || index >= slots.length) {
            throw new IndexOutOfBoundsException("Slot index out of range");
        }
        slots[index] = (slots[index] == Sorter_Positions.GREEN) ? Sorter_Positions.PURPLE : Sorter_Positions.GREEN;
    }
}
