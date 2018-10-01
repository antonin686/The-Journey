package gameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import assets.Assets;
import mainGame.GameManager;

public class Block extends GameObject
{

	public Block(int x, int y,BufferedImage image, GameManager gM) {
		super(x, y, ID.Block, gM);
		this.image = image;
	}

	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(image, x, y,width,height,null);
		
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
