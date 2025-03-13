import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

// to do:

//add arrows to lines that connect to flowItems
//add start and end nodes
//make lines slightly thicker?
//**** Make it so box shrinks when text is deleted
// add image instead of bin


public class Board extends JPanel implements ActionListener {

  private static final long serialVersionUID = 1L;
  private final int B_WIDTH = 1900;
  private final int B_HEIGHT = 1060;
  private Boolean imageMake = false;
  //time before each redraw
  private final int DELAY = 25;

  //made list of flowItems to store the rects and diamonds etc
  public LinkedList<flowItem> flowItemList;
  //another linked list to store my lines
  public LinkedList<Line> lineList;

  boolean l1 = false;

  private Timer timer;
  //public Boolean imageMake;
  BufferedImage flowchartImg;
  private int fileNum;

  private Image bin;

  //Accessor for LinkedList
  public LinkedList<flowItem> getList() {
    return this.flowItemList;
  }

  //method for finding the largest value in an array
  private static int getLargest(int[] a) {
    //default value that will be overwritten
    int max = 0;

    for (int j : a) {
      //checks if current max value is less than item currently being inspected
      if (max < j) {
        max = j;
      }
    }
    return max;
  }

  //method for finding the smallest value in an array
  private static int getSmallest(int[] a) {
    //default value that will be overwritten
    int min = 100000;

    for (int j : a) {
      //checks if current min value is greater than item currently being inspected
      if (min > j) {
        min = j;
      }
    }
    return min;
  }

  public Board() throws IOException {

    initBoard();

    flowItemList = new LinkedList<>();
    lineList = new LinkedList<>();

    //for bin
    fileNum = 0;

    KeyListener kl = new KeyListener() {
      public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
      }
    };
    this.addKeyListener(kl);

