/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdavismthree.dvdlibrary.dao;

import java.util.List;
import mdavismthree.dvdlibrary.dto.DVD;

/**
 *
 * @author Mathieu Davis
 */
//This interface defines the methods
//that must be implemented by any class that wants to play the role of DAO in the application

public interface DVDLibraryDao {
   
    void add(DVD newDVD);

    DVD getDVD(String dvdTitle);
    
    void updateDVDTitleLibrary(String ogTitle);
    
    void delete(DVD dvd);
    
    List<DVD> getAllDVDs();

    void loadLibrary() throws DVDLibraryDaoException;
    
    void writeLibrary() throws DVDLibraryDaoException;
}