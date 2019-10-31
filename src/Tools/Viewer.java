/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;


import java.util.Scanner;
import COM.Client;
import COM.Connection;
import COM.Connection.UserConnection;
import javax.swing.SwingUtilities;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.image.ImageProducer;
import javax.imageio.ImageIO;
import java.awt.Robot; 
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer.*;
import java.net.*;
import java.awt.Dimension;


/**
 *
 * @author jhord_000
 */
public class Viewer extends JFrame implements ImageObserver, Runnable
{   
    private int index = 1,seccions = 1, width = 0;
    protected String host = "localhost";
    protected Graphics graphics;
    protected InputStream  file;
    protected ImageCanvas lienzo = new ImageCanvas();
    public Viewer(int seccions) throws AWTException{
        super();
        
        setUndecorated(true);
        add(lienzo,BorderLayout.CENTER);
        setVisible(true);

        this.seccions = seccions;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        width = d.width;
        setSize(d);
    }
    public void connect(String host){
        this.host = host;
        (new Thread(this)).start();
    }
    @Override
    public void run(){
        Socket socket = null; 
        try{
            socket = new Socket(host, Connection.PORT);
            InputStream inputStream = socket.getInputStream(); 
            BufferedImage image;
            while(true){
                if(index >= seccions){
                    index = 1;
                }else{
                    index++;
                }
                image = ImageIO.read(inputStream);
                if(image!=null){
                    lienzo.setImage(image,(width/seccions)*index,0);
                    Viewer.this.getContentPane().repaint();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            if(socket!=null){
                try{
                    socket.close();
                }catch(Exception ee){
                    ee.printStackTrace();
                }
            }
        }
    }    

    public static void main(String[] args) throws AWTException, FileNotFoundException {   
        // TODO code application logic here
        String host = args.length>0?"localhost":args[0];
        Viewer v = new Viewer(1);   
        v.connect(host);    
    }
}
