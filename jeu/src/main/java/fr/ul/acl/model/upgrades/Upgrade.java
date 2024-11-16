package fr.ul.acl.model.upgrades;

import java.awt.Color;

import fr.ul.acl.model.Player;

public class Upgrade {
    public String nom;
    public Color color;
    public String description;

    public Upgrade(String nom, String description , Color color){
        this.nom = nom;
        this.description = description;
        this.color = color;
    }

    public void update_player(Player player){
        System.out.println("Ne pas utiliser cette fonction [Upgrade.update_player]");
    }
}
