package module.model;

import module.model.proxy.ProxyImage;
import module.view.Main;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

//For input and control of the dragon's movement
public class Dragon extends GameObject {

    private ProxyImage proxyImage;
    private Tube[] tube;

    public Dragon(int x, int y) {  //Control the movement of the dragon
        super(x, y);
        if (proxyImage == null) {
            proxyImage = new ProxyImage("/resource/Dragon5.gif");
        }
        this.image = proxyImage.loadImage().getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.x -= width;
        this.y -= height;
        tube = new Tube[1];
        tube[0] = new Tube(900, Main.HEIGHT - 60);
        this.dy = 4;
    }

    @Override
    public void tick() { //For the dragon's hit box
        if (dy < 5) {
            dy += 2;
        }
        this.y += dy;
        tube[0].tick();
        checkWindowBorder();
    }

    public void jump() {
        if (dy > 0) {
            dy = 0;
        }
        dy -= 15;
    }

    private void checkWindowBorder() {
        if (this.x > Main.WIDTH) {
            this.x = Main.WIDTH;
        }
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y > Main.HEIGHT - 50) {
            this.y = Main.HEIGHT - 50;
        }
        if (this.y < 0) {
            this.y = 0;
        }
    }

    @Override
    public void render(Graphics2D g, ImageObserver obs) {
        g.drawImage(image, x, y, obs);
        tube[0].render(g, obs);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
