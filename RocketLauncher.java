import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RocketLauncher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        damage =1000;
        tooltip="Rocket Launcher\nA powerful but slow fire rate\nranged weapon. Explodes on impact.\nDamage: "+String.valueOf(damage);
    }
    /**
     * Act - do whatever the P90 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public void use(){ //x and y: current player pos
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
