package module.view;

import module.controller.Controller;
import module.model.Dragon;
import module.model.Tube;
import module.model.TubeColumn;
import module.model.soundEffect;
import module.model.proxy.ProxyImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

    private boolean isRunning = false;
    private ProxyImage proxyImage;
    private Image background;
    private Dragon dragon;
    private TubeColumn tubeColumn;
    private int score;
    private int highScore;
    public static boolean MUTE = false;

    //input background game
    public Game() {

        proxyImage = new ProxyImage("/resource/Background3.jpeg");
        background = proxyImage.loadImage().getImage();
        setFocusable(true);
        setDoubleBuffered(false);
        addKeyListener(new GameKeyAdapter());
        Timer timer = new Timer(15, this);
        timer.start();
        new soundEffect(MUTE);

    }

    //get score from game
    public int getScore() {
        return score;
    }

    //calculate score
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        if (isRunning) {
            ////////////////////////////////
            dragon.tick();
            tubeColumn.tick();
            checkColision();
            setScore(getScore() + 1);
            ///////////////////////////////
        }

        repaint();
    }

    //Show the word in game
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, null);
        if (isRunning) {
            ///////////////////////////////
            this.dragon.render(g2, this);
            this.tubeColumn.render(g2, this);
            g2.setColor(Color.black);
            g.setFont(new Font("Arial", 1, 20));
            g2.drawString("Your score: " + this.tubeColumn.getPoints(), 10, 20);
            ///////////////////////////////
        } else {
            g2.setColor(Color.black);
            g.setFont(new Font("Monospaced", Font.BOLD, 20));
            g2.drawString("Press 'Enter' to Start", Main.WIDTH / 2 - 130, Main.HEIGHT / 2 - 150);
            // g2.setColor(Color.black);
            // g.setFont(new Font("Arial", 50, 15));
        }
        g2.setColor(Color.black);
        g.setFont(new Font("Arial", 1, 20));
        g2.drawString("High Score: " + highScore, Main.WIDTH - 160, 20);

        g.dispose();
    }

    //to restart the game
    private void restartGame() {
        if (!isRunning) {
            this.isRunning = true;
            this.dragon = new Dragon(Main.WIDTH / 2, Main.HEIGHT / 2);
            this.tubeColumn = new TubeColumn();
            // new soundEffect(MUTE);
        }
    }

    //to detect 
    private void endGame() {
        this.isRunning = false;
        if (this.tubeColumn.getPoints() > highScore) {
            this.highScore = this.tubeColumn.getPoints();
        }
        this.tubeColumn.setPoints(0);
        // new soundEffect(!MUTE);

    }

    private void checkColision() {
        Rectangle rectDragon = this.dragon.getBounds();
        Rectangle rectTube;

        for (int i = 0; i < this.tubeColumn.getTubes().size(); i++) {
            Tube tempTube = this.tubeColumn.getTubes().get(i);
            rectTube = tempTube.getBounds();
            if (rectDragon.intersects(rectTube)) {
                endGame();
            }
        }
    }

    // Key
    private class GameKeyAdapter extends KeyAdapter {

        private final Controller controller;

        public GameKeyAdapter() {
            controller = new Controller();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                restartGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (isRunning) {
                controller.controllerReleased(dragon, e);
            }
        }
    }
}
