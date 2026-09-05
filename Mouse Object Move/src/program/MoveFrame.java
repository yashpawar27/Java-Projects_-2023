package program;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MoveFrame extends JFrame
{

    private MovePanel panel;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    MoveFrame frame = new MoveFrame();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public MoveFrame()
    {
        ImageIcon icon = new ImageIcon("src/images/m.png");
        setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(200, 0);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Drag and Drop");
        panel = new MovePanel();
        setContentPane(panel);
        pack();
    }

}