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

public class Crate extends GameObject
{

	public Crate(int x, int y, GameManager gM) 
	{
		super(x, y, ID.Crate, gM);
	}

	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render(Graphics g) 
	{
		g.setColor(Color.CYAN);
		g.drawImage(Assets.crate, x, y, width, height, null);
		//g.fillRect(x, y, width, height);
	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void die() 
	{
		active = false;
	}

}
