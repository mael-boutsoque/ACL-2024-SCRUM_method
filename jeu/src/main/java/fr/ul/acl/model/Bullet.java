package fr.ul.acl.model;

import java.awt.Graphics2D;

public class Bullet extends Entity {
    protected double angle;
    protected int speed;

    Bullet(int x, int y, int width, int height , double angle , int speed) {
        super(x, y, width, height);
        this.angle = angle;
        this.speed = speed;
        this.image = null;
        this.load_image();
    }

    public void draw(Graphics2D crayon){
        crayon.drawOval(this.get_x(),this.get_y(),width,height);
    }

    public void evolve(Entities entities) {
    	int dx = (int) (this.speed*Math.cos(angle));
        int dy = (int) (this.speed*Math.sin(angle));
        move(dx,dy,entities);
    }

    public void move(int x,int y,Entities entities){
    	if (this.can_move(x, y, entities)) {
            this.x += x;
            this.y += y;
            this.hitbox.move(this.get_x(),this.get_y());
    	}
    	else {
            this.is_dead = true;
    	}
    }

    
    
}
