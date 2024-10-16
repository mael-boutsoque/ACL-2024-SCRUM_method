package model;

public class MonstreTest extends Entity{
	MonstreTest(int x,int y,int width,int height){
		super(x,y,width,height);
		   image_path = "model\\images\\OuroudjSama.png";
	        this.load_image();
	}
	
	public void evolve(Entities entities) {
		
		int dx = 0 , dy = 0;
		if(entities.get_player().get_x()>this.get_x()){
			dx = 1;
		}
		else dx = -1;

		if(entities.get_player().get_y()>this.get_y()){
			dy = 1;
		}
		else dy = -1;

		this.move(dx,0,entities);
		this.move(0,dy,entities);
		
	}
		
}
