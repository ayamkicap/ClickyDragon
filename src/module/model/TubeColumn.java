package module.model;

import module.view.Main;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TubeColumn {
    //Declare base
    private int base = Main.HEIGHT - 60;

    //Declare point, speed
    private List<Tube> tubes;
    private Random random;
    private int points = 0;
    private int speed = 5;
    private int changeSpeed = speed;

    //Manage tube (randomeness)
    public TubeColumn() {
        tubes = new ArrayList<>();
        random = new Random();
        initTubes();
    }

    private void initTubes() {

        int last = base;
        int randWay = random.nextInt(10);

        for (int i = 0; i < 20; i++) {

            Tube tempTube = new Tube(900, last);
            tempTube.setDx(speed);
            last = tempTube.getY() - tempTube.getHeight();
            if (i < randWay || i > randWay + 4) {
                tubes.add(tempTube);
            }

        }

    }

    //Manage tube (obstacle)
    public void tick() {

        for (int i = 0; i < tubes.size(); i++) {
            tubes.get(i).tick();

            if (tubes.get(i).getX() < 0) {
                tubes.remove(tubes.get(i));
            }
        }
        if (tubes.isEmpty()) {
            this.points += 1;
            //Increase the speed of the tube
            if (changeSpeed == points) {  
                this.speed += 1;
                changeSpeed += 5;
                System.out.println(speed);
                
            }
            initTubes();
        }

    }
    //
    public void render(Graphics2D g, ImageObserver obs) {
        for (int i = 0; i < tubes.size(); i++) {
            tubes.get(i).render(g, obs);
        }

    }

    public List<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }

    //current point during the game
    public int getPoints() {  
        return points;
    }

    //Saved point(s) after the game end with new highscore
    public void setPoints(int points) { 
        this.points = points;
    }

}
