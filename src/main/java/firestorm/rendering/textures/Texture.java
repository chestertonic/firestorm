package firestorm.rendering.textures;

import firestorm.utils.managers.TextureManager;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by slinkee on 10/10/2016.
 */
public class Texture {
    /* Use a Map of Strings mapped to Textures. This allows us to store textures that can be used for
     * multiple sprites without having to reload the same texture twice.
     */
    private final static Map<String, TextureManager> texMap = new HashMap<String, TextureManager>();
    private TextureManager manager;
    private String fileName;

    public Texture(String fileName) {
        this.fileName = fileName;
        TextureManager oldTexture = texMap.get(fileName);
        if (oldTexture != null) {
            manager = oldTexture;
            manager.addReference();
        } else {
            try {
                System.out.println("Loading Texture: " + fileName);
                manager = new TextureManager(ImageIO.read(this.getClass().getResource("/textures/" + fileName + ".png")));
                texMap.put(fileName, manager);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (manager.removeReference() && !fileName.isEmpty()) {
            texMap.remove(fileName);
            System.out.println("removing texture from memory:" + fileName);
        }
        super.finalize();
    }

    public void render(Graphics g, double x, double y) {
        g.drawImage(manager.getImage(), (int) x, (int) y, null);
    }

    public BufferedImage getImage() {
        return manager.getImage();
    }
}
