package assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Assets 
{
	//public static AudioPlayer fireM;
	public static BufferedImage mainWorld,dung1,dung2,dung;
	public static BufferedImage axe,kunai;
	public static BufferedImage shop,grass,block,floor,tree,rock,wood,blockMW,floor2, rockSmall, crate, gate;
	public static BufferedImage axeIcon,rockIcon,woodIcon;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] enemy_move;
	public static BufferedImage backGround,framebg;
	private static int w =32;
	private static int h =32;
	public static void init()
	{
		
		mainWorld = ImageLoader.loadImage("/world/main.png");
		dung1 = ImageLoader.loadImage("/world/dung1.png");
		dung2 = ImageLoader.loadImage("/world/dung2.png");
		axe = ImageLoader.loadImage("/texture/axe.png");
		dung = ImageLoader.loadImage("/world/dung.png");
		backGround = ImageLoader.loadImage("/texture/background.png");
		framebg = ImageLoader.loadImage("/texture/framebg.jpg");
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/sheet.png"));
		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/texture/spriteSheet.png"));
		SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/texture/MainGuySpriteSheet.png"));
		SpriteSheet CSheet = new SpriteSheet(ImageLoader.loadImage("/texture/CSheet.png"));
		SpriteSheet enemy_sheet = new SpriteSheet(ImageLoader.loadImage("/texture/sprite_sheet.png"));
		
		
		
		//grass = sheet2.crop(0, 0, 8,8);
		floor2 = sheet2.crop(8, 0, 8, 8);
		//floor2 = sheet2.crop(0, 8, 8, 8);
		//block = sheet2.crop(8 * 2, 0, 8, 8);
		kunai = sheet2.crop(8 * 3, 0, 8, 8);
		//grass = sheet.crop(w *2, 0, w, h);
		//floor = sheet.crop(w *1, 0, w, h);
		//blockMW = sheet.crop(w *3, 0, w, h);
		//tree = sheet.crop(0, 0, w, h * 2);
		rockSmall = sheet.crop(0,h * 2, w, h);
		//wood = sheet.crop(w, h, w, h);
		////////
		
		crate = CSheet.crop(5*32, 0, 32, 32);
		rock = CSheet.crop(3*32, 2*32, 32, 32);
		//rockSmall = CSheet.crop(4*32, 2*32, 32, 32);
		tree = CSheet.crop(0, 0, 32*2, 32*3);
		wood = CSheet.crop(2*32, 1*32, 32, 32);
		grass = CSheet.crop(2*32, 0, 32,32);
		blockMW = CSheet.crop(5*32, 1*32, 32, 32);
		floor = CSheet.crop(5*32, 2*32, 32, 32);
		block = CSheet.crop(6*32, 0, 32, 32);
		gate = CSheet.crop(2*32, 2*32, 32, 32);
		shop = CSheet.crop(6*32, 1*32, 32, 32);
		
		////////
		//Icons
		axeIcon = ImageLoader.loadImage("/icons/axeIcon.png");
		rockIcon = ImageLoader.loadImage("/icons/rockIcon.png");
		woodIcon = ImageLoader.loadImage("/icons/woodIcon.png");
		
		/////////// Player
		
		
		player_down = new BufferedImage[3];
		player_up = new BufferedImage[3];
		player_left = new BufferedImage[3];
		player_right = new BufferedImage[3];
		
		player_down[0] = player.crop(0, 0, 32, 32);
		player_down[1] = player.crop(1*32, 0, 32, 32);
		player_down[2] = player.crop(2*32, 0, 32, 32);
		player_up[0] = player.crop(0*32, 2*32, 32, 32);
		player_up[1] = player.crop(1*32, 2*32, 32, 32);
		player_up[2] = player.crop(2*32, 2*32, 32, 32);
		player_right[0] = player.crop(0*32, 1*32, 32, 32);
		player_right[1] = player.crop(1*32, 1*32, 32, 32);
		player_right[2] = player.crop(2*32, 1*32, 32, 32);
		player_left[0] = player.crop(0*32, 3*32, 32, 32);
		player_left[1] = player.crop(1*32, 3*32, 32, 32);
		player_left[2] = player.crop(2*32, 3*32, 32, 32);
		
		//////////
		
		enemy_move = new BufferedImage[3];
		
		enemy_move[0] = enemy_sheet.crop(3*32, 0, 32, 32);
		enemy_move[1] = enemy_sheet.crop(4*32, 0, 32, 32);
		enemy_move[2] = enemy_sheet.crop(5*32, 0, 32, 32);
		
		////
		
	}
}
