package fr.ul.acl.model;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Gun implements MouseListener {

    public void draw(Graphics2D crayon,Player player){
        crayon.drawLine(player.get_x(),player.get_y(),0,0);
    }

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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
}
