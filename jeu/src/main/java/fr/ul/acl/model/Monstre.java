package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Monstre extends Entity{

	int health;
	int health0;
	int body_damage = 1;
	ArrayList<BufferedImage> images;
	int image_size = 10;
	int image_id = 0 ;
	int speed = 2;
	int x_rd;
	int y_rd;
	public Monstre(int x,int y,int width,int height,int health){
		super(x,y,width,height);
		this.health0 = health;
		this.health = health;
		image_path = "src/main/resources/entity.png";
		images = new ArrayList<BufferedImage>();
	    this.load_image();

		Random randomNumbers = new Random();
		x_rd = 100-randomNumbers.nextInt(20);
		y_rd = 100-randomNumbers.nextInt(20);
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
	
		
}
