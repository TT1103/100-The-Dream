import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Barrett here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SniperGun extends Weapon
{
    int bulletSpeed =40;
    int bulletDamage=400;
    GreenfootImage gunSprite = new GreenfootImage("gun_sprite.png");
    
    public SniperGun(Player player){
        //super("barrett");
        super(player);
        speedDelay =5;//50
        speed=5;
        itemImage = new GreenfootImage("snipergun_item_image.png");
        name = "snipergun";
        damageType = "range";
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
            GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
            effect.setVolume(75);
            effect.play();
            speedDelay =0;
            PlayerSniperBullet bullet = new PlayerSniperBullet(bulletSpeed,bulletDamage);
            player.getWorld().addObject(bullet, player.getX(), player.getY());
        }
    }
}
