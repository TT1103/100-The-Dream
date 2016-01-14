import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class used to model an inventory box.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class InventoryBox extends Actor
{
    boolean pressed =false;
    Equipment item;
    int delay;
    GreenfootImage image = new GreenfootImage("inventory_box.png");
    
    /**
     * Act - do whatever the InventoryBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

        MouseInfo mi = Greenfoot.getMouseInfo();
        if(mi !=null){
            if(mi.getX() > getX()-24 && mi.getX() < getX()+24 && mi.getY() >getY()-24 && mi.getY()< getY()+24 && Greenfoot.mouseClicked(null)){
                pressed =true;
            }else{
                pressed =false;
            }
        }else{
            pressed =false;
        }
    }

    
    /**
     * Assigns an Equipment object to this inventory box.
     * 
     * @param item The Equipment object to assign to this box.
     */

    public void placeItem(Equipment item){
        if(item == null) return;
        this.item =item;
        item.setImage(item.itemImage);
        getWorld().addObject(item, getX(), getY());
        item.setLocation(getX(),getY());
    }

    /**
     * Removes the item and returns it.
     * 
     * @return An Equipment object that was in this inventory box.
     */

    public Equipment grabItem(){
        Equipment temp = item;
        item = null;
        return temp;
    }
}
