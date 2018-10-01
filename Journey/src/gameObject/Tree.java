package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import assets.Assets;
import assets.AudioPlayer;
import mainGame.GameManager;

public class Tree extends GameObject
{
	
	public Tree(int x, int y, GameManager gM) 
	{
		super(x, y, ID.Tree, gM);
		health = 50;
		width = 64;
		height = 96;
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
		g.drawImage(Assets.tree, x, y,width,height, null);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void die() 
	{
		gM.getGoM().addItems(new Wood(x,y,gM));
		active = false;
	}

}
