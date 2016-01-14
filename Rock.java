import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * A rock object.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class Rock extends Impassable
{
    
    /**
     * A constructor used to select a random rock image.
     */
    public Rock(){ 
        Random rand = new Random();
        int i = rand.nextInt(6)+1;
        setImage(new GreenfootImage("rock"+i+".png"));
    }

}
