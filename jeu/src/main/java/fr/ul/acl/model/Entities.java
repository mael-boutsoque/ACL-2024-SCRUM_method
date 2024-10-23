package fr.ul.acl.model;

import java.util.ArrayList;

public class Entities {
    private ArrayList<Entity> liste;
    private Player player;

    /*
     * the map is always the first entity
     * the player is always the last entity
     */
    public Entities(){
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
        player = new Player( 1920/4 , 1080/4);
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
}
