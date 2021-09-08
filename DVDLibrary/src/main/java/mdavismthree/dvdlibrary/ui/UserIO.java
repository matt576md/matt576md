/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdavismthree.dvdlibrary.ui;

/**
 *
 * @author Mathieu Davis
 */
//This interface defines the methods that must be implemented 
//by any class that wants to directly interact with the user interface technology

public interface UserIO {
    
    void print(String msg);
    
    void printWithBanner(String msg);
    
    String readString(String prompt);
    
    String readFilledString(String prompt);
    
    int readInt(String prompt);
    
    int readInt(String prompt, int min, int max);
    
}
