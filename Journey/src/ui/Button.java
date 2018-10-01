package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import mainGame.Game;
import utils.Fonts;

public class Button {
	private Font    font, selectedFont;
    private Color   color, selectedColor;
    private boolean selected;
    private String  text;
    private int     textY;
    int x, y;
	int width, height;
    
    
    public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor) {
        this.text = text;
        this.textY = textY;
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
    public void render(Graphics g){
    	g.setColor(Color.gray);
    	g.fillRect(x - 10, y, width + 20, height + 10);
        if(selected) {
            Fonts.drawString(g, selectedFont, selectedColor, text, textY);
        	getBounds();
        }
        else {
            Fonts.drawString(g, font, color, text, textY);
            getBounds();
        }
        FontMetrics fm = g.getFontMetrics();
        this.x = (Game.WIDTH - fm.stringWidth(text)) / 2 ;
        this.y = textY - fm.getHeight() + 5;
        this.width = fm.stringWidth(text );
        this.height = fm.getHeight();
        
        g.drawRect(x - 10, y, width + 20, height + 10);
    }
    
    public Rectangle getBounds() {
    	return new Rectangle(x, y, width, height);
    }
    
}