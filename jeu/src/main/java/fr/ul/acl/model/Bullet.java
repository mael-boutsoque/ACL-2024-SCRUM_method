package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Entity {
    protected double angle;
    protected int speed;
    protected int power;

    Bullet(int x, int y, int width, int height , double angle , int speed , int power) {
        super(x, y, width, height);
        this.angle = angle;
        this.speed = speed;
        this.power = power;
        this.image = null;
        this.load_image();
    }

    public void draw(Graphics2D crayon){
        crayon.setColor(Color.BLACK);
        crayon.fillOval(this.get_x(),this.get_y(),width,height);
    }

    public void evolve(Entities entities) {
    	int dx = (int) (this.speed*Math.cos(angle));
        int dy = (int) (this.speed*Math.sin(angle));
        move(dx,dy,entities);
    }

    public boolean can_move(int x, int y,Entities entities) {
    	hitboxTemp = new Hitbox(this.get_x()+x, this.get_y()+y , this.get_width(), this.get_height());
		for(int i=0;i<entities.obstacles.size();i++) {
			if(entities.obstacles.get(i).get_hitbox().colide(hitboxTemp) && entities.obstacles.get(i).is_colidable && entities.obstacles.get(i) != this) {
				this.on_collision(entities);
                this.is_dead = true;
                return false;
			}
		}
        for(int i=0;i<entities.enemies.size();i++) {
			if(entities.enemies.get(i).get_hitbox().colide(hitboxTemp) && entities.enemies.get(i).is_colidable && entities.enemies.get(i) != this) {
				this.on_collision(entities);
                entities.get_enemi(i).damage(power);
                this.is_dead = true;
                return false;
			}
		}
    	return true;
    }

    public void move(int x,int y,Entities entities){
    	if (this.can_move(x, y, entities)) {
            this.x += x;
            this.y += y;
            this.hitbox.move(this.get_x(),this.get_y());
    	}
    }

    
    
}
