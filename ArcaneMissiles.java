import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ArcaneMissiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArcaneMissiles extends Weapon
{
    public ArcaneMissiles(Player player){
        super(player);
        speed =10;
        speedDelay =speed;
        damage =35;
        name = "arcanemissiles";
        itemImage = new GreenfootImage("arcanemissiles_item_image.png");
    }
    /**
     * Act - do whatever the ArcaneMissiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    public void use(){
        if(speedDelay>=speed){
            GreenfootSound effect = new GreenfootSound("arcanemissile_effect.wav");
            effect.setVolume(75);
            effect.play();
            speedDelay=0;
            PlayerArcaneMissile am = new PlayerArcaneMissile(10,damage);
            
            player.getWorld().addObject(am, player.getX(), player.getY());
        }
    }
}
