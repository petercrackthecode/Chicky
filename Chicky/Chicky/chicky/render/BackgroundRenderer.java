package render;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.Vector2D;

public class BackgroundRenderer implements Renderer {
	private BufferedImage image;
	private int width, height;
	
	public BackgroundRenderer(String path, int width, int height)	{
		this.image= this.loadImage(path);
		this.width= width;
		this.height= height;
	}

	@Override
	public void render(Graphics graphics, Vector2D position) {
		System.out.println("Image did appear");
		graphics.drawImage(this.image, (int) position.x, (int) position.y, this.width, this.height, null);
	}
	
	private BufferedImage loadImage(String path) {
        try {
        	System.out.println("OK");
            return ImageIO.read(new File(path));
        } catch (IOException e) {
        	System.out.println("wrong image path");
            return null;
        }
    }
}
