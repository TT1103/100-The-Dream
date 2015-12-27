import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends UnScrollable
{
    int time =0;
    boolean timed = false;
    String text="";
    int fontSize=20;
    public Text(String text){
        this.text = text;
    }
    public Text(String text, int fontSize){
        this.text = text;
        this.fontSize = fontSize;
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
        
        setImage(new GreenfootImage(text, fontSize, Color.BLACK, null));
    }    
    
    public void setText(String text){
        this.text = text;
    }
}
