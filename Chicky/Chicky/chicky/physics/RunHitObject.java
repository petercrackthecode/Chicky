package physics;
import java.util.Arrays;
import java.util.List;

import base.GameObject;
import base.GameObjectManager;

public class RunHitObject<D extends GameObject & Physics> {
	private List<Class<D>> list;
	public RunHitObject(Class<D>... classes)	{
		this.list = Arrays.asList(classes);
	}
	
	public <P extends GameObject & Physics> void run(P object)	{
		this.list
		.stream()
		.map(aClass -> GameObjectManager.instance.checkCollision(object.getBoxCollider(), aClass))
		.filter(gameObject -> gameObject != null)
		.forEach(gameObject -> {
			gameObject.getHit(object);
			object.getHit(gameObject);
		});
	}
}
