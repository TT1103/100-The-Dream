import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ArcaneExplosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArcaneExplosion extends Weapon
{
    int manaCost =50;
    
    public ArcaneExplosion(Player player){
        super(player);
        speed =20;
        speedDelay =speed;
        damage =125;
        name = "arcanemissiles";
        damageType = "magic";
        itemImage = new GreenfootImage("arcaneexplosion_item_image.png");
        tooltip = "Arcane Explosion\nAn average-high damage, medium\nfire rate magic weapon.\nRelease a deadly explosion\nof magic around you.\nDamage: "+String.valueOf(damage)+"\nMana Cost: "+String.valueOf(manaCost);

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
            GreenfootSound effect = new GreenfootSound("arcanemissile_effect.wav");
            effect.setVolume(70);
            effect.play();
            speedDelay=0;
            PlayerArcaneMissile am = new PlayerArcaneMissile(10,damage+player.intelligence*damageRatio);
            player.reduceMana(manaCost);
            player.getWorld().addObject(am, player.getX(), player.getY());
        }else if(player.curMana < manaCost){
            player.attacking =false;
        }
    } 
}
