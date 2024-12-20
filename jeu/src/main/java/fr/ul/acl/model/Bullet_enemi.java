package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet_enemi extends Monstre{

	int health0 = 10;
	int dir_x;
	int dir_y;
	boolean gonadie = false;
	public Bullet_enemi(int x,int y,int width,int height,int health,int dir_x,int dir_y, Entities entities){
		super(x,y,width,height,health, entities);
		this.dir_x = dir_x;
		this.dir_y = dir_y;
		this.health = health;
		image_path = null;
	    this.load_image();

		this.health = health0;
		this.body_damage = (int) Math.round(15*Math.log(level)+3);
		this.speed=16;
	}
	
	public void evolve(Entities entities) {
		
		int dx = (int) (dir_x*speed/100);
		int dy = (int) (dir_y*speed/100);

		this.move(dx,0,entities);
		this.move(0,dy,entities);
		
	}
	
	public void move(int x,int y,Entities entities){
		this.x += x;
        this.y += y;
        this.hitbox.move(this.get_x(),this.get_y());
    	if (this.can_move(x, y, entities)) {
    	}
		else{
			if(gonadie){
				this.is_dead = true;
			}
			gonadie = true;
		}
    }
	
	public void draw(Graphics2D crayon){
		crayon.setColor(Color.gray);
        crayon.fillOval(this.get_x(),this.get_y(),width,height);
    }
}
