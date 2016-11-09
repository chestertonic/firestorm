package firestorm.states;


import java.awt.Graphics2D;

/**
 * Created by slinkee on 10/12/2016.
 */
public interface State {

    void init();

    void enter();

    void tick(StateManager stateManager);

    void render(Graphics2D g);

    void exit();

    String getName();

}
