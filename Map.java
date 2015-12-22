import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map extends World
{
    private Player player = new Player();
    private boolean gameOver = false;
    
    //absolute size of the world
    int worldX =1200;
    int worldY = 1200; 
    
    //current position within the world
    int curX =600;
    int curY=600;
    
    GreenfootImage background = new GreenfootImage("grass.png");
    /**
     * Constructor for objects of class Map.
     * 
     */
    public Map()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 
        generateGraph();
        addObject(player, 400,400);
        player.setup();

        // Layering the actors 
        setPaintOrder (GameOver.class, PlayerHealthNumber.class, PlayerHealth.class, Tree.class,Player.class);

        for (int i =300; i < 700; i+=30){

            addObject(new Rock(), i,300);
        }

        for (int i =0; i < 800;i++){
            if (i %30==0){
                addObject(new Rock(), i,12);
                addObject(new Rock(), i,788);
                addObject(new Rock(), 788,i);
                //addObject(new Rock(), 12,i);
            }
        }

        for(int i =1 ; i< 2; i++){
            Dog d = new Dog(100);
            //addObject(d, i*50, 600);
            //d.setup();
        }

        Sentry s=new Sentry(500);
        addObject(s, 600, 600);
        s.setup();
        MortarTower m=new MortarTower(1000);
        addObject(m, 100,100);
        m.setup();
        
        
        Tree t = new Tree();
        addObject(t,300,500);
        Tree t1 = new Tree();
        addObject(t1,350,600);
        Tree t2 = new Tree();
        addObject(t2,250,400);
    }


    public void generateGraph(){
        for (int x = 10 ; x <= 790; x+=10){
            for (int y =10 ; y <=790; y+=10){
                Enemy.graph.put(String.valueOf(x) + " " + String.valueOf(y), new int[][] {{x+10,y-10},{x+10,y+10},{x-10,y-10},{x-10,y+10}});
            }
        }
    }
    
    /**
     * Method used to end the game and transition to game over screen with score.
     * Code is currently not running. More implementations soon.
     */
    public void endGame () {
        if (player.getHealth() <= 0) {
           //addObject (new GameOver(), player.getX(), player.getY());
           //gameOver = true;
        }
    }

    public void act() {
        if (!gameOver) {
            endGame();     
        }
        scrollWorld();
    }
    
    public void scrollWorld(){
        int pX = player.getX();
        int pY=player.getY();
        int offY = pY-getHeight()/2;
        int offX = pX-getWidth()/2;
        
        if (offY ==0 && offX==0){ //already in middle, no need to move
            return;
        }
        
        /*if(curX + offX > worldX){
            offX = worldX-curX;
        }else if(curX+offX<0){
            offX = curX;
        }else if(curY + offY >worldY){
            offY = worldY-curY;
        }else if(curY+offY<0){
            offY = curY;
        }
        */
        //System.out.println(curX +" " + curY);
        player.setLocation(400,400);
        
        List<Actor> li = getObjects(null);
        for(Actor a : li){
            if(!a.getClass().equals(Player.class) && !a.getClass().equals(PlayerHealth.class) && !a.getClass().equals(PlayerHealthNumber.class)){
                a.setLocation(a.getX()-offX,a.getY()-offY);
                
            }
            
        }
        
        scrollBackground(-offX,-offY);
        curX+=offX;
        curY += offY;
    }
    
    protected final void scrollBackground(int offX, int offY) {
        int x;
        int y;
        GreenfootImage bg = new GreenfootImage(getBackground());
        if (offX > 0) {
            for (x = offX; x > 0; x -= bg.getWidth()) {
                ;
            }
        }
        else {
            for (x = offX; x < 0; x += bg.getWidth()) {
                ;
            }
            x -= bg.getWidth();
        }
        if (offY > 0) {
            for (y = offY; y > 0; y -= bg.getHeight()) {
                ;
            }
        }
        else {
            for (y = offY; y < 0; y += bg.getHeight()) {
                ;
            }
            y -= bg.getHeight();
        }
        getBackground().clear();
        for (int i = x; i < getWidth(); i += bg.getWidth()) {
            for (int j = y; j < getHeight(); j += bg.getHeight()) {
                getBackground().drawImage(bg, i, j);
            }
        }
    }
    public boolean getGameStatus() {
        return gameOver;
    }
    
    public int getWorldX(){ //return absolute x
        return curX;
    }
    
    public int getWorldY(){//return absolute y
        return curY;
    }

}
