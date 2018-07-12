package game.enemy;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import renderer.ImageRenderer;

import physic.BoxCollider;

public class Enemy extends GameObject {

    public Vector2D velocity;
    private List<BulletEnemy> bulletEnemies;
    public BoxCollider boxCollider;

    public Enemy() {
        this.velocity = new Vector2D();
        this.bulletEnemies = new ArrayList<>();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
        this.boxCollider= new BoxCollider(20, 20);
    }

    @Override
    public void run() {
        super.run();

        Player player = GameObjectManager.instance.findPlayer();
        if (player != null) {
            Vector2D velocity = player.position
                    .subtract(this.position)
                    .normalize()
                    .multiply(1.5f);
            this.velocity.set(velocity);
        }
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
    }
}
