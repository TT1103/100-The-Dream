import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A marker is used to help accurately guide the player's bullets.
 * 
 * @author Enoch Poon
 * @version January 14, 2016
 */
public class Marker extends Actor
{
    PlayerProjectile bullet;
    public Marker(PlayerProjectile bullet){
        getImage().setTransparency(0);
        this.bullet = bullet;
    }
    /**
     * Act - do whatever the Marker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getObjectsInRange(bullet.speed/2, PlayerProjectile.class).contains(bullet)){
            getWorld().removeObject(this);
        }
    }    
}
