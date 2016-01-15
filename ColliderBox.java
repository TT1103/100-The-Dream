import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An invisible box used as a collider for objects such as trees, which only collide in the center.
 * 
 * @author Tiger Zhao
 * @version December 20, 2015
 */
public class ColliderBox extends Impassable
{
    /**
     * Default constructor.
     * The default size of the box is 32 by 32
     */
    public ColliderBox(){ 
    }
    
    /**
     * A constructor used to create a specific size of the box.
     * 
     * @param x The width of the box to create.
     * @param y The height of the box to create.
     */
    public ColliderBox(int x, int y){
        GreenfootImage cur = getImage();
        cur.scale(x,y);
    }

}
