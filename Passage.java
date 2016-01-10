import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Passage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
