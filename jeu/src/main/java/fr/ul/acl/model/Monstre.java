package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Monstre extends Entity{

	int health;
	int health0;
	int body_damage = 1;
	public Monstre(int x,int y,int width,int height,int health){
		super(x,y,width,height);
		this.health0 = health;
		this.health = health;
		   image_path = "src/main/resources/entity.png";
	        this.load_image();
	}

	public void draw(Graphics2D crayon){
		super.draw(crayon);
		crayon.setColor(Color.white);
		//crayon.drawRoundRect(this.get_x(), this.get_y()-heal_bar_height, this.get_width(),heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x(), this.get_y()-heal_bar_height, this.get_width(), heal_bar_height);
        crayon.setColor(Color.red);
        //crayon.drawRoundRect(this.get_x(), this.get_y()-heal_bar_height, this.get_width(), heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x()+1, this.get_y()-heal_bar_height+1, (int)(0.01*(this.get_width()-2)*(100*this.health/this.health0)), heal_bar_height-2);
	}
	
	public void damage(int degats) {
		if (this.health <= 0) {
			this.is_dead = true;
		}
		else
			this.health -= degats;
	}

	public boolean can_move(int x, int y,Entities entities) {
    	hitboxTemp = new Hitbox(this.get_x()+x, this.get_y()+y , this.get_width(), this.get_height());

		for(int i=0;i<entities.obstacles.size();i++) {
			if(entities.obstacles.get(i).get_hitbox().colide(hitboxTemp) && entities.obstacles.get(i).is_colidable && entities.obstacles.get(i) != this) {
				this.on_collision(entities);
                return false;
			}
		}
		if(entities.get_player().get_hitbox().colide(hitboxTemp)){
			this.on_collision(entities);
			damage(1);
			entities.get_player().damage(body_damage);
			return false;
		}
    	return true;
    }
	
		
}
