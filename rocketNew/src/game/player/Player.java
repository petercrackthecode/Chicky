package game.player;
import java.awt.*;

import base.GameObject;
import base.Vector2D;
import renderer.PolygonRenderer;

public class Player extends GameObject {
    public Vector2D velocity;
    public double angle = 0.0;
    public int life= 3;

    public Player() {
        this.renderer = new PolygonRenderer(
                Color.RED,
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20, 8)
        );
        this.velocity = new Vector2D(3.5f, 0);
        this.attributes.add(new PlayerShoot());
        this.attributes.add(new PlayerMove());
    }

    @Override
    public void run() {
        super.run();
        ((PolygonRenderer) this.renderer).angle = this.angle;
    }

}
