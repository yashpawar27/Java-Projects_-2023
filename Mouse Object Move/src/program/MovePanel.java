package program;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MovePanel extends JPanel implements MouseListener, MouseMotionListener
{
    private JLabel movingLBL = new JLabel();
    private ImageIcon icon = new ImageIcon("src/images/BlueShip.png");
    private Point place1, place2;
    private int WIDTH = 600, HEIGHT = 600;
    private Dimension size = new Dimension(WIDTH, HEIGHT);

    public MovePanel() 
    {
        setLayout(null);
        this.setPreferredSize(size);
        movingLBL.setIcon(icon);
        movingLBL.setBounds(200, 200, icon.getIconWidth(), icon.getIconHeight());
        movingLBL.addMouseListener(this);
        movingLBL.addMouseMotionListener(this);
        this.add(movingLBL);
    }
    
    private void moveLBL() 
    {
        int xChange = place2.x - place1.x;
        int yChange = place2.y - place1.y;
        Point p = new Point(movingLBL.getX() + xChange, movingLBL.getY() + yChange);
        movingLBL.setLocation(p);
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
    }

    public void mousePressed(MouseEvent e) 
    {
        place1 = e.getLocationOnScreen();
        place2 = e.getLocationOnScreen();
    }

    public void mouseReleased(MouseEvent e) 
    {
    }

    public void mouseEntered(MouseEvent e) 
    { 
    }
    public void mouseExited(MouseEvent e) 
    {
    }
    @Override
    public void mouseDragged(MouseEvent e) 
    {
        place2 = e.getLocationOnScreen();
        moveLBL();
        place1 = place2;
    }
    @Override
    public void mouseMoved(MouseEvent e) 
    {
    }
}