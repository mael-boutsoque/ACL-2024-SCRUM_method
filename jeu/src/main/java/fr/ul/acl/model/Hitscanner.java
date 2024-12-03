package fr.ul.acl.model;
import java.util.ArrayList;
import java.util.List;

public class Hitscanner {
	
	private int curr_x;
	private int target_x;
	private int curr_y;
	private int target_y;
	public List<Entity> liste_obstacles;
	
	public Hitscanner(ArrayList<Entity> obstacles) {
		this.liste_obstacles = obstacles;
	}
	
	public Hitscanner() {}

	//	public void get_entities(ArrayList<Entity> liste) {
//		this.liste_obstacles = liste;
//		int len = this.liste_obstacles.size();
//		Entity player = this.liste_obstacles.remove(len);
//		this.target_x = player.x + player.width/2;
//		this.target_x = player.y - player.height/2;
//	}
	public void set_current_pos(int x, int y) {
		this.curr_x = x;
		this.curr_y = y;
	}
	
	public void set_target_pos(int x, int y) {
		this.target_x = x;
		this.target_y = y;
	}
	
	public boolean test_intersect(Entity obstacle) {
		
		int h_min = obstacle.y;
		int h_max = obstacle.y + obstacle.height;
		int l_min = obstacle.x;
		int l_max = obstacle.x + obstacle.width;
		
		/*
		System.out.println("test");
		System.out.println("h_max = " + Integer.toString(h_max));
		System.out.println("h_min = " + Integer.toString(h_min));
		System.out.println("l_max = " + Integer.toString(l_max));
		System.out.println("l_min = " + Integer.toString(l_min));
		System.out.println("curr_y = " + Integer.toString(curr_y));
		System.out.println("target_y = " + Integer.toString(target_y));
		System.out.println("curr_x = " + Integer.toString(curr_x));
		System.out.println("target_x = " + Integer.toString(target_x));
		System.out.println();
		*/
		
		if (this.target_y > h_max && this.curr_y > h_max) {return false;}
		if (this.target_y < h_min && this.curr_y < h_min) {return false;}
		if (this.target_x > l_max && this.curr_x > l_max) {return false;}
		if (this.target_x < l_min && this.curr_x < l_min) {return false;}
		
		if (this.target_x == this.curr_x) {
			if (this.target_x > l_max || this.target_x < l_min) {return false;}
			else {return true;}
		}
		
		else if (this.target_y == this.curr_y) {
			if (this.target_y > h_max || this.target_y < h_min) {return false;}
			else {return true;}
		}
		else {
			
			float a = (float) (this.target_y - this.curr_y)/(this.target_x - this.curr_x);
			float b = (float) this.target_y - a*this.target_x;
			
			if (a*l_min + b < h_max && a*l_min + b > h_min) {return true;}
			if (a*l_max + b < h_max && a*l_max + b > h_min) {return true;}
			if ((h_min-b)/a < l_max && (h_min-b)/a >l_min) {return true;}
			if ((h_max-b)/a < l_max && (h_max-b)/a >l_min) {return true;}
			else {return false;}
		}
	}
	
	public boolean test_all() {
		for (Entity wall:this.liste_obstacles) {
			if (wall.is_colidable && test_intersect(wall)) {return true;}
		}
		return false;
	}
}
