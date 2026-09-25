/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;

/**
 *
 * @author kfalcon
 */
public class VitalSignsHistory {

private ArrayList<VitalSigns> historyList = new ArrayList<>();

public void addVitalSigns(VitalSigns v) {
    historyList.add(v);
}

public void deleteVitalSigns(int index) {
    historyList.remove(index);
}

public VitalSigns getVitalSigns(int index) {
    return historyList.get(index);
}

public ArrayList<VitalSigns> getAllVitalSigns() {
    return historyList;
}
    
}
