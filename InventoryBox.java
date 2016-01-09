import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InventoryBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InventoryBox extends GUI
{
    boolean pressed =false;
    Equipment item;
    /**
     * Act - do whatever the InventoryBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mousePressed(this)){
            pressed = true;
        }else{
            pressed=false;
        }
    }
    public void placeItem(Equipment item){
        item.setImage(item.itemImage);
        getWorld().addObject(item, getX(), getY());
    }
    
    public Equipment grabItem(){
        Equipment temp = item;
        item = null;
        return temp;
    }
}
