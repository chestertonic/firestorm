package firestorm;

import firestorm.input.KeyInput;
import firestorm.input.MouseInput;
import firestorm.states.GameState;
import firestorm.states.MenuState;
import firestorm.states.StateManager;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;


/**
 * Created by slinkee on 10/10/2016.
 */

public class Game extends Canvas implements Runnable {

    public static final String TITLE = "Ghost ft. Patrick Swazy";
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 4 * 3;
    public static Game INSTANCE;
    private boolean running;    // used to exit program when game stops running
    private StateManager stateManager;

    public Game() {

        addKeyListener(new KeyInput());
        MouseInput mi = new MouseInput();
        addMouseListener(mi);
        addMouseMotionListener(mi);
        stateManager = new StateManager();

        stateManager.addState(new MenuState());
        stateManager.addState((new GameState()));

        INSTANCE = this;

    }

    private void tick() {
        stateManager.tick();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(-6, -28);
        //System.out.println("Page flipping: " + bs.getCapabilities().isPageFlipping());
        //System.out.println("Multiple Buffers: " + bs.getCapabilities().isMultiBufferAvailable());
        //System.out.println("FSEM Required: " + bs.getCapabilities().isFullScreenRequired());
        //System.out.println("Backbuffer accelerated: " + bs.getCapabilities().getBackBufferCapabilities().isAccelerated());
        //System.out.println("Frontbuffer accelerated: " + bs.getCapabilities().getFrontBufferCapabilities().isAccelerated());
        // Set objects to render
        //g2d.setColor(Color.RED);
        //g2d.fillRect(0, 0, WIDTH, HEIGHT);
        stateManager.render(g2d);

        g.dispose();
        bs.show();

    }

    protected void start() {
        if (running) return;
        running = true;
        new Thread(this, "FirestormMain-Thread").start();
    }

    public void stop() {
        if (!running) return;
        running = false;
    }

    public void run() {
        requestFocus();
        double target = 60.0;   // target limit for tps/fps
        double nsPerTick = 1000000000.0 / target;   // nanoseconds per tick
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();    // used to print tps/fps calcs to console
        double unprocessed = 0.0;   //  time unprocessed, used to know when we need to tick
        int fps = 0;    // frames per second - used to print out to console
        int tps = 0;    // ticks per second - used to print out to console
        boolean canRender;  // used to limit our tps/fps
        System.out.println("Starting game loop:");
        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;    // time from last loop to current loop
            lastTime = now;     // will now be used to set unprocessed on next loop

            if (unprocessed >= 1.0) {   // to determine if we tick
                tick();     // we tick
                KeyInput.update();      // update our keyboard input
                MouseInput.update();    // update our mouse input
                unprocessed--;  // take unprocessed down by 1.0
                tps++;          // increase tick per second by 1
                canRender = true;   // allows us to render on tick
            } else canRender = false;   // if we don't tick, we don't render

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canRender) {
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer) {
                timer += 1000;
                System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
                fps = 0;
                tps = 0;
            }
        }
        System.exit(0);
    }
}
