/**
 * 
 */
package edu.cnm.deepdive.life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;



/**
 * @author Kelly Escobar
 *
 */
public class Surface extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 2495214640084762801L;

  
  private static final int INSET = 1;
  private static final Color CELL_COLOR = Color.CYAN;
  private static final Color OLD_CELL_COLOR = Color.MAGENTA;
  
  public final int width;
  public final int height;
  public final float scale;
  
  private byte[][] field;
  
  
  public Surface(int width, int height, float scale) {
    super(true);
    this.width = width;
    this.height = height;
    this.scale = scale;
    setBorder(LineBorder.createGrayLineBorder());
  }


  /**
   * 
   */
  @Override
  protected void paintComponent(Graphics g) {
    setBackground(Color.BLACK);
    super.paintComponent(g);
    synchronized (this) {
      for (int i = 0; i < height; i++) {
        int top = INSET + Math.round(i * scale);
        int ovalheight = INSET + Math.round((i + 1) * scale) - top;
        for (int j = 0; j < width; j++) {
          int left = INSET + Math.round(j * scale);
          int ovalwidth = INSET + Math.round((j + 1) * scale) - left;
          byte cellGeneration = field[i][j];
          if (cellGeneration > 1) {
            g.setColor(OLD_CELL_COLOR);
            g.fillOval(left, top, ovalwidth, ovalheight);
          } else if (cellGeneration == 1) {
            g.setColor(CELL_COLOR);
            g.fillOval(left, top, ovalwidth, ovalheight);
          }
        }
      }
    }
  }


  /**
   * 
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1 + 2 * INSET + Math.round(width * scale),
        1 + 2 * INSET + Math.round(height * scale));
  }
  
  public synchronized void setField(byte[][] field) {
    this.field = field;
    repaint();
  }
  
}
















