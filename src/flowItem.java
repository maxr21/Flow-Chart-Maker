
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

//super class of all flowchart components
public class flowItem {
// all variables that the different components use, e.g polygons use xvert and y vert, rect uses x&y
	public int x;
	public int y;
	public int w;
	public int h;
	public int[] xVert;
	public int[] yVert;
	public JTextField textField;
	public int textFieldSize = 60;
	public JPanel panel;
	public String textObj = "";
	
	public flowItem(JPanel panel){
		this.panel = panel;
		//every component has a textfield
        textField = new JTextField();
        //puts text in middle of textbox
        textField.setHorizontalAlignment(JTextField.CENTER);
        //initial size of textbox
        textField.setBounds(x, y, textFieldSize, 40);
        //default text so that the textbox is visible and easy to understand what it does
        textField.setText("type...");
        //no border because it looks bad and clutters the screen
        textField.setBorder(BorderFactory.createEmptyBorder());
        // adds textfield to board JPanel so it can be seen
        panel.add(textField);
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void imageify(Graphics g, BufferedImage flowchartImg) {
        
        //BufferedImage flowchartImg = new BufferedImage(1800, 900, BufferedImage.TYPE_INT_RGB);
       
    	Graphics2D g2d = flowchartImg.createGraphics();
    	System.out.println("Imageification");

    	
    	//add key listener when key activates, loop through all flowItems + lines and do imageify for them. Learn to save that as png.
    }

	
    //method for finding largest value in an array
    protected static int getLargest(int[] a) {
    	//default smallest value which will be overwritten
    	int max = 0;
    	//loops through entire array submitted into method
    	for(int i = 0; i<a.length; i++) {
    		// checks all items with the biggest one yet found, replaces max if a bigger value is found
    		if (max < a[i]) {
    			max = a[i];
    		}
    	}
    	//returns so can be used outside of method
    	return max;
    }    
    //method for finding smallest value in an array
    protected static int getSmallest(int[] a) {
    	//very large number which is unlikely to be exceeded by smallest value in any array that is used in this program
    	int min = 100000;
    	//loops through entire array
    	for(int i = 0; i<a.length; i++) {
    		//checks smallest found value with current value, replaces min if current value is less than it
    		if (min > a[i]) {
    			min = a[i];
    		}
    	}
    	return min;
    }  
	
	
	public int getX(){
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getW() {
		return w;
	}
	public void setW(int w) {

	}
	
	public int getH() {
		return h;
	}
	
	public int[] getXVertices() {
		return xVert;
	}
	
	public int[] getYVertices() {
		return yVert;
	}
	public JTextField getText() {
		return this.textField;
	}
	public void setTextW (int w) {
		this.textFieldSize = this.textFieldSize + w;
	}
	
	public void deleteTextObj() {
		
		this.panel.remove(textField);
		this.textField = null;
	}
}