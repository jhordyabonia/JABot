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

    private class Pack{
        public int posX=0,posY=0;
        public BufferedImage img;
        public Pack(BufferedImage in,int x,int y){
            posX = x; posY = y; img = in;
        }
    }
    private Pack[] imgs;

    public ImageCanvas(int length){
        imgs = new Pack[length];
    }
    public void setImage(int index,BufferedImage in,int x,int y) throws IOException{
        if(index<imgs.length){           
            imgs[index] = new Pack(in,x,y);
            //ImageIO.write(in, "png", new File("viwer/"+index+".png"));//test  
            repaint();
        }
    }
    @Override
    public Dimension getPreferredSize() {
        BufferedImage img = null;
        for (Pack pack : imgs) {
            if (pack != null) {
                img = pack.img;
            }
        }

        return  img == null ? 
                new Dimension(200, 200) : 
                new Dimension(img.getWidth(), img.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Pack pack : imgs) {
            if (pack != null) {
                g.drawImage(pack.img, pack.posX, pack.posY, this);  
                System.gc();                  
            }
        }
        
        
    }

}