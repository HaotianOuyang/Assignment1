import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for displaying generated maze puzzle
 */
public class MazePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel titleLabel;
    JPanel displayMaze;
    public static final boolean  isMazeDisplayed = false;

    /**
     * This constructor is used to made up base of maze panel
     */
    MazePanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
//        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.blue);
//        this.setLayout(null);
    }

    /**
     * This method is used to create detail design of generate maze page
     */
    public void prepareComponents() {
        titleLabel = new JLabel();
        titleLabel.setText("Maze will display here");
        titleLabel.setBounds(100,40,100,30);

        displayMaze = new JPanel();
        displayMaze.setPreferredSize(new Dimension(500, 500));
        displayMaze.setBackground(Color.white);

        if(isMazeDisplayed){
            mazeGenerator mazeGen = new mazeGenerator(50,50,25,25);
            mazeGen.generate();
            mazeGen.setSize(500,500);

            displayMaze.add(mazeGen);
            displayMaze.setSize(830,650);
            displayMaze.setVisible(true);
        }



        this.add(titleLabel);
        this.add(displayMaze);
    };

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
