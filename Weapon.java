import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Actor
{
    int speed;
    int speedDelay;
    int damage;

    GreenfootSound soundEffect;
    String weapon;

    /**
     * Act - do whatever the Weapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(speedDelay <speed){
            speedDelay++;
        }
    }    

    public void use(Player player){
        
    }
}
