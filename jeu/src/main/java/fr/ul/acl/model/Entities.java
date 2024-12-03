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
    protected Map map;
    protected int nbMonstre = 0;
    protected int nbMonstreMax = 10;
	

    /*
     * the map is always the first entity
     */
    public Entities(int win_width,int win_height){
        final int coef_carte = 6;
        final int carte_x = 1200;
        final int carte_y = 630;
        obstacles = new ArrayList<Entity>() ;
        enemies = new ArrayList<Entity>() ;
        projectiles = new ArrayList<Entity>() ;
        
        map = new Map(0,0,carte_x*coef_carte,carte_y*coef_carte,this);
        obstacles.add(map);
        map.load_map(coef_carte, this);
        map.load_graphe_pathfinding(coef_carte, this);

        //liste.add(new Entity(0,0,100,100));
        //liste.add(new Entity(300, 300,100,70));

        //Monstre
        //liste.add(new MonstreTest(700,300,110,110,2));

        //Spawner
        //obstacles.add(new Spawner(2000,800,110,110));

        player = new Player( 1536/2 , 864/2 ,this,10);

        this.player.move(500*coef_carte, 500*coef_carte,this);
       
        this.pathfinder.locate_player(this);
        this.pathfinder.generate_path_to_player();

        //add_enemi(new Zombie(1000,1000,200,200,1000));
        //add_enemi(new Zombie(1000,1200,100,100,100));
        
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
        projectiles.add(projectile);
    }

    public Player get_player(){
        return player;
    }
    
    public void calculate_paths_to_player() {
    	
    	
    	/*
    	GrapheWaypoint p1 = this.pathfinder.get_player_pos();
    	this.pathfinder.locate_player(this);
    	GrapheWaypoint p2 = this.pathfinder.get_player_pos();
    	
    	if (!p1.equals(p2)){
    		this.pathfinder.generate_path_to_player();
    	}
    	*/
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

    public void draw(Graphics2D crayon){
    	
        for(int i=0;i<obstacles.size();i++){
			obstacles.get(i).draw(crayon);
        }
        for(GrapheWaypoint g : pathfinder.get_graphe_map().keySet()) {
    		g.draw(crayon);
    		crayon.setColor(Color.RED);
    		int x1 = g.get_x();
    		int y1 = g.get_y();
    		int x2 = g.get_x();
    		int y2 = g.get_y();
    		for (GrapheWaypoint g2 : pathfinder.get_graphe_map().get(g)) {
    			x2 = g2.get_x();
    			y2 = g2.get_y();
    			crayon.drawLine(x1, y1, x2, y2);
    		}
    	}
        this.pathfinder.locate_player(this);
        GrapheWaypoint p1 = this.pathfinder.get_player_pos();
        crayon.setColor(Color.GREEN);
        crayon.drawOval(p1.get_x(), p1.get_y(), 20, 20);
        
        
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
                enemies.remove(i);
                player.xp += 2;
            }
        }
    }

    public String toString(){
        return "[obstacles : "+String.valueOf(obstacles.size())+" , monstres : "+String.valueOf(enemies.size())+" , balles : "+String.valueOf(projectiles.size())+"]";

    }
}
