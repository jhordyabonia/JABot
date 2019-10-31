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
import static COM.Connection.PORT;
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
    private Rectangle[] seccions;
    public ServerImage(int seccions){
        this.seccions = new Rectangle[seccions];
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = d.width/seccions,x=0;
        int height = d.height/seccions;
        for (int index = 0; seccions>index; index++) {            
            this.seccions[index] = new Rectangle(x,0,width,height);
            x+=width;
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
                ServerSocket sc = new ServerSocket(PORT);
                Socket so = sc.accept();
                int index = 0;
                while(!sc.isClosed()){  
                    try {
                        if(index >= seccions.length){
                            index = 0;
                        }
                        BufferedImage image = new Robot().createScreenCapture(seccions[index++]);                       
                        OutputStream outputStream = so.getOutputStream();
                        ImageIO.write(image, "png", outputStream);
                        outputStream.flush();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Thread.sleep(1000);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
   
    public static void main(String []args) throws Exception{
        ServerImage s = new ServerImage(1);        
        //s.acepted();
        s.start();
    }

}