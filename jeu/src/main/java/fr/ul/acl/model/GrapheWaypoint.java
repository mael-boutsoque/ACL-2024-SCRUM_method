package fr.ul.acl.model;

import java.awt.Graphics2D;

public class GrapheWaypoint extends Entity{
	
	public GrapheWaypoint(int x, int y){
		super(x, y, 0, 0);
		this.is_colidable = true;
	}
	
	public void draw(Graphics2D crayon) {
		crayon.drawImage(this.get_image(), this.get_x(), this.get_y(), 100, 100, null, null);
	}
}
