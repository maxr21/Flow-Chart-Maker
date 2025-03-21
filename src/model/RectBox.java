package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class RectBox extends FlowItem {

	private int rectW = 100;
	private final int rectH = 50;
	private int x = 175;
	private int y = 126;

	public RectBox(JPanel panel) {
		super(panel);
		textField.setBackground(Color.BLUE);
		textField.setForeground(Color.YELLOW);
	}

	//draws rectangle and updates position
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLUE);

		//anchor in top left
		Rectangle rect1 = new Rectangle(x, y, rectW, rectH);

		//x: half way across the rectangle - half the size of the text field to center it along X
		//y: plus half the rectangle height - the textfield height /2 (textfield height is 40 in all cases and unchanging)
		textField.setBounds(x + rectW / 2 - textFieldSize / 2, y + rectH / 2 - 40 / 2, textFieldSize,
				40);

		g2d.fill(rect1);


	}

	public void imageify(Graphics g, BufferedImage flowchartImg) {
		//BufferedImage flowchartImg = new BufferedImage(1800, 900, BufferedImage.TYPE_INT_RGB);
		System.out.println("whoa");
		Graphics2D g2d = flowchartImg.createGraphics();
		g2d.setColor(Color.BLUE);
		g2d.fillRect(this.x, this.y, this.rectW, rectH);
	}

	@Override
	public void setW(int w) {
		this.rectW = rectW + w;
	}
}
