import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A ranged weapon.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
 */
public class MachineGun extends Weapon
{
    int bulletSpeed =20;

    GreenfootImage gunSprite = new GreenfootImage("gun_sprite.png");

    public MachineGun(Player player){
        super(player);
        speedDelay=10;
        speed =10;
        name = "machinegun";
        damageType = "range";
        itemImage = new GreenfootImage("machinegun_item_image.png");
        damage=50;
        tooltip ="Machine Gun\nA low damage but high fire\nrate ranged weapon.\nDamage: "+String.valueOf(damage);
    }

    /**
     * Act - do whatever the MachineGun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    

    public void use(){ 
        GreenfootImage gunImage = new GreenfootImage(player.getImage());

        if (speedDelay >= speed){
            
            GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
            effect.setVolume(75);
            effect.play();
            speedDelay =0;
            PlayerBullet bullet = new PlayerBullet(bulletSpeed,damage+ player.precision*damageRatio);
            player.getWorld().addObject(bullet, player.getX(),player.getY());

        }
        gunImage.drawImage(gunSprite, 12,0);
        player.setImage(gunImage);
        
    }

}
