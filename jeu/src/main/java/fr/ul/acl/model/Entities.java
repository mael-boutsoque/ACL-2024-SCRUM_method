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
        liste.add(new Map(0,0,1200*4,630*4));
        liste.add(new Entity(0,0,100,100));
        liste.add(new Entity(300, 300,100,70));

        //bord de map
        liste.add(new Invisible(0, -10,1200*4,10));//haut
        liste.add(new Invisible(0, 630*4,1200*4,0));//bas
        liste.add(new Invisible(-10, 0,10,630*4));//gauche
        liste.add(new Invisible(1200*4, 0,10,630*4));//droite

        //Monstre
        liste.add(new MonstreTest(700,300,110,110));

        //Obstacle 
        //Bloc Gris
        liste.add(new Invisible(110*4,25*4,45*4,144*4));
        liste.add(new Invisible(206*4,25*4,70*4,96*4));
        liste.add(new Invisible(374*4,337*4,70*4,120*4));
        //Lac bleu
        liste.add(new Invisible(88*4,373*4,88*4,127*4));
        liste.add(new Invisible(71*4,400*4,126*4,72*4));
        //Bloc gris avec escalier
        liste.add(new Invisible(158*4,169*4,1*4,144*4));
        liste.add(new Invisible(158*4,169*4,261*4,1*4));
        liste.add(new Invisible(419*4,169*4,1*4,96*4));
        liste.add(new Invisible(161*4,286*4,21*4,27*4));
        liste.add(new Invisible(185*4,310*4,45*4,27*4));
        liste.add(new Invisible(251*4,310*4,72*4,27*4));
        liste.add(new Invisible(323*4,286*4,48*4,27*4));
        liste.add(new Invisible(371*4,262*4,24*4,27*4));
        liste.add(new Invisible(395*4,238*4,24*4,27*4));
        //Bloc gris 2 étages
        liste.add(new Invisible(737*4,310*4,69*4,27*4));
        liste.add(new Invisible(827*4,310*4,312*4,27*4));
        liste.add(new Invisible(734*4,121*4,1*4,213*4));
        liste.add(new Invisible(734*4,121*4,22*4,1*4));
        liste.add(new Invisible(758*4,96*4,1*4,94*4));
        liste.add(new Invisible(758*4,193*4,50*4,21*4));
        liste.add(new Invisible(806*4,193*4,1*4,40*4));
        liste.add(new Invisible(806*4,236*4,72*4,26*4));
        liste.add(new Invisible(899*4,238*4,21*4,24*4));
        liste.add(new Invisible(758*4,73*4,70*4,24*4));
        liste.add(new Invisible(828*4,97*4,95*4,1*4));
        liste.add(new Invisible(923*4,25*4,216*4,240*4));
        liste.add(new Invisible(1139*4,265*4,1*4,42*4));
        //Gazon
        liste.add(new Invisible(151*4,483*4,61*4,51*4));
        liste.add(new Invisible(200*4,385*4,51*4,102*4));
        //Cactus
        liste.add(new Invisible(350*4,28*4,45*4,42*4));
        liste.add(new Invisible(182*4,556*4,39*4,38*4));
        liste.add(new Invisible(254*4,460*4,45*4,43*4));
        liste.add(new Invisible(758*4,532*4,42*4,42*4));
        //Rochers
        liste.add(new Invisible(806*4,389*4,39*4,41*4));
        liste.add(new Invisible(280*4,550*4,40*4,23*4));
        liste.add(new Invisible(1022*4,478*4,40*4,23*4));

        //liste.add(new MonstreTest(700,300,110,110));
        //liste.add(new MonstreTest(500,0,20,20));
        player = new Player( win_width/2 , win_height/2 , this);
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

    public void add_entity(Entity entity){
        liste.add(entity);
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

    public void kill_dead_entities(){
        for(int i=liste.size()-1 ; i>=0 ; i--){
            if (liste.get(i).is_dead){
                liste.remove(i);
            }
        }
    }
}
