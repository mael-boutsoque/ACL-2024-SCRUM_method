package fr.ul.acl.model;


public class Zombie_quick extends Monstre{

	int health0 = 10;
	public Zombie_quick(int x,int y,int width,int height,int health, Entities entities){
		super(x, y, width, height, health, entities);
		this.health = health;
		image_size = 64;
		image_path = "src/main/resources/zombie2.png";
	    this.load_image();

		this.health = health0;
		this.body_damage = 2;
		this.speed = 10;
	}		
}
