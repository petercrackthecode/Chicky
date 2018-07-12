package game.background;
import java.awt.*;

import base.GameObject;
import renderer.BackgroundRenderer;

public class Background extends GameObject {
    public Background() {
        this.renderer = new BackgroundRenderer(
                Color.BLACK,
                1024,
                600
        );
    }
}
