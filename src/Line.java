
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Line {
	int x1;
	int x2;
	int y1;
	int y2;
	
	public JTextField textField;
	public int textFieldSize = 40;
	public JPanel panel;
	
	public Line(int x1, int y1, int x2, int y2) {
		//When constructor is called, i chose where the line start and ends
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	
		this.textField = new JTextField();
        this.textField.setHorizontalAlignment(JTextField.CENTER);
        this.textField.setBounds((x1+x2)/2+10, (y1+y2)/2 - 25, 20, 20);
        this.textField.setText("");
        this.textField.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public void draw(Graphics g) {		
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
	}
	
	public void imageify(Graphics g, BufferedImage flowchartImg) {
        //BufferedImage flowchartImg = new BufferedImage(1800, 900, BufferedImage.TYPE_INT_RGB);

    	Graphics2D g2d = flowchartImg.createGraphics();
    	g2d.setColor(Color.BLACK);
		g2d.drawLine(x1, y1, x2, y2);
	}

	
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY2() {
		return y2;
	}
	
	public JTextField getTextField() {
		return textField;
	}

}
