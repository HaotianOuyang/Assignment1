import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class JDBCMazeDataSource implements MazeDataSource {
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maze (" +
            "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," +
             "mazeName VARCHAR(30),"
            + "author VARCHAR(30),"
            + "mazeID VARCHAR(30),"
            + "mazeCreated VARCHAR(30),"
            + "mazeEdited VARCHAR(30)" + ");";

    private static final String INSERT_MAZE = "INSERT INTO maze (mazeName, author, mazeID, mazeCreated, mazeEdited) VALUES (?, ?, ?, ?, ?);";

    private static final String GET_NAMES = "SELECT mazeName FROM maze";

    private static final String GET_MAZE = "SELECT * FROM maze WHERE mazeName=?";

    private static final String DELETE_MAZE = "DELETE FROM maze WHERE mazeName=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM maze";


    private Connection connection;

    private PreparedStatement addMaze;

    private PreparedStatement getNameList;

    private PreparedStatement getMaze;

    private PreparedStatement deleteMaze;

    private PreparedStatement rowCount;

    public JDBCMazeDataSource(){
        connection = DatabaseConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);
            addMaze = connection.prepareStatement(INSERT_MAZE);
            getNameList = connection.prepareStatement(GET_NAMES);
            getMaze = connection.prepareStatement(GET_MAZE);
            deleteMaze = connection.prepareStatement(DELETE_MAZE);
            rowCount = connection.prepareStatement(COUNT_ROWS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMaze(Maze m) {
         try{
             addMaze.setString(1, m.getMazeName());
             addMaze.setString(2, m.getAuthor());
             addMaze.setString(3, m.getMazeID());
             addMaze.setString(4, m.getDateCreated());
             addMaze.setString(5, m.getDateEdited());
             addMaze.executeUpdate();
         }catch (SQLException e) {
             e.printStackTrace();
         }
    }

    @Override
    public Maze getMaze(String mazeName) {
        Maze m = new Maze();
        ResultSet rs = null;

        try{
            getMaze.setString(1, mazeName);
            rs = getMaze.executeQuery();
            rs.next();
            m.setMazeName(rs.getString("mazeName"));
            m.setMazeID(rs.getString("mazeID"));
            m.setAuthor(rs.getString("author"));
            m.setDateCreated(rs.getString("mazeCreated"));
            m.setDateEdited(rs.getString("mazeEdited"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public int getSize() {
        ResultSet rs = null;
        int rows = 0;

        try {
            rs = rowCount.executeQuery();
            rs.next();
            rows = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rows;
    }

    @Override
    public void deleteMaze(String mazeName) {
        try{
            deleteMaze.setString(1, mazeName);
            deleteMaze.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> mazeNameSet() {
        Set<String> mazes = new TreeSet<String>();
        ResultSet rs = null;

        try {
            rs= getNameList.executeQuery();
            while (rs.next()){
                mazes.add(rs.getString("mazeName"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return mazes;
    }




}
