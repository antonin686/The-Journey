package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import assets.Assets;
import mainGame.Game;
import mainGame.GameManager;
import ui.Button;
import utils.Fonts;
import worlds.WorldManager;

public class MenuState extends States
{
	private Button[] options;
	private int currentSelection;
	int tickCount = 0;
	

	public MenuState(GameManager gM) 
	{
		super(gM);
		loadButtons();
		//System.out.println(statusM.length);
		 
		
	}

	@Override
	public void tick() 
	{
		
		if (gM.getKeyM().menuUp ) {
			gM.getKeyM().menuUp = false;
			currentSelection--;
			if (currentSelection < 0) 
				currentSelection = options.length - 1;
			
		}
		else if (gM.getKeyM().menuDown) {
			
			currentSelection++;
			if (currentSelection >= options.length) currentSelection = 0;
			gM.getKeyM().menuDown = false;
			
			
		}
		else if (gM.getKeyM().enter)
            select();
		
		int mouseX = gM.getMouseM().getMouseX();
		int mouseY = gM.getMouseM().getMouseY();
		//System.out.println(mouseX + " " + mouseY);
		
		if(mouseX > 420 && mouseX < 580 && mouseY > 205 && mouseY < 270)
		{
			currentSelection = 0;
			if(gM.getMouseM().leftPressed)
				select();
		}
		else if(mouseX > 405 && mouseX < 595 && mouseY > 290 && mouseY < 345)
		{
			currentSelection = 1;
			if(gM.getMouseM().leftPressed)
				select();
		}
		else if(mouseX > 440 && mouseX < 560 && mouseY > 370 && mouseY < 425)
		{
			currentSelection = 2;
			if(gM.getMouseM().leftPressed)
				select();
		}
		else if(mouseX > 460 && mouseX < 540 && mouseY > 450 && mouseY < 505)
		{
			currentSelection = 3;
			if(gM.getMouseM().leftPressed)
				select();
		}
	
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(Assets.backGround, 0, 0,gM.getWidth(),gM.getHeight(), null);
		Fonts.drawString(g, new Font("Arial", Font.BOLD, 72), Color.ORANGE, "Journey", 100);
		
		for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) options[i].setSelected(true);
            else options[i].setSelected(false);

            options[i].render(g);
        }
	}
	
	private void select() {
		switch (currentSelection) {
            case 0:
            	gM.getGame().fileM.loadSavedGame();
            	WorldManager.setCurrentWorld(gM.getWorld(),gM.getPlayer(), gM, gM.getPlayer().getX(),gM.getPlayer().getY());
            	gM.inGame = true;
                States.setState(gM.getGame().gameState);
                break;
            case 1:
            	gM.getGame().fileM.newGame();
            	gM.getGame().fileM.saveGame();
            	gM.inGame = true;
            	States.setState(gM.getGame().gameState);
                break;
            case 2:
            	States.setState(gM.getGame().helpState);
    			break;
            case 3:
                System.exit(0);
        }
    }
	
	
	

	public void loadButtons()
	{
		options = new Button[4];
        options[0] = new Button("Continue",(250 + 0 * 80),
                new Font("Arial", Font.PLAIN, 32), new Font("Arial", Font.BOLD, 48),
                Color.WHITE, Color.YELLOW);
        options[1] = new Button("New Game", 250 + 1 * 80,
                new Font("Arial", Font.PLAIN, 32), new Font("Arial", Font.BOLD, 48),
                Color.WHITE, Color.YELLOW);
        options[2] = new Button("Help", 250 + 2 * 80,
                new Font("Arial", Font.PLAIN, 32), new Font("Arial", Font.BOLD, 48),
                Color.WHITE, Color.YELLOW);
        options[3] = new Button("Exit", 250 + 3 * 80,
                new Font("Arial", Font.PLAIN, 32), new Font("Arial", Font.BOLD, 48),
                Color.WHITE, Color.YELLOW);
	}
	
}
