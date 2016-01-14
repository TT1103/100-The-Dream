import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Used to allow the player to move between maps
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class Passage extends Actor
{
    int range =5;
    boolean containsBoss =false;
    boolean start =true;
    public void containsBoss(){ //cannot leave if there is a boss
        if(getWorld().getObjects(Boss.class).size()>0){
            containsBoss = true;
        }
    }
    
    public void act(){
        if(!containsBoss) containsBoss();
    }
    
}
