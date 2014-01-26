/*
 * 1. Create Button class 
 * 		button switches public boolean -> allows certain menu to render
 * 		button has set method which has parameter to signify certain menu
 * 2. Create menu classes that buttons link to
 * 		Menu
 * 		LeaderBoard
 * 3. KeyListener
 * 		Starts in certain location, tracks movement of location (arrow keys)
 * 			-> knows where currently is and can use certain parameter for button set method
 * 4. KeyReleased
 * 		Add so that button does not move infinitely
 */

package fallingBlocks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements KeyListener {
	
	private static int frameW = 810, frameH = 600;

	//Game Stuff
	private Player player;
	private Platform ground;
	private EnemyManager manager;
	private static int mvpxAD = 10;
	//Color initialization
	Color background = new Color(17, 18, 19);
	Color text = new Color(170, 180, 190);
	//Menu stuff
	public boolean mainMenu = true;
	public boolean menu1 = false;
	public boolean menu2 = false;
	//Menu button stuff
	private Button button;
	private static int mvpxButton = 10;
	
	
	public static void main(String[] args){
		
		Main menu = new Main();
		JFrame frame = new JFrame();
		frame.add(menu);
		frame.setTitle("Test Menu");
		frame.setSize(frameW, frameH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		
	}
	
	public Main(){
		Random random = new Random();
		setFocusable(true);
		addKeyListener(this);
		button = new Button(this);
		ground = new Platform(0, 522, frameW, 50);
		player = new Player(this, random.nextInt(780)+10, 498, 20, 20);
		manager = new EnemyManager(this, 10);
	}
	
	public void paint(Graphics g){
		
		setBackground(g);
		
		if (mainMenu == true){
			button.render(g);
			setButtonTextFormat(g);
			g.drawString("PLAY", 200, 200);
			g.drawString("Test String", 570, 200);
		}
		else if (mainMenu == false && menu1 == true){ //will be changed to specific menu (w/ diff bool) later. Menu depends on location
			setButtonTextFormat(g);
			//g.drawString("You are in: Menu1", 340, 300);
			if (player.lives <= 0){
				g.setColor(Color.white);
				g.setFont(new Font("Chiller", Font.BOLD, 100));
				g.drawString("Game Over", 200, 300);
				g.setFont(new Font("Century Gothic", Font.PLAIN, 13));
				g.drawString("Lives: " + player.lives, 20, 20);
				g.drawString("Time: " + (manager.millisCurrent-manager.millisInit) , 20, 40);
				
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
		else if (mainMenu == false && menu2 == true){
			setButtonTextFormat(g);
			g.drawString("You are in: Menu2", 340, 300);
		}
		g.dispose();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		
		int c = e.getKeyCode();
		
		//Changing button location
		if (mainMenu == true){
			if (c == KeyEvent.VK_RIGHT || c == KeyEvent.VK_D){
				button.setButtonXD(mvpxButton);
			}
			if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_A){
				button.setButtonXD(-mvpxButton);
			}
		
			//Enter
			if (c == KeyEvent.VK_ENTER){
				button.enterMenu(); //will change boolean value of certain value depending on location of button
				mainMenu = false;
			}
		}
		else{ //if in game mode
			if (c == KeyEvent.VK_A || c == KeyEvent.VK_LEFT){
				player.setPlayerXD(-mvpxAD);
			}
			
			if (c == KeyEvent.VK_D || c == KeyEvent.VK_RIGHT){
				player.setPlayerXD(mvpxAD);
			}
			if (c == KeyEvent.VK_ESCAPE){
				menu1 = false;
				menu2 = false;
				mainMenu = true;
				player.reset();
				manager.reset();
			}
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		player.friction();
	}
	
	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	private void setBackground(Graphics g){
		g.setColor(background);
		g.fillRect(0, 0, frameW, frameH);
	}
	
	private void setButtonTextFormat(Graphics g){
		g.setColor(text); 
		g.setFont(new Font("Century Gothic", Font.PLAIN, 13));
	}
	
	public Button getButton(){ return button; }
	public int getMVPXButton(){ return mvpxButton; }
	
	public Platform getGround(){ return ground; }
	public Player getPlayer(){ return player; }
	public EnemyManager getManager(){ return manager; }
}

