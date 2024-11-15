package fr.ul.acl.model;

public class Mur_v extends Entity {
    Mur_v(int x,int y,int width,int height){
        super(x,y,width,height);
        this.is_colidable = true;
        image_path = "src/main/resources/mur_v.png";
        load_image();
    }
}
