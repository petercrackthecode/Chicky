package background;

import base.GameObject;
import render.BackgroundRenderer;

public class Background extends GameObject{
	public Background()	{
		this.renderer = new BackgroundRenderer(
				"image/Background.png",
				1024,
				600
				);
	}
}
