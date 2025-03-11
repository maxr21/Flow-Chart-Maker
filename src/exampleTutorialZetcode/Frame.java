package exampleTutorialZetcode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Objects;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Frame extends JFrame {
	
	static exampleTutorialZetcode.Board board;
	//size of window
	public final static int width = 1800;
	public final static int height = 900;

	
    public Frame() {      
        initUI();
    }
    
    private void initUI() {
    	
    	//window settings
        this.setResizable(true);        
        this.pack();
        this.setTitle("FlowChart");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        
        Frame.board = new Board();
        
        //this allows me to choose where to put the buttons
        board.setLayout(null);
        
        Icon exportIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("exportIcon.png")));
        //RectBox button image
        Icon rectIcon = new ImageIcon(
            Objects.requireNonNull(getClass().getClassLoader().getResource("rectButtonIcon.png")));
        //Diamond button image
        Icon diamondIcon = new ImageIcon(Objects.requireNonNull(
            getClass().getClassLoader().getResource("diamondButtonIcon.png")));
        //parallelogram button image
        Icon slantSqrIcon = new ImageIcon(Objects.requireNonNull(
            getClass().getClassLoader().getResource("slantSqrButtonIcon.png")));
        
        JButton exportB = new JButton(exportIcon);
        exportB.setContentAreaFilled(false);
        exportB.setFocusPainted(false);
        exportB.setBorderPainted(false);
        
        exportB.setBounds(1250, 20, 200, 100);
        
        exportB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	board.setImageMake(true);
            }
        });  
        
        board.add(exportB);
        
        JButton rectButton = new JButton(rectIcon);
        rectButton.setContentAreaFilled(false);
        rectButton.setFocusPainted(false);
        rectButton.setBorderPainted(false);
        
        rectButton.setBounds(25, height/3 - height/6 - 25, 100, 50); //rect height is 50, 50/2 = 25
        /*i want each button to occupy a different third of the screen, hence height/3 will make button occupy first third
          -height/6 is so that the button is in the center of this third of the screen rather than at the top     
        */

        //listener for when pushing the button
        rectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	//adds a rectBox object to board when pushed
            	board.getList().add(new RectBox(board));

            }
        });  
        
        //Diamond Button
        JButton diamondButton = new JButton(diamondIcon);
        diamondButton.setContentAreaFilled(false);
        diamondButton.setFocusPainted(false);
        diamondButton.setBorderPainted(false);
        diamondButton.setBounds(24, 2*height/3 - height/6 - 40, 80, 80); //diam height is 80, 80/2 = 40
        
        //listener for when pushing the button
        diamondButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	//adds a Diamond object to board when pushed
            	board.getList().add(new Diamond(board));
              
              }
        });  
        
        //SlantSqr button
        JButton SlantSqrButton = new JButton(slantSqrIcon);
        SlantSqrButton.setContentAreaFilled(false);
        SlantSqrButton.setFocusPainted(false);
        SlantSqrButton.setBorderPainted(false);
        SlantSqrButton.setBounds(25, height - height/6 - 20, 100, 25); //slant height is 40, 40/2 = 20
        
        //listener for when pushing the button
        SlantSqrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	//adds a SlantSqr object to board when pushed
            	board.getList().add(new SlantSqr(board));
              
            }
        });  
        
        //all buttons added to board
        board.add(rectButton);
        board.add(diamondButton);
        board.add(SlantSqrButton);
        
        //adds newly added flowItems textbox to board
        for (int i = 0; i < board.getList().size()-1; i++) {
        	board.add(board.getList().get(i).getText());
        }
        
        this.add(board);
    }
}