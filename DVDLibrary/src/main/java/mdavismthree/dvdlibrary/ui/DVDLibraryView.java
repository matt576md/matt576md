/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdavismthree.dvdlibrary.ui;

import java.util.List;
import mdavismthree.dvdlibrary.dto.DVD;

/**
 *
 * @author Mathieu Davis
 */
//This class handles all the UI logic

public class DVDLibraryView {
   private final UserIO io;
   private final String prompt = "Choose an option.";
    
    // implement a constructor that initializes the io member field
   public DVDLibraryView(UserIO io){
        this.io = io;
    }
    
    // Menu Methods
    
    public int printLibraryMenuAndGetSelection(){
        io.printWithBanner("Library Menu");
        io.print("1 - Add");
        io.print("2 - Search for DVD");
        io.print("3 - List Dvds");
        io.print("4 - Exit");
        io.print("to delete first search for DVD");
        
        return io.readInt(prompt, 1, 4);
    }
    
    public int printDVDMenuAndGetSelection(){
        io.printWithBanner("DVD Menu");
        io.print("1 - Edit details");
        io.print("2 - Delete");
        io.print("3 - Return");
        io.print("");
        
        return io.readInt(prompt, 1, 3);
    }
    
    public int printEditDVDMenuAndGetSelection(){
        io.printWithBanner("Edit details");
        io.print("1 - Title");
        io.print("2 - Release Date");
        io.print("3 - MPAA Rating");
        io.print("4 - Director's Name");
        io.print("5 - Studio");
        io.print("6 - Rating/Notes");
        io.print("7 - Return");
        io.print("");
        return io.readInt("Select field to edit.", 1, 7);
    }
    
    public int displayDeleteDVDMenuAndGetSelection(DVD dvdToDelete){
        io.printWithBanner("Delete DVD");
        io.print("Delete " + dvdToDelete.getTitle() + "?");
        io.print("1 - Yes");
        io.print("2 - No");
        io.print("");
        return io.readInt(prompt, 1, 2);
    }
    
    // Edit DVD Methods
    
    public String promptDVDTitle(){
        return io.readFilledString("Enter Title");
    }
    
    public String promptReleaseDate(){
        return io.readFilledString("Enter Release Date");
    }
    
    public String promptMPAARating(){
        return io.readFilledString("Enter MPAA Rating");
    }
    
    public String promptDirectorName(){
        return io.readFilledString("Enter Director's Name");
    }
    
    public String promptStudio(){
        return io.readFilledString("Enter Studio");
    }
    
    public String promptNote(){
        return io.readFilledString("Enter Rating/Notes for DVD");
    }
    
    public void displayEditDVDSuccess(){
        io.print("DVD Saved.\n");
    }
    
    // Add DVD Methods
    
    public void displayAddDVDBanner(){
        io.printWithBanner("Add DVD");
    }
    
    public void displayAddDVDSuccessMessage(DVD newDVD){
        io.print(newDVD.getTitle() + " added.\n");
    }
    
    public DVD getNewDVDInfo(){
        DVD newDVD = new DVD(promptDVDTitle());
        newDVD.setReleaseDate(promptReleaseDate());
        newDVD.setMPAARating(promptMPAARating());
        newDVD.setDirectorName(promptDirectorName());
        newDVD.setStudio(promptStudio());
        newDVD.setNote(promptNote());
        
        return newDVD;
    }
    
    // Delete DVD 
    
    public void displayDeleteDVDSuccess(DVD dvd){
        io.print(dvd.getTitle() + " deleted.");
    }
    
    // List All DVDS
    
    public void displayAllDVDsBanner(){
        io.printWithBanner("All DVDs");
    }
    
    public void displayAllDVDs(List<DVD> allDVDs){
        for (DVD dvd : allDVDs){
            io.print(dvd.getTitle());
        }
        io.print("");
    }
    
    // Search DVD 
    
    public void displaySearchForDVD(){
        io.printWithBanner("DVD Search");
    }
    
    public void displayDVDNotFound(){
        io.print("DVD not found.\n");
    }
    
    // DVD info
    
    public void displayDVDInfo(DVD dvd){
        io.print("Title: " + dvd.getTitle());
        io.print("Release Date: " + dvd.getReleaseDate());
        io.print("MPAA Rating: " + dvd.getMPAARating());
        io.print("Director's Name: " + dvd.getDirectorName());
        io.print("Studio: " + dvd.getStudio());
        io.print("Rating/Notes: " + dvd.getNote());
        io.print(" ");
    }
    
    // messages
    
    public void displayUnknownCommand(){
        io.print("Unknown command.\n");
    }
    
    public void enterToContinue(){
        io.readString("Enter to continue.\n");
    }
    
    public void displayErrorMessage(String errorMsg){
        io.printWithBanner("ERROR");
        io.print(errorMsg);
    }
    
    public void displaySaveSuccess(){
        io.print("DVD Library saved successfully.\n");
    }
    
    public void printExitMessage(){
        io.print("Closing DVD Library.\n");
    }
    
}
