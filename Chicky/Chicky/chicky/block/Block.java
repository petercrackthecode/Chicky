package block;

import java.util.*;

import base.GameObject;
import base.Vector2D;
import render.ImageRenderer;

public class Block extends GameObject {
	Random rand= new Random();
	public Vector2D velocity;
	public Block()	{
		this.objectWidth= 200;
		this.objectHeight= 10;
		this.imagePath= "chicky.image/step1.png";
		this.velocity = new Vector2D();
		this.renderer = new ImageRenderer(imagePath, objectWidth, objectHeight);
	}
	
	public void run()	{
		super.run();
		this.position.addUp(this.velocity);
		super.isDied();
	}
	
}
