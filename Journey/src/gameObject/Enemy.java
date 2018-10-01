package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import assets.Animation;
import assets.Assets;
import assets.AudioPlayer;
import mainGame.GameManager;

public class Enemy extends GameObject
{
	private int px = 0,py = 0;
	private int choose = 0;
	private int counter = 0;
	AudioPlayer eDM;
	private Animation enemyMove;
	Random r = new Random();
	
	
	
	public Enemy(int x, int y, GameManager gM) 
	{
		super(x, y, ID.Enemy, gM);
		health = 10;
		
		enemyMove = new Animation(100, Assets.enemy_move);
		
		try {
			eDM = new AudioPlayer("eDM.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() 
	{
		if(health <= 0)
			die();
		move();
		
		collision();
		
		enemyMove.tick();
	}
		
		
	

	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		//g.fillRect(x, y, width, height);
		g.drawImage(enemyMove.getCurrentFrame(), x, y, width, height, null);
	}
	
	

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,width,height);
	}
	
	public Rectangle getBoundsBig()
	{
		return new Rectangle(x -16,y-16,width * 2,height *2);
	}
	public void move()
	{
		
		x += moveX ;
		y += moveY ;
		choose = r.nextInt(10);
		
		if(shouldChase())
		{
			moveX = px - x;
			moveY = py - y;
			
		}else
		{
			if(choose == 0)
			{
				moveX = (r.nextInt(4 - -4) + -4);
				moveY = (r.nextInt(4 - -4) + -4);
			}
		}
		
		if(moveX > 3)
			moveX = 3;
		if(moveX < -3)
			moveX = -3;
		
		if(moveY > 3)
			moveY = 3;
		if(moveY < -3)
			moveY = -3;

		
	}
	
	public void collision()
	{
		
		for(int i = 0; i < gM.getGoM().objects.size(); i++)
		{
			GameObject o = gM.getGoM().objects.get(i);
			if(o.getId() == ID.Block)
			{
				if(getBounds().intersects(o.getBounds()))
				{
					x += moveX  * 5 * -1;
					y += moveY  * 5 * -1;
					
					moveX *= -1;
					moveY *= -1;
				}
			}
		}
	}
	
	public boolean shouldChase()
	{
		for(GameObject o : gM.getGoM().objects)
		{
			if(o.id == ID.Player)
			{
				px = o.getX();
				py = o.getY();
			}
		}
		double dx = x - px;
		double dy = y - py;
		double res = Math.sqrt((dx * dx) + (dy * dy));
		if( res < 200)
			return true;
		else return false;
	}

	@Override
	public void die() 
	{
		eDM.play();
		gM.getPlayer().cash +=10;	
		gM.getPlayer().exp += 100;
		active = false;
	}

}
