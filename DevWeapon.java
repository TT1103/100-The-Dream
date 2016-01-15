import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A special weapon used by the developers to test the game.
 * This ranged weapon is essentially an over powered sniper used to clear the game quickly.
 * 
 * @author Tiger Zhao
 * @version January 16, 2016
 */
public class DevWeapon extends Weapon
{
    int bulletSpeed =40;

    GreenfootImage gunSprite = new GreenfootImage("gun_sprite.png");
    
    public DevWeapon(Player player){
        super(player);
        speedDelay =7;
        speed=7;
        itemImage = new GreenfootImage("snipergun_item_image.png");
        name = "devweapon";
        damageType = "range";
        damage =5000;
        tooltip="Developer Weapon\nA weapon used for testing purposes.\nQuickly defeat enemies and clear the game.\nDo not use this if you intend to legitimately\nplay the game.\nDamage: "+String.valueOf(damage);
    }
    /**
     * Act - do whatever the DevWeapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public void use(){ 
       GreenfootImage gunImage = new GreenfootImage(player.getImage());
       gunImage.drawImage(gunSprite, 12,0);
       player.setImage(gunImage);
       
       if (speedDelay >= speed){
            GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
            effect.setVolume(75);
            effect.play();
            speedDelay =0;
            PlayerSniperBullet bullet = new PlayerSniperBullet(bulletSpeed,damage + player.precision*damageRatio);
            player.getWorld().addObject(bullet, player.getX(), player.getY());
        }
    }
}
