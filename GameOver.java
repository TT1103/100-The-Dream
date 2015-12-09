import greenfoot.*;

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends GameOverElements
{
    private boolean displayScore = false;
    private boolean initializedTimer = false;

    /**
     * Act - do whatever the GameOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Map map = (Map) getWorld();
        if (map.getGameStatus()){
            GreenfootImage image = getImage();
            image.scale(image.getWidth() + 400, image.getHeight() + 400);
            setImage(image);
        }
    }    
}
