import greenfoot.*;
import java.util.Timer;
/**
 * @author Gary Yu, Tiger Zhao
 * @version January 13, 2016
 */
public class GameOver extends Actor
{
    private boolean displayScore = false;
    private boolean createdTimer = false;


    /**
     * Act - do whatever the GameOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
            Greenfoot.setWorld(new LevelSelector());
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
