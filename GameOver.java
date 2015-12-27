import greenfoot.*;

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
    Stopwatch s;

    /**
     * Act - do whatever the GameOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Map map = (Map) getWorld();
        if (map.getGameStatus()){
            GreenfootImage image = getImage();
            image.scale(image.getWidth() * 2, image.getHeight() * 2);
            setImage(image);
            if (!createdTimer) {
                s = new Stopwatch();
                createdTimer = true;
            }
        }
        if (createdTimer && s.elapsedTime() >= 0.05) {
            setImage ("GameOverScreen.png");  
            Greenfoot.stop();
        }
    }    

}
