import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ArcaneLaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArcaneLaser extends Weapon
{
    int manaCost =0;
    
    public ArcaneLaser(Player player){
        super(player);
        speed =30;
        speedDelay =speed;
        damage =400;
        name = "arcanelaser";
        damageType = "magic";
        itemImage = new GreenfootImage("arcanelaser_item_image.png");
        tooltip = "Arcane Laser\nA high damage, medium\nfire rate magic weapon.\nLasers target far away enemies.\nDamage: "+String.valueOf(damage)+"\nMana Cost: "+String.valueOf(manaCost);

    }
    /**
     * Act - do whatever the ArcaneMissiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        super.act();
    }    
    
    public void use(){
        if(speedDelay>=speed && player.curMana >= manaCost){
            GreenfootSound effect = new GreenfootSound("laser_shoot.wav");
            effect.setVolume(70);
            effect.play();
            speedDelay=0;
            PlayerArcaneLaser laser = new PlayerArcaneLaser(20,damage+player.intelligence*damageRatio);
            player.reduceMana(manaCost);
            player.getWorld().addObject(laser, player.getX(), player.getY());
        }else if(player.curMana < manaCost){
            player.attacking =false;
        }
    } 
}
