package frc.robot.commands;

import com.ctre.phoenix.ILoopable;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LED;


public class LEDCommand extends Command{
    
    LED _ledSub;
    // PS4Controller _controller;

    // DriveTele _driveTele;
    // IndexTele _index;

    public LEDCommand(LED _ledSub){
        this._ledSub = _ledSub;

        addRequirements(_ledSub);
        // _controller = RobotMap.controller;
        // _driveTele = new DriveTele();
        // _index = new IndexTele();

    }

    public void onStart(){
        _ledSub.RainbowParty();
    }

    public void onLoop(){
        // _ledLoops.Idle();
        // _ledLoops.fillIndex();
        // if(_controller.getR1Button()){
        //     _ledLoops.RainbowParty();
        // }
        // else if(!_index.sensor.isPressed() && !_index.sensor2.isPressed()){
        //     _ledLoops.fullIndex();
        // }
        // else if(_controller.getL1Button()){
        //     _ledLoops.flames();
        // }
    }

    public boolean isDone(){
        return false;
    }

    public void onStop(){
        _ledSub.RainbowParty();
    }

}
