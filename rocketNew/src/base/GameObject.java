package base;
import java.awt.Graphics;
import java.util.*;

import renderer.Renderer;

public class GameObject {

    public Vector2D position;
    public Renderer renderer;
    public List<GameObjectAttributes> attributes;
    public int life= 1;
    public boolean isAlive= true;

    public GameObject() {
        this.position = new Vector2D();
        this.attributes= new ArrayList<>();
    }

    public void run() {
    	this.attributes.forEach(attribute -> attribute.run(this));
    }

    public void render(Graphics graphics) {
        if (this.renderer != null) {
            this.renderer.render(graphics, this.position);
        }
    }
}
