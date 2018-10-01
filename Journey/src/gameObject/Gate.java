package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import assets.Assets;
import mainGame.GameManager;

public class Gate extends GameObject
{

	public Gate(int x, int y, int gID, GameManager gM) 
	{
		super(x, y, ID.Gate, gM);
		this.gID = gID;
		
	}

	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render(Graphics g)
	{
		//g.setColor(Color.magenta);
		g.drawImage(Assets.gate, x, y, width*2, height*2, null);
		//g.fillRect(x, y, width*2, height *2);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,45,45);
	}

	@Override
	public void die() 
	{
		
	}
	
}
