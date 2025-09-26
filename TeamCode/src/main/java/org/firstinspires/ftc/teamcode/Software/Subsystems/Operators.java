package org.firstinspires.ftc.teamcode.Software.Subsystems;

public class Operators {

    public double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }
}
