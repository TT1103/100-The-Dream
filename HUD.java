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
    PlayerManaBar manaBar;
    boolean start = true;
    
    //used to determine an update
    int prevHealth =0;
    int prevExp =0; 
    
    int prevMana=0;
    
    Button menuButton;
    
    PlayerMenu menu;
    public HUD(Player p){
        this.player = p;
        hpBar = new PlayerHealthBar(player.curHealth, player.maxHealth);
        expBar = new PlayerExpBar(player.curExp, player.maxExp);
        manaBar = new PlayerManaBar(player.curMana, player.maxMana);
        player.getWorld().addObject(hpBar, hpBar.xSize/2,hpBar.ySize/2);
        player.getWorld().addObject(manaBar, manaBar.xSize/2,hpBar.ySize + manaBar.ySize/2);
        player.getWorld().addObject(expBar, expBar.xSize/2,hpBar.ySize +manaBar.ySize+ expBar.ySize/2);
        
        menuButton = new Button ("player_menu_button.png");
        player.getWorld().addObject(menuButton, 736,14);
   
    }
    
    /**
     * Act - do whatever the HUD wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        controlHealth();
        controlExp();
        controlMana();
        controlMenu();
    }   
    
    public void controlHealth(){
        if(prevHealth != player.curHealth){
            prevHealth = player.curHealth;
            hpBar.updateHealth(player.curHealth,player.maxHealth);
        }
    }
    
    public void controlMana(){
        if(prevMana != player.curMana){
            prevMana = player.curMana;
            manaBar.updateMana(player.curMana,player.maxMana);
        }
    }
    
    public void controlExp(){
        if (prevExp != player.curExp){
            prevExp = player.curExp;
            expBar.updateExp(player.curExp,player.maxExp);
        }
    }
    
    public void controlMenu(){
        if(!player.paused){
            String recentKey = Greenfoot.getKey();
            if(menuButton.pressed ||  (recentKey!=null && recentKey.equals("e"))){
                
                menu = new PlayerMenu(player);
                player.getWorld().addObject(menu, 400,400);
         
            }
        }
    }
    
    public void displayText(String text, int cnt){
        Text t = new Text(text);
        player.getWorld().addObject(t,player.getX(),player.getY()-1);
    }
 
}
