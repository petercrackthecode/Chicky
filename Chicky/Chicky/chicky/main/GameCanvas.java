package main;

import javax.swing.*;

import background.Background;
import base.GameObjectManager;
import block.CreateBlock;
import lava.CreateLava;
import player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameCanvas extends JPanel {
	BufferedImage backBuffered;
	
	final Dimension screenSize = new Dimension(1024, 600);
	
	final int BufferedImageWidth= 1024, BufferedImageHeight= 600;
	
	final int drawImageStartingPointX = 0, drawImageStartingPointY= 0;
	
	Graphics graphics;

	public GameCanvas() {
		
		this.setSize(screenSize);
		
		setUpBackBuffered();
		setUpCharacter();
		
		this.setVisible(true);
		
	}
	
	private void setUpBackBuffered()	{
		this.backBuffered = new BufferedImage(BufferedImageWidth, BufferedImageHeight, BufferedImage.TYPE_INT_RGB);
		this.graphics = this.backBuffered.getGraphics();
	}
	
	private void setUpCharacter()	{
		GameObjectManager.instance.add(new Background());
		CreateBlock createBlock= GameObjectManager.instance.recycle(CreateBlock.class);
		createBlock.configAction();
		//CreateLava createLava = GameObjectManager.instance.recycle(CreateLava.class);
		//createLava.configAction();
		setUpPlayer();
	}
	
	private void setUpPlayer()	{
		Player player = new Player();
		GameObjectManager.instance.add(player);
	}
	
	@Override
	protected void paintComponent(Graphics g)	{
		super.paintComponent(g);
		g.drawImage(this.backBuffered, drawImageStartingPointX, drawImageStartingPointY, null);
	}
	
	public void renderAll()	{
		GameObjectManager.instance.renderAll(this.graphics);
        this.repaint();
	}
	
	public void runAll()	{
		GameObjectManager.instance.runAll();
	}
	
}
