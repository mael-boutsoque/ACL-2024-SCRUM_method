package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class Player extends Entity implements MouseInputListener {
    private int speed;
    private double angle = 1;
    private Gun gun;
    private Entities entities;
    private boolean is_shooting;
    private int xp=0;
    private int xp_to_next_lvl = 100;

    Player(int x , int y , Entities entities){
        super(x - 70, y - 70 , 80 , 80);
        this.entities = entities;

        //chargement image
        image_path = "src/main/resources/player.png";
        this.load_image();
        this.show_hitbox = false;

        // stats
        this.speed = 2;

        this.gun = new Gun();
    }

    public void draw(Graphics2D crayon){
        if(this.show_hitbox) {
			crayon.drawRect(this.hitbox.get_x(), this.hitbox.get_y(), this.hitbox.get_width(), this.hitbox.get_height());
			crayon.drawRect(this.x, this.y, this.get_width(), this.get_height());
		}
        this.xp++;
		crayon.setColor(Color.white);
		crayon.drawRoundRect(this.get_x(), this.y-heal_bar_height, this.get_width(),heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x(), this.get_y()-heal_bar_height, this.get_width(), heal_bar_height);
        crayon.setColor(Color.green);
        crayon.drawRoundRect(this.get_x(), this.y-heal_bar_height, this.get_width(), heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x()+1, this.get_y()-heal_bar_height+1, (int)(0.01*(this.get_width()-2)*(100*this.xp/this.xp_to_next_lvl)), heal_bar_height-2);

        int correction = 40;
		crayon.translate(this.get_x()+this.get_width()/2, this.get_y()+this.get_height()/2);
		crayon.rotate(this.get_angle());
		crayon.translate(-(this.get_width()+correction)/2,-(this.get_height()+correction)/2);
		crayon.drawImage(this.get_image(), 0, 0, this.get_width()+correction, this.get_height()+correction, null, null);
		if(this.xp > this.xp_to_next_lvl) {
			this.xp = 0;
		}
		
    }

    public boolean can_move(int x, int y,Entities entities) {
    	hitboxTemp = new Hitbox(this.get_x()+x, this.get_y()+y , this.get_width(), this.get_height());
		for(int i=0;i<entities.obstacles.size();i++) {
			if(entities.obstacles.get(i).get_hitbox().colide(hitboxTemp) && entities.obstacles.get(i).is_colidable && entities.obstacles.get(i) != this) {
				this.on_collision(entities);
                return false;
			}
		}
    	return true;
    }

    public void move(int x,int y,Entities entities){
        this.move_relative(x, y, entities);
    }

    public void move_relative(int i,int j,Entities entities){
    	entities.player_move(i, j, entities);
    }

    public void evolve(Entities entities) {
        if(is_shooting) this.shoot(this.entities);
    	this.gun.update();
    }

    public void damage(int degats){
        System.out.println("Pas de fonction Player.damage()");
    }

    public int get_speed() {
        return speed;
    }

    public double get_angle(){
        return angle;
    }

    private void update_angle(MouseEvent e){
        int mx= e.getX() - this.x - this.get_width()/2;
        int my= e.getY() - this.y - this.get_height()/2;
        if(mx==0 && my<0){
            angle=0;
        }
        else{
            angle = -Math.atan2(mx,my)-3.2 ;
        }
    }

    private void shoot(Entities entities){
        this.gun.try_to_shoot(angle - 1.5 , entities);
    }

    public Gun get_gun(){
        return gun;
    }
    
    public void change_shooting_state(){
         is_shooting=!is_shooting;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.update_angle(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.update_angle(e);
    }

//Inutile
    @Override
public void mousePressed(MouseEvent e) {
}
@Override
public void mouseReleased(MouseEvent e) {
}
@Override
public void mouseClicked(MouseEvent e) {
}
@Override
public void mouseEntered(MouseEvent e) {
}

@Override
public void mouseExited(MouseEvent e) {
}
}















