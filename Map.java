import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map extends World
{

    /**
     * Constructor for objects of class Map.
     * 
     */
    public Map()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 
        generateGraph();
        Player player = new Player();
        addObject(player, 400,400);
        player.setup();

        // Layering the actors 
        setPaintOrder (PlayerHealthNumber.class, PlayerHealth.class,Player.class);
        
        //instructionScreen();

        for (int i =300; i < 700; i+=30){

            addObject(new Rock(), i,300);
        }

        for (int i =0; i < 800;i++){
            if (i %30==0){
                addObject(new Rock(), i,0);
                addObject(new Rock(), i,800);
                addObject(new Rock(), 800,i);
                addObject(new Rock(), 0,i);
            }
        }

        for(int i =1 ; i< 5; i++){
            Dog d = new Dog(100);
            addObject(d, i*50, 600);
            d.setup();
        }

        Sentry s=new Sentry(500);
        addObject(s, 600, 600);
        s.setup();
        MortarTower m=new MortarTower(1000);
        addObject(m, 100,100);
        m.setup();
    }


    public void generateGraph(){
        for (int x = 10 ; x <= 790; x+=10){
            for (int y =10 ; y <=790; y+=10){
                Enemy.graph.put(String.valueOf(x) + " " + String.valueOf(y), new int[][] {{x+10,y-10},{x+10,y+10},{x-10,y-10},{x-10,y+10}});
            }
        }
    }

}
