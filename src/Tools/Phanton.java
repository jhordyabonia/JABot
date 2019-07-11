/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

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


/**
 *
 * @author jhord_000
 */
public class Phanton extends JFrame implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener 
{
    private PrintStream outL;
    private static long T=1;
    private boolean rec = true;
    private JLabel R=new JLabel("R°");
    private long TIME = System.currentTimeMillis();
    
    public Phanton() throws AWTException{
        super();
        make(System.out);
    }

    public Phanton(PrintStream t) throws AWTException{
        super();
        make(t);
    }

    private void make(PrintStream t)throws AWTException{
        outL=t;        

        add(R,BorderLayout.NORTH);
        R.setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD, 32)); 
        R.setForeground(Color.red); 
        R.setVisible(rec);
        
        setUndecorated(true);

        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);

        setBackground(new Color(0, 0, 0, 20));
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        for(AWTEventListener tt:Toolkit.getDefaultToolkit().getAWTEventListeners())
               outL.println(tt.hashCode());
    }

    public void println(String s){
        if(rec){
            waiting();
            if(T!=0)
                outL.println("[WAIT "+(T*100)+"]");
            outL.println(s);
        } 
        T=0;        
    }
    public void waiting(){
        long now = System.currentTimeMillis();
        if(rec)
            T = (now - TIME) / 1000; 
        else T = 0;    
        TIME = now; 
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e){
        println("[MOUSE "+btn(e.getButton())+" 0]"); 
    }
    @Override
    public void mouseReleased(MouseEvent e){
        println("[MOUSE "+btn(e.getButton())+" 1]");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e){   
        println("[MOUSE W "+e.getWheelRotation()+"]");
    }
    
    public String btn(int b){
        if(b==MouseEvent.BUTTON3) return "R";
        return "L";
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e){
        Integer x=e.getXOnScreen();
        Integer y=e.getYOnScreen();
        println("[MOUSE M "+x+" "+y+"]"); 
    }           

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) { 
        println("["+key(e.getExtendedKeyCode())+"]");
    }

    @Override
    public void keyReleased(KeyEvent e) {       
       if(key(e.getExtendedKeyCode()).contains("F12")){
             rec=!rec; 
             R.setVisible(rec);
             return;
       }else if(key(e.getExtendedKeyCode()).contains("scap")){
             rec=false; 
             setVisible(false);
             return;
       }       
       
       println("[/"+key(e.getExtendedKeyCode())+"]");
    } 

    private String key(Integer k){
        return KeyEvent.getKeyText(k).toUpperCase();    
    }     
  
    public static void main(String[] args) throws AWTException {   
        // TODO code application logic here
        Phanton p = new Phanton();
        p.setVisible(true);
    }
}
