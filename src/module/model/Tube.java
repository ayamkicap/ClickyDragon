
package module.model;

import module.model.proxy.ProxyImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

//Manage the image of the obstacle 
public class Tube extends GameObject {

    private ProxyImage proxyImage;
    public Tube(int x, int y) {
        super(x, y);
        if (proxyImage == null) {
            proxyImage = new ProxyImage("/resource/Mid-repeat.png");

        }
        this.image = proxyImage.loadImage().getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    @Override
    public void tick() {
        this.x -= dx;
    }

    @Override
    public void render(Graphics2D g, ImageObserver obs) {
        g.drawImage(image, x, y, obs);

    }

    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
