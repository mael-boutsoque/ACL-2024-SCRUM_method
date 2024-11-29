package fr.ul.acl.model;


public class Zombie_tireur extends Monstre{

	int health0 = 10;
	int counter = 0;
	int counter_max = 100;
	public Zombie_tireur(int x,int y,int width,int height,int health){
		super(x,y,width,height,health);
		this.health = health;
		image_size = 64;
		image_path = "src/main/resources/tireur.png";
	    this.load_image();

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

		if(counter>=counter_max){
			counter = 0;
			int px = entities.get_player().get_x() - this.get_x();
			int py = entities.get_player().get_y() - this.get_y();
			double angle = Math.atan2(px,py);
			entities.add_enemi(new Bullet_enemi(x,y,20,20,1,(int)(Math.cos(angle)*100),(int)(Math.sin(angle)*100)));
		}
		else counter++;
	}
	
	public void move(int x,int y,Entities entities){
    	if (this.can_move(x, y, entities)) {
        this.x += x;
        this.y += y;
        this.hitbox.move(this.get_x(),this.get_y());
    	}
    }		
}
