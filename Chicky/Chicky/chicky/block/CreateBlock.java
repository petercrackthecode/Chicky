package block;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import action.Action;
import action.ActionAdapter;
import action.RepeatActionForever;
import action.SequenceAction;
import base.GameObject;
import base.GameObjectManager;
import base.SpeedLevel;
import base.Vector2D;

public class CreateBlock extends GameObject{
	Random rand = new Random();
	Vector2D lastPosition= null;
	Vector2D lastSize = new Vector2D();
	public void configAction()	{
		List<Block> blocks = new ArrayList<>();
		Action createAction = new ActionAdapter()	{
			@Override
			public boolean run(GameObject owner)	{
				Block block = GameObjectManager.instance.recycle(Block.class);
				blockSetUp(block);
				blocks.add(block);
				return true;
			}
		};
		
		
		Action waitAction = new ActionAdapter()	{
			@Override
			public boolean run(GameObject owner)	{
				blocks.removeIf(block -> !block.isAlive);
				return (blocks.size() == 4);
			}
		};
		
		this.addAction(
				new RepeatActionForever(
						new SequenceAction(
								createAction,
								waitAction
								)
						)
				);
		
	}
	
	public void blockSetUp(Block block)	{
		if (lastPosition == null) block.position.set(30, 500);
		else {
			if ((int) rand.nextInt(2) == 1)  {
				if (lastPosition.x + 80 + 200 < 1024) 
					block.position.set(lastPosition.addUp(80, -80));
				else block.position.set(lastPosition.addUp(-80, -80));
			}
			else	{
				if (lastPosition.x - 80 - 200 > 0)
					block.position.set(lastPosition.addUp(-80, -80));
				else block.position.set(lastPosition.addUp(80, -80));
			}
		}
		
		block.velocity.set(0, SpeedLevel.level*3);
	}
	
}
