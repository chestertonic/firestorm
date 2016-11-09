package firestorm.entities;

import firestorm.rendering.textures.Sprite;
import firestorm.states.GameState;
import firestorm.world.Tile;

/**
 * Created by slinkee on 10/12/2016.
 */
public abstract class Mob extends Entity {

    protected double dx, dy;
    protected double maxDY;
    protected double gravity;
    protected boolean falling = true;
    protected boolean canJump;

    public Mob(Sprite sprite, double x, double y, GameState state) {
        super(sprite, x, y, state);
        gravity = 0.5;
        maxDY = 6;
    }

    @Override
    public void tick() {
        fall();
        move();
    }

    public void move() {
        if (!hasHorizontalCollision()) x += dx;
        if (!hasVerticalCollision()) y += dy;
    }

    protected boolean hasVerticalCollision() {
        for (int i = 0; i < state.getTiles().size(); i++) {
            Tile t = state.getTiles().get(i);
            if (getBounds().intersects(t.getTop()) && dy > 0) {
                canJump = true;
                dy = 0;
                return true;
            }

            if (getBounds().intersects(t.getBottom()) && dy < 0) {
                dy = 0;
                return true;
            }

        }
        return false;
    }

    protected boolean hasHorizontalCollision() {
        for (int i = 0; i < state.getTiles().size(); i++) {
            Tile t = state.getTiles().get(i);
            if (getBounds().intersects(t.getRight()) && dx < 0){
                canJump = false;
                dx = 0;
                return true;
            }

            if (getBounds().intersects(t.getLeft()) && dx > 0) {
                canJump = false;
                dx = 0;
                return true;
            }
        }
        return false;
    }

    protected void fall() {
        dy += gravity;
        if (dy > maxDY) {
            dy = maxDY;
        }
    }

    protected void jump(double jumpForce) {
        if (canJump) {
            dy -= jumpForce;
            canJump = false;
        }
    }
}
