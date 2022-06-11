public class Title implements Comparable<Title> {
    String mazeTitle;

    //コンストラクタ
    public Title(String mazeTitle) {setMazeTitle(mazeTitle);}

    public String getMazeTitle() {return this.mazeTitle;}

    public void setMazeTitle(String mazeTitle) {
        if(mazeTitle == null) {
            throw new IllegalArgumentException();
        }
        this.mazeTitle = mazeTitle;
    }

    public String toString() {return this.mazeTitle;}

    public int compareTo(Title another) {return this.mazeTitle.compareTo(another.getMazeTitle());}

    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj instanceof Title) {
            Title another = (Title) obj;
            if(this.mazeTitle.equals(another.getMazeTitle())) {
                return true;
            }
        }
        return false;
    }
    public int hashCode() {return this.mazeTitle.hashCode();}
}
