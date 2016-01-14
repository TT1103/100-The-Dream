import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A melee weapon.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
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
        damage=40;
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
