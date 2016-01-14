import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Displays health bar, exp bar, cur level, and other player related stats during gameplay.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class HUD extends Actor 
{
    Player player;
    
    PlayerHealthBar hpBar;
    PlayerExpBar expBar;
    PlayerManaBar manaBar;
    boolean start = true;
    
    //used to determine an update if these change
    int prevHealth =0;
    int prevExp =0; 
    
    int prevMana=0;
    
    Button menuButton;
    
    PlayerMenu menu;
    
    /**
     * The constructor for the HUD. 
     * 
     * @param p The player object that the HUD represents.
     */
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
    
    /**
     * Manages and updates the health.
     */
    public void controlHealth(){
        if(prevHealth != player.curHealth){
            prevHealth = player.curHealth;
            hpBar.updateHealth(player.curHealth,player.maxHealth);
        }
    }
    
    /**
     * Manages and updates the mana.
     */
    public void controlMana(){
        if(prevMana != player.curMana){
            prevMana = player.curMana;
            manaBar.updateMana(player.curMana,player.maxMana);
        }
    }
    
    /**
     * Manages and updates the experience.
     */
    public void controlExp(){
        if (prevExp != player.curExp){
            prevExp = player.curExp;
            expBar.updateExp(player.curExp,player.maxExp);
        }
    }
    
    /**
     * Manages and updates the menu.
     */
    public void controlMenu(){
        if(!player.paused){
            String recentKey = Greenfoot.getKey();
            if(menuButton.pressed ||  (recentKey!=null && recentKey.toLowerCase().equals("e"))){
                
                menu = new PlayerMenu(player);
                player.getWorld().addObject(menu, 400,400);
         
            }
        }
    }
 
 
}
