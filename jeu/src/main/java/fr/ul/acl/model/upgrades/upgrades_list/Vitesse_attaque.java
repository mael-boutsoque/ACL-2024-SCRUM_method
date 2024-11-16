package fr.ul.acl.model.upgrades.upgrades_list;

import java.awt.Color;

import fr.ul.acl.model.Player;
import fr.ul.acl.model.upgrades.Upgrade;

public class Vitesse_attaque extends Upgrade {
    public Vitesse_attaque(){
        super("VITESSE D'ATTAQUE","ajoute 50 vitesse d'attaque",Color.magenta);
    }

    public void update_player(Player player){
        player.get_gun().add_shoot_rate(50);
    }
}
