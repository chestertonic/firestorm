package firestorm.entities;

import firestorm.rendering.textures.Sprite;
import firestorm.states.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Created by slinkee on 10/12/2016.
 */

public abstract class Entity {

    protected double x, y;
    protected Sprite sprite;
    protected GameState state;

    public Entity(Sprite sprite, double x, double y, GameState state) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.state = state;
        this.state.addEntity(this);
    }

    public abstract void tick();

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
