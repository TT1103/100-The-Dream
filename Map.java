import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map extends World
{
    private Player player;
    private boolean gameOver = false;

    //absolute size of the world
    //int worldX =1200;
    //int worldY = 1200; 

    //current position within the world
    int curX =0;
    int curY=0;

    int curLevel;

    int curMapX=0; //indicates which map it is on
    int curMapY=0;
    GreenfootImage background = new GreenfootImage("grass.png");

    boolean fadedIn = false;
    

    /**
     * Constructor for objects of class Map.
     * 
     */
    public Map()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 
        generateGraph();
        player = new Player();
        addObject(player, 400,400);
        player.setup();

        // Layering the actors 
        setPaintOrder();
    }

    public Map(int level)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 
        player=new Player();
        addObject(player, 400,400);
        player.setDefaults();
        player.setup();
        curLevel = level;
        // Layering the actors 
        setPaintOrder();

        String mapFile = "data/level_"+level+"/"+level+"_map_0_0.txt";
        loadMap(mapFile);
        fadeIn();
    }

    public Map(int level, Player player)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1,false); 
        this.player = player;
        addObject(player, 400,400);
        player.setup();
        curLevel = level;
        // Layering the actors 
        setPaintOrder();

        String mapFile = "data/level_"+level+"/"+level+"_map_0_0.txt";
        loadMap(mapFile);
        fadeIn();
    }

    public Map(int level, Player player,String mapFile, String dirFrom, int newX, int newY){    
        super(800, 800, 1,false); 
        this.player=player;
        addObject(player, 400,400);
        player.setup();
        curLevel = level;
        setPaintOrder();
        curMapX=newX;
        curMapY=newY;
        loadMap(mapFile);
        System.out.println("Switched from: " + dirFrom);

        //used to set player position
        int offset =64; //off set for setting player location according to passages
        if(dirFrom.equals("down")){
            List<DownPassage> li = getObjects(DownPassage.class);
            DownPassage d = li.get(0);
            player.setLocation(d.getX(), d.getY()-offset);
        }else if(dirFrom.equals("up")){
            List<UpPassage> li = getObjects(UpPassage.class);
            UpPassage d = li.get(0);
            player.setLocation(d.getX(), d.getY()+offset);
        }else if(dirFrom.equals("right")){
            List<RightPassage> li = getObjects(RightPassage.class);
            RightPassage d = li.get(0);
            player.setLocation(d.getX()-offset, d.getY());
        }else if(dirFrom.equals("left")){
            List<LeftPassage> li = getObjects(LeftPassage.class);
            LeftPassage d = li.get(0);
            player.setLocation(d.getX()+offset, d.getY());
        }
    }

    public void setPaintOrder(){
        super.setPaintOrder(Shade.class,GameOver.class, Equipment.class,Text.class, Button.class,InventoryBox.class,PlayerMenu.class,PlayerHealthBar.class, PlayerExpBar.class, Tree.class, Explosion.class, Player.class);
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
        if (player.curHealth <= 0) {
            addObject (new GameOver(), 400, 400);
            gameOver = true;
        }
    }

    public void act() {
        if (!gameOver) { //if not gameover, check to see if it is
            endGame();     
        }

        scrollWorld();

        if(!fadedIn){
            fadedIn=true;
            fadeIn();
        }
    }

    public void scrollWorld(){
        int pX = player.getX();
        int pY=player.getY();
        int offY = pY-getHeight()/2;
        int offX = pX-getWidth()/2;

        if (offY ==0 && offX==0){ //already in middle, no need to move
            return;
        }

        //System.out.println(curX +" " + curY);
        player.setLocation(400,400);

        List<Actor> li = getObjects(null);
        for(Actor a : li){
            if(!a.getClass().equals(Player.class) && !a.getClass().equals(Button.class)&& !a.getClass().equals(HUD.class) && !a.getClass().equals(Text.class)&& !a.getClass().equals(PlayerHealthBar.class)&& !a.getClass().equals(PlayerExpBar.class)&& !a.getClass().equals(Slash.class)){
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
            for (x = offX; x > 0; x -= bg.getWidth()) ;
        }else {
            for (x = offX; x < 0; x += bg.getWidth()) ;
            x -= bg.getWidth();
        }
        if (offY > 0) {
            for (y = offY; y > 0; y -= bg.getHeight()) ;
        }else {
            for (y = offY; y < 0; y += bg.getHeight());
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

    public void loadMap(String filename){
        File file = new File(filename);
        try{
            Scanner input = new Scanner(file);

            if(input.hasNextLine()){
                String line = input.nextLine();
                //declare background
                setBackground(line+".png");
                //backgroundName = line;
            }
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] temp = line.split(" ");
                String name = temp[0];
                int x = Integer.valueOf(temp[1]);
                int y = Integer.valueOf(temp[2]);

                //if statement time....
                if(name.equals("tree")){
                    Tree t = new Tree();
                    addObject(t,x,y);
                }else if (name.equals("rock")){
                    Rock t = new Rock();
                    addObject(t,x,y);
                }else if (name.equals("sandbag")){
                    Sandbag t = new Sandbag();
                    addObject(t,x,y);
                }else if (name.equals("sentry")){
                    Sentry t = new Sentry();
                    addObject(t,x,y);
                    t.setup();
                }else if (name.equals("dog")){
                    Dog t = new Dog();
                    addObject(t,x,y);
                }else if (name.equals("mortar")){
                    MortarTower t = new MortarTower();
                    addObject(t,x,y);
                    t.setup();
                }else if (name.equals("gunman")){
                    Tree t = new Tree();
                    addObject(t,x,y);
                }else if (name.equals("knifeman")){
                    Tree t = new Tree();
                    addObject(t,x,y);
                }else if (name.equals("bush")){
                    Bush t = new Bush();
                    addObject(t,x,y);
                }else if (name.equals("leftpassage")){
                    LeftPassage t = new LeftPassage();
                    addObject(t,x,y);
                }else if (name.equals("rightpassage")){
                    RightPassage t = new RightPassage();
                    addObject(t,x,y);
                }else if (name.equals("downpassage")){
                    DownPassage t = new DownPassage();
                    addObject(t,x,y);
                }else if (name.equals("uppassage")){
                    UpPassage t = new UpPassage();
                    addObject(t,x,y);
                }else if (name.equals("levelexit")){
                    LevelExit t = new LevelExit();
                    addObject(t,x,y);
                }
            }
            input.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * x y indicates how much to change
     * y: + up ; - down
     * x: + right; - left
     * 
     */
    public void changeMap(int changeX, int changeY){ 
        int newX = curMapX+changeX;
        int newY = curMapY+changeY;
        String newMapFile = "data/level_"+curLevel+"/"+curLevel+"_map_"+newX+"_"+newY+".txt";

        String dirFrom="up";
        if (changeX >0){//going right
            dirFrom = "left";
        }else if (changeX <0){//going left
            dirFrom = "right";
        }if (changeY >0){//going up
            dirFrom = "down";
        }if (changeY <0){//going down
            dirFrom = "up";
        }
        System.out.println("Switching maps: "+ newMapFile + " "+ dirFrom);
        fadeOut();
        Greenfoot.setWorld(new Map(curLevel, player, newMapFile, dirFrom,newX,newY));

    }

    public void fadeIn(){

        Shade shade = new Shade();
        addObject(shade,getWidth()/2, getHeight()/2);

        for (int i =255; i >=0; i-=15){

            shade.getImage().setTransparency(i);
            Greenfoot.delay(1);
        }
        removeObject(shade);

    }

    public void fadeOut(){
        Shade shade = new Shade();
        addObject(shade,getWidth()/2, getHeight()/2);
        for (int i =0; i <=255; i+=15){
            shade.getImage().setTransparency(i);
            Greenfoot.delay(1);
        }
        // removeObject(shade);
    }
    
    public void pauseAll(){
        /*
         * objects to pause: player, bullets, enemy, enemy bullet, mortar, particle, mortar target,weapons
         */
        
        List<Player> a = getObjects(Player.class);
        for (Player p: a){
            p.paused =true;
        }
        
        List<Bullet> b = getObjects(Bullet.class);
        for (Bullet p: b){
            p.paused =true;
        }
        
        List<Enemy> c = getObjects(Enemy.class);
        for (Enemy p: c){
            p.paused =true;
        }
        
        List<EnemyBullet> d = getObjects(EnemyBullet.class);
        for (EnemyBullet p: d){
            p.paused =true;
        }
        
        List<Mortar> e = getObjects(Mortar.class);
        for (Mortar p: e){
            p.paused =true;
        }
        
        List<Particle> f = getObjects(Particle.class);
        for (Particle p: f){
            p.paused =true;
        }
        
        List<Weapon> g = getObjects(Weapon.class);
        for (Weapon p: g){
            p.paused =true;
        }
        
        List<MortarTarget> h = getObjects(MortarTarget.class);
        for (MortarTarget p: h){
            p.paused =true;
        }
    }
    
    public void unpauseAll(){
        List<Player> a = getObjects(Player.class);
        for (Player p: a){
            p.paused =false;
        }
        
        List<Bullet> b = getObjects(Bullet.class);
        for (Bullet p: b){
            p.paused =false;
        }
        
        List<Enemy> c = getObjects(Enemy.class);
        for (Enemy p: c){
            p.paused =false;
        }
        
        List<EnemyBullet> d = getObjects(EnemyBullet.class);
        for (EnemyBullet p: d){
            p.paused =false;
        }
        
        List<Mortar> e = getObjects(Mortar.class);
        for (Mortar p: e){
            p.paused =false;
        }
        
        List<Particle> f = getObjects(Particle.class);
        for (Particle p: f){
            p.paused =false;
        }
        
        List<Weapon> g = getObjects(Weapon.class);
        for (Weapon p: g){
            p.paused =false;
        }
        
        List<MortarTarget> h = getObjects(MortarTarget.class);
        for (MortarTarget p: h){
            p.paused =false;
        }
    }

}
