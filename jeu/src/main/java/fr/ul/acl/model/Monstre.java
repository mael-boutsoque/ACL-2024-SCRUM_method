package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Monstre extends Entity{

	int health;
	int health0;
	int body_damage = 1;
	ArrayList<BufferedImage> images;
	int image_size = 10;
	int image_id = 0 ;
	public double speed;
	int x_rd;
	int y_rd;
	int t = 0;
	Entity target;

	
	public Monstre(int x,int y,int width,int height,int health, Entities entities){
		super(x,y,width,height);
		this.health0 = health;
		this.health = health;
		
		image_path = "src/main/resources/entity.png";
		images = new ArrayList<BufferedImage>();
	    this.load_image();
		
		this.target = entities.pathfinder.locate_closest_node_init(this);
	}

	public void draw(Graphics2D crayon){
		load_image();
		Image image_temp = get_image();
		crayon.drawImage(image_temp, this.get_x(), this.get_y(), this.get_width(), this.get_height(), null, null);
		if(this.show_hitbox) {
			//crayon.setColor(Color.blue);
			crayon.drawRect(this.hitbox.get_x(), this.hitbox.get_y(), this.hitbox.get_width(), this.hitbox.get_height());
		}


		crayon.setColor(Color.white);
		//crayon.drawRoundRect(this.get_x(), this.get_y()-heal_bar_height, this.get_width(),heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x(), this.get_y()-heal_bar_height, this.get_width(), heal_bar_height);
        crayon.setColor(Color.red);
        //crayon.drawRoundRect(this.get_x(), this.get_y()-heal_bar_height, this.get_width(), heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x()+1, this.get_y()-heal_bar_height+1, (int)(0.01*(this.get_width()-2)*(100*this.health/this.health0)), heal_bar_height-2);
	}
	

	public void load_image(){
        if (image_path==null){
            images = null;
        }
        else{
            try {
                image = (ImageIO.read(new File(image_path)).getSubimage((image_id/10)*image_size,0, 80, 64));
				image_id++;
				if(image_id>=7*10){
					image_id = 0;
				}
            }
            catch(IOException e) {
            System.err.println("image not load for "+this.getClass().getName());
            show_hitbox = true;
            }
        }
    }


	public void damage(int degats) {
		if (this.health <= 0) {
			this.is_dead = true;
		}
		else
			this.health -= degats;
	}
	
	/*
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
			entities.get_player().compteur += 1;
                if (entities.get_player().compteur%10==0){
                    entities.get_player().health_p = Math.max(entities.get_player().health_p-1,0);
                }
			return false;
		}
    	return true;
    }
	*/
	
	public void evolve(Entities entities) {
		
		Entity next_target;
		int d = (int) this.distance_to(target);
		//System.out.println(d);
		entities.pathfinder.set_current_pos(x, y);
		entities.pathfinder.set_target_pos(-entities.map.get_x() + 768, -entities.map.get_y() + 432);

		if (target == entities.get_player()) {
			if (t<20) {t++;}
			else {
				t = 0;
				if (entities.pathfinder.test_all()) {
					next_target = entities.pathfinder.locate_closest_node_init(this);
					entities.pathfinder.set_target_pos(next_target.x, next_target.y);
					if (entities.pathfinder.test_all()) {
						target = next_target;
					}
					else {
						target = entities.pathfinder.get_shortest_path_map().get(next_target);
					}
				}
			}
		}
		
		if (d<30) {
			next_target = entities.pathfinder.get_shortest_path_map().get(target);
		
			if (!entities.pathfinder.test_all()) {
				next_target = entities.get_player();
			}
			
			if (next_target != null){target = next_target;}
		}
		
		if (target == null || (target.x == 0 && target.y ==0)) {target = entities.pathfinder.locate_closest_node_init(this);}
		
		int dx = 0 , dy = 0;
		double x = this.get_x()+0.5*width;
		double y = this.get_y()+0.5*height;
		double px = target.get_x()+30;
		double py = target.get_y()+30;
		double angle = Math.atan2(px-x, py-y);
		dx = (int) (speed*Math.sin(angle));
		dy = (int) (speed*Math.cos(angle));
				
		this.move(dx,dy,entities);
	}
}
