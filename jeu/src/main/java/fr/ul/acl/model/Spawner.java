package fr.ul.acl.model;

public class Spawner extends Entity {

    protected int spawnrate = 100;
    protected int spawncounter = 0;

    Spawner(int x,int y,int width,int height){
        super(x,y,width,height);
        this.is_colidable = false;
        image_path = null;
        load_image();
    }

    public void spawn_monster(Entities entities){   
        entities.add_entity(new MonstreTest(this.get_x(),this.get_y(),width,height,10));

    }

    public void evolve(Entities entities){
        if (entities.nbMonstreMax>entities.nbMonstre && spawncounter>spawnrate){
            entities.nbMonstre=entities.nbMonstre+1;
            this.spawncounter=0;
            this.spawn_monster(entities);
        }
        else{
            this.spawncounter=this.spawncounter+1;
        }

    }
} 