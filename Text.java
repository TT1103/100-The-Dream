import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Used to easily display text on the screen.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
 */
public class Text extends Actor
{
    int time =0;
    boolean timed = false;
    String text="";
    int fontSize=20;
    boolean setColor =false;
    Color color;
    public Text(String text){
        this.text = text;
    }
    public Text(String text, int fontSize){
        this.text = text;
        this.fontSize = fontSize;
    }
    
    public Text(String text, int fontSize,Color color){
        this.text = text;
        this.fontSize = fontSize;
        setColor = true;
        this.color = color;
    }
    
    public Text(int time, String text){
        this.text = text;
        this.timed = true;
        this.time = time;
    }
    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(timed){
            time--;
            if(time <=0){
                getWorld().removeObject(this);
                return;
            }
        }
        if(setColor){
            setImage(new GreenfootImage(text, fontSize, color, null));  
        }else{
            setImage(new GreenfootImage(text, fontSize, Color.BLACK, null));
        }
    }    
    
    public void setText(String text){
        this.text = text;
    }
}
