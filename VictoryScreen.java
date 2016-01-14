import greenfoot.*;
import java.util.Timer;

/**
 * Screen used to display end of game storyline, as well as transition
 * back to level selection screen 
 * 
 * @author Gary Yu, Tiger Zhao 
 * @version January 13, 2016 
 */
public class VictoryScreen extends Actor
{
    /**
     * Will animate the victory screen to indicate to users that the game has ended and completed.
     * Users will then be returned to the level selection screen. 
     */
    public void act() 
    {
        Map map = (Map) getWorld();
        if (map.getGameCompletionStatus()){
            scaleImage(4);
        }

        setLocation(400,400);
        setImage ("VictoryScreen.png"); 
   
        Greenfoot.delay(400);
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (player !=null){ 
            //return to level selection
            player.saveData();
            ((Map) getWorld()).fadeOut();
            ((Map) getWorld()).music.stop();
            Greenfoot.setWorld(new LevelSelector()); // Goes back to level selection screen 
        }
        
    }    
    
    /**
     * This is a recursive method to generate a visual effect of the game over
     * screen inscreasing in size.
     * 
     * @param cnt An integer indicating the number of times to scale the image.
     */
    public void scaleImage(int cnt){
        if(cnt<=0) {
            return;
        }
        GreenfootImage image = getImage();
        image.scale(image.getWidth() * 2, image.getHeight() * 2);
        setImage(image);
        Greenfoot.delay(1);
        cnt--;
        scaleImage(cnt);
    }
}
