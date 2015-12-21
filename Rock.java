import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Rock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rock extends Impassable
{
    
    public Rock(){ //select a random rock 
        Random rand = new Random();
        int i = rand.nextInt(6)+1;
        setImage(new GreenfootImage("rock"+i+".png"));
    }
    /**
     * Act - do whatever the Rock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
