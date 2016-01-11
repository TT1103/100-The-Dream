import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class PlayerManaBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerManaBar extends UnScrollable
{
    int curMana;
    int maxMana;
    boolean start = true;
    Text text;
    int xSize =350;
    int ySize =20;
    
    public PlayerManaBar(int curMana, int maxMana){
        GreenfootImage bar = new GreenfootImage(xSize, ySize);
        bar.setColor(Color.MAGENTA);
        bar.fillRect(0,0, (int)( xSize*((double)curMana/maxMana)), getImage().getHeight());
        setImage(bar);
        this.maxMana=maxMana;
        this.curMana = curMana;
        text=new Text(String.valueOf(curMana) +"/"+ String.valueOf(maxMana));
    }
    
    /**
     * Act - do whatever the PlayerManaBar wants to do. This method is called whenever
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
    
    public void updateMana(int newCurMana, int newMaxMana){
        curMana=newCurMana;
        maxMana = newMaxMana;
        getImage().setTransparency(0);
        GreenfootImage newbar = new GreenfootImage(getImage().getWidth(), getImage().getHeight());
        setImage(newbar);
        
        newbar.setColor(Color.GRAY);
        newbar.fillRect(0,0, xSize, ySize);
        newbar.setColor(Color.MAGENTA);
        newbar.fillRect(0,0, (int)(xSize * ((double)curMana/maxMana)), getImage().getHeight());
        text.setText(String.valueOf(curMana) +"/"+ String.valueOf(maxMana));

    }
}
