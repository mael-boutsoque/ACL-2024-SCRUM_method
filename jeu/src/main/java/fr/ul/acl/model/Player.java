package fr.ul.acl.model;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class Player extends Entity implements MouseInputListener {
    private int speed;
    private double angle = 1;

    Player(int x , int y){
        super(x - 70, y - 70 , 80 , 80);

        //chargement image
        image_path = "images\\player.png";
        this.load_image();
        //this.show_hitbox = true;

        // stats
        this.speed = 2;
    }

    public void draw(Graphics2D crayon){
        if(this.show_hitbox) {
			//crayon.setColor(Color.BLUE);
			crayon.drawRect(this.hitbox.get_x(), this.hitbox.get_y(), this.hitbox.get_width(), this.hitbox.get_height());
		}

        int correction = 40;
		crayon.translate(this.get_x()+this.get_width()/2, this.get_y()+this.get_height()/2);
		crayon.rotate(this.get_angle());
		crayon.translate(-(this.get_width()+correction)/2,-(this.get_height()+correction)/2);
		crayon.drawImage(this.get_image(), 0, 0, this.get_width()+correction, this.get_height()+correction, null, null);
    }

    public void move(int x,int y,Entities entities){
        this.move_relative(x, y, entities);
    }

    public void move_relative(int i,int j,Entities entities){
    	entities.player_move(i, j, entities);
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
        angle = -Math.atan2(mx,my)-3 ;
    }

    private void shoot(){
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.update_angle(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.update_angle(e);
        this.shoot();
    }













    // inutile
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
