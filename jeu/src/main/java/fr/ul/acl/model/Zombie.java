package fr.ul.acl.model;


public class Zombie extends Monstre{

	double angle = 0;
	boolean can_dash =true;
	public Zombie(int x,int y,int width,int height,int level, Entities entities){
		super(x,y,width,height,level, entities);
		this.level= level;
		this.health=(int) Math.round(25*Math.log(level)+5);
		this.health0 = this.health;
		image_size = 64;
		image_path = "src/main/resources/zombie_big.png";
	    this.saved_images();


		this.body_damage = (int) Math.round(10*Math.log(level)+6);
		this.speed0 = this.speed;
	}
	
	public void evolve(Entities entities) {
		int plx = entities.get_player().get_x();
		int ply = entities.get_player().get_y();
		double distance = Math.sqrt(Math.pow(plx+30-get_x()-get_width()/2, 2) + Math.pow(ply+30-get_y()-get_height()/2, 2));
		int dx = 0 , dy = 0;
		if(distance>300+get_width()/2){		
			this.move_using_graphe(entities);
			can_dash = true;
		}
		else{
			if(can_dash){
				int px = plx - this.get_x();
				int py = ply - this.get_y();
				this.angle = -Math.atan2(px,py) + 3.14/2;
				can_dash = false;
			}
			else{
				dx = (int)(Math.cos(angle)*100)/12;
				dy =(int)(Math.sin(angle)*100)/12;
			}
		}
		//System.out.println(String.valueOf(dx)+" , "+String.valueOf(dy));
		this.move(dx,0,entities);
		this.move(0,dy,entities);
	}
	
	public void move(int x,int y,Entities entities){
    	if (this.can_move(x, y, entities)) {
			this.x += x;
			this.y += y;
			this.hitbox.move(this.get_x(),this.get_y());
    	}

    }


	public boolean can_move(int x, int y,Entities entities) {
    	hitboxTemp = new Hitbox(this.get_x()+x, this.get_y()+y , this.get_width(), this.get_height());

		for(int i=0;i<entities.obstacles.size();i++) {
			if(entities.obstacles.get(i).get_hitbox().colide(hitboxTemp) && entities.obstacles.get(i).is_colidable && entities.obstacles.get(i) != this) {
				this.on_collision(entities);
                return false;
			}
		}
		if(entities.get_player().get_hitbox().colide(hitboxTemp)){
			this.on_collision(entities);
			if(!can_dash){
				if(entities.get_player().can_move(x, y, entities))entities.get_player().move(x, y, entities);
			}
			entities.get_player().compteur += 1;
                if (entities.get_player().compteur%10==0){
                    entities.get_player().health_p = Math.max(entities.get_player().health_p-1,0);
                }
			return false;
		}
    	return true;
    }
}
