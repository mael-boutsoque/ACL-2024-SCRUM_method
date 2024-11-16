package fr.ul.acl.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {
    // position et dimension
    protected int x;
    protected int y;
    protected int x_relative = 0;
    protected int y_relative = 0;
    protected int height = 100;
    protected int width = 100;
    protected boolean is_colidable = true;
    protected boolean is_dead = false;
    protected int health;
    Hitbox hitbox;
    Hitbox hitboxTemp;

    // image
    protected String image_path;
    protected BufferedImage image;

    protected int heal_bar_height = 10;

    // debug
    public boolean show_hitbox = false;

    Entity(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        image_path = "src/main/resources/entity.png";
        load_image();
        this.show_hitbox = false;
        this.load_hitbox();
        this.health = 9999;
    }

    /*
     * bouge le l'entitee sur la carte
     */
    public void move(int x,int y,Entities entities){
        this.x += x;
        this.y += y;
        this.hitbox.move(this.get_x(),this.get_y());
    }

    /*
     * bouge l'entitee en fonction de la position du joueur (utilisé dans )
     */
    public void move_relative(int x,int y,Entities entities){
        this.x_relative += x;
        this.y_relative += y;
        this.hitbox.move(this.get_x(),this.get_y());
    }

    public void draw(Graphics2D crayon){
        crayon.drawImage(this.get_image(), this.get_x(), this.get_y(), this.get_width(), this.get_height(), null, null);
        if(this.show_hitbox) {
            //crayon.setColor(Color.blue);
            crayon.drawRect(this.hitbox.get_x(), this.hitbox.get_y(), this.hitbox.get_width(), this.hitbox.get_height());
        }
    }

    protected Hitbox get_hitbox() {
		return this.hitbox;
	}

	public int get_x(){
        return x + x_relative;
    }
    public int get_y(){
        return y + y_relative;
    }

    public void load_image(){
        if (image_path==null){
            image = null;
        }
        else{
            try {
                image = ImageIO.read(new File(image_path));
            }
            catch(IOException e) {
            System.err.println("image not load for "+this.getClass().getName());
            }
        }
    }

    public Image get_image() {
        return image;
    }

    public int get_width(){
        return width;
    }

    public int get_height(){
        return height;
    }

    public int get_speed() {
        throw new UnsupportedOperationException("this entity have no speed");
    }

    public boolean colide(Entity entity2) {
    	return this.hitbox.colide(entity2.hitbox);
    }

    public boolean colidable(){
        return is_colidable;
    }
    public void load_hitbox() {
    	this.hitbox = new Hitbox(this.get_x(),this.get_y(),this.get_width(),this.get_height());
    }
    
    public void evolve(Entities entities) {
    	
    }

    public void on_collision(Entities entities){

    }

}
