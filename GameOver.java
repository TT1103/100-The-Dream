import greenfoot.*;
import java.util.Timer;
/**
 * Screen used to display a game over.
 * 
 * @author Gary Yu, Tiger Zhao
 * @version January 13, 2016
 */
public class GameOver extends Actor
{
    /**
     * Will animate the game over screen to indicate to users that they have depleted 
     * all their health. Game music will stop and level selection screen music will proceed. 
     */
    public void act() 
    {
        Map map = (Map) getWorld();
        if (map.getGameStatus()){
            scaleImage(4);
        }

        setLocation(400,400);
        setImage ("GameOverScreen.png"); 
   
        Greenfoot.delay(200);
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (player !=null){ 
            //return to level.
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
