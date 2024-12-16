package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.Random;



public class Spawner extends Entity {

    protected int spawnrate = 100;
    protected int spawncounter = 0;
    private ArrayList<Monstre> monstres;
    protected boolean isActive;

    Spawner(int x,int y,int width,int height,int wave, boolean isActive){
        super(x,y,width,height);
        this.is_colidable = false;
        image_path = "src/main/resources/spawner_image.png";
        load_image();
        this.wave=wave;
        this.monstres = new ArrayList<>();
        this.isActive = isActive;
    }

    public void spawn_monster(Entities entities){  
        Random random = new Random();
        int niveauAleatoire = random.nextInt(entities.get_wave()+1)+1;
        load_monstres(niveauAleatoire, entities); 

        int tailleListe = monstres.size();
        int indiceAleatoire = random.nextInt(tailleListe);
        entities.add_enemi(monstres.get(indiceAleatoire));
        entities.set_nbMonstreApparu(entities.get_nbMonstreApparu()+1);
    }

    @Override public void evolve(Entities entities){
        spawnrate = 200/entities.get_wave();
        if(this.isActive && !entities.get_canGoNextWave()){
            if (spawncounter>spawnrate){
                
                this.spawncounter=0;
                this.spawn_monster(entities);
            }
            else{
                this.spawncounter=this.spawncounter+1;
            }
            if(entities.get_nbMonstreApparu()==entities.get_nbMonstreMax()){
                entities.set_canGoNextWave(true);
            }
        }
    }
    private void load_monstres(int level, Entities entities){
        monstres.clear();
        Zombie z = new Zombie(this.x,this.y,width,height,20, entities);
    	z.move_relative(this.x_relative, this.y_relative, entities);
        monstres.add(z);
        
        Zombie_quick z2 = new Zombie_quick(this.x,this.y, width,height,20, entities);
    	z2.move_relative(this.x_relative, this.y_relative, entities);
    	monstres.add(z2);
        
        Zombie_tireur z3 = new Zombie_tireur(this.x,this.y, width,height,20, entities);
    	z3.move_relative(this.x_relative, this.y_relative, entities);
    	 monstres.add(z3);
    }

} 