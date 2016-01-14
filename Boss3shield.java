import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A shield used by Boss3.
 * 
 * @author Enoch Poon
 * @version January 14, 2016
 */
public class Boss3shield extends Actor
{
    boolean paused =false;
    public Boss3shield(){
        getImage().setTransparency(0);
    }
    /**
     * Act - do whatever the Boss3shield wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        getImage().scale(150, 150);
        getImage().setTransparency(100);
        if(getImage().getTransparency() < 100){
            getImage().setTransparency(getImage().getTransparency() + 10);
        }
        //makes boss invulnerable to all ranged attacks
        //makes boss more resistant to melee attacks
        //breaks when all spawns are killed
        //fades slowly, then explodes when fully faded
        if(getWorld().getObjects(Enemy.class).size() <= 1){
            getWorld().addObject(new ExplodeWarning(), getX(), getY());
            getWorld().removeObject(this);
            return;
        }
        
            
        //heals boss slowly
    }    
}
