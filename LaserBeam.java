import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LaserBeam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LaserBeam extends Actor
{
    int timer = 20;
    public LaserBeam(){
        getImage().scale(100, 5);
    }

    /**
     * Act - do whatever the LaserBeam wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(timer-- == 0){
            GreenfootSound effect = new GreenfootSound("laser_shoot.wav");
            effect.setVolume(80);
            effect.play();
            getImage().scale(100, 50);
            Player player = (Player)getOneIntersectingObject(Player.class);
            if(player != null){
                player.damage(100);
                player.knockback=true;
                player.knockbackStrength=20;
                player.knockbackRotation=getRotation() + 90;
            }
        }
        if(timer < 0){
            getImage().setTransparency(getImage().getTransparency()-20);
            if(getImage().getTransparency() < 20)getWorld().removeObject(this);
        }
    }    
}
