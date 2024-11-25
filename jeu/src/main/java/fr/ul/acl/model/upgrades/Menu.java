package fr.ul.acl.model.upgrades;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

import fr.ul.acl.model.Player;
import fr.ul.acl.model.upgrades.upgrades_list.*;

public class Menu {
    private int width;
    private int height;
    private int dwidth;
    private int dheight;
    private int button_width;
    private int button_height;
    private Boolean isOpen = false;
    private ArrayList<Boutton> bouttons;
    private ArrayList<Upgrade> upgrades;

    public Menu(int width , int height){
        this.width = width;
        this.dwidth = width/5;
        this.dheight = height/10;
        this.height = height;
        button_width = dwidth-10;
        button_height = dheight-10;

        upgrades = new ArrayList<>();
        load_upgrades();
        bouttons = new ArrayList<>();
    }

    public void draw(Graphics2D crayon){
        crayon.setColor(Color.BLUE);
        crayon.fillRect(1250, 50, 250, 80);
        crayon.setColor(Color.YELLOW);
        crayon.setFont(new Font("SansSerif", Font.PLAIN, 40));
        String texte = "MENU :";
        FontMetrics metrics = crayon.getFontMetrics();
        int txt_x = 1250 + (250 -  metrics.stringWidth(texte))/2;
        int txt_y = 50 + ((80 -  metrics.getHeight())/2) + metrics.getAscent();
        crayon.drawString(texte, txt_x, txt_y);
        crayon.setFont(new Font("Arial", Font.PLAIN, 20));

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
            //bouttons.add(new Boutton(dwidth*(1+i)-button_width/2, height/2-button_height/2, 250, 80,upgrades.get(indiceAleatoire)));
            bouttons.add(new Boutton(1250, 100+dheight*(1+i)-button_height/2, 250, 80,upgrades.get(indiceAleatoire)));
            /*System.out.println("x = " + (dwidth*(1+i)-button_width/2));
            System.out.println("y = " + (height/2-button_height/2));
            System.out.println("width = " + (button_width));
            System.out.println("height = " + (button_height/2));*/
        }

    }

    public void select_augment(int choix , Player player){
        bouttons.get(choix-1).on_clic(player);
        this.close();
    }
}
