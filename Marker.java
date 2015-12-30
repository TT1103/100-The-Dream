import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Marker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Marker extends Actor
{
    Bullet bullet;
    public Marker(Bullet bullet){
        //getImage().setTransparency(0);
        this.bullet = bullet;
    }
    /**
     * Act - do whatever the Marker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getObjectsInRange(bullet.speed/2, Bullet.class).contains(bullet)){
            getWorld().removeObject(this);
        }
    }    
}
