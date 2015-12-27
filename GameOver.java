import greenfoot.*;
import java.util.Timer;
/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    private boolean displayScore = false;
    private boolean createdTimer = false;
    int time =4;

    /**
     * Act - do whatever the GameOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Map map = (Map) getWorld();
        if (map.getGameStatus() && time>0){
            GreenfootImage image = getImage();
            image.scale(image.getWidth() * 2, image.getHeight() * 2);
            setImage(image);
            
        }
        time--;
        if (time <0) {
            setLocation(400,400);
            setImage ("GameOverScreen.png"); 
       
            Greenfoot.delay(200);
            Player player = (Player) getWorld().getObjects(Player.class).get(0);
            if (player !=null){ 
                //return to level.
                ((Map) getWorld()).fadeOut();
                
                Greenfoot.setWorld(new LevelSelector());
            }
        }
    }    

}
