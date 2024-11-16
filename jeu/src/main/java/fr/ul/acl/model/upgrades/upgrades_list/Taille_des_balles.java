package fr.ul.acl.model.upgrades.upgrades_list;

import java.awt.Color;

import fr.ul.acl.model.Player;
import fr.ul.acl.model.upgrades.Upgrade;

public class Taille_des_balles extends Upgrade {
    public Taille_des_balles(){
        super("taille des balles","les balles sont plus grosses",Color.green);
    }

    public void update_player(Player player){
        player.get_gun().add_bullet_size(10);
    }
}
