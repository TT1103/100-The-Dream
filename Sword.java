import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sword here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sword extends Weapon
{
    public Sword(Player player){
        super(player);
        speedDelay=15;
        speed =15;
        itemImage = new GreenfootImage("sword_item_image.png");
        name = "sword";
        damageType="melee";
        damage=400;
        tooltip = "Sword\nA powerful melee weapon.\nGood for dismembering dogs.\nDamage: "+String.valueOf(damage);
    }
    /**
     * Act - do whatever the Sword wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public void use(){
        
        if(speedDelay >=speed){
            Slash s = new Slash(damage+player.dexterity*damageRatio);
            player.getWorld().addObject(s, player.getX(),player.getY());
            GreenfootSound effect = new GreenfootSound("slash_effect.wav");
            effect.setVolume(80);
            effect.play();
            speedDelay =0;
        }
    }
}
