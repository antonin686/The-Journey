package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import assets.Assets;
import mainGame.GameManager;

public class Rock extends GameObject
{

	public Rock(int x, int y, GameManager gM)
	{
		super(x, y, ID.Rock, gM);
		health = 50;
		width = 92;
		height = 92;
	}

	@Override
	public void tick()
	{
		if(health <= 0)
			die();
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.rock, x, y,width,height, null);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x+21, y+12, 57,42);
	}

	@Override
	public void die() 
	{
		active = false;
		gM.getGoM().addItems(new Stone(x,y,gM));
	}


}
