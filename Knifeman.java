import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knifeman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knifeman extends Enemy
{
    private GreenfootImage[]strike=new GreenfootImage[5];
    private int striketimer=0;
    private boolean attack=false;
    private GreenfootImage[]walk;
    private int walktimer=0;
    private boolean running=false;
    private boolean hit=false;
    public Knifeman(int health){
        super(health);
    }

    /**
     * Act - do whatever the Knifeman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        if(getObjectsInRange(30,Player.class)!=null){
            attack=true;
        }
        running=(canSeePlayer())?true:false;
        if(attack){
            attack();
            walktimer=0;
        }else{
            walktimer++;
            int a=(running)? 4:6;
            setImage(walk[walktimer/a]);
        }
        move((canSeePlayer())? 15:9);
    }
    public boolean hit(){
        return hit;
    }
    public void attack(){
        striketimer++;
        setImage(strike[striketimer/4]);
        hit=(striketimer>16)? true:false;//deals damage only when in 4th or 5th frame
        if(striketimer==23){
            attack=false;
            striketimer=0;
        }
    }
}
