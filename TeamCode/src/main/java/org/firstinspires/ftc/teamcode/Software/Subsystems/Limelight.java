package org.firstinspires.ftc.teamcode.Software.Subsystems;


import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class Limelight{
    RobotHardware rob;
    TelemetryManager tel;
    Variables var;

    public Limelight(RobotHardware passedRob, TelemetryManager passedTel, Variables passedVar){
        this.rob = passedRob;
        this.tel = passedTel;
        this.var = passedVar;
    }

    public void changePipeline(int pipelineNum) {
        rob.limelight.pipelineSwitch(pipelineNum);
    }

    public LLResult  getInfo() {
        tel.log("info", rob.limelight.getLatestResult());
        return rob.limelight.getLatestResult();
    }

}
