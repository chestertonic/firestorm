package firestorm.world;

import firestorm.rendering.textures.Texture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by slinkee on 10/13/2016.
 */
public class Background {
    private BufferedImage image;

    public Background(String texName) {
        Texture tex = new Texture(texName);
        this.image = tex.getImage();
    }
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
