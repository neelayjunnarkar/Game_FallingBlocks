package fallingBlocks;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EnemyManager {
	private ArrayList<EnemyBlocks> enemies = new ArrayList<EnemyBlocks>();
	private Main instance;
	private int enemyCap;
	private int enemyCapOriginal;
	public int enemyYDTrue= 8;
	private int enemyYDOriginal = enemyYDTrue;
	public long millisInit;
	public boolean millisInitBool = false;
	public long millisCurrent;
	
	public EnemyManager(Main instance, int enemyCap){
		this.instance = instance;
		this.enemyCap = enemyCap;
		enemyCapOriginal = this.enemyCap;
		spawn();
	}
	
	private void spawn(){
		Random random = new Random();
		int numEnemies = enemies.size();
		if (numEnemies < enemyCap){
			for (int i = 0; i < (enemyCap - numEnemies); i++){
				int side = random.nextInt(20)+10;
				enemies.add(new EnemyBlocks(instance, random.nextInt(811), random.nextInt(31), side, side, enemyYDTrue));
			}
			
		}
		if (numEnemies > enemyCap){
			for (int i = 0; i < (numEnemies - enemyCap); i++){
				enemies.remove(i);
			}
		}
	}
	
	public void render(Graphics g){
		if (millisInitBool == false){
			millisInit = System.currentTimeMillis();
			millisInitBool = true;
		}
		
		millisCurrent = System.currentTimeMillis();
		if ((millisCurrent-millisInit)%10000 <= 20 /*&& (millisCurrent - millisInit) != 0*/){
			enemyYDTrue += 2;//increases speed of enemies every now and then
			enemyCap += 1;
		}
		
		update();
		//System.out.println("EnemyYDTrue: " + enemyYDTrue + "  MillisCurrent: " + millisCurrent + "  Enemy #s: " + enemies.size());

		
		for (EnemyBlocks e : enemies){
			e.render(g);
		}
	}
	
	private void update(){
		for (int i = 0; i < enemies.size(); i++){
			if (enemies.get(i).isDead()){
				enemies.remove(i);				
				spawn();
			}
		}
		//spawn();	
	}
	
	public void reset(){
		
		for (int i = 0; i < enemies.size(); i++){
			enemies.remove(i);
		}
		enemyCap = enemyCapOriginal;
		enemyYDTrue = enemyYDOriginal;
		
	}
	
	public void start(){
		reset();
		spawn();
		millisInit = System.currentTimeMillis();
	}
	public int getEnemyYDTrue(){ return enemyYDTrue; }
	

}
