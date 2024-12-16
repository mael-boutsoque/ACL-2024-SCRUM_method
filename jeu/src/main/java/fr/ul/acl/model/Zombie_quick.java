package fr.ul.acl.model;


public class Zombie_quick extends Monstre{

	public Zombie_quick(int x,int y,int width,int height,int level, Entities entities){
		super(x,y,width,height,level, entities);
		this.level= level;
		this.health=(int) Math.round(25*Math.log(level)+5);
		this.health0 = this.health;
		this.speed = (int) Math.round(10*Math.log(level)+10);;
		this.speed = 6;
		this.speed0 = this.speed;
		
		image_size = 64;
		image_path = "src/main/resources/zombie2.png";
	    this.saved_images();


	}
	
	public void evolve(Entities entities) {
		
		this.move_using_graphe(entities);
	}
	
	public void move(int x,int y,Entities entities){
    	if (this.can_move(x, y, entities)) {
        this.x += x;
        this.y += y;
        this.hitbox.move(this.get_x(),this.get_y());
    	}
    }		
}
