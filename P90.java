import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class P90 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class P90 extends Weapon
{
    int bulletSpeed =20;
    int bulletDamage=30;
    public P90(){
        super("p90");
    }
    /**
     * Act - do whatever the P90 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public void use(int xPos, int yPos){ //x and y: current player pos
        super.use(xPos,yPos);
        
    }
    
}
