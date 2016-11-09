package firestorm.states;

import firestorm.Game;
import firestorm.entities.Entity;
import firestorm.entities.Player;
import firestorm.rendering.textures.Sprite;
import firestorm.rendering.textures.SpriteSheet;
import firestorm.rendering.textures.Texture;
import firestorm.world.Background;
import firestorm.world.Tile;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Created by slinkee on 10/12/2016.
 */
public class GameState implements State {

    private ArrayList<Entity> entities;
    private ArrayList<Tile> tiles;
    private Background bg;


    public void init() {
        entities = new ArrayList<Entity>();
        tiles = new ArrayList<Tile>();
        bg = new Background("bg");
        new Player(new Sprite("ghoul2"), 100, 100, this);
        float x = 0;
        float y = Game.HEIGHT - 64;
        tiles.add(new Tile(200, 200, new Sprite(new SpriteSheet(new Texture("terrain_test2"), 64), 3, 1)));
        tiles.add(new Tile(100, 480 - 128, new Sprite(new SpriteSheet(new Texture("terrain_test2"), 64), 3, 1)));
        tiles.add(new Tile(400, 50, new Sprite(new SpriteSheet(new Texture("terrain_test2"), 64), 3, 1)));
        tiles.add(new Tile(300, 300, new Sprite(new SpriteSheet(new Texture("terrain_test2"), 64), 3, 1)));
        tiles.add(new Tile(640 - 64, 300, new Sprite(new SpriteSheet(new Texture("terrain_test2"), 64), 3, 1)));
        for (int i = 0; i < 4; i++) {
            tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain_test2"), 64), 1, 1)));
            x += 64;
        }
        for (int i = 0; i < 3; i++) {
            tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain_test2"), 64), 2, 1)));
            x += 64;
        }
        for (int i = 0; i < 3; i++) {
            tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain_test2"), 64), 1, 1)));
            x += 64;
        }
    }

    public void enter() {

    }

    public void tick(StateManager stateManager) {
        for (Entity e : entities) {
            e.tick();
        }
    }

    public void render(Graphics2D g) {
        bg.render(g);
        for (Entity e : entities) {
            e.render(g);
        }
        for (Tile t : tiles) {
            t.render(g);
        }
    }

    public void exit() {
        entities.clear();
    }

    public String getName() {
        return "level1";
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
