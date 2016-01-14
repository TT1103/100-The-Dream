import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class DownPassage extends Passage
{
    /**
     * Act - do whatever the DownPassage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        Player p = (Player)getOneIntersectingObject(Player.class);
        if (p!=null &&  Math.abs(getY()-p.getY()) <range && !containsBoss){
            ((Map)getWorld()).changeMap(0,-1);
        }
        
        if(p!=null && p.getY() >getY()+range){
            p.setLocation(p.getX(), getY()+range);
        }
    }
    
    
}
