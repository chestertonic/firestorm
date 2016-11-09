package firestorm.entities;

import firestorm.input.KeyInput;
import firestorm.rendering.textures.Sprite;
import firestorm.states.GameState;

import java.awt.event.KeyEvent;

/**
 * Created by slinkee on 10/12/2016.
 */
public class Player extends Mob {

    public Player(Sprite sprite, double x, double y, GameState state) {
        super(sprite, x, y, state);

    }

    @Override
    public void tick() {
        if (KeyInput.isDown(KeyEvent.VK_W)) jump(10);
        if (KeyInput.isDown(KeyEvent.VK_S)) dy = 4;
        if (KeyInput.isDown(KeyEvent.VK_A)) dx = -4;
        if (KeyInput.isDown(KeyEvent.VK_D)) dx = 4;

        if (KeyInput.wasReleased(KeyEvent.VK_W) || KeyInput.wasReleased(KeyEvent.VK_S)) dy = 0;
        if (KeyInput.wasReleased(KeyEvent.VK_A) || KeyInput.wasReleased(KeyEvent.VK_D)) dx = 0;
        super.tick();
    }


}
