package main;

import input.KeyboardInput;

import javax.swing.JFrame;

import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
	GameCanvas gameCanvas;
	
	final Point screenLocation = new Point(200, 100);
	final String title = "Chicky";
	
	long lastTime= 0L;
	final long frame60XPS = 17_000_000;
	final int screenWidth= 1024, screenHeight= 600;
	
	public GameWindow()	{
		this.setSize(screenWidth, screenHeight);
		
		this.setName(title);
		this.setLocation(screenLocation);
		
		this.gameCanvas= new GameCanvas();
		
		this.add(this.gameCanvas);
		
		this.event();
		
		this.setVisible(true);	
	}
	
	private void event() {
		this.keyboardEvent();
		this.windowEvent();
	}

	private void windowEvent() {
		this.addWindowListener(new WindowAdapter()	{
			@Override
			public void windowClosing(WindowEvent e)	{
				System.exit(1);
			}
		});
	}

	private void keyboardEvent() {
		this.addKeyListener(KeyboardInput.instance);
	}

	public void gameLoop()	{
		while (true)	{
			long currentTime = System.nanoTime();
            if (currentTime - lastTime >= frame60XPS) {
                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }
		}
	}
}
