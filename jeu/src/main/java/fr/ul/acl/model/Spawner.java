package fr.ul.acl.model;

public class Spawner extends Entity {

    protected int spawnrate = 100;
    protected int spawncounter = 0;

    Spawner(int x,int y,int width,int height){
        super(x,y,width,height);
        this.is_colidable = false;
        image_path = "src/main/resources/spawner_image.png";
        load_image();
    }

    public void spawn_monster(Entities entities){   
        entities.add_enemi(new Zombie(this.get_x(),this.get_y(),width,height,20));
        entities.add_enemi(new Zombie_quick(this.get_x(),this.get_y(),width,height,10));
        entities.add_enemi(new Zombie_tireur(this.get_x(),this.get_y(),width,height,10));

    }

    public void evolve(Entities entities){
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