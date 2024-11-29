package fr.ul.acl.model;

import java.awt.Graphics2D;

public class Bullet_enemi extends Monstre{

	int health0 = 10;
	int dir_x;
	int dir_y;
	public Bullet_enemi(int x,int y,int width,int height,int health,int dir_x,int dir_y){
		super(x,y,width,height,health);
		this.dir_x = dir_x;
		this.dir_y = dir_y;
		this.health = health;
		image_path = null;
	    this.load_image();

		this.health = health0;
		this.body_damage = 3;
		this.speed=10;
	}
	
	public void evolve(Entities entities) {
		
		int dx = dir_x*speed/100 , dy = dir_y*speed/100 ;

		this.move(dx,0,entities);
		this.move(0,dy,entities);
		
	}
	
	public void move(int x,int y,Entities entities){
    	if (this.can_move(x, y, entities)) {
        this.x += x;
        this.y += y;
        this.hitbox.move(this.get_x(),this.get_y());
    	}
		else{
			this.is_dead = true;
		}
    }
	
	public void draw(Graphics2D crayon){
        crayon.fillOval(this.get_x(),this.get_y(),width,height);
    }
}
