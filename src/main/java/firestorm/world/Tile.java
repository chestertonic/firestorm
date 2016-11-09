package firestorm.world;

import firestorm.rendering.textures.Sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Created by slinkee on 10/12/2016.
 */
public class Tile {
    protected float x, y;
    protected Sprite sprite;
    protected boolean solid;

    public Tile(float x, float y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.solid = true;
    }

    public void render(Graphics2D g) {
        sprite.render(g, x, y);
        g.setColor(Color.YELLOW);
        g.draw(getTop());
        g.setColor(Color.BLUE);
        g.draw(getBottom());
        g.setColor(Color.MAGENTA);
        g.draw(getRight());
        g.setColor(Color.ORANGE);
        g.draw(getLeft());
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
    }

    public Rectangle getTop() {
        return new Rectangle((int) x + 5, (int) y, sprite.getWidth() - 10, 4);
    }

    public Rectangle getBottom() {
        return new Rectangle((int) x + 5, (int) y + sprite.getHeight() - 4, sprite.getWidth() - 10, 4);
    }

    public Rectangle getRight() {
        return new Rectangle((int) x + sprite.getWidth() - 4, (int) y + 5, 4, sprite.getHeight() - 10);
    }

    public Rectangle getLeft() {
        return new Rectangle((int) x, (int) y + 5, 4, sprite.getHeight() - 10);
    }
}
