package Game_FallingBlocks.Game.src.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button {
	
	private static int buttonX, buttonY, buttonW, buttonH;
	private Rectangle hitbox;
	public Main instance;
	private int[] location = {0, 0};
	private int[] oldLocation = location;
	private Color buttonColor = new Color(0, 40, 77);
	private int buttonXD = 0, buttonYD = 0;
	
	public Button(Main instance){
		this.instance = instance;
		buttonX = 180;
		buttonY = 180;
		buttonW = 100;
		buttonH = 30;
		hitbox = new Rectangle(buttonX, buttonY, buttonW, buttonH);
		
	}
	
	public void render(Graphics g){
		move();
		g.setColor(buttonColor);
		g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		//System.out.println("coords: " + hitbox.x + "," + hitbox.y);
		
	}
	
	private void move(){
		
		if (buttonYD == 0 && buttonXD == 0){
			return;
		}
		
		if (buttonXD < 0){
			while (hitbox.x > 180){
				hitbox.x += buttonXD;
				setLocation(0, getLocationY());
			}
		}
		else if (buttonXD > 0){
			while (hitbox.x < 550){
				hitbox.x += buttonXD;
			}
			setLocation(1, getLocationY());
		}
		
		if (buttonYD < 0){
			
		}
		else if (buttonYD > 0){
			
		}
		
	}
	
	public void enterMenu(){
		
		if (getLocationX() == 0 && getLocationY() == 0){
			//System.out.println("Menu: " + getLocationX() + "," + getLocationY());
			instance.menu1 = true;
			instance.getManager().start();
		}
		
		if (getLocationX() == 1 && getLocationY() == 0){
			//System.out.println("Menu: " + getLocationX() + "," + getLocationY());
			instance.menu2 = true;
		}
		
	}
	
	// Location 
	public void setLocation(int x, int y){
		location[0] = x;
		location[1] = y;
	}
	public int getLocationX(){ return location[0]; }
	public int getLocationY(){ return location[1]; }
	
	// OldLocation
	public void setOldLocation(int x, int y){
		oldLocation[0] = x;
		oldLocation[1] = y;
	}
	public int getOldLocationX(){ return oldLocation[0]; }
	public int getOldLocationY(){ return oldLocation[1]; }
	
	// ButtonXD and ButtonYD
	public void setButtonXD(int xd){
		buttonXD = xd;
	}
	public int getButtonXD(){ return buttonXD; }
	
	public void setButtonYD(int yd){
		buttonYD = yd;
	}
	public int getButtonYD(){ return buttonYD; }
	
}


