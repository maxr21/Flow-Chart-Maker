
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class SlantSqr extends flowItem{
	
		//Starting X and Y coordinates of centre of shape
		private int x = 175;
		private int y = 800;
		
		//only starting values, Gets updated immediately in draw()
		private int[] slantX = {0, 0, 0, 0};
		private int[] slantY = {0, 0, 0, 0};
		
		//distance of vertices from center of shape (the bigger this is the bigger the shape)
		private int offsetX = 40;
		private int offsetY = 40;
		
		public String textObj = "";
		
		public SlantSqr(JPanel panel){
			super(panel);        
	        textField.setBackground(Color.RED);
	        textField.setForeground(Color.BLACK);
		}
		public void draw(Graphics g) {
			super.draw(g);
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setColor(Color.RED);
			
			//Moving the x vertices of Slant square (offset is the distance from the middle of the diamond)
			slantX[0] = x + offsetX+40;
	    	slantX[1] = x - offsetX;
	    	slantX[2] = x - offsetX-40;
	    	slantX[3] = x + offsetX;

			//Moving the y vertices of Diamond (offset is the distance from the middle of the diamond)
	    	slantY[0] = y - offsetY/2;
	    	slantY[1] = y - offsetY/2;
	    	slantY[2] = y + offsetY/2;
	    	slantY[3] = y + offsetY/2;

			
	        Polygon diam1 = new Polygon(slantX, slantY, 4);
	        g2d.fillPolygon(diam1);	
	        
	        textField.setBounds(x - textFieldSize/2, y - 40/2, textFieldSize, 40);
			
		}
		
		public void imageify(Graphics g, BufferedImage flowchartImg) {
	        //BufferedImage flowchartImg = new BufferedImage(1800, 900, BufferedImage.TYPE_INT_RGB);

	    	Graphics2D g2d = flowchartImg.createGraphics();
	    	g2d.setColor(Color.RED);
			g2d.fillPolygon(slantX, slantY, 4);
		}

		
		// mutator and accessor methods for slant square: x, y, x vertices, y vertices
		public void setX(int x) {
			this.x = x;
		}
		
		public int getX(){
			return x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public int getY(){
			return y;
		}
		
		public int getW() {
			//calculates width
			int w =  getLargest(slantX) - getSmallest(slantX);
			return w;
		} 
		public void setW(int w) {
			// increases width of SlantedSqr, w is set to eight for all shapes so I adjust it here to a reasonable value by /2
			offsetX = offsetX + w/2;
		}
		
		public int[] getXVertices() {
			return slantX;
		}
		
		public int[] getYVertices() {
			return slantY;
		}
		public JTextField getText() {
			return this.textField;
		}

}
