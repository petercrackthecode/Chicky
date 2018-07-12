package game.player;

import base.FrameCounter;
import base.GameObjectAttributes;
import base.GameObjectManager;
import input.KeyboardInput;

public class PlayerShoot implements GameObjectAttributes<Player> {

    private FrameCounter frameCounter;

    public PlayerShoot() {
        this.frameCounter = new FrameCounter(15);
    }

    @Override
	public void run(Player gameObject) {
		if (KeyboardInput.instance.isSpace)
			if (this.frameCounter.run()) {
				BulletPlayer bulletPlayer = new BulletPlayer();
				bulletPlayer.position.set(gameObject.position);
				bulletPlayer.velocity.set(gameObject.velocity.copy().multiply(2f));
				GameObjectManager.instance.add(bulletPlayer);
				this.frameCounter.reset(); // when shoot the frameCounter resets back to 0
			}
	}
    
}
