package fr.ul.acl.model;

public class Mur_h extends Entity {
    Mur_h(int x,int y,int width,int height){
        super(x,y,width,height);
        this.is_colidable = true;
        image_path = "src/main/resources/mur_h.png";
        load_image();
    }
}
