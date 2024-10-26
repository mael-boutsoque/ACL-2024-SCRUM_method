package fr.ul.acl.model;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class Player extends Entity implements MouseInputListener {
    private int speed;
    private double angle = 1;

    Player(int x , int y){
        super(x - 30, y - 30 , 60 , 60);

        //chargement image
        image_path = "images\\player.png";
        this.load_image();

        // stats
        this.speed = 1;
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
        System.out.println("tir");
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
