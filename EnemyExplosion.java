import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyExplosion extends Actor
{
    Player player;
    GreenfootImage[]images={
            new GreenfootImage("explosion1.png"), 
            new GreenfootImage("explosion2.png"), 
            new GreenfootImage("explosion3.png"), 
            new GreenfootImage("explosion4.png")};
           
    int delay=0;
    boolean paused = false;

    int damage = 50;
    
    public EnemyExplosion(Player p, int damage){
        this.damage= damage;
    }

    public EnemyExplosion(Player p){
        player=p;
        GreenfootSound effect = new GreenfootSound("explosion_effect.wav");
        effect.setVolume(80);
        effect.play();
    }


    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        delay++;
        if(delay < 32){
            setImage(images[delay/8]);
            Map map=(Map)getWorld();
            
        }else{
            getWorld().removeObject(this);

            return;
        }
        
        if(delay <=1 && isTouching(Player.class)){
            player.damage(50);

            player.knockback = true;
            player.knockbackStrength = 15;
            turnTowards(player.getX(),player.getY());
            player.knockbackRotation = getRotation();
            
        }
        getImage().setTransparency(getImage().getTransparency()-5);
        turn(1);
        
    }    
}