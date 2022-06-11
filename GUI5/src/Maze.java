import java.io.Serial;
import java.io.Serializable;

public class Maze implements Comparable<Maze>, Serializable {

    @Serial
    private static final long serialVersionUID = 890514783655843673L;
    private String mazeName;

    private String author;

    private String dateCreated;

    private String dateEdited;

    private String mazeID;

    public Maze(){}

    public Maze(String mazeName, String author, String mazeID, String dateCreated, String dateEdited){
        this.mazeName = mazeName;
        this.author = author;
        this.mazeID = mazeID;
        this.dateCreated = dateCreated;
        this.dateEdited = dateEdited;
    }

    public String getMazeName(){
        return mazeName;
    }

    public void setMazeName(String mazeName){
        this.mazeName = mazeName;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getDateCreated(){
        return dateCreated;
    }

    public void setDateCreated(String dateCreated){
        this.dateCreated = dateCreated;
    }

    public String getDateEdited(){
        return dateEdited;
    }

    public void setDateEdited(String dateEdited){
        this.dateEdited = dateEdited;
    }

    public String getMazeID(){
        return mazeID;
    }

    public void setMazeID(String mazeID){
        this.mazeID = mazeID;
    }

    @Override
    public int compareTo(Maze o) {
        return this.mazeName.compareTo(o.mazeName);
    }
    public String toString(){
        return mazeName + " " + author + " " + mazeID + " " + dateCreated + " "+ dateEdited;
    }
}
