import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PuzzlePiece extends JPanel {
	
	private int num;
	private Color textColor = Color.black;
	
	public PuzzlePiece() {
		num = 0;
		setBorder(new LineBorder(Color.black, 1));
	}

	public PuzzlePiece(int num) {
		this.num = num;
		setBorder(new LineBorder(Color.black, 1));
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		repaint();
	}
	
	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(textColor);
		
		if(num!=0) {
			g.drawString(""+num, 25, 25);
		} else {
			g.drawString("", 25, 25);
		}
		
	}
	
}