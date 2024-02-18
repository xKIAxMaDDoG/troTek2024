// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class navX {
    AHRS ahrs;


public navX() {
//Creates NavX MXP   
try {
    AHRS m_ahrs = new AHRS(SPI.Port.kMXP);
} catch (RuntimeException ex ) {
    DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
}
//Reset YAW
public void resetYaw(){
    ahrs.zeroYaw();
    }

public void CreateYAW(){
    SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
}
}


