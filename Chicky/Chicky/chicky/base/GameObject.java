package base;
import java.util.List;

import action.Action;
import render.Renderer;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class GameObject {
	public Vector2D position;
	public Renderer renderer;
	public List<GameObjectAttribute> attributes;
	public boolean isAlive= true;
	private List<Action> actions;
	public int objectWidth= 0, objectHeight= 0;
	public String imagePath;
	
	public GameObject() {
		this.position = new Vector2D();
		this.actions = new ArrayList<>();
		this.attributes = new ArrayList<>();
	}
	
	public void run() {
        this.attributes.forEach(attribute -> attribute.run(this));
        this.actions.removeIf(action -> action.run(this));
    }
	
	public void render(Graphics graphics) {
        if (this.renderer != null) {
            this.renderer.render(graphics, this.position);
        }
    }
    
    public void addAction(Action action) {
    	this.actions.add(action);
    }
	
	protected void isDied()	{
		if (this.position.x > 600 || this.position.y < 0 || this.position.y > 1024)
			this.isAlive = false;
	}
    
}
