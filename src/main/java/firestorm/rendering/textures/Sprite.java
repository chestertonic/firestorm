package firestorm.rendering.textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by slinkee on 10/10/2016.
 */
public class Sprite {
    private BufferedImage image;

    public Sprite(SpriteSheet spritesheet, int x, int y) {
        this.image = spritesheet.getTexture().getImage().getSubimage((x * spritesheet.getWidth()) - spritesheet.getWidth(),
                (y * spritesheet.getHeight()) - spritesheet.getHeight(), spritesheet.getWidth(), spritesheet.getHeight());
    }

    public Sprite(String texName) {
        Texture tex = new Texture(texName);
        this.image = tex.getImage();
    }
    public void render(Graphics g, double x, double y) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

}