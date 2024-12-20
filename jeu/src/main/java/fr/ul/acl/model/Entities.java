package fr.ul.acl.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Entities {
    public ArrayList<Entity> obstacles;
    public ArrayList<Entity> enemies;
    public ArrayList<Entity> projectiles;
    public GraphePathfinding pathfinder;
    private Player player;

    private GrapheWaypoint closest_node_to_player;
    private int t = 0;
    protected Map map;
    private int wave = 1;
    private int nbMonstreApparu = 0;
    private int nbMonstreMax = 10;
    private boolean canGoNextWave =false;
    public int compteur = 0;
    public int compteur_0 = 0;
    final int carte_x = 1200;
    final int carte_y = 630;

    /*
     * the map is always the first entity
     */
    // SI VOUS VOULEZ RAJOUTER DES OBSTACLES, FAITES LE DANS MAP, SINON ILS N'APPARAISSENT PAS
    public Entities(int win_width,int win_height){
        final int coef_carte = 6;
        obstacles = new ArrayList<Entity>() ;
        enemies = new ArrayList<Entity>() ;
        projectiles = new ArrayList<Entity>() ;
        
        map = new Map(0,0,carte_x*coef_carte,carte_y*coef_carte,this);
        obstacles.add(map);
        map.load_map(coef_carte, this);

        //liste.add(new Entity(0,0,100,100));
        //liste.add(new Entity(300, 300,100,70));

        //Monstre

        //add_enemi(new Zombie_tireur(2000,900,100,100,1));
        //add_enemi(new Zombie_quick(100, 100, 100, 100, 1));


        player = new Player( 1536/2 , 864/2 ,this,20);
        this.player.move(-200, -200,this);

        //this.player.move(1902, 1080,this);
        try {
        	this.pathfinder.locate_player(this);
        	this.pathfinder.generate_path_to_player();
        }
        catch (Exception e) {
        	throw new NullPointerException("Cannot spawn player inside of a wall");
        }

        //add_enemi(new Zombie(1000,1000,200,200,1));
        //add_enemi(new Zombie(1000,1200,100,100,1));

        //add_enemi(new Boss(4500,2000,400,400,1));       

    }

    public void supp_entities(Entity entity) {
    	for(int i =0; i<obstacles.size();i++) {
    		if (obstacles.get(i)==entity) {
    			obstacles.remove(i);
    		}
    	}
        for(int i =0; i<enemies.size();i++) {
    		if (enemies.get(i)==entity) {
    			enemies.remove(i);
    		}
    	}
        for(int i =0; i<projectiles.size();i++) {
    		if (projectiles.get(i)==entity) {
    			projectiles.remove(i);
    		}
    	}
    }

    public Monstre get_enemi(int id){
        return (Monstre) enemies.get(id);
    }
    public void add_enemi(Monstre enemi){
        enemies.add(enemi);
    }

    public Entity get_obstacle(int id){
        return obstacles.get(id);
    }
    public void add_obstacle(Entity obstacle){
        obstacles.add(obstacle);
    }

    public Bullet get_projectile(int id){
        return (Bullet) projectiles.get(id);
    }
    public void add_projectile(Bullet projectile){
        this.compteur++;
        projectiles.add(projectile);
    }
    public int compteur_projectile(int limite){
        if (this.compteur - this.compteur_0 < limite){
            return 0;
        }
        else {
            return 1;
        }
    }

    public Player get_player(){
        return player;
    }

     public GrapheWaypoint get_player_node() {
    	 return closest_node_to_player;
     }

    public int get_nbMonstreMax(){
        return this.nbMonstreMax;
    }

    public void set_nbMonstreMax(int wave){
        //this.nbMonstreMax= 2;
        this.nbMonstreMax= (int) Math.round(5*Math.pow(2,wave));
    }

    public int get_wave(){
        return this.wave;
    }

    public void set_wave(int valeur){
        this.wave=valeur;
    }

    public int get_nbMonstreApparu(){
        return this.nbMonstreApparu;
    }

    public void set_nbMonstreApparu(int valeur){
        this.nbMonstreApparu=valeur;
    }

    public boolean get_canGoNextWave(){
        return this.canGoNextWave;
    }

    public void set_canGoNextWave(boolean valeur){
        this.canGoNextWave=valeur;
    }
    
    public void player_move(int x,int y,Entities entities){
        for(int i=0;i<projectiles.size();i++){
            projectiles.get(i).move_relative(-x, -y,entities);
        }
        for(int i=0;i<obstacles.size();i++){
            obstacles.get(i).move_relative(-x, -y,entities);
        }
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).move_relative(-x, -y,entities);
        }
        for(GrapheWaypoint g : pathfinder.get_graphe_map().keySet()) {
    		g.move_relative(-x, -y,entities);
    	}
    }
    public void locate_player() {
    	if (this.t<50){t++;}
        else {
        	this.pathfinder.locate_player(this);
        	this.pathfinder.generate_path_to_player();
        	t = 0;
        }
        this.closest_node_to_player= this.pathfinder.get_player_pos();
    }
    
    
    public void draw(Graphics2D crayon){
    	
        for(int i=0;i<obstacles.size();i++){
			obstacles.get(i).draw(crayon);
        }
        
        /*for(GrapheWaypoint g : pathfinder.get_graphe_map().keySet()) {
            g.draw(crayon);
    		crayon.setColor(Color.RED);
    		int x1 = g.get_x();
    		int y1 = g.get_y();
    		int x2 = g.get_x();
    		int y2 = g.get_y();
    		for (GrapheWaypoint g2 : pathfinder.get_graphe_map().get(g)) {
    			x2 = g2.get_x();
    			y2 = g2.get_y();
    			//crayon.drawLine(x1, y1, x2, y2);
    		}
    	}
        
        for(GrapheWaypoint g : pathfinder.get_graphe_map().keySet()) {
        	GrapheWaypoint g2 = pathfinder.get_shortest_path_map().get(g);
        	int x1 = g.get_x();
    		int y1 = g.get_y();
    		int x2 = g.get_x();
    		int y2 = g.get_y();
			//crayon.setColor(Color.GREEN);
			if (g2 != null) {
				x2 = g2.get_x();
				y2 = g2.get_y();
				//crayon.drawLine(x1, y1, x2, y2);
			}
        }

        //crayon.setColor(Color.GREEN);
        //crayon.drawOval(this.closest_node_to_player.get_x(), this.closest_node_to_player.get_y(), 20, 20);
        */

        
        for(int i=0;i<enemies.size();i++){
			enemies.get(i).draw(crayon);
        }
        for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).draw(crayon);
        }
        
        
        this.get_player().draw(crayon);
    }

    public void kill_dead_entities(){
        for(int i=projectiles.size()-1 ; i>=0 ; i--){
            if (projectiles.get(i).is_dead){
                projectiles.remove(i);
            }
        }
        for(int i=obstacles.size()-1 ; i>=0 ; i--){
            if (obstacles.get(i).is_dead){
                obstacles.remove(i);
            }
        }
        for(int i=enemies.size()-1 ; i>=0 ; i--){
            if (enemies.get(i).is_dead){
                if(!(enemies.get(i) instanceof Bullet_enemi)){
                    player.xp += 2;
                    player.get_gun().xp_effet += (int) Math.round(20*Math.log(enemies.get(i).get_level())+5);
                }
                enemies.remove(i);
            }
        }
    }

    public String toString(){
        return "[obstacles : "+String.valueOf(obstacles.size())+" , monstres : "+String.valueOf(enemies.size())+" , balles : "+String.valueOf(projectiles.size())+"]";

    }

    public void changeWave(){
        if(canGoNextWave && this.enemies.isEmpty()){
        this.canGoNextWave=false;
        for(int i=obstacles.size()-1 ; i>=0 ; i--){
            if ( obstacles.get(i).wave==wave ){
                if(!(obstacles.get(i) instanceof Spawner)){
                obstacles.get(i).is_dead=true;
                }
                else{
                    obstacles.get(i).set_isActive(true);
                }
            }
        }
        this.wave++;
        this.nbMonstreApparu=0;
        set_nbMonstreMax(wave);
        }
    }
}
