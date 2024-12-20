package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;

import javax.swing.event.MouseInputListener;
import fr.ul.acl.model.upgrades.Menu;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.net.URL;


public class Player extends Entity implements MouseInputListener {
    private int speed;
    private double angle = 1;
    private Gun gun;
    private Entities entities;
    private boolean is_shooting;
    protected int xp = 0;
    private int xp_to_next_lvl = 5;
    protected int lvl = 0;
    private char[] affichage_lvl = {'L','v','l',' ','x','y'};
    protected int health_p=20;
    protected int health0_p=20;
    protected int body_damage_p=1;
    private char[] affichage_vie = {'V','i','e',' ','x','y'};
    int compteur = 0;

    Clip clip;
    URL soundURL[] = new URL[5];


    Player(int x , int y , Entities entities , int health_p){
        super(x, y, 80 , 80);
        //super(x - 70, y - 70 , 80 , 80);
        this.entities = entities;

        this.health_p = health_p;
        this.health0_p = health_p;

        //chargement image
        image_path = "src/main/resources/player.png";
        this.load_image();
        this.show_hitbox = false;

        // stats
        this.speed = 2;
        this.gun = new Gun();

        // Sound effects
        soundURL[0] = getClass().getResource("/lvl_up_sound.wav");

        setFile(0);

    }

    public void draw(Graphics2D crayon){
        if(this.show_hitbox) {
			crayon.drawRect(this.hitbox.get_x(), this.hitbox.get_y(), this.hitbox.get_width(), this.hitbox.get_height());
			crayon.drawRect(this.x, this.y, this.get_width(), this.get_height());
		}
        //Expérience
		crayon.setColor(Color.white);
		affichage_lvl[4] = this.intToChar(this.lvl/10);
        affichage_lvl[5] = this.intToChar(this.lvl%10);
		crayon.drawChars(affichage_lvl,0,affichage_lvl.length,this.get_x()-40,this.get_y());
		crayon.drawRoundRect(this.get_x(), this.y-heal_bar_height, this.get_width(),heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x(), this.get_y()-heal_bar_height, this.get_width(), heal_bar_height);
        crayon.setColor(Color.green);
        crayon.drawRoundRect(this.get_x(), this.y-heal_bar_height, this.get_width(), heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x()+1, this.get_y()-heal_bar_height+1, (int)(0.01*(this.get_width()-2)*(100*this.xp/this.xp_to_next_lvl)), heal_bar_height-2);

        //Niveau de vie
        crayon.setColor(Color.white);
        affichage_vie[4] = this.intToChar(this.health_p/10);
        affichage_vie[5] = this.intToChar(this.health_p%10);
		crayon.drawChars(affichage_vie,0,affichage_vie.length,this.get_x()-40,this.get_y()-20);
		crayon.drawRoundRect(this.get_x(), this.y-heal_bar_height-20, this.get_width(),heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x(), this.get_y()-heal_bar_height-20, this.get_width(), heal_bar_height);
        crayon.setColor(Color.red);
        crayon.drawRoundRect(this.get_x(), this.y-heal_bar_height-20, this.get_width(), heal_bar_height, 10, 10);
        crayon.fillRect(this.get_x()+1, this.get_y()-heal_bar_height+1-20, (int)(0.01*(this.get_width()-2)*(100*this.health_p/this.health0_p)), heal_bar_height-2);

        //Effet de la balle
        crayon.setColor(Color.black);
        crayon.fillRect(50, 750, 150, 50);
        crayon.setColor(Color.white);
        crayon.setFont(new Font("SansSerif", Font.PLAIN, 20));
        String effet_txt = "METAL";
        if (this.gun.effet.equals("Glace")){
            crayon.setColor(Color.cyan);
            crayon.fillRect(50, 750, 150, 50);
            crayon.setColor(Color.black);
            crayon.setFont(new Font("SansSerif", Font.PLAIN, 20));
            effet_txt = "GLACE";
        }
        else if (this.gun.effet.equals("Feux")){
            crayon.setColor(Color.red);
            crayon.fillRect(50, 750, 150, 50);
            crayon.setColor(Color.black);
            crayon.setFont(new Font("SansSerif", Font.PLAIN, 20));
            effet_txt = "FEUX";
        }

        FontMetrics effet_metrics = crayon.getFontMetrics();
        int effet_txt_x = 50 + (150 -  effet_metrics.stringWidth(effet_txt))/2;
        int effet_txt_y = 750 + ((50 -  effet_metrics.getHeight())/2) + effet_metrics.getAscent();
        crayon.drawString(effet_txt, effet_txt_x, effet_txt_y);

        int correction = 40;
		crayon.translate(this.get_x()+this.get_width()/2, this.get_y()+this.get_height()/2);
		crayon.rotate(this.get_angle());
		crayon.translate(-(this.get_width()+correction)/2,-(this.get_height()+correction)/2);
		crayon.drawImage(this.get_image(), 0, 0, this.get_width()+correction, this.get_height()+correction, null, null);	
    }
    private char intToChar(int i){
	    String s = ""+i;
	    return s.charAt(0);
    }

    public void damage(int degats){
        //coder autre part
    }

    public boolean can_move(int x, int y,Entities entities) {
    	hitboxTemp = new Hitbox(this.get_x()+x, this.get_y()+y , this.get_width(), this.get_height());
		for(int i=0;i<entities.obstacles.size();i++) {
			if(entities.obstacles.get(i).get_hitbox().colide(hitboxTemp) && entities.obstacles.get(i).is_colidable && entities.obstacles.get(i) != this) {
				this.on_collision(entities);
                return false;
			}
		}
        for (int i=0;i<entities.enemies.size();i++){
            if(entities.enemies.get(i).get_hitbox().colide(hitboxTemp) && entities.enemies.get(i).is_colidable && entities.enemies.get(i) != this){
                this.on_collision(entities);
                compteur += 1;
                if (compteur%10==0){
                    this.health_p = this.health_p-1;
                    System.out.println("health : [pv = +"+String.valueOf(this.health_p)+"]");
                }

                //return false;
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

    public void evolve(Entities entities , Menu menu) {
        if(this.health_p<=0) System.exit(0);
        
        if(is_shooting) this.shoot(this.entities);
    	this.gun.update();

        if(this.xp > this.xp_to_next_lvl) {
            clip.stop();
            PlayMusic(0);
			this.xp = 0;
			this.lvl++;
            resetPlayer();
            menu.open();
            
		}

    }

    
    public void add_life(int value) {
        this.health_p += value;
        System.out.println("Vie : [Vie = "+String.valueOf(this.health_p)+"]");
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

    //REINITIALISER ET AUGMENTER LES STATS POUR CHAQUE VAGUES
    public void resetPlayer(){
        this.xp_to_next_lvl = (int) Math.round(20*Math.log(this.lvl)+5);
        this.health_p = (int) Math.round(15*Math.log(this.lvl)+20);
        this.health0_p = this.health;
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
    public void setFile(int i){
        //System.out.println(soundURL[i]);
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            System.out.println("error 99");
        }

    }

    public void PlayMusic(int i){
        setFile(i);
        clip.start();
    }

    public void loop(int i){
        setFile(i);
        System.out.println(clip);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}















