package player;

import base.GameObjectAttribute;
import base.GameObjectManager;
import base.Vector2D;
import input.KeyboardInput;

public class PlayerMove implements GameObjectAttribute<Player> {
	
	@Override
	public void run(Player player) {
		if (player.direction == Direction.LEFT)
			player.velocity.set(new Vector2D(-3.5f, 3.5f));
		else player.velocity.set(new Vector2D(3.5f, 3.5f));
		if (GameObjectManager.instance.findBlockByPlayer(player) != null) player.velocity.y= 0f;
		player.position.addUp(player.velocity);
	}
	
}
