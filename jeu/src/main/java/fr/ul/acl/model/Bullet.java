package fr.ul.acl.model;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import fr.ul.acl.model.upgrades.Boutton;

public class Bullet extends Entity {
    protected double angle;
    protected int speed;
    protected int power;
    protected String element;
    private ArrayList<Boutton> bouttons_effet;

    Bullet(int x, int y, int width, int height , double angle , int speed , int power, String element) {
        super(x, y, width, height);
        this.angle = angle;
        this.speed = speed;
        this.power = power;
        this.image = null;
        this.element = element;
        this.load_image();
    }

    public void draw(Graphics2D crayon){
        if (element.equals("Glace")){
            crayon.setColor(Color.blue);
        }
        else if (element.equals("Feux")){
            crayon.setColor(Color.red);
        }
        else {
            crayon.setColor(Color.BLACK);
        }
        crayon.fillOval(this.get_x(),this.get_y(),width,height);
    }

    public void draw_effet(Graphics2D crayon, String couleur) {
        if (couleur.equals("red")){
            crayon.setColor(Color.red);
        }
        else if (couleur.equals("blue")){
            crayon.setColor(Color.blue);
        }
        else {
            crayon.setColor(Color.black);
        }
        crayon.fillRect(1250, 500, 250, 80);
        String texte = "Balle";
        FontMetrics metrics = crayon.getFontMetrics();
        int txt_x = 1250 + (250 -  metrics.stringWidth(texte))/2;
        int txt_y = 50 + ((80 -  metrics.getHeight())/2) + metrics.getAscent();
        crayon.drawString(texte, txt_x, txt_y);

        Boutton boutton = new Boutton(1250, 100, 200, 200, null);
        boutton.draw(crayon);
        boutton = null;
    }

    public String get_element(){
        return this.element;
    }

    public void evolve(Entities entities) {
    	int dx = (int) (this.speed*Math.cos(angle));
        int dy = (int) (this.speed*Math.sin(angle));
        move(dx,dy,entities);
    }

    public boolean can_move(int x, int y,Entities entities) {
    	hitboxTemp = new Hitbox(this.get_x()+x, this.get_y()+y , this.get_width(), this.get_height());
		for(int i=0;i<entities.obstacles.size();i++) {
			if(entities.obstacles.get(i).get_hitbox().colide(hitboxTemp) && entities.obstacles.get(i).is_colidable && entities.obstacles.get(i) != this) {
				this.on_collision(entities);
                this.is_dead = true;
                return false;
			}
		}
        for(int i=0;i<entities.enemies.size();i++) {
			if(entities.enemies.get(i).get_hitbox().colide(hitboxTemp) && entities.enemies.get(i).is_colidable && entities.enemies.get(i) != this) {
                this.on_collision(entities);
                this.is_dead = true;
                if (element.equals("Glace") && !(entities.get_enemi(i) instanceof Bullet_enemi)){
                    entities.get_enemi(i).immobile();
                }
				else if (element.equals("Feux")&& !(entities.get_enemi(i) instanceof Bullet_enemi)){
                    entities.get_enemi(i).feux(entities.get_enemi(i).level);
                }
                else if( !(entities.get_enemi(i) instanceof Bullet_enemi)){
                    entities.get_enemi(i).damage(power);
                }
                return false;
			}
		}
    	return true;
    }

    public void move(int x,int y,Entities entities){
    	if (this.can_move(x, y, entities)) {
            this.x += x;
            this.y += y;
            this.hitbox.move(this.get_x(),this.get_y());
    	}
    }
}
