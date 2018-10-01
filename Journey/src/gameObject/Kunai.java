package gameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import assets.Assets;
import mainGame.GameManager;

public class Kunai extends GameObject
{
	

		public Kunai(int x, int y, GameManager gM, int mx, int my) 
		{
			super(x, y, ID.Kunai, gM);
			width = 16;
			height = 8;
			
			moveX = (mx - x) / 20;
			moveY = (my - y) / 20;
		}

		@Override
		public void tick() 
		{
			x += moveX;
			y += moveY;
			
			Iterator<GameObject> itr = gM.getGoM().objects.iterator();
			
			while(itr.hasNext())
			{
				GameObject o = (GameObject) itr.next();
				if(o.getId() == ID.Block )
				{
					if(getBounds().intersects(o.getBoundsBig()))
					{
						x += moveX * -1;
						y += moveY * -1;
					}
						
				}else if(o.getId() == ID.Enemy)
				{
					if(getBounds().intersects(o.getBounds()))
					{
						o.health -= 100;
					}
						
				}
				
			}
			
		}

		@Override
		public void render(Graphics g) 
		{
			g.drawImage(Assets.kunai, x,y, width, height, null);
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
