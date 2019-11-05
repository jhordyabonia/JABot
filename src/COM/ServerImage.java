/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM;

import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.Robot; 
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import COM.Connection;
import static COM.Connection.POST_DISPLAY;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer.*;
import java.awt.Dimension;

/**
 *
 * @author jhord_000
 */
public class ServerImage implements Runnable
{   
    public double DELAY = 5;
    private Rectangle[] seccions;
    public ServerImage(int seccions){
        this.seccions = new Rectangle[seccions];
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = d.width/seccions;
        int height = d.height;
        for (int index = 0; seccions>index; index++) {            
            this.seccions[index] = new Rectangle(index*width,0,width,height);
        }
    }
    public void start(){
        new Thread(this).start();
    }
    @Override
    public final void run() 
    {
        while(true){ 
            try{      
                Robot bot = new Robot();
                ServerSocket sc = new ServerSocket(POST_DISPLAY);
                Socket so = sc.accept();
                int index = 0;
                while(!sc.isClosed()){  
                    try {
                        if(index >= seccions.length){
                            index = 0;
                            Thread.sleep((int)DELAY*1000);
                        }
                        BufferedImage image = bot.createScreenCapture(seccions[index++]);                       
                        OutputStream outputStream = so.getOutputStream();
                        ImageIO.write(image, "png", outputStream);
                        outputStream.flush();
                        System.gc();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
   
    public static void main(String []args) throws Exception{
        ServerImage s = new ServerImage(2);    
        s.start();
    }

}