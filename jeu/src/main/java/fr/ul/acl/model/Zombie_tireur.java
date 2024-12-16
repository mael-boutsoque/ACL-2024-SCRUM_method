package fr.ul.acl.model;

public class Zombie_tireur extends Monstre{

	int counter = 0;
	int counter_max = 30;
	public Zombie_tireur(int x,int y,int width,int height,int level){
		super(x,y,width,height,level);
		this.level= level;
		this.health=(int) Math.round(25*Math.log(level)+10);
		image_size = 64;
		image_path = "src/main/resources/tireur.png";
	    this.saved_images();

		this.speed = 8;
		this.health0 = 10;
		this.health = health0;
		this.body_damage = 2;
		this.speed0 = this.speed;
	}
	
	public void evolve(Entities entities) {
		int plx = entities.get_player().get_x();
		int ply = entities.get_player().get_y();
		int dx = 0 , dy = 0;

		if(entities.get_player().get_x()>this.get_x()+0.5*width){
			dx = speed*x_rd/100;
		}
		else dx = -speed*x_rd/100;

		if(entities.get_player().get_y()>this.get_y()+0.5*height){
			dy = speed*y_rd/100;
		}
		else dy = -speed*y_rd/100;

		double distance = Math.sqrt(Math.pow(plx-get_x(), 2) + Math.pow(ply-get_y(), 2));
		if(distance>500){
			this.move(dx,0,entities);
			this.move(0,dy,entities);
		}
		else if(distance<400){
			this.move((int) (-dx/(1+distance)),0,entities);
			this.move(0,(int) (-dy/(1+distance)),entities);
		}

		if(counter>=counter_max){
			counter = 0;
			int px = entities.get_player().get_x() - this.get_x();
			int py = entities.get_player().get_y() - this.get_y();
			double angle = -Math.atan2(px,py) + 3.14/2;
			entities.add_enemi(new Bullet_enemi(this.get_x()+this.get_width()/2,this.get_y()+this.get_height()/2,20,20,1,(int)(Math.cos(angle)*100),(int)(Math.sin(angle)*100)));
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
