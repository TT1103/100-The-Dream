import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A magic weapon.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
 */
public class ArcaneExplosion extends Weapon
{
    int manaCost =5;
    
    public ArcaneExplosion(Player player){
        super(player);
        speed =20;
        speedDelay =speed;
        damage =350;
        name = "arcaneexplosion";
        damageType = "magic";
        itemImage = new GreenfootImage("arcaneexplosion_item_image.png");
        tooltip = "Arcane Explosion\nA medium damage, slow\nfire rate magic weapon.\nRelease a deadly explosion\nof magic at where you aim.\nDamage: "+String.valueOf(damage)+"\nMana Cost: "+String.valueOf(manaCost);

    }
    /**
     * Act - do whatever the ArcaneExplosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        super.act();
    }    
    
    public void use(){
        if(speedDelay>=speed && player.curMana >= manaCost){
            
            speedDelay=0;
            PlayerArcaneExplosion am = new PlayerArcaneExplosion(damage+player.intelligence*damageRatio);
            player.reduceMana(manaCost);
            MouseInfo mi = Greenfoot.getMouseInfo();
            if(mi !=null){
                player.getWorld().addObject(am, mi.getX(), mi.getY());
            }
        }else if(player.curMana < manaCost){
            player.attacking =false;
        }
    } 
}
