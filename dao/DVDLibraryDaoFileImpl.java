/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdavismthree.dvdlibrary.dao;

import java.io.BufferedReader;
import mdavismthree.dvdlibrary.dto.DVD;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Mathieu Davis
 */
//This is the text file-specific implementation of the DVDLibraryDao interface
//Dao stores info and sends it to file

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    private static final String FILE_NAME = "DVDLibrary.txt";
    private static final String DELIMITER = "::";
    private final Map<String, DVD> dvdLibrary = new HashMap<>();
    
    @Override
    public DVD getDVD(String dvdTitle) {
        return dvdLibrary.get(dvdTitle);
    }
    
     /**
     * Updates the dvdLibrary HashMap when the DVD title is changed.
     * @param ogTitle - The String value of the previous key to the DVD in 
     * dvdLibrary.
     */
    @Override
    public void updateDVDTitleLibrary(String ogTitle){
        DVD dvd = getDVD(ogTitle);
        String newTitle = dvd.getTitle();
        //throws error if no ! when trying to change title
        if (!newTitle.equals(ogTitle)){
            dvdLibrary.put(newTitle, dvd);
            dvdLibrary.remove(ogTitle);
        }
    }
    
    //put function from hashmap insert mapping key title value of added dvd
    @Override
    public void add(DVD newDVD) {
        dvdLibrary.put(newDVD.getTitle(), newDVD);
    }
    
    // remove from dvd library
    @Override
    public void delete(DVD dvd) {
        dvdLibrary.remove(dvd.getTitle());
    }
    
    //returns list of every dvd in array
    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList<DVD>(dvdLibrary.values());
    }
    
    //no delimiter after last would be pointless since returning after getNote
    //delimiter important for splitting
    private String marshallDVD(DVD dvd){
        String dvdMarshall = dvd.getTitle() + DELIMITER;
        dvdMarshall += dvd.getReleaseDate() + DELIMITER;
        dvdMarshall += dvd.getMPAARating() + DELIMITER;
        dvdMarshall += dvd.getDirectorName() + DELIMITER;
        dvdMarshall += dvd.getStudio() + DELIMITER;
        dvdMarshall += dvd.getNote();
        return dvdMarshall;
    }
    
    //will split on delimiter. assign each index based on how many delimiters are before it
    private DVD unmarshallDVD(String dvdUnmarshall){
        String[] dvdIndex = dvdUnmarshall.split(DELIMITER);
        //index of each property in token
        String dvdTitle = dvdIndex[0];
        DVD dvd = new DVD(dvdTitle);
        dvd.setReleaseDate(dvdIndex[1]);
        dvd.setMPAARating(dvdIndex[2]);
        dvd.setDirectorName(dvdIndex[3]);
        dvd.setStudio(dvdIndex[4]);
        dvd.setNote(dvdIndex[5]);
        
        return dvd;
    }
    
    //writes to file that's set to FILE_NAME
    @Override
    public void writeLibrary() throws DVDLibraryDaoException {
        
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(FILE_NAME));
        }
        catch (IOException ex){
            throw new DVDLibraryDaoException("Couldn't save DVD info.", ex);
        }
        
        //for loop to go through info in getAllDVDS and write to file
        for (DVD dvd: getAllDVDs()){
            String dvdMarshall = marshallDVD(dvd);
            out.println(dvdMarshall);
            out.flush();
        }
        out.close();
    }
    
    @Override
    public void loadLibrary() throws DVDLibraryDaoException {
        
        Scanner scanner;
        
        try{
            scanner = new Scanner(
                    new BufferedReader(new FileReader(FILE_NAME)));
        }
        catch (FileNotFoundException ex){
            throw new DVDLibraryDaoException(
                    "Couldn't load DVD Library in.", ex);
        }
        
        String currentLine;
        DVD currentDVD;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvdLibrary.put(currentDVD.getTitle(), currentDVD);
        }
        
        scanner.close();
        
    }

    
    }

    