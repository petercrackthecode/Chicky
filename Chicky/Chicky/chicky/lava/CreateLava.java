package lava;

import java.util.ArrayList;
import java.util.List;

import action.Action;
import action.ActionAdapter;
import action.SequenceAction;
import base.GameObject;
import base.GameObjectManager;
import render.ImageRenderer;

public class CreateLava extends GameObject {
	
	List<Lava> lavas;
	boolean direction= true; // true = left, right = false;
	
	public void configAction()	{
		
		lavas = new ArrayList<>();
		Action createAction = new ActionAdapter()	{
			@Override
			public boolean run(GameObject owner)	{
				Lava lava = GameObjectManager.instance.recycle(Lava.class);
				lava.objectWidth= 40; 
				lava.objectHeight= 600;
				if (direction == true) {
					lava.position.set(0, 0);
					lava.renderer= new ImageRenderer("chicky.image/lavaLeft.png", objectWidth, objectHeight);
					lava.boxCollider.set(0, 0);
				}
				else {
					lava.position.set(1024 - 40, 0);
					lava.renderer= new ImageRenderer("chicky.image/lavaRight.png", 40, 1000);
					lava.boxCollider.set(1024 - 40, 0);
				}
				lavas.add(lava);
				direction= !direction;
				return true;
			}
		};
		
		Action waitAction = new ActionAdapter()	{
			@Override
			public boolean run(GameObject owner)	{
				lavas.removeIf(lava -> !lava.isAlive);
				return (lavas.size() == 2);
			}
		};
		
		this.addAction(
					new SequenceAction(
							createAction,
							waitAction
							)
				);
	}
} 

