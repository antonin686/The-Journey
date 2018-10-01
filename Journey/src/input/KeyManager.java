package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import mainGame.GameManager;

public class KeyManager extends KeyAdapter
{

	private boolean[] keys;
	public boolean up,down,left,right,inventory,healthPotion;
	public boolean menuUp,menuDown,menuLeft,menuRight;
	public boolean enter;
	public boolean attack;
	public boolean tp,interect,skillUI;
	public boolean esc;
	private GameManager gM;
	
	public KeyManager(GameManager gM)
	{
		this.gM = gM;
		keys = new boolean [256];
	}

	public void tick()
	{
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		tp = keys[KeyEvent.VK_SPACE];
		enter = keys[KeyEvent.VK_ENTER];
		esc = keys[KeyEvent.VK_ESCAPE];
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		//System.out.println("Pressed");
		int key = e.getKeyCode();
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		
		keys[e.getKeyCode()] = true;
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		//System.out.println("Released");
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		
		keys[e.getKeyCode()] = false;
		
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_I)
			inventory = true;
		else if(key == KeyEvent.VK_E)
			interect = true;
		else if(key == KeyEvent.VK_P)
			skillUI = true;
		else if(key == KeyEvent.VK_3)
			healthPotion = true;
		else if(key == KeyEvent.VK_W)
			menuUp = true;
		else if(key == KeyEvent.VK_S)
			menuDown = true;
	}
	


}
