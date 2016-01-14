import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Used to represent the experience bar for the player.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
 */
public class PlayerExpBar extends Actor
{
    int curExp;
    int maxExp;
    boolean start = true;
    int xSize = 300;
    int ySize =15;
    Text text;
    public PlayerExpBar (int curExp, int maxExp){
        this.curExp = curExp;
        this.maxExp = maxExp;
        GreenfootImage bar = new GreenfootImage(xSize, ySize);
        bar.setColor(Color.GRAY);
        bar.fillRect(0,0, xSize, ySize);
        bar.setColor(Color.BLUE);
        bar.fillRect(0,0, (int)(xSize* ((double)curExp/maxExp)), ySize);
        setImage(bar);
        text=new Text(String.valueOf(curExp) +"/"+ String.valueOf(maxExp),15);
    }
    /**
     * Act - do whatever the PlayerExpBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(start){
            getWorld().addObject(text,getX(),getY());
            start=false;
        }
        
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0,0,getImage().getWidth()-1, getImage().getHeight()-1);
    }    
    
    public void updateExp(int newCurExp, int newMaxExp){
        curExp=newCurExp;
        maxExp = newMaxExp;
        getImage().setTransparency(0);
        GreenfootImage newbar = new GreenfootImage(getImage().getWidth(), getImage().getHeight());
        setImage(newbar);
        
        newbar.setColor(Color.GRAY);
        newbar.fillRect(0,0, xSize, ySize);
        newbar.setColor(Color.BLUE);
        newbar.fillRect(0,0, (int)(xSize * ((double)curExp/maxExp)), getImage().getHeight());
        text.setText(String.valueOf(curExp) +"/"+ String.valueOf(maxExp));
        //getWorld().showText(String.valueOf(health),getX(),getY());
    }
}
