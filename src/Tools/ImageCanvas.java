package Tools;


import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageCanvas extends Canvas {

    private BufferedImage[] imgs;

    public ImageCanvas(int length){
        imgs = new BufferedImage[length];
    }
    public void setImage(int index,BufferedImage in) throws IOException{
        if(index<imgs.length&&index<=0){           
            imgs[index] = in;
            //ImageIO.write(in, "png", new File("viwer/"+index+".png"));//test  
            repaint();
        }
    }
    @Override
    public Dimension getPreferredSize() {
        int width = 0, height = 0;
        for (BufferedImage img : imgs) {
            if (img != null) {
                width = img.getWidth();
                height = img.getHeight();
            }
        }

        return  new Dimension(width,height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int posX = 0;
        for (BufferedImage img : imgs) {
            if (img != null) {
                g.drawImage(img, posX, 0, this);
                posX += img.getWidth();
            }
        }  
        System.gc();                  
    }

}