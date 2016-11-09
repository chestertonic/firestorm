package firestorm.utils.managers;

import java.awt.image.BufferedImage;

/**
 * Created by slinkee on 10/10/2016.
 */
public class TextureManager extends ResourceManager {
    private BufferedImage image;

    public TextureManager(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
