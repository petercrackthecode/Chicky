package render;
import java.awt.*;
import javax.vecmath.*;

import base.Vector2D;

public interface Renderer {
	
	void render(Graphics graphics, Vector2D position);
}
