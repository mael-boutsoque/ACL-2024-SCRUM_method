package fr.ul.acl.model.upgrades.upgrades_list;

import java.awt.Color;

import fr.ul.acl.model.Player;
import fr.ul.acl.model.upgrades.Upgrade;

public class Bonus_de_vie extends Upgrade {
    public Bonus_de_vie(){
        super("BONUS DE VIE","ajoute 1 vie",Color.CYAN);
    }

    public void update_player(Player player){
        player.add_life(1);
    }
}
