package lava;

import base.GameObject;
import base.Vector2D;
import physics.BoxCollider;
import physics.Physics;

public class Lava extends GameObject implements Physics{
	BoxCollider boxCollider;
	
	boolean direction= true;
	
	public Lava(Vector2D position)	{
		
	}

	@Override
	public BoxCollider getBoxCollider() {
		return this.boxCollider;
	}

	@Override
	public void getHit(GameObject gameObject) {
		
	}
	
}
