import javax.swing.*;

import input.KeyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    GameCanvas gameCanvas;
    long lastTime = 0;


    public GameWindow() {
        this.setSize(1024, 600);

        this.gameCanvas = new GameCanvas();

        this.add(this.gameCanvas);

        this.event();

        this.setVisible(true);
    }

    private void event() {
        this.keyboardEvent();
        this.windowEvent();
    }

    private void keyboardEvent() {
        this.addKeyListener(KeyboardInput.instance);
    }

    private void windowEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }


    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= 17_000_000) {
                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }

        }
    }

}
