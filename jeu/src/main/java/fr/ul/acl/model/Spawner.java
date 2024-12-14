package fr.ul.acl.model;

public class Spawner extends Entity {

    protected int spawnrate = 1000;
    protected int spawncounter = 9900;

    Spawner(int x,int y,int width,int height){
        super(x,y,width,height);
        this.is_colidable = false;
        image_path = "src/main/resources/spawner_image.png";
        load_image();
    }

    public void spawn_monster(Entities entities){   
    	Zombie z = new Zombie(this.x,this.y,width,height,20, entities);
    	z.move_relative(this.x_relative, this.y_relative, entities);
        entities.add_enemi(z);
        
        Zombie_quick z2 = new Zombie_quick(this.x,this.y, width,height,20, entities);
    	z2.move_relative(this.x_relative, this.y_relative, entities);
        entities.add_enemi(z2);

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