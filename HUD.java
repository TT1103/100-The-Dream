import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Displays health bar, exp bar, cur level, items, menu button, etc.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HUD extends UnScrollable 
{
    Player player;
    
    PlayerHealthBar hpBar;
    PlayerExpBar expBar;
    boolean start = true;
    
    //used to determine an update
    int prevHealth =0;
    int prevExp =0; 
    public HUD(Player p){
        this.player = p;
        hpBar = new PlayerHealthBar(player.curHealth, player.maxHealth);
        expBar = new PlayerExpBar(player.curExp, player.maxExp);
        player.getWorld().addObject(hpBar, hpBar.xSize/2,hpBar.ySize/2);
        player.getWorld().addObject(expBar, expBar.xSize/2,hpBar.ySize + expBar.ySize/2);
    }
    
    /**
     * Act - do whatever the HUD wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        controlHealth();
        controlExp();
        
    }   
    
    public void controlHealth(){
        if(prevHealth != player.curHealth){
            prevHealth = player.curHealth;
            hpBar.updateHealth(player.curHealth,player.maxHealth);
        }
    }
    
    public void controlExp(){
        if (prevExp != player.curExp){
            prevExp = player.curExp;
            expBar.updateExp(player.curExp,player.maxExp);
        }
    }
    
    public void displayText(String text, int cnt){
        Text t = new Text(text);
        getWorld().addObject(t,player.getX(),player.getY()-1);
    }
 
}
