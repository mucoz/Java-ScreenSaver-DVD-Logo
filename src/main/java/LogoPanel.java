import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LogoPanel extends JPanel implements ActionListener{
    private int x, y, dx, dy, logoWidth, logoHeight;
    private static final int TOP_AND_BOTTOM_CLEARENCE = 30;
    private CircularLinkedList imageList;
    private BufferedImage currentImage;
    private Timer timer;
    public LogoPanel() {
        setBackground(Color.BLACK);
        imageList = new CircularLinkedList();
        loadImages();
        currentImage = imageList.getCurrentImage();
        x = 100;
        y = 100;
        dx = 8;
        dy = 5;
        timer = new Timer(30, this);
        timer.start();
    }
    private void loadImages() {
        URL resourceFolder = getClass().getClassLoader().getResource("");
        if (resourceFolder != null) {
            File folder = new File(resourceFolder.getPath());
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            if (files != null) {
                for (File file : files) {
                    try {
                        BufferedImage image = ImageIO.read(file);
                        imageList.addImage(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(currentImage, x, y, this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Dimension size = getSize();
        boolean hitWall = false;

        if (x + dx < 0 || x+ logoWidth + dx > size.width){
            dx = -dx;
            hitWall = true;
        }
        if (y + dy < -TOP_AND_BOTTOM_CLEARENCE || y + logoHeight + dy > size.height + TOP_AND_BOTTOM_CLEARENCE){
            dy = -dy;
            hitWall = true;
        }
        if (hitWall) {
            currentImage = imageList.getNextImage();
            logoWidth = currentImage.getWidth(this);
            logoHeight = currentImage.getHeight(this);
        }
        x += dx;
        y += dy;
        repaint();
    }
}
