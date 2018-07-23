package render;

import javax.imageio.ImageIO;

import base.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRenderer implements Renderer {

    public BufferedImage image;
    public int width;
    public int height;

    public ImageRenderer(String path, int width, int height) {
        this.image = this.loadImage(path);
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        graphics.drawImage(this.image, (int) position.x - this.width / 2, (int) position.y - this.height / 2, this.width, this.height, null);
    }

    
    
    public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
