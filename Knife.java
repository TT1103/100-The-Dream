import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knife here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knife extends Weapon
{

    
    
    public Knife(Player player){
        super(player);
        speedDelay=18;
        speed =18;
        itemImage = new GreenfootImage("knife_item_image.png");
        name = "knife";
        damageType="melee";
        damage=30;
        tooltip = "Knife\nAn average melee weapon.\nDamage: "+String.valueOf(damage);
    }
    /**
     * Act - do whatever the Knife wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public void use(){
        
        if(speedDelay >=speed){
            Slash s = new Slash(damage + player.dexterity*damageRatio);
            player.getWorld().addObject(s, player.getX(),player.getY());
            GreenfootSound effect = new GreenfootSound("slash_effect.wav");
            effect.setVolume(80);
            effect.play();
            speedDelay =0;
        }
    }
}
