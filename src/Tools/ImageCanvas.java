package Tools;

import java.io.IOException;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageCanvas extends Canvas {

    private int posX=0,posY=0;
    private BufferedImage img;

    public ImageCanvas(){}
    public void setImage(BufferedImage in,int x,int y) {
        posX = x;
        posY = y;
        img = in;           
        repaint();
    }
    @Override
    public Dimension getPreferredSize() {
        return  img == null ? 
                new Dimension(200, 200) : 
                new Dimension(img.getWidth(), img.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (img != null) {
            g.drawImage(img, posX, posY, this);                      
        }
        
    }

}