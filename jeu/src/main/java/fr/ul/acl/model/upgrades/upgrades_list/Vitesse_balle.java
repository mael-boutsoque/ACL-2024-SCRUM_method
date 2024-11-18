package fr.ul.acl.model.upgrades.upgrades_list;

import java.awt.Color;

import fr.ul.acl.model.Player;
import fr.ul.acl.model.upgrades.Upgrade;

public class Vitesse_balle extends Upgrade {
    public  Vitesse_balle(){
        super("vitesse balle","augmente la vitesse des balles",Color.orange);
    }

    public void update_player(Player player){
        player.get_gun().add_bullet_speed(10);
    }
}
