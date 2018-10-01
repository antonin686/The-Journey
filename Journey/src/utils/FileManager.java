package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mainGame.GameManager;
import worlds.WorldManager;

public class FileManager 
{
	private GameManager gM;

	public FileManager(GameManager gM) 
	{
		this.gM = gM;
	}
	

	public void loadSavedGame()
	{
		String file = FileLoader.loadFileAsString("res/file/save.txt");
		String[] pos = file.split("\\s+");
		
		gM.getPlayer().health = FileLoader.parseInt(pos[0]);
		gM.getPlayer().maxHealth = FileLoader.parseInt(pos[1]);
		gM.getPlayer().axes = FileLoader.parseInt(pos[2]);
		gM.getPlayer().maxAxes = FileLoader.parseInt(pos[3]);
		gM.getPlayer().cash = FileLoader.parseInt(pos[4]);
		gM.getPlayer().wood = FileLoader.parseInt(pos[5]);
		gM.getPlayer().rock = FileLoader.parseInt(pos[6]);
		gM.getPlayer().healthPotion = FileLoader.parseInt(pos[7]);
		gM.getPlayer().exp = FileLoader.parseInt(pos[8]);
		
		gM.getPlayer().setX(FileLoader.parseInt(pos[9]));
		gM.getPlayer().setY(FileLoader.parseInt(pos[10]));
		gM.worldTOLOAD = FileLoader.parseInt(pos[11]);
	}
	public void newGame()
	{
		gM.getPlayer().health = 100;
		gM.getPlayer().maxHealth = 100;
		gM.getPlayer().axes = 100;
		gM.getPlayer().maxAxes = 100;
		gM.getPlayer().cash = 1000;
		gM.getPlayer().wood = 0;
	  	gM.getPlayer().rock = 0;
	  	gM.getPlayer().healthPotion = 0; 
	  	gM.getPlayer().exp = 0;
	  	gM.getPlayer().setX(100) ;
	  	gM.getPlayer().setY(200) ;
	  	gM.worldTOLOAD = 0;
	}
	public void saveGame() 
	{
		File file = new File("res/file/save.txt");
		String save =  "" + gM.getPlayer().health + " " + gM.getPlayer().maxHealth + " "
						  + gM.getPlayer().axes + " " + gM.getPlayer().maxAxes + " "
						  + gM.getPlayer().cash + " " + gM.getPlayer().wood + " "
						  + gM.getPlayer().rock + " " + gM.getPlayer().healthPotion + " "
						  + gM.getPlayer().exp + " " + gM.getPlayer().getX() + " "
						  + gM.getPlayer().getY() + " " + WorldManager.getCurrentWorld().getWorldID();
		String[] partitions = save.split(" ");
		
		if(file.exists())
		{
			try {
				FileWriter fileW = new FileWriter(file);
				BufferedWriter buffW = new BufferedWriter(fileW);
				for (String word: partitions) {
			        buffW.write(word);
			        buffW.newLine();
			    }
				buffW.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else
		{
			System.exit(1);
		}
		
	}
}
