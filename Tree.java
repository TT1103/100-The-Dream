import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class used to model tree. 
 * Note: A tree is not completely impassable, only the trunk is.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class Tree extends Actor
{
    ColliderBox cb;
    boolean start = false;
    public Tree(){
        cb = new ColliderBox(); //collider box to represent the tree trunk
    }
    
    /**
     * Act - do whatever the Tree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(! start){
            getWorld().addObject(cb, getX(), getY());
            start = true;
        }
    }    
}
