package game.player;

import java.util.Random;

import base.GameObject;
import base.GameObjectAttributes;
import base.Vector2D;
import input.KeyboardInput;

public class PlayerMove implements GameObjectAttributes<Player> {

    private Random random = new Random();
    private float playerMoveRate= 1f;
	@Override
	public void run(Player gameObject) {
		if (KeyboardInput.instance.isLeft)	{
			gameObject.angle += 2.0;
		}
		
		if (KeyboardInput.instance.isRight)	{
			gameObject.angle -= 2.0;
		}
		
		if (KeyboardInput.instance.isUp)	{
			playerMoveRate= 1.5f;
		}
		else {
			playerMoveRate= 1;
		}
		
		gameObject.velocity.set(new Vector2D(3.5f, 0.0f).rotate(gameObject.angle)
				.multiply(playerMoveRate));	
		gameObject.position.addUp(gameObject.velocity);
		this.backtoScreen(gameObject);
	}
	
	private void backtoScreen(GameObject gameObject) {
        if (gameObject.position.x < 0) gameObject.position.set(1024, this.random.nextInt(600));

        if (gameObject.position.x > 1024) gameObject.position.set(0, this.random.nextInt(600));

        if (gameObject.position.y < 0) gameObject.position.set(this.random.nextInt(1024), 600);

        if (gameObject.position.y > 600) gameObject.position.set(this.random.nextInt(1024), 0);
    }
	
}
