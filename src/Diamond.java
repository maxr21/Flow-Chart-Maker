
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Diamond extends flowItem {

  //Starting X and Y coordinates of centre of shape
  private int x = 175;
  private int y = 449;

  //Gets updated immediately in draw()
  private final int[] diamX = {0, 0, 0, 0};
  private final int[] diamY = {0, 0, 0, 0};

  //distance of vertices from mouse (the bigger this is the bigger the shape)
  // width changes as text increases
  private int offsetX = 40;
  private final int offsetY = 40;

  public Diamond(JPanel panel) {
    super(panel);
    textField.setBackground(Color.ORANGE);
    textField.setForeground(Color.BLACK);
  }

  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.ORANGE);

    //Moving the x vertices of Diamond (offset is the distance from the middle of the diamond)
    diamX[0] = x - offsetX;
    diamX[1] = x;
    diamX[2] = x + offsetX;
    diamX[3] = x;

    //Moving the y vertices of Diamond (offset is the distance from the middle of the diamond)
    diamY[0] = y;
    diamY[1] = y + offsetY;
    diamY[2] = y;
    diamY[3] = y - offsetY;

    Polygon diam1 = new Polygon(diamX, diamY, 4);
    g2d.fillPolygon(diam1);

    textField.setBounds(x - (textFieldSize - 20) / 2, y - 40 / 2, textFieldSize - 20, 40);
  }

  public void imageify(Graphics g, BufferedImage flowchartImg) {
    //BufferedImage flowchartImg = new BufferedImage(1800, 900, BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = flowchartImg.createGraphics();
    g2d.setColor(Color.ORANGE);
    g2d.fillPolygon(diamX, diamY, 4);
  }

  // mutator and accessor methods for diamond: x, y, x vertices, y vertices
  public void setX(int x) {
    this.x = x;
  }

  public int getX() {
    return x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getY() {
    return y;
  }

  @Override
  protected int getW() {
    //calculates width
    return getLargest(diamX) - getSmallest(diamX);
  }

  @Override
  public void setW(int w) {
    offsetX = offsetX + w;
  }

  public int[] getXVertices() {
    return diamX;
  }

  public int[] getYVertices() {
    return diamY;
  }
}