    MouseMotionListener mouseMotionListener = new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {
        boolean isMoving = false;
        for (int i = 0; i < flowItemList.size(); i++) {

          if (!isMoving) {

            // moving flowItems -- if checks the subclass of flowItem
            if (flowItemList.get(i) instanceof RectBox) {
              //RectBox movement

              //checks position of mouse vs area covered by shape,
              //first that  right side > mouse x > left side then same for y
              if ((e.getX() > flowItemList.get(i).getX() &&
                  e.getX() < flowItemList.get(i).getX() + flowItemList.get(i).getW()) &&
                  (e.getY() > flowItemList.get(i).getY() &&
                      e.getY() < flowItemList.get(i).getY() + flowItemList.get(i)
                          .getH())) { //then...
                //if mouse is inside shape, set shapes x pos to mouse position minus width of shape so it is centered
                flowItemList.get(i).setX(e.getX() - flowItemList.get(i).getW() / 2);
                flowItemList.get(i).setY(e.getY() - flowItemList.get(i).getH() / 2);

                //flowItemList.get(i)
                isMoving = true;
                //if shape is within bin area -- not exact just approx. for user friendliness
                if (flowItemList.get(i).getX() > 1300 &&
                    flowItemList.get(i).getX() < 1400 &&
                    flowItemList.get(i).getY() > 700 &&
                    flowItemList.get(i).getY() < 750) {
                  //deletes the text and removes it from linked list so that it doesn't get redrawn
                  flowItemList.get(i).deleteTextObj();
                  flowItemList.remove(i);

                }
              }
            } else {//the flowitems is a diamond or slantSqr, same code works as they are both polygons with vertex co-ords

              // same as for rect however as the polys are made with point co-ords must check the co-ords to find area
              if ((e.getX() > getSmallest(flowItemList.get(i).getXVertices()) &&
                  e.getX() < getLargest(flowItemList.get(i).getXVertices()) &&
                  (e.getY() > getSmallest(flowItemList.get(i).getYVertices()) &&
                      e.getY() < getLargest(flowItemList.get(i).getYVertices())))) {

                //use mutator to change x and y of all vertices by the same amount so the shape doesnt change
                flowItemList.get(i).setX(e.getX());
                flowItemList.get(i).setY(e.getY());
                isMoving = true;
                //in bin if
                if (1300 < getSmallest(flowItemList.get(i).getXVertices()) &&
                    700 < getSmallest(flowItemList.get(i).getYVertices())) {

                  flowItemList.get(i).deleteTextObj();
                  flowItemList.remove(i);
                }

              }

            }

          }
        }

        // check rmb clicked
        if (SwingUtilities.isRightMouseButton(e)) {
          // loop through lines
          for (int i = 0; i < lineList.size(); i++) {

            //makes array of x and y for lines so I can use my getLargest & smalles methods
            int[] x = {lineList.get(i).getX1(), lineList.get(i).getX2()};
            int[] y = {lineList.get(i).getY1(), lineList.get(i).getY2()};

            int leftX = getSmallest(x);
            int rightX = getLargest(x);
            int upY = getSmallest(y);
            int downY = getLargest(y);

            //use variables to make easy to understand. Was at one point a bug because too confusing for me
            boolean x1Limit = e.getX() >= leftX - 10;
            boolean x2Limit = e.getX() <= rightX + 10;
            boolean y1Limit = e.getY() >= upY - 10;
            boolean y2Limit = e.getY() <= downY + 10;

            System.out.println("x1Limit: " + x1Limit);
            // checks if rmb goes through line's "limits"
            if (x1Limit && x2Limit && y1Limit && y2Limit) {

              remove(lineList.get(i).getTextField());

              lineList.remove(i);
            }

          }
        }
        isMoving = false;
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
      }
    };

    this.addMouseMotionListener(mouseMotionListener);

    MouseListener mouseListener = new MouseListener() {
      //for line co-ords
      private int x1;
      private int y1;
      private int x2;
      private int y2;
      //to check whether click one or two
      boolean flag = false;

      @Override
      public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        // first click, checks that LMB was clicked
        if (e.getButton() == 1) {
          if (!flag) {
            //sets x&y of mouse to x&y of line's first point
            x1 = e.getX();
            y1 = e.getY();
            flag = true;
          } else {
            // second click
            //set x of 2nd line co-ord to mouse X
            x2 = e.getX();
            //set y of 2nd line co-ord to mouse Y
            y2 = e.getY();
            //reset to false to go back to first click
            flag = false;
            //gradient of line, to check if more vertical or horizontal
            double g = (y2 - y1) / (x2 - x1);

            //if dy > dx, then line horizontal & g > 1
            //if dy < dx, then line vertical & g < 1
            //if dy = dx then g = 1, I count as vertical
            if (g >= 1 || g <= -1) {
              // make vertical
              x2 = x1;
            } else {
              //make horizontal
              y2 = y1;
            }
            //adds new line to lineList and adds line to board.
            lineList.add(new Line(x1, y1, x2, y2));
            add(lineList.get(lineList.size() - 1).getTextField());

            //l1 checks that a line exists, this is so that we check null parameter to do with non-existant lines
            l1 = true;
          }
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
      }
    };
    //adds to board
    this.addMouseListener(mouseListener);
  }


  private void loadImage() throws IOException {
    //loads image, bin.png will always exist
    BufferedImage image = ImageIO.read(new File("docs" + File.separator + "bin.png"));
    ImageIcon ii = new ImageIcon(image);
    bin = ii.getImage();
  }


  private void initBoard() throws IOException {
    //bg colour
    setBackground(Color.WHITE);
    //size of panel
    setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    setFocusable(true);
    this.requestFocusInWindow();
    //timer calls actionEvent after every delay ms (set at top). This is for repainting.
    timer = new Timer(DELAY, this);
    timer.start();

    loadImage();

  }


  @Override
  public void paintComponent(Graphics g) {
    //inherits from super class, runs super method with g as parameter.
    super.paintComponent(g);

    //loop through flowItems to draw each one
    for (flowItem flowItem : flowItemList) {

      flowItem.draw(g);

      //this checks whether the text written on a flowItem is larger than the text box
      if (flowItem.getText().getHorizontalVisibility().getValue() > 1) {

        //increases the width of the textbox and flowItem object by 8 every 25ms that the text is too large for the box
        flowItem.setTextW(8);
        flowItem.setW(8);
      }
    }

    if (imageMake) {
      BufferedImage flowchartImg = new BufferedImage(1800, 900, BufferedImage.TYPE_INT_RGB);

      Graphics2D g2d = flowchartImg.createGraphics();
      g2d.setColor(Color.WHITE);
      g2d.fillRect(0, 0, 1800, 900);

      for (Line line : lineList) {
        int x1 = line.getX1();
        int y1 = line.getY1();
        int x2 = line.getX2();
        int y2 = line.getY2();
        g2d.setColor(Color.BLACK);
        line.imageify(g, flowchartImg);
        g2d.drawString(line.getTextField().getText(), (x1 + x2) / 2 + 10,
            (y1 + y2) / 2 - 25);
      }

      for (flowItem obj : flowItemList) {
        obj.imageify(g, flowchartImg);
        int x = obj.getX();
        int y = obj.getY();
        int w = obj.getW();
        int h = obj.getH();
        int textW = obj.getText().getWidth();

        if (obj instanceof RectBox) {

          g2d.setColor(Color.WHITE);
          g2d.drawString(obj.getText().getText(), x + w / 8, y + h / 2);

        } else if (obj instanceof Diamond) {
          g2d.setColor(Color.BLACK);
          g2d.drawString(obj.getText().getText(), x - textW / 2, y);

        } else {
          g2d.setColor(Color.BLACK);
          g2d.drawString(obj.getText().getText(), x - textW / 4, y);
        }
      }

      fileNum++;
      File file = new File("diagrams\\image" + fileNum + ".png");

      try {
        ImageIO.write(flowchartImg, "png", file);
        System.out.println("SAVED!!");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      setImageMake(false);
    }

    //l1 checks that at least 1 line exists so that error doesnt occur when looping through empty linked list (loop parameter error)
    if (l1) {
      for (Line line : lineList) {
        line.draw(g);
      }
    }
    //The below is the bin
    g.drawImage(bin, 1350, 700, this);
    Toolkit.getDefaultToolkit().sync();
  }


  @Override  //gets called when timer activates (after DELAY seconds)
  public void actionPerformed(ActionEvent e) {
    //calls paintComponent()
    repaint();
  }

  public void setImageMake(boolean b) {
    imageMake = b;
  }
}