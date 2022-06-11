import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.BorderLayout;

/**
 * This class is made up of Generate Maze Menu
 * This Generate maze menu is created by two panels button panel and maze panel
 */
public class GenerateMazeMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    JLabel generateMazeLabel;
    ButtonPanel buttonPanel;
    MazePanel mazePanel;

    SearchMazeData data;

    /**
     * This constructor is used to made up of base of generate maze menu
     */
    GenerateMazeMenu(SearchMazeData data) {
        this.setLayout(new BorderLayout());

        mazePanel = new MazePanel();
        buttonPanel = new ButtonPanel(new SearchMazeData());
        this.add(mazePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.WEST);
        this.data  = data;
    }

    /**
     * This method is used to create detail design of generate maze page
     */
    public void prepareComponents() {
        generateMazeLabel = new JLabel();
        generateMazeLabel.setText("Generate Maze menu");
        generateMazeLabel.setBounds(100, 200, 100,30);
        this.add(generateMazeLabel);

        mazePanel.prepareComponents();
        buttonPanel.prepareComponents();

    }
}