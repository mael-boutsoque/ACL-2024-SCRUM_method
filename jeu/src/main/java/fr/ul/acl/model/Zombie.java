package fr.ul.acl.model;


public class Zombie extends Monstre{

	int health0 = 30;
	public Zombie(int x,int y,int width,int height,int health, Entities entities){
		super(x, y, width, height, health, entities);
		this.health = health;
		image_size = 80;
		image_path = "src/main/resources/zombie.png";
	    this.load_image();

		this.health = health0;
		this.body_damage = 6;
		this.speed = 6;
	}
	
		
}
