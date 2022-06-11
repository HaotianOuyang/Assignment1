import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This method is used to control all menus transition and created base of panels
 */
public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;

    ScreenMode screenMode = ScreenMode.GENERATE;
    //width of frame
    final int WIDTH = 800;
    //height of frame
    final int HEIGHT = 600;

    CardLayout layout = new CardLayout();

    //Components
    SearchMazeMenu searchMazeManuPanel;
    GenerateMazeMenu generateMazeMenuPanel;

    MenuBarToGenearateListener menuBarToGenearateListener;
    MenuBarToSearchListener menuBarToSearchListener;

    SearchMazeData data;

    /**
     * This contractor is created base of frame
     */
    Menu(SearchMazeData data) {
        //title
        this.setTitle("");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.green);
        //this.getContentPane().setBackground(new Color(0xF6F6F6));
        //layout
        this.setLayout(layout);
        //size
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        //to be auto size
        this.pack();
        this.setLocationRelativeTo(null);
        this.data = data;
        //this.setLocation(450, 50);
    }

    /**
     * this method is used for creating menubar for window
     */
    public void prepareMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");


        JMenuItem menuitem1 = new JMenuItem("Generate Maze");
        JMenuItem menuitem2 = new JMenuItem("Search Maze");
        JMenuItem menuitem3 = new JMenuItem("Exit");

        menuBarToGenearateListener = new MenuBarToGenearateListener();
        menuitem1.addActionListener(menuBarToGenearateListener);

        menuBarToSearchListener = new MenuBarToSearchListener();
        menuitem2.addActionListener(menuBarToSearchListener);

        menubar.add(menu);
        menu.add(menuitem1);
        menu.add(menuitem2);
        menu.add(menuitem3);

        setJMenuBar(menubar);
    }

    /**
     * This method is used for transition to Generate Maze menu by clicking button
     */
    private class MenuBarToGenearateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.mainWindow.setFrontScreenAndFocus(ScreenMode.GENERATE);
        }
    }

    /**
     * This method is used for transition to Search Maze menu by clicking button
     */
    private class MenuBarToSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { Main.mainWindow.setFrontScreenAndFocus(ScreenMode.SEARCH);}
    }

    /**
     * This method is used for call two menu elements to display
     */
    public void preparePanels() {
        generateMazeMenuPanel = new GenerateMazeMenu(new SearchMazeData());
        this.add(generateMazeMenuPanel, "Generate Mane Menu");
        searchMazeManuPanel = new SearchMazeMenu( new SearchMazeData());
        this.add(searchMazeManuPanel, "Search Maze Menu");
        this.pack();
    };

    /**
     * This method is used for call two menus (panels) information to designing them
     */
    public void prepareComponents() {
        searchMazeManuPanel.prepareComponents();
        generateMazeMenuPanel.prepareComponents();
    }

    /**
     * This method is used for transition system between two menus(panels)
     * @param s
     */
    public void setFrontScreenAndFocus(ScreenMode s) {
        screenMode = s;

        switch(screenMode) {
            case SEARCH:
                layout.show(this.getContentPane(), "Search Maze Menu");
                searchMazeManuPanel.requestFocus();
                break;
            case GENERATE:
                layout.show(this.getContentPane(),"Generate Mane Menu");
                generateMazeMenuPanel.requestFocus();
                break;
        }
    }
}
