package model;

public class Hitbox {
	int x;
	int y;
	int width;
	int height;
	Hitbox(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int get_x(){
		return x;
	}
	public int get_y(){
		return y;
	}
	public int get_width(){
		return width;
	}
	public int get_height(){
		return height;
	}
	public boolean colide(Hitbox entity2){
        if(this.get_x()<entity2.get_x()+entity2.width 
        		&& this.get_x()+this.width > entity2.get_x()
        		&& this.get_y() < entity2.get_y()+entity2.height
        		&& this.height+this.get_y() > entity2.get_y()) {return true;}
        return false;
    }
	
	public void move(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	
}
