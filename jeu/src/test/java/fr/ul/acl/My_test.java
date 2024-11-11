package fr.ul.acl;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import fr.ul.acl.model.*;


public class My_test {
    MonstreTest monstre;

    @Before
    public void init(){
        monstre = new MonstreTest(0,0,100,100,50);
    }

    @Test
    public void Test_test(){
        Boolean test_mis_en_place = true;
        Assert.assertTrue(test_mis_en_place);
    }
}