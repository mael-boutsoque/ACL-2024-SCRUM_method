package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class Gun implements MouseInputListener {
    Player player;
    //position de la souri
    int x;
    int y;
    double angle = 1;

    Gun(Player player){
        this.player = player;
    }

    public void draw(Graphics2D crayon){
        crayon.drawLine(player.get_x(),player.get_y(),x,y);
        crayon.setColor(Color.GREEN);
        crayon.drawLine(player.get_x(),player.get_y(),player.get_x()+((int)(100*Math.cos(angle))),player.get_y()+((int)(100*Math.sin(angle))));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x= e.getX() - player.x;
        y= e.getY() - player.y;
        angle = -Math.atan2(x,y) + 1.5;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x= e.getX() - player.x;
        y= e.getY() - player.y;
        angle = -Math.atan2(x,y) + 1.5;

        // doit tirer
    }





    //fonctions inutilisees
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("tir");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("tir");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }    
}
