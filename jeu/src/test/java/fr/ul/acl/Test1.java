package fr.ul.acl;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import fr.ul.acl.model.*;


public class Test1 {
    Monstre monstre;

    @Before
    public void init(){
        monstre = new Zombie(0,0,100,100,50);
    }

    @Test
    public void Test_test(){
        Boolean test_mis_en_place = true;
        Assert.assertTrue(test_mis_en_place);
    }

    @Test
    public void Test_collision(){
        Hitbox box1 = new Hitbox(0,0,100,100);
        Hitbox box2 = new Hitbox(99,99,100,100);
        Assert.assertTrue(box1.colide(box2));
    }

    @Test
    public void Test_no_collision(){
        Hitbox box1 = new Hitbox(0,0,100,100);
        Hitbox box2 = new Hitbox(100,100,100,100);
        Assert.assertFalse(box1.colide(box2));
    }

    @Test
    public void Test_collision_zeros(){
        Hitbox box1 = new Hitbox(0,0,0,0);
        Hitbox box2 = new Hitbox(0,0,10,10);
        Assert.assertFalse(box1.colide(box2));
    }
}