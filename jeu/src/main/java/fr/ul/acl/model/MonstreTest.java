package fr.ul.acl.model;

public class MonstreTest extends Monstre{

	int health;
	private int speed;
    private double angle = 1;
    private Gun gun;
    private boolean is_shooting;
	public MonstreTest(int x,int y,int width,int height,int health){
		super(x,y,width,height,health);
		this.health = health;
		image_path = "src/main/resources/OuroudjSama.png";
	    this.load_image();

		this.gun = new Gun();
	}
	
	public void evolve(Entities entities) {
		
		int dx = 0 , dy = 0;
		if(entities.get_player().get_x()+30>this.get_x()+0.5*width){
			dx = 1;
		}
		else dx = -1;

		if(entities.get_player().get_y()+30>this.get_y()+0.5*height){
			dy = 1;
		}
		else dy = -1;

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
