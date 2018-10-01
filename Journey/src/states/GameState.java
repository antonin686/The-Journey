package states;

import java.awt.Graphics;

import mainGame.GameManager;
import worlds.WorldManager;

public class GameState extends States
{

	public GameState(GameManager gM) 
	{
		super(gM);
	}

	@Override
	public void tick() 
	{
		if(gM.getKeyM().esc)
		{
			States.setState(gM.getGame().escapeState);
		}
		if(WorldManager.getCurrentWorld() != null)
			WorldManager.getCurrentWorld().tick();
	}

	@Override
	public void render(Graphics g) 
	{
		if(WorldManager.getCurrentWorld() != null)
			WorldManager.getCurrentWorld().render(g);
	}

}
