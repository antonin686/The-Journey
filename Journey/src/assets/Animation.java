package assets;

import java.awt.image.BufferedImage;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed, index;
	private long lastTime, timer;
	public BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames){
		this.speed = speed;
		this.frames = frames;
		
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			index++;
			timer = 0;
			//System.out.println(index);
			//System.out.println(frames.length);
			if(index >= frames.length)
				index = 0;
			
		}
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	
}