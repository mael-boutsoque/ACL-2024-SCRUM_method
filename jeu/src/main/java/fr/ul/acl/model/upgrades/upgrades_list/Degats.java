package fr.ul.acl.model.upgrades.upgrades_list;

import java.awt.Color;

import fr.ul.acl.model.Player;
import fr.ul.acl.model.upgrades.Upgrade;

public class Degats extends Upgrade {
    public Degats(){
        super("DEGATS","augmente les dégats de 1",Color.red);
    }

    public void update_player(Player player){
        player.get_gun().add_damages(5);
    }
}
