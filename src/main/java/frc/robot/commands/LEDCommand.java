package frc.robot.commands;

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

    public void intialize(){
        _ledSub.flames();
    }

    public void execute(){
        _ledSub.flames();
    }

    public void end(){
        _ledSub.flames();
    }

    
}
