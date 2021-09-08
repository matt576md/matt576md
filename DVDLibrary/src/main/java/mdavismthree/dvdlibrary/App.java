/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdavismthree.dvdlibrary;
import mdavismthree.dvdlibrary.controller.DVDLibraryController;
import mdavismthree.dvdlibrary.dao.DVDLibraryDao;
import mdavismthree.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import mdavismthree.dvdlibrary.ui.UserIO;
import mdavismthree.dvdlibrary.ui.UserIOConsoleImpl;
import mdavismthree.dvdlibrary.ui.DVDLibraryView;

/**
 *
 * @author Mathieu Davis
 */
public class App {
    public static void main(String[] args) {
	UserIO io = new UserIOConsoleImpl();
	DVDLibraryView view = new DVDLibraryView(io);
	DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
	DVDLibraryController controller = new DVDLibraryController(view, dao);
	
	controller.run();
}
}
