import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Used to exit a level back to the level selector.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class LevelExit extends Actor
{
    /**
     * Act - do whatever the LevelExit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Player player = (Player)getOneIntersectingObject(Player.class);
        if (player !=null && Math.sqrt(Math.pow(getX()-player.getX(),2) + Math.pow(getY()-player.getY(),2)) <32  ){ //player touches
            //return to level.
            ((Map) getWorld()).fadeOut();
            ((Map)getWorld()).music.stop();
            player.saveData();
            Greenfoot.setWorld(new LevelSelector());
        }
    }    
    
    /**
     * Remove objects that are blocking this exit.
     */
    public void removeBlocking(){
        getWorld().removeObjects(getIntersectingObjects(Impassable.class));
    }
}
