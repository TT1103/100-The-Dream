import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class MortarTarget extends Actor
{
    Mortar mortar;
    boolean paused =false;
    public MortarTarget(Mortar parent){
        mortar=parent;
        getImage().scale(80,80);
        getImage().setTransparency(0);
    }

    /**
     * Act - do whatever the MissileTarget wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused){
            return;
        }
        int distance=(Math.abs(mortar.getX()-getX())/2+Math.abs(mortar.getY()-getY())/2);
        if(255-distance<0){
            getImage().setTransparency(0);
        }else{
            getImage().setTransparency(255-distance);
        }

    }    
}
