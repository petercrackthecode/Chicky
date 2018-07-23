package base;
import java.util.ArrayList;
import javax.swing.*;

import block.Block;
import physics.BoxCollider;
import physics.Physics;
import player.Direction;
import player.Player;
import render.*;

import java.awt.*;

public class GameObjectManager {
		public static GameObjectManager instance = new GameObjectManager();
		private ArrayList<GameObject> list = new ArrayList<>();
		private ArrayList<GameObject> tempList = new ArrayList<>();

		private GameObjectManager()	{

		}

		public void add(GameObject gameObject)	{
			this.tempList.add(gameObject);
		}

		public void runAll()	{
			this.list
			.stream()
			.filter(gameObject -> gameObject.isAlive)
			.forEach(gameObject -> gameObject.run());
			this.list.addAll(this.tempList);
			this.tempList.clear();
		}

		public void renderAll(Graphics graphics)	{
			this.list
			.stream()
			.filter(gameObject -> gameObject.isAlive)
			.forEach(gameObject -> gameObject.render(graphics));
		}

		public Player findPlayer()	{
			return (Player) this.list
							.stream()
							.filter(gameObject -> gameObject instanceof Player)
							.filter(gameObject -> gameObject.isAlive)
							.findFirst()
							.orElse(null);
		}
		
		//----------------------------------------------
		public Block findBlockByPlayer(Player player)	{
			return (Block) this.list
					.stream()
					.filter(gameObject -> gameObject instanceof Block)
					.filter(gameObject -> gameObject.isAlive)
					.filter(gameObject -> {
						ImageRenderer pseudoRenderer= (ImageRenderer) (gameObject.renderer);
						ImageRenderer playerRenderer= (ImageRenderer) (player.renderer);
						return (pseudoRenderer.height == playerRenderer.height + player.objectHeight); 
					})
					.findFirst()
					.orElse(null);
		}
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		public Block findBlockInDirection(Block block, Direction direction)	{
			return (Block) this.list
					.stream()
					.filter(gameObject -> gameObject instanceof Block)
					.filter(gameObject -> gameObject.isAlive)
					.filter(gameObject -> {
						if (direction == Direction.LEFT) return (gameObject.position.x < block.position.x);
						return (gameObject.position.x > block.position.x);
					})
					.findFirst()
					.orElse(null);
		}
		
		public <T extends GameObject> T recycle(Class<T> aClass)	{
			T gameObject = (T) this.list
									.stream()
									.filter(object -> aClass.isInstance(object))
									.filter(object -> !object.isAlive)
									.findFirst()
									.orElse(null);
			if (gameObject != null)	{
				gameObject.isAlive= true;
			}	else 	{
				try {
					gameObject = aClass.newInstance();
					this.add(gameObject);
				} catch (InstantiationException | IllegalAccessException e)	{
					e.printStackTrace();
					return null;
				}
			}
			return gameObject;
		}

		public <D extends GameObject & Physics> D checkCollision(BoxCollider boxCollider, Class<D> aClass)	{
			return (D) this.list
						.stream()
						.filter(gameObject -> gameObject.isAlive)
						.filter(gameObject -> aClass.isInstance(gameObject)).filter(gameObject -> {
							BoxCollider other = ((D) gameObject).getBoxCollider();
							return boxCollider.checkCollision(other);
						})
						.findFirst()
						.orElse(null);
		}
		
		public void clear()	{
			this.list.clear();
			this.tempList.clear();
		}
		
}

