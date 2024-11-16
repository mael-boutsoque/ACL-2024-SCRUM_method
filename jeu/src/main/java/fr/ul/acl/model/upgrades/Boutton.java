package fr.ul.acl.model.upgrades;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.ul.acl.model.Player;

public class Boutton {
    private String name;
    private String description;
    private Color color;
    private Upgrade upgrade;
    private int x;
    private int y;
    private int width;
    private int height;

    public Boutton(int x ,int y , int width , int height , String name , Color color , Upgrade upgrade){
        this.name = name;
        this.color = color;
        this.description = "";
        this.upgrade = upgrade;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Boutton(int x ,int y , int width , int height , Upgrade upgrade){
        this.name = upgrade.nom;
        this.color = upgrade.color;
        this.description = upgrade.description;
        this.upgrade = upgrade;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D crayon){
        crayon.setColor(color);
        crayon.fillRect(x , y , width , height);
        crayon.setColor(Color.white);
        crayon.drawString(name, x, y+20);
        crayon.drawString(description, x, y+height/2);
    }

    public void on_clic(Player player){
        upgrade.update_player(player);
    }
}
