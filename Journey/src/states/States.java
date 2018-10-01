package states;

import java.awt.Graphics;

import mainGame.GameManager;


public abstract class States 
{
	private static States currentState;
	protected GameManager gM;
	public int countM;
	
	public States(GameManager gM)
	{
		this.gM = gM;
	}
	
	public static void setState(States state)
	{
		currentState = state;
	}
	
	public static States getState()
	{
		return currentState;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

}
