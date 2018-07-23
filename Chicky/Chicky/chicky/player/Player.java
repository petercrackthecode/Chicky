package player;

import base.GameObject;
import base.Vector2D;
import input.KeyboardInput;
import physics.BoxCollider;
import physics.Physics;
import physics.RunHitObject;
import render.ImageRenderer;

public class Player extends GameObject implements Physics {
	
	Vector2D velocity = new Vector2D(3.5f, 0);
	private BoxCollider boxCollider= new BoxCollider(0, 0);
	private RunHitObject runHitObject= new RunHitObject();
	private boolean isDrifting= false;
	
	public final Vector2D initialPosition= new Vector2D();
	public final String playerPath= "image/birdMoveRight.png";
	
	Direction direction= Direction.RIGHT;
	
	public Player()	{
		this.objectWidth= 40; 
		this.objectHeight= 40;
		
		this.renderer= new ImageRenderer(playerPath, objectWidth, objectHeight);
		this.position= initialPosition;
		this.attributes.add(new PlayerMove());
	}
	
	@Override
	public void run()	{
		if (KeyboardInput.instance.isLeft || KeyboardInput.instance.isRight) {
			this.attributes.clear();
			this.attributes.add(new PlayerJump());
		}
		
		super.run();
		this.boxCollider.position.set(this.position.x, this.position.y);
		this.runHitObject.run(this);
	}
	
	public void getHit(GameObject gameObject)	{
		this.isAlive= false;
	}
	
	public BoxCollider getBoxCollider()	{
		return this.boxCollider;
	}
}
