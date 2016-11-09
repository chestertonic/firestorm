package firestorm;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by slinkee on 10/10/2016.
 */

public class Firestorm {
    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame(Game.TITLE);
        frame.add(game);
        frame.setSize(Game.WIDTH, Game.HEIGHT);
        //frame.setResizable(false);
        frame.setFocusable(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("Exiting Game");
                System.exit(0);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }
}
