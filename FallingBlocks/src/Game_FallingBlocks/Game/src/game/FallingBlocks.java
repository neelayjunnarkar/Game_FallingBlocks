/*
 * 1. Different modes
 * 		Easy/Medium/Hard
 * 		Gravity
 * 		Flashing Colors
 * 		For people who I want to make look really bad (belie its difficulty)
 * 2  Enemies can overlap
 * 3. MENU
 * 4. RESTART
 * 5. LEADERBOARD/HIGHSCORE
 

package menuTest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FallingBlocks extends JPanel implements KeyListener{
	
	protected static int frameW = 810, frameH = 600;
	private Platform ground;
	private Player player;
	private EnemyManager manager;
	private static int mvpxAD = 10;
	private static int mvpxJ = 10;
	private static boolean startDelayB = false;
	private static int startDelay = 100;
	private int jumpc = 0;
	

	public static void main(String[] args){
		
		FallingBlocks game = new FallingBlocks();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setTitle("FallingBlocks");
		frame.setSize(frameW, frameH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	public FallingBlocks(){
		
		Random random = new Random();
		setFocusable(true);
		addKeyListener(this);

		ground = new Platform(0, 522, frameW, 50);
		player = new Player(this, random.nextInt(780)+10, 498, 20, 20);
		manager = new EnemyManager(this, 10);
		
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frameW, frameH);
		
		if (startDelayB == false){
			
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
				startDelayB = true;
				
			}
			catch (InterruptedException e){
				e.printStackTrace();
				
			}
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, frameW, frameH);
			player.render(g);
			ground.render(g);
		}
		else{
		if (player.lives <= 0){
			g.setColor(Color.white);
			g.setFont(new Font("Chiller", Font.BOLD, 100));
			g.drawString("Game Over", 200, 300);
			g.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			g.drawString("Lives: " + player.lives, 20, 20);
			g.drawString("Time: " + (manager.millisCurrent-manager.millisInit) , 20, 40);
		}
		
		else{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			g.drawString("Lives: " + player.lives , 20, 20);
			g.drawString("Time: " + (manager.millisCurrent - manager.millisInit), 20, 40);
			ground.render(g);
			player.render(g);
			manager.render(g);
		
			try {
				TimeUnit.MILLISECONDS.sleep((long) 10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		g.dispose();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		
		int c = e.getKeyCode();
		
		if (c == KeyEvent.VK_A || c == KeyEvent.VK_LEFT){
			player.setPlayerXD(-mvpxAD);
		}
		/*if (c == KeyEvent.VK_W){
			player.setPlayerYD(-mvpxJ);
		}
		if (c == KeyEvent.VK_D || c == KeyEvent.VK_RIGHT){
			player.setPlayerXD(mvpxAD);
		}
		if (c == KeyEvent.VK_W){
			if (!ground.isCollided(player.getHitbox()) && jumpc >= 1){
			} else{
				player.setPlayerYD(-mvpxJ);
				jumpc++;
				if (ground.isCollided(player.getHitbox())){ 
					jumpc = 0;
				}
			}
			
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		player.friction();
		//player.setPlayerXD(0);
	}
	
	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	public Platform getGround(){ return ground; }
	public Player getPlayer(){ return player; }
	public EnemyManager getManager(){ return manager; }
}
*/