package physics;
import java.awt.*;

import base.Vector2D;
public class BoxCollider {
	public Vector2D position;
	private int width;
	private int height;
	
	public BoxCollider(int width, int height)	{
		this.width= width;
		this.height= height;
		position= new Vector2D();
	}
	
	public boolean checkCollision(BoxCollider other)	{
		Rectangle r1= new Rectangle((int) position.x, (int) position.y, width, height),
				r2= new Rectangle((int) position.x, (int) position.y, width, height);
		return r1.intersects(r2);
	}

	public void set(int width, int height) {
		this.width= width;
		this.height= height;
	}
}
