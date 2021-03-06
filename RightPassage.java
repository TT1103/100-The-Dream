import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Passage for moving right.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class RightPassage extends Passage
{
    /**
     * Act - do whatever the RightPassage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        Player p = (Player)getOneIntersectingObject(Player.class);
        if (p!=null &&  Math.abs(getX()-p.getX()) <range && !containsBoss){
            ((Map)getWorld()).changeMap(1,0);
        }
        
        if(p!=null && p.getX() >getX()+range){
            p.setLocation(getX() +range,p.getY());
        }
    }    
}
