import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpPassage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpPassage extends Passage
{
    /**
     * Act - do whatever the UpPassage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        
        Player p = (Player)getOneIntersectingObject(Player.class);
        if (p!=null && Math.abs(getY()-p.getY()) <range && !containsBoss){
            ((Map)getWorld()).changeMap(0,1);
        }
        
        if(p!=null && p.getY() <getY()-range){
            p.setLocation(p.getX(), getY()-range);
        }
    }    
}
