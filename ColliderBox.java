import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An invisible box used as a collider for objects such as trees, which only collide in the center.
 * 
 * @author Tiger Zhao
 * @version December 20, 2015
 */
public class ColliderBox extends Impassable
{
    
    public ColliderBox(){ //default size is 32x32
    }
    
    public ColliderBox(int x, int y){
        GreenfootImage cur = getImage();
        cur.scale(x,y);
    }
    /**
     * Act - do whatever the HitBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }    
}
