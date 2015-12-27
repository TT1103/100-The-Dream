import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DownPassage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DownPassage extends Passage
{
    /**
     * Act - do whatever the DownPassage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Player p = (Player)getOneIntersectingObject(Player.class);
        if (p!=null &&  Math.abs(getY()-p.getY()) <range){
            ((Map)getWorld()).changeMap(0,-1);
        }
    }
    
    
}
