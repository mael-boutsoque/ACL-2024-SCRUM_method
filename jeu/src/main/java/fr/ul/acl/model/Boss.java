package fr.ul.acl.model;


public class Boss extends Monstre{

	public Boss(int x,int y,int width,int height,int level, Entities entities){
		super(x,y,width,height,level, entities);
		this.level= level;
		this.health=(int) Math.round(25*Math.log(level)+1000);
		image_size = 64;
		image_path = "src/main/resources/bossr.png";
	    this.saved_images();

		this.health0 = 10000;
		this.health = health0;
		this.body_damage = 100;
		this.speed = 2;
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
