import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setAlwaysOnTop(true);
        frame.add(new LogoPanel());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = toolkit.createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        frame.setCursor(blankCursor);
        frame.setVisible(true);
    }
}
