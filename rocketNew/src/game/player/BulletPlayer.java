package game.player;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.Enemy;
import physic.BoxCollider;
import renderer.ImageRenderer;

public class BulletPlayer extends GameObject {

    public Vector2D velocity;
    public BoxCollider boxCollider;

    public BulletPlayer() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 8, 8);
        this.boxCollider= new BoxCollider(8, 8);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        Enemy enemy= GameObjectManager.instance.checkCollision(this);
        if (enemy != null)	{
        	enemy.isAlive= false;
        	this.isAlive= false;
        }
    }

}
