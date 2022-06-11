/**
 * This method is used for creating main window
 */
public class Main {
    static Menu mainWindow;
    public static void main(String[] args) {
        //prepare menu
        mainWindow = new Menu(new SearchMazeData());
        mainWindow.prepareMenu();
        //create panels
        mainWindow.preparePanels();
        //create the panels fetures
        mainWindow.prepareComponents();
        //make to display the generate maze page after open up this product
        mainWindow.setFrontScreenAndFocus(ScreenMode.GENERATE);
        mainWindow.setVisible(true);
    }
}
