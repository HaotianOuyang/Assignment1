import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mazeGenerator extends Canvas {

    private static Random rand = new Random();
    private int WIDTH = 80;
    private int HEIGHT = 60;
    private int TILE_WIDTH = 10;
    private int TILE_HEIGHT = 10;
    private int step = 0;

    mazeGenerator(int WIDTH, int HEIGHT, int TILE_WIDTH, int TILE_HEIGHT){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.TILE_WIDTH = TILE_WIDTH;
        this.TILE_HEIGHT = TILE_HEIGHT;
    }


    private List<Vector2I> maze = new ArrayList<>();

    public void paint(Graphics g){
        super.paint(g);

        //
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);
        g.setColor(Color.LIGHT_GRAY);



        // Draws box on the outside
        g.drawLine(0,0,0,HEIGHT * TILE_HEIGHT);
        g.drawLine(0,0,WIDTH * TILE_WIDTH,0);
        g.drawLine(WIDTH * TILE_WIDTH,0,WIDTH * TILE_WIDTH,HEIGHT * TILE_HEIGHT);
        g.drawLine(0,HEIGHT * TILE_HEIGHT,WIDTH * TILE_WIDTH,HEIGHT * TILE_HEIGHT);


        List<Vector2I> mazeSteped = maze;

        for(int y = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                int current = (y * WIDTH) + x;
                int lower = ((y +1) * WIDTH) + x;
                if(!maze.contains(new Vector2I(current,lower))){
                    g.drawLine(x*TILE_WIDTH, (y + 1) * TILE_HEIGHT,(x+1)*TILE_WIDTH,(y+1)* TILE_HEIGHT);
                }

                if(!maze.contains(new Vector2I(current,current + 1))){
                    g.drawLine((x+1)*TILE_WIDTH, y  * TILE_HEIGHT,(x+1)*TILE_WIDTH,(y+1)* TILE_HEIGHT);
                }
            }
        }

    }

    public void generate(){
        List<Integer> visited = new ArrayList<>();
        List<Vector2I> toVisit = new ArrayList<>();

        visited.add(0);
        toVisit.add(new Vector2I(0,1));
        toVisit.add(new Vector2I(0,WIDTH));


        while(toVisit.size() > 0){
            int randomIndex = rand.nextInt(toVisit.size());
            Vector2I nextPath = toVisit.remove(randomIndex);

            if(visited.contains(nextPath.end)){
                continue;
            }

            if(nextPath.start > nextPath.end){
                maze.add(new Vector2I(nextPath.end, nextPath.start));
            }
            else{
                maze.add(nextPath);
            }

            visited.add(nextPath.end);

            int above = nextPath.end - WIDTH;
            if(above > 0 && !visited.contains(above)){
                toVisit.add(new Vector2I(nextPath.end, above));
            }

            int left = nextPath.end - 1;
            if(left % WIDTH != WIDTH - 1 && !visited.contains(left)){
                toVisit.add(new Vector2I(nextPath.end, left));
            }

            int right = nextPath.end + 1;
            if(right % WIDTH != 0 && !visited.contains(right)){
                toVisit.add(new Vector2I(nextPath.end, right));
            }

            int below = nextPath.end + WIDTH;
            if(below < WIDTH * HEIGHT && !visited.contains(below)){
                toVisit.add(new Vector2I(nextPath.end, below));
            }
        }

    }

    public void step() {
        step++;
        if(step >= maze.size()){
            step = maze.size() - 1;
        }

    }


//    public static void main(String[] args){
//        mazeGenerator mazeGen = new mazeGenerator(30,30,10,10);
//        mazeGen.generate();
//        mazeGen.setSize(100,100);
//        JFrame frame = new JFrame("Maze Generator");
//        frame.add(mazeGen);
//        frame.setSize(830,650);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    }


}
