package firestorm.rendering.ui;

import firestorm.Game;
import firestorm.utils.Fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Created by slinkee on 10/12/2016.
 */
public class Button extends Rectangle {

    private Font font, selectedFont;
    private Color color, selectedColor;
    private boolean selected;
    private String text;
    private int textY;  // Needed to render text since y value of string is set at lower-left corner
    // instead of upper-left as the rectangle


    public Button(Font font, Font selectedFont, Color color, Color selectedColor, String text, int textY) {
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
        this.text = text;
        this.textY = textY;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void render(Graphics g) {
        if (selected) {
            Fonts.drawString(g, selectedFont, selectedColor, text, textY);
        } else {
            Fonts.drawString(g, font, color, text, textY);
        }
        FontMetrics fm = g.getFontMetrics();
        this.x = (Game.WIDTH - fm.stringWidth(text)) / 2;
        this.y = textY - fm.getHeight();
        this.width = fm.stringWidth(text);
        this.height = fm.getHeight();
        g.drawRect(x, y, width, height);
    }
}