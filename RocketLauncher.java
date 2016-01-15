import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A ranged weapon.
 * 
 * @author Tiger Zhao
 * @version January 14, 2016
 */
public class RocketLauncher extends Weapon
{
    int bulletSpeed =25;

    GreenfootImage gunSprite = new GreenfootImage("gun_sprite.png");
    
    public RocketLauncher(Player player){

        super(player);
        speedDelay =25;
        speed=25;
        itemImage = new GreenfootImage("rocketlauncher_item_image.png");
        name = "rocketlauncher";
        damageType = "range";
        damage =1500;
        tooltip="Rocket Launcher\nA powerful but slow fire rate\nranged weapon. Explodes on impact.\nDamage: "+String.valueOf(damage);
    }
    
    /**
     * Act - do whatever the RocketLauncher wants to do. This method is called whenever
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
            GreenfootSound effect = new GreenfootSound("rocket_shoot.wav");
            effect.setVolume(75);
            effect.play();
            speedDelay =0;
            PlayerRocket rocket = new PlayerRocket(bulletSpeed,damage+ player.precision*damageRatio);
            player.getWorld().addObject(rocket, player.getX(), player.getY());
        }
    }
}
