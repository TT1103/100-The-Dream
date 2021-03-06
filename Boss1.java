import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.awt.Color;
/**
 * The first boss of the game.
 * 
 * @author Tiger Zhao
 * @version January 13, 2016
 */
public class Boss1 extends Boss
{
    int maxTurnDelay =2;
    int turnDelay =maxTurnDelay;
    int turnSpeed=1;
    boolean attacking = false;
    
    int maxAttackDelay =150;
    int attackDelay = maxAttackDelay;
    
    int curAttack;
    
    int laserCnt=0;
    int maxLaserAttackDelay =20;
    int laserAttackDelay = maxLaserAttackDelay;

    int maxMoveDelay =5;
    int moveDelay=maxMoveDelay;
    
    int bulletCnt=0;
    int maxBulletAttackDelay =5;
    int bulletAttackDelay = maxBulletAttackDelay;
    boolean start = true;
    
    BossHealthBar hpBar;
   
    /**
     * Act - do whatever the Boss1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(paused) return;
        
        if(start){
            start = false;
            hpBar = new BossHealthBar(20000, this);
            getWorld().addObject(hpBar, 400,775);
            getWorld().addObject(new Text("Alien Spaceship", 18,Color.BLACK),400,775);
        }
        controlMovement();
        controlWeapons();
        controlDeath();
    }   
    
    public void controlMovement(){
        if (turnDelay >0) turnDelay--;
        
        if(turnDelay ==0){
            turnDelay = maxTurnDelay;
            turn(turnSpeed);
        }
        
        if(!attacking){ //slowly move towards player
            if (moveDelay>0)moveDelay--;
            if(moveDelay==0){
                moveDelay = maxMoveDelay;
                Player p = (Player) getWorld().getObjects(Player.class).get(0);
                int originalRotation = getRotation();
                this.turnTowards(p.getX(), p.getY());
                int rotation = getRotation();
                this.setRotation(originalRotation);
        
                double s = 2;
                double cos = Math.cos(Math.toRadians(rotation));
                double sin = Math.sin(Math.toRadians(rotation));
                
                setLocation((int) (getX()+cos*s) ,(int) (getY()+sin*s));
            }
        }
        
    }
    
    public void controlWeapons(){
        Random rand = new Random();
        
        if (attackDelay >0 && !attacking) attackDelay--;
        
        if(attackDelay==0){
            attacking = true;
            curAttack = rand.nextInt(3);//0,1,2
            attackDelay =maxAttackDelay;
        }
        
        if(attacking){
            if(curAttack ==0){
                if(getWorld().getObjectsAt(getX(), getY(), Impassable.class).size() >0 ||getWorld().getObjectsAt(getX(), getY(), Sentry.class).size() >0 ){ //something is blocking
                    //shoot lasers instead
                    laserCnt =10;
                    attacking =false;
                    attackDelay +=laserCnt * laserAttackDelay;
                }else{
                    GreenfootSound effect = new GreenfootSound("spawn_effect.wav");
                    effect.setVolume(80);
                    effect.play();
                    Sentry sentry = new Sentry(4);
                    getWorld().addObject(sentry, getX(), getY());
  
                    attacking =false;
                }
            }else if(curAttack ==1){
                laserCnt =10;
                attacking =false;
                attackDelay +=laserCnt * laserAttackDelay;
            }else if(curAttack ==2){ //bullet barrage
                bulletCnt =50;
                attacking =false;
                attackDelay +=bulletCnt * bulletAttackDelay;
            }
        }

        if(laserCnt>0){
            if(laserAttackDelay >0) laserAttackDelay--;
            if(laserAttackDelay ==0){
                laserAttackDelay = maxLaserAttackDelay;
                GreenfootSound effect = new GreenfootSound("laser_shoot.wav");
                effect.setVolume(75);
                effect.play();
                Player p = (Player) getWorld().getObjects(Player.class).get(0);
                laserCnt--;
                int targetX = p.getX() + (rand.nextInt(200)-100); 
                int targetY= p.getY() + (rand.nextInt(200)-100); ;
                EnemyLaser laser = new EnemyLaser(10,50,targetX, targetY); //10,20
                laser.turnTowards(p.getX(),p.getY());
                getWorld().addObject(laser, getX(), getY()); 
            }
            if(laserCnt ==0){
                attacking =false;
            }
        }
        
        if(bulletCnt>0){
            if(bulletAttackDelay >0) bulletAttackDelay--;
            if(bulletAttackDelay ==0){
                bulletAttackDelay = maxBulletAttackDelay;
                GreenfootSound effect = new GreenfootSound("p90_shoot.wav");
                effect.setVolume(75);
                effect.play();
                Player p = (Player) getWorld().getObjects(Player.class).get(0);
                bulletCnt--;
                int targetX = p.getX() + (rand.nextInt(200)-100); 
                int targetY= p.getY() + (rand.nextInt(200)-100); ;
                EnemyBullet bullet = new EnemyBullet(10,20,targetX, targetY); //10,20
                bullet.turnTowards(p.getX(),p.getY());
                getWorld().addObject(bullet, getX(), getY()); 
            }
            
            if(bulletCnt ==0){
                attacking = false;
            }
        }
        
        
    }
    
    public void damage(int d){
        hpBar.damage(d);
        if (hpBar.health<=0){
            dead =true;
        }
    }
    
    public void controlDeath(){
        if(dead){
            Player p = (Player) getWorld().getObjects(Player.class).get(0);
            p.gainExp(3000);
            
            if(p.curGameLevel<2){ //increase the player's game progress
                p.curGameLevel=2;
                Text text = new Text("You have defeated the first boss!\nNew weapons and armor have been added to your inventory.\nExit this level and continue the game.", 24, Color.WHITE);
                getWorld().addObject(text, 400,200);
                p.addToInventory(new Sword(p));
                p.addToInventory(new SniperGun(p));
                p.addToInventory(new ArcaneExplosion(p));
                p.addToInventory(new IronHelmet());
                p.addToInventory(new IronChest());
                p.addToInventory(new IronLegs());
            }
            
            LevelExit exit = new LevelExit();
            getWorld().addObject(exit, getX(), getY());
            exit.removeBlocking();
            getWorld().removeObject(this);
        }
    }
    
    
}
