package physics;

import base.GameObject;

public interface Physics {
	BoxCollider getBoxCollider();
	
	void getHit(GameObject gameObject);
}
