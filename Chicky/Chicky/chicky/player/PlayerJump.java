package player;

import action.Action;
import action.ActionAdapter;
import action.SequenceAction;
import action.WaitAction;
import base.GameObject;
import base.GameObjectAttribute;
import base.GameObjectManager;
import base.Vector2D;
import block.Block;
import input.KeyboardInput;

public class PlayerJump implements GameObjectAttribute<Player>{
	public JumpCounter isJumping= JumpCounter.END;
	public Vector2D blockPosition= null;
	public Vector2D[] JumpLocation= new Vector2D[2];
	
	@Override
	public void run(Player player) {
			if (KeyboardInput.instance.isLeft) {
				player.direction = Direction.LEFT;
			} else if (KeyboardInput.instance.isRight) {
				player.direction = Direction.RIGHT;
			}
			
			this.blockPosition.set(findBlock(player).position);
			
			Action jumpAction = new ActionAdapter() {
				@Override
				public boolean run(GameObject owner) {
					player.position.set(jumpPosition(player));
					return true;
				}
			};
			
			Action landAction = new ActionAdapter()	{
				@Override
				public boolean run(GameObject owner) {
					player.position.set(blockPosition);
					return true;
				}
			};
			
			Action reset = new ActionAdapter()	{
				@Override
				public boolean run(GameObject owner)	{
					player.attributes.clear();
					player.attributes.add(new PlayerMove());
					return true;
				}
			};

			player.addAction(new SequenceAction(
					jumpAction, 
					new WaitAction(10),
					landAction,
					new WaitAction(2),
					reset
			));
		
		
	}
	
	private Block findBlock(Player player)	{
		Block pseudoBlock = GameObjectManager.instance.findBlockByPlayer(player);
		Block realBlock = GameObjectManager.instance.findBlockInDirection(pseudoBlock, player.direction);
		return realBlock;
	}
	
	private Vector2D jumpPosition(Player player)	{
		float jumpHeight= -50;
		if (player.position.x > blockPosition.x) 
			return (new Vector2D((player.position.x - blockPosition.x) / 2, blockPosition.y - jumpHeight));
		
		return (new Vector2D((blockPosition.x - player.position.x) / 2, blockPosition.y - jumpHeight));
	}
	
}
