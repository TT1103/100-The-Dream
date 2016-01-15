import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * A class to model a specific weapon's explosion that damages enemies. 
 * This class is slightly different from PlayerExplosion.
 * 
 * @author Tiger Zhao
 * @version January 14, 2015
 */
public class PlayerArcaneExplosion extends PlayerExplosion
{
    
    public PlayerArcaneExplosion(int damage){
        super(damage);
        images= new GreenfootImage[]{
            new GreenfootImage("arcaneexplosion1.png"), 
            new GreenfootImage("arcaneexplosion2.png"), 
            new GreenfootImage("arcaneexplosion3.png"), 
            new GreenfootImage("arcaneexplosion4.png"),
            new GreenfootImage("arcaneexplosion5.png")
        };
    }

    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        delay++;
        if(delay < 40){
            setImage(images[delay/8]);
            Map map=(Map)getWorld();
            
        }else{
            getWorld().removeObject(this);
        }
        if(delay <=1){
            List<Enemy> enemies = getIntersectingObjects(Enemy.class);
            for(Enemy e: enemies){
                if(e!=null){
                    e.damage(damage);
                }
            }
            

            
        }
        
        getImage().setTransparency(getImage().getTransparency()-4);
        turn(1);
        
    }      
}
