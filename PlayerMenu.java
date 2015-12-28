import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
/**
 * Used to display inventory and stats.
 * Allows player to equip weapons, change stats and etc.
 * 
 * @author Tiger Zhao
 * @version December 28, 2015
 */
public class PlayerMenu extends GUI
{
    Player player;
    Button closeButton;
    List<Object> obj = new ArrayList<Object>();
    Button[] addStatButtons = new Button[6];
    int statFontSize=20;
    Color statColor = Color.WHITE;
    Text[] statTexts = new Text[]{ 
        new Text("Precision",statFontSize,statColor), new Text("Intelligence",statFontSize,statColor),
        new Text("Dexterity",statFontSize,statColor), new Text("Defense",statFontSize,statColor), 
        new Text("Endurance",statFontSize,statColor), new Text("Spirituality",statFontSize,statColor),
        new Text("Stat Points",statFontSize,statColor)
    };
    Text[] statValues = new Text[7];
    /*
     * 0 precision
     * 1 intelligence
     * 2 dexterity
     * 3 defense
     * 4 endurance
     * 5 spirituality
     * 6 stat points
     */
    public PlayerMenu(Player player){
        this.player = player;
        closeButton = new Button("close_button.png");
        player.getWorld().addObject(closeButton, 736,14);
        ((Map)player.getWorld()).pauseAll();
        displayStats();
        displayInventory();
        obj.add(closeButton);
    }
    
    /**
     * Act - do whatever the PlayerMenu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {

        controlStats();
        controlInventory();
        
        if(closeButton.pressed){
            ((Map)player.getWorld()).unpauseAll();
            //getWorld().removeObject(closeButton);
            getWorld().removeObjects(obj);
            getWorld().removeObject(this);
            return;
        }
        
    }    
    
    public void controlStats(){
        int i = 0;
        for (Button b: addStatButtons){
            if(b.pressed && player.curStatPoints>0){
                player.curStatPoints--;
                String newText="";
                if(i ==0){
                    player.precision++;
                    newText+=player.precision;
                }else if(i ==1){
                    player.intelligence++;
                    newText+=player.intelligence;
                }else if(i ==2){
                    player.dexterity++;
                    newText+=player.dexterity;
                }else if(i ==3){
                    player.defense++;
                    newText+=player.defense;
                }else if(i ==4){
                    player.endurance++;
                    newText+=player.endurance;
                }else if(i ==5){
                    player.spirituality++;
                    newText+=player.spirituality;
                }
                statValues[i].setText(newText);
                statValues[6].setText(String.valueOf(player.curStatPoints));
                player.updateStats();
            }
            i++;
        }
        
    }
    
    public void controlInventory(){
    }
    
    public void displayStats(){
        for (int i =0; i < 6;i++){
            addStatButtons[i] = new Button("add_button.png");
            obj.add(addStatButtons[i]);
        }
        statValues[0] = new Text(String.valueOf(player.precision), statFontSize, statColor);
        statValues[1] = new Text(String.valueOf(player.intelligence), statFontSize, statColor);
        statValues[2] = new Text(String.valueOf(player.dexterity), statFontSize, statColor);
        statValues[3] = new Text(String.valueOf(player.defense), statFontSize, statColor);
        statValues[4] = new Text(String.valueOf(player.endurance), statFontSize, statColor);
        statValues[5] = new Text(String.valueOf(player.spirituality), statFontSize, statColor);
        statValues[6] = new Text(String.valueOf(player.curStatPoints), statFontSize, statColor);
        
        int y =75;
        int yChange=30;
        for (Text t: statTexts){
            player.getWorld().addObject(t,115,y);
            obj.add(t);
            y+=yChange;
        }

        y =75;
        for (Button a : addStatButtons){
            player.getWorld().addObject(a,240,y);
            obj.add(a);
            y+=yChange;
        }
        
        y =75;
        for (Text s : statValues){
            player.getWorld().addObject(s,200,y);
            obj.add(s);
            y+=yChange;
        }
        
    }
    
    public void displayInventory(){
    }
    
    
}
