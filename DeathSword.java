import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeathSword here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathSword extends Weapon
{
    public DeathSword(Player player){
        super(player);
        speedDelay=12;
        speed =12;
        itemImage = new GreenfootImage("deathsword_item_image.png");
        name = "deathsword";
        damageType="melee";
        damage=900;
        tooltip = "Dead Sword\nA deadly melee weapon.\nUsed by the devel to slaughter\nonions for delicious stew.\nDamage: "+String.valueOf(damage);
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
