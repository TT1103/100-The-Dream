import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tree extends Actor
{
    ColliderBox cb;
    boolean start = false;
    public Tree(){
        cb = new ColliderBox();
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
