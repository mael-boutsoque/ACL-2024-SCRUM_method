package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.Random;



public class Spawner extends Entity {

    protected int spawnrate = 100;
    protected int spawncounter = 0;
    private ArrayList<Monstre> monstres;
    private boolean isActive;

    Spawner(int x,int y,int width,int height,boolean isActive){
        super(x,y,width,height);
        this.is_colidable = false;
        image_path = "src/main/resources/spawner_image.png";
        load_image();
        this.monstres = new ArrayList<>();
        this.isActive = isActive;
    }

    public void spawn_monster(Entities entities){  
        load_monstres(); 
        Random random = new Random();
        int tailleListe = monstres.size();
        int indiceAleatoire = random.nextInt(tailleListe);
        entities.add_enemi(monstres.get(indiceAleatoire));

    }

    public void evolve(Entities entities){
        if(this.isActive){
            if (spawncounter>spawnrate){
                entities.nbMonstre=entities.nbMonstre+1;
                this.spawncounter=0;
                this.spawn_monster(entities);
            }
            else{
                this.spawncounter=this.spawncounter+1;
            }
        }
    }
    private void load_monstres(){
        monstres.clear();
        monstres.add(new Zombie(this.get_x(),this.get_y(),width,height,20));
        monstres.add(new Zombie_quick(this.get_x(),this.get_y(),width,height,10));
        monstres.add(new Zombie_tireur(this.get_x(),this.get_y(),width,height,10));
    }
    public boolean get_isActive(){
        return this.isActive;
    }

    public void set_isActive(boolean valeur){
        this.isActive = valeur;
    }
} 