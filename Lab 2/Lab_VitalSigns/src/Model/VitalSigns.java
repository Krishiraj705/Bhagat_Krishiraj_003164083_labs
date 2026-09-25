/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kfalcon
 */
public class VitalSigns {private String date;
private float temperature;
private double bloodPressure;
private int pulse;
private boolean isConscious;

public VitalSigns(String date, float temperature, double bloodPressure, int pulse, boolean isConscious) {
    this.date = date;
    this.temperature = temperature;
    this.bloodPressure = bloodPressure;
    this.pulse = pulse;
    this.isConscious = isConscious;
}

public String getDate() { return date; }
public float getTemperature() { return temperature; }
public double getBloodPressure() { return bloodPressure; }
public int getPulse() { return pulse; }
public boolean isConscious() { return isConscious; }
    
}
