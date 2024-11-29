package fr.ul.acl.model;


public class Zombie_quick extends Monstre{

	public Zombie_quick(int x,int y,int width,int height,int health){
		super(x,y,width,height,health);
		this.health = health;
		image_size = 64;
		image_path = "src/main/resources/zombie2.png";
	    this.load_image();

		this.health0 = 10;
		this.speed = 10;
		this.health = health0;
		this.body_damage = 2;
		this.speed = 6;
	}
	
	public void evolve(Entities entities) {
		
		int dx = 0 , dy = 0;
		if(entities.get_player().get_x()+30>this.get_x()+0.5*width){
			dx = speed*x_rd/100;
		}
		else dx = -speed*x_rd/100;

		if(entities.get_player().get_y()+30>this.get_y()+0.5*height){
			dy = speed*y_rd/100;
		}
		else dy = -speed*y_rd/100;

		this.move(dx,0,entities);
		this.move(0,dy,entities);
	}
	
	public void move(int x,int y,Entities entities){
    	if (this.can_move(x, y, entities)) {
        this.x += x;
        this.y += y;
        this.hitbox.move(this.get_x(),this.get_y());
    	}
    }		
}