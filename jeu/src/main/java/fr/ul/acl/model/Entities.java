package fr.ul.acl.model;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Entities {
    private ArrayList<Entity> liste;
    private Player player;

    /*
     * the map is always the first entity
     */
    public Entities(int win_width,int win_height){
        liste = new ArrayList<Entity>() ;
        liste.add(new Map(0,0,1200*4,600*4));
        liste.add(new Entity(0,0,100,100));
        liste.add(new Entity(300, 300,100,70));

        //bord de map
        liste.add(new Invisible(0, -10,1200*4,10));//haut
        liste.add(new Invisible(0, 600*4,1200*4,10));//bas
        liste.add(new Invisible(-10, 0,10,600*4));//gauche
        liste.add(new Invisible(1200*4, 0,10,600*4));//droite

        //Monstre
        liste.add(new MonstreTest(700,300,110,110));

        //Obstacle 

        //liste.add(new MonstreTest(700,300,110,110));
        //liste.add(new MonstreTest(500,0,20,20));
        player = new Player( win_width/2 , win_height/2 );
        this.player.move(1902, 1080,this);
    }

    public Entity get_by_id(int id){
        return liste.get(id);
    }
    public void supp_entities(Entity entity) {
    	for(int i =0; i<liste.size();i++) {
    		if (liste.get(i)==entity) {
    			liste.remove(i);
    		}
    	}
    }

    public int size(){
        return liste.size();
    }

    public Player get_player(){
        return player;
    }
    
    public void player_move(int x,int y,Entities entities){
        for(int i=0;i<this.size();i++){
            this.get_by_id(i).move_relative(-x, -y,entities);
        }
    }

    public void draw(Graphics2D crayon){
        for(int i=0;i<this.size();i++){
			this.get_by_id(i).draw(crayon);
        }

        this.get_player().draw(crayon);
    }
}
