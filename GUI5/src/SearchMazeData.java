import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 * This is a class for retrieve data by using MazeDataSource for SearchMaze GUI functionality.
 *
 * @author Haotian Ouyang
 */
public class SearchMazeData {
    DefaultListModel listModel;

    MazeDataSource mazeData;

    public SearchMazeData(){
        listModel = new DefaultListModel();

        mazeData = new JDBCMazeDataSource();

        for (String mazeName: mazeData.mazeNameSet()){
            listModel.addElement(mazeName);
        }
    }


    public void add(Maze m){
        if(!listModel.contains(m.getMazeName())){
            listModel.addElement(m.getMazeName());
            mazeData.addMaze(m);
        }
    }

    public void remove(Object k){
        listModel.removeElement(k);
        mazeData.deleteMaze((String) k);
    }

    public void persist(){ mazeData.close();}

    public Maze get(Object k){ return mazeData.getMaze((String) k);}

    public ListModel getModel(){return listModel;}

    public int getSize(){ return mazeData.getSize();}
}
