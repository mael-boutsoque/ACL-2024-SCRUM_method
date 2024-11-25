package fr.ul.acl.model.upgrades;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import fr.ul.acl.model.Player;
import fr.ul.acl.model.upgrades.upgrades_list.*;

import java.awt.Font;

public class Menu {
    private int width;
    private int height;
    private int dwidth;
    private int button_width;
    private int button_height;
    private Boolean isOpen = false;
    private ArrayList<Boutton> bouttons;
    private ArrayList<Upgrade> upgrades;

    public Menu(int width , int height){
        this.width = width;
        this.dwidth = width/5;
        this.height = height;
        button_width = dwidth-10;
        button_height = height/3;

        upgrades = new ArrayList<>();
        load_upgrades();
        bouttons = new ArrayList<>();
    }

    public void draw(Graphics2D crayon){
        crayon.setColor(Color.BLUE);
        crayon.setFont(new Font("SansSerif", Font.PLAIN, 40));
        crayon.drawString("[ | MENU |      selectionner avec les bouttons '1' '2' '3']", button_width, button_height/2);
        crayon.setFont(new Font("SansSerif", Font.PLAIN, 20));

        for(int i=0 ; i<3 ; i++){
            bouttons.get(i).draw(crayon);
        }
    }

    public Boolean is_opened(){
        return isOpen;
    }
    public void open(){
        load_rd_button();
        isOpen = true;
    }
    public void close(){
        isOpen = false;
    }

    private void load_upgrades(){
        upgrades.clear();
        upgrades.add(new Vitesse_attaque());
        upgrades.add(new Degats());
        upgrades.add(new Vitesse_attaque());
        upgrades.add(new Degats());
        upgrades.add(new Vitesse_attaque());
        upgrades.add(new Degats());
        upgrades.add(new Taille_des_balles());
        upgrades.add(new Vitesse_balle());
        upgrades.add(new Bonus_de_vie());
    }

    private void load_rd_button(){
        bouttons.clear();

        Random random = new Random();
        int tailleListe = upgrades.size();
        for (int i = 0; i < 3; i++) {
            int indiceAleatoire = random.nextInt(tailleListe);
            bouttons.add(new Boutton(dwidth*(1+i)-button_width/2, height/2-button_height/2, button_width, button_height,upgrades.get(indiceAleatoire)));
        }

    }

    public void select_augment(int choix , Player player){
        bouttons.get(choix-1).on_clic(player);
        this.close();
    }
}
