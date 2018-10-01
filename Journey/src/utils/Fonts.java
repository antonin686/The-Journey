package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import mainGame.Game;

public class Fonts {
	
	public static void drawString(Graphics g, Font f, Color c, String text, int x, int y) {
        g.setColor(c);
        g.setFont(f);
        g.drawString(text, x, y);
    }

    public static void drawString(Graphics g, Font f, Color c, String text) {
        FontMetrics fm = g.getFontMetrics(f);
        int x = (Game.WIDTH - fm.stringWidth(text)) / 2; // Horizontal center
        int y = ((Game.HEIGHT - fm.getHeight()) / 2) + fm.getAscent(); // Vertical center
        drawString(g, f, c, text, x, y);
    }

    public static void drawString(Graphics g, Font f, Color c, String text, double x) {
        FontMetrics fm = g.getFontMetrics(f);
        int y = ((Game.HEIGHT - fm.getHeight()) / 2) + fm.getAscent(); // Vertical center
        drawString(g, f, c, text, (int) x, y);
    }

    public static void drawString(Graphics g, Font f, Color c, String text, int y) {
        FontMetrics fm = g.getFontMetrics(f);
        int x = (Game.WIDTH - fm.stringWidth(text)) / 2; // Horizontal center
        drawString(g, f, c, text, x, y);
    }

}
