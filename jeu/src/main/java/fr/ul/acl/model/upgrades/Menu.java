package fr.ul.acl.model.upgrades;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

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
        upgrades.add(new Upgrade("test 1", "descp 1", Color.blue));
        upgrades.add(new Upgrade("test 2", "descp 2", Color.red));
        upgrades.add(new Upgrade("test 3", "descp 3", Color.green));
        upgrades.add(new Upgrade("test 4", "descp 4", Color.yellow));
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
}
