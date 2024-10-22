package fr.ul.acl.model;

public class MonstreTest extends Entity{
	MonstreTest(int x,int y,int width,int height){
		super(x,y,width,height);
		   image_path = "images\\entity.png";
	        this.load_image();
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
		
}
