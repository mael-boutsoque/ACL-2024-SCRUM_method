package fr.ul.acl.model.upgrades;

import java.awt.Color;

public class Upgrade {
    public String nom;
    public Color color;
    public String description;

    public Upgrade(String nom, String description , Color color){
        this.nom = nom;
        this.description = description;
        this.color = color;
    }
}
