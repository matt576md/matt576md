/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdavismthree.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author Mathieu Davis
 */
//This is the console-specific implementation of the UserIO interface

public class UserIOConsoleImpl implements UserIO {
    
    private final Scanner sc = new Scanner(System.in);
    
   
    @Override
    public void print(String msg){
        System.out.println(msg);
    }
    
    /**
     * Takes in a message to display on the console with a small banner on each
     * side of the message.
 
     */
    @Override
    public void printWithBanner(String msg){
        System.out.println(" "  + msg + " ");
    }
    
    /**
     * Takes in a message to display on the console and then waits for an
     * answer from the user to return.
  
     */
    @Override
    public String readString(String prompt){
        this.print(prompt);
        String answer = sc.nextLine();
        this.print("");
        return answer;
    }
    
    
    // Takes message if it's length is greater than 0 returns
     
    @Override
    public String readFilledString(String prompt){
        String answer;
        do{
            answer = this.readString(prompt);
        } while (answer.isEmpty() || answer.length() == 0);
        return answer;
    }
    
    /**
     *
     * A simple method that takes in a message to display on the console, 
     * and continually reprompts the user with that message until they enter an integer
     * to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as integer
     */
    @Override
    public int readInt(String prompt){
        int num = 0;
        while (true){
            try{
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue);
                break;
            }
            catch (NumberFormatException ex){
                this.print("wrong input try again.\n");
            }
        }
        return num;
    }
    
    /**
     *
     * A slightly more complex method that takes in a message to display on the console, 
     * and continually reprompts the user with that message until they enter an integer
     * within the specified min/max range to be returned as the answer to that message.
     * @return an integer value as an answer to the message prompt within the min/max range
     */
    @Override
    public int readInt(String prompt, int min, int max){
        int num = 0;
        do{
            num = readInt(prompt);
        } while (num < min || num > max);
        return num;
    }
    
    
}
