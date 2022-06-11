import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/**
 * This class is made up of database menu panel
 * */
public class SearchMazeMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    JButton buttonAdd;
    JButton buttonSearch;
    JButton buttonSort;

    JButton buttonDelete;
    JList<Title> listMazeTitle;

    JList mazeList;
    java.util.List<Title> mazeTitles  = new ArrayList<>();

    JButton buttonExport;
    JButton buttonModify;

    ModifyMazewithData modifyMazewithData;

    JTextField mazeIDF;
    JTextField authorF;
    JTextField DateCreatedF;
    JTextField DateEditedF;
    SearchMazeData data;
    /**
     * This constructor is used to made up of base of database menu panel
     *
     * @param data The underlying data/model class the UI needs.
     */
    SearchMazeMenu(SearchMazeData data) {
        this.data = data;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.gray);


    }

    /**
     * This method is used to create detail design of database page
     */
    public void prepareComponents() {
        //Button for open up the pane for adding new item to database
        buttonAdd = new JButton("Add New Maze Title");
        buttonAdd.setBounds(30,50,150,30);
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addMazeTitle();
            }
        });

        //Button for open up the pane for searching item from database
        buttonSearch = new JButton("Search Maze Title");
        buttonSearch.setBounds(350,50,150, 30);
        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchMazeTitle();
            }
        });

        //Button for sort the item in list below of the panel
        buttonSort = new JButton("Sort Titles");
        buttonSort.setBounds(650,50,150, 30);
        buttonSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sortMazeTitle();
            }
        });

        //List for displaying the list in the database
       /* listMazeTitle = new JList<>();
        listMazeTitle.setPreferredSize(new Dimension(400, 400));
        listModel = new CustomListModel<Title>(mazeTitles);
        listMazeTitle.setModel(listModel);*/
        //Add item in the database
      //  listModel.addElement(new Title("Maze Title List"));

        //Button for exporting image as JPEG(there is no features at this stage)
        buttonExport = new JButton("Export as JPEG");
        buttonExport.setBounds(50,600,150, 30);
        buttonExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sortMazeTitle();
            }
        });

        //Button for modify maze with selected data(Just move to GenerateMaze panel at this stage)
        buttonModify = new JButton("Modify Maze");
        buttonModify.setBounds(650,600,150, 30);
        modifyMazewithData = new ModifyMazewithData();
        buttonModify.addActionListener(modifyMazewithData);

        buttonDelete = new JButton("Delete Maze");
        buttonDelete.setBounds(0, 0, 150, 30);
        buttonDelete.setEnabled(false);
        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(mazeList != null) {
                    deletePressed();
                }
            }
        });

        JPanel rightButtons = new JPanel();
        rightButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightButtons.add(buttonModify);
        rightButtons.add(buttonExport);
        rightButtons.add(buttonDelete);

        //Add all features
        JPanel topButtons = new JPanel();
        topButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        topButtons.add(buttonAdd );
        topButtons.add(buttonSearch );
        topButtons.add(buttonSort);

        JPanel rightPart = new JPanel();
        rightPart.setLayout(new BorderLayout());

        JPanel rightTop = new JPanel();
        rightTop.setLayout(new BorderLayout());
        rightTop.add(mazeDetailsPanel());

        rightPart.add(rightButtons, BorderLayout.SOUTH);
        rightButtons.setPreferredSize(new Dimension(200, 400));
        rightPart.add(rightTop, BorderLayout.NORTH);
        rightPart.setPreferredSize(new Dimension(200, 400));

        this.add(topButtons, BorderLayout.NORTH);
        this.add(rightPart, BorderLayout.EAST);
        this.add(makeMazeListPane(), BorderLayout.CENTER);

        addMazeListListener(new mazeListListener());
        addClosingListener(new ClosingListener());
    }

    /**
     * New list for Mazes.
     * @return scroller list
     */
    public JScrollPane makeMazeListPane(){
        mazeList = new JList(data.getModel());

        JScrollPane scroller = new JScrollPane(mazeList);
        scroller.setViewportView(mazeList);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize(new Dimension(200, 400));

        return scroller;
    }



    private void setFieldsEditable(boolean editable){
        mazeIDF.setEditable(editable);
        authorF.setEditable(editable);
        DateEditedF.setEditable(editable);
        DateCreatedF.setEditable(editable);
    }
    private void display(Maze m){
        if(m != null){
            mazeIDF.setText(m.getMazeID());
            authorF.setText(m.getAuthor());
            DateEditedF.setText(m.getDateEdited());
            DateCreatedF.setText(m.getDateCreated());
        }
    }
    private JPanel mazeDetailsPanel(){
        JPanel detailsPanel = new JPanel();

        detailsPanel.setLayout(new GridLayout(4, 2, 0, 5));


        mazeIDF = new JTextField(5);
        authorF= new JTextField(5);
        DateCreatedF= new JTextField(5);
        DateEditedF= new JTextField(5);

        setFieldsEditable(false);

        JLabel authorName = new JLabel("Author");
        detailsPanel.add(authorName);
        detailsPanel.add(authorF);
        JLabel idLabel = new JLabel("MazeID");
        detailsPanel.add(idLabel);
        detailsPanel.add(mazeIDF);
        JLabel DateCreated = new JLabel("Date Created");
        detailsPanel.add(DateCreated);
        detailsPanel.add(DateCreatedF);
        JLabel DateEdited = new JLabel("Date Edited");
        detailsPanel.add(DateEdited);
        detailsPanel.add(DateEditedF);

        return detailsPanel;
    }


    /**
     * This methods is used to open a new pane and add item to database(just maze title list at this stage) by user input
     */
    private void addMazeTitle() {
        String mazeTitle = JOptionPane.showInputDialog(this, "Enter maze Title");
        if(mazeTitle != null && !mazeTitle.equals("")) {
            Maze m = new Maze(mazeTitle, "Unknown", "Unknown", "Unknown","Unknown");
            data.add(m);
        }
        checkListSize();
    }

    /**
     * This methods is used to open a new pane and sort the items displying by user input
     */
    private void sortMazeTitle() {
        //Collections.sort(mazeTitles);
        //listModel.fireDateChanged();


    }

    /**
     * This methods is used to open a new pane and search and dispplay the item by user input
     */
    private void searchMazeTitle() {
        String mazeTitle = JOptionPane.showInputDialog(this, "Enter person name to search for:");

        if(mazeTitle == null) {
            return;
        }
        Collections.sort(mazeTitles);

        int foundIndex = Collections.binarySearch(mazeTitles, new Title(mazeTitle));

        if(foundIndex >= 0) {
            listMazeTitle.setSelectedIndex(foundIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Could not find the maze title" + mazeTitle);
        }
    }

    private void checkListSize(){
         buttonDelete.setEnabled(data.getSize() != 0);
    }
    private void addMazeListListener(ListSelectionListener listener){
        mazeList.addListSelectionListener(listener);
    }

    private void addClosingListener(WindowListener listener) {
        addWindowListener(listener);
    }
    /**
     * This method is used to transition to generate Maze page with user selected data(just trsnsition to generation panel at this stage)
     */
    private class ModifyMazewithData implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.mainWindow.setFrontScreenAndFocus(ScreenMode.GENERATE);
        }
    }

    private void deletePressed(){
        int index = mazeList.getSelectedIndex();
        data.remove(mazeList.getSelectedValue());
        index--;
        if (index == -1) {
            if (data.getSize() != 0) {
                index = 0;
            }
        }
        mazeList.setSelectedIndex(index);
        checkListSize();
    }
    private void clearFields() {
        mazeIDF.setText("");
        authorF.setText("");
        DateCreatedF.setText("");
        DateEditedF.setText("");
    }
    private class mazeListListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e) {
            if (mazeList.getSelectedValue() != null
                    && !mazeList.getSelectedValue().equals("")) {
                display(data.get(mazeList.getSelectedValue()));
                buttonDelete.setEnabled(true);
            }else{
                clearFields();
            }
        }
    }

    private class ClosingListener extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            data.persist();
            System.exit(0);
        }
    }


}
