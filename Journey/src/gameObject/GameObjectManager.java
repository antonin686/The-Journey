package gameObject;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;


public class GameObjectManager 
{
	public LinkedList <GameObject> objects;
	public LinkedList <GameObject> bullets;
	public LinkedList <GameObject> items;
	//public LinkedList 
	
	
	private boolean up = false, down = false, right = false, left = false;
	int count = 0;
	
	public GameObjectManager()
	{
		objects = new LinkedList<GameObject>();
		bullets = new LinkedList<GameObject>();
		items = new LinkedList<GameObject>();
	}
	
	public void tick()
	{
		Iterator<GameObject> itr = objects.iterator();
		while(itr.hasNext())
		{
			GameObject o = itr.next();
			
			o.tick();
			
			if(!o.isActive())
				itr.remove();
		}
		
		Iterator<GameObject> itrb = bullets.iterator();
		while(itrb.hasNext())
		{
			GameObject b = itrb.next();
			
			b.tick();
			
			if(!b.isActive())
				itrb.remove();
		}
		
		Iterator<GameObject> itri = items.iterator();
		while(itri.hasNext())
		{
			GameObject i = itri.next();
			
			i.tick();
			
			if(!i.isActive())
				itri.remove();
		}
		
	}
	
	public void render(Graphics g)
	{
		for(GameObject o : objects)
		{
			o.render(g);
		}
		
		for(GameObject b : bullets)
		{
			b.render(g);
		}
		
		for(GameObject i : items)
		{
			i.render(g);
		}
		
	}
	
	public void addObject(GameObject o)
	{
		objects.add(o);
	}
	
	public void addBullet(GameObject o)
	{
		bullets.add(o);
	}
	
	public void addItems(GameObject o)
	{
		items.add(o);
	}
	
	public void removeObject(GameObject o)
	{
		objects.remove(o);
	}

	public void removeBullet(GameObject o)
	{
		bullets.remove(o);
	}
	
	
}
