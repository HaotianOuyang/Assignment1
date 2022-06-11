import java.util.Set;

public interface MazeDataSource {

    void addMaze(Maze m);

    Maze getMaze(String mazeName);

    int getSize();

    void deleteMaze(String mazeName);

    void close();

    /**
     * Retrieves a set of maze details from the data source that used in maze list.
     * @return set of maze details
     */
    Set<String> mazeNameSet();



}
