/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdavismthree.dvdlibrary.dto;

/**
 *
 * @author Mathieu Davis
 */
 //This is the DTO that holds all the DVD info you can input 

public class DVD {
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String note;
    
    public DVD(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getReleaseDate(){
        return this.releaseDate;
    }
    
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    
    public String getMPAARating(){
        return this.mpaaRating;
    }
    
    public void setMPAARating(String mpaaRating){
        this.mpaaRating = mpaaRating;
    }
    
    public String getDirectorName(){
        return this.directorName;
    }
    
    public void setDirectorName(String directorName){
        this.directorName = directorName;
    }
    
    public String getStudio(){
        return this.studio;
    }
    
    public void setStudio(String studio){
        this.studio = studio;
    }
    
    public String getNote(){
        return this.note;
    }
    
    public void setNote(String note){
        this.note = note;
    }

}
