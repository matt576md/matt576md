/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdavismthree.dvdlibrary.controller;

import java.util.List;
import mdavismthree.dvdlibrary.dao.DVDLibraryDao;
import mdavismthree.dvdlibrary.dao.DVDLibraryDaoException;
import mdavismthree.dvdlibrary.dto.DVD;
import mdavismthree.dvdlibrary.ui.DVDLibraryView;

/**
 *
 * @author Mathieu Davis
 */
//This is the orchestrator of the application. 
//It knows what needs to be done, when it needs to be done, and what component can do the job.

public class DVDLibraryController{
    
    private DVDLibraryView view;
    private DVDLibraryDao dao;
    
    /*dependency injection constructor in the controller that has a DVDLibrary Dao 
    *parameter and a DVDLibraryView parameter and uses the incoming values to 
    *initialize the ClassRosterDao and ClassRosterView member fields
    */
    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao){
        this.view = view;
        this.dao = dao;
    }
    /**
     * Loads the DVD library, displays the main menu for the DVD library,
     * and saves the DVD library.
     */
    public void run(){
        
        try{
            dao.loadLibrary();
        }
        catch (DVDLibraryDaoException ex){
            displayErrorMessage(ex.getMessage());
        }
        
        displayLibraryMenu();
        
        try{
            dao.writeLibrary();
            view.displaySaveSuccess();
        }
        catch (DVDLibraryDaoException ex){
            displayErrorMessage(ex.getMessage());
        }
        
        displayExitMessage();
    }
    
    private void displayLibraryMenu(){
        boolean keepGoing = true;
        do{
            int libraryMenuSelection = getLibraryMenuSelection();

            switch (libraryMenuSelection){
                case 1:
                    add();
                    break;
                case 2:
                    searchForDVD();
                    break;
                case 3:
                    listAllDVDs();
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    displayUnknownCommand();
            }            
        } while (keepGoing);
    }
    
    private int getLibraryMenuSelection(){
        return view.printLibraryMenuAndGetSelection();
    }
    
    private void add(){
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.add(newDVD);
        view.displayAddDVDSuccessMessage(newDVD);
        view.enterToContinue();
    }
    
    private void searchForDVD(){
        view.displaySearchForDVD();
        String dvdTitle = view.promptDVDTitle();
        DVD dvd = dao.getDVD(dvdTitle);
        if (dvd == null){
            view.displayDVDNotFound();
            view.enterToContinue();
        }
        else{
            displayDVDMenu(dvd);
        }
    }
    
     private void listAllDVDs(){
        view.displayAllDVDsBanner();
        view.displayAllDVDs(dao.getAllDVDs());
        view.enterToContinue();
    }
    /**
     * Displays user options for a single DVD.
     * @param dvd - Currently selected DVD object
     */
    private void displayDVDMenu(DVD dvd){
        view.displayDVDInfo(dvd);
        view.enterToContinue();
        boolean keepGoing = true;
        do{
            int dvdMenuSelection = view.printDVDMenuAndGetSelection();

            switch (dvdMenuSelection){
                case 1:
                    displayEditDVDMenu(dvd);
                    break;

                case 2:
                    if (delete(dvd)){
                        keepGoing = false;
                    }
                    break;

                case 3:
                    keepGoing = false;
                    break;

                default:
                    displayUnknownCommand();
            }
        } while (keepGoing);
    }
    
    /**
     * Displays user options for editing a single DVD.
     * @param dvd - DVD object to be edited.
     */
    private void displayEditDVDMenu(DVD dvd){
        boolean keepGoing = true;
        do{
            view.displayDVDInfo(dvd);
            int editDVDMenuSelection
                    = view.printEditDVDMenuAndGetSelection();
            switch (editDVDMenuSelection){
                case 1:
                    editTitle(dvd);
                    break;
                case 2:
                    editReleaseDate(dvd);
                    break;
                case 3:
                    editMPAARating(dvd);
                    break;
                case 4:
                    editDirectorName(dvd);
                    break;
                case 5:
                    editStudio(dvd);
                    break;
                case 6:
                    editNote(dvd);
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    displayUnknownCommand();
            }
        } while (keepGoing);
    }
    
    private void editTitle(DVD dvd) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(view.promptDVDTitle());
        dao.updateDVDTitleLibrary(oldTitle);
        view.displayEditDVDSuccess();
    }
    
    private void editReleaseDate(DVD dvd){
        dvd.setReleaseDate(view.promptReleaseDate());
        view.displayEditDVDSuccess();
    }
    
    private void editMPAARating(DVD dvd){
        dvd.setMPAARating(view.promptMPAARating());
        view.displayEditDVDSuccess();
    }
    
    private void editDirectorName(DVD dvd){
        dvd.setDirectorName(view.promptDirectorName());
        view.displayEditDVDSuccess();
    }
    
    private void editStudio(DVD dvd){
        dvd.setStudio(view.promptStudio());
        view.displayEditDVDSuccess();
    }
    
    private void editNote(DVD dvd){
        dvd.setNote(view.promptNote());
        view.displayEditDVDSuccess();
    }
    
    private boolean delete(DVD dvd){
        int deleteDVDMenuSelection
                = view.displayDeleteDVDMenuAndGetSelection(dvd);
        switch (deleteDVDMenuSelection){
            case 1:
                dao.delete(dvd);
                view.displayDeleteDVDSuccess(dvd);
                return true;
            case 2:
                return false;
            default:
                displayUnknownCommand();
        }
        return false;
    }
    
    private void displayExitMessage(){
        view.printExitMessage();
    }
    
    private void displayUnknownCommand(){
        view.displayUnknownCommand();
    }
    
    private void displayErrorMessage(String errorMsg){
        view.displayErrorMessage(errorMsg);
    }
}
