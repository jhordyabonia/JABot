/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;
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


/**
 *
 * @author jhord_000
 */
public class Phanton extends JFrame implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener 
{
    public boolean COMPLETED_MODE = true;
    private int MOUSE_TOLERANCE = 0, MOUSE_X = -1, MOUSE_Y = -1;    
    private PrintStream outL;
    private static long T=1;
    private JLabel R = new JLabel("R°");
    private long TIME = System.currentTimeMillis();
    protected Client client = null;
    protected boolean rec = false,fullscreen = true;
    
    public Phanton() throws AWTException{
        super();
        make(System.out);
    }

    public Phanton(PrintStream t) throws AWTException{
        super();
        make(t);
    }

    public void connect(String s){
        UserConnection u = new UserConnection() {            
            public void write(String data){}
            public void start(Connection c){
                try{
                    rec = true;
                    setVisible(true);
                }catch(Exception e){}
            }
        };
        client = new Client(s);
        client.setUser(u);
        client.startInteractive();
    }
    public void setFullScreen(boolean in){
        fullscreen = in;        
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
        
        if(fullscreen){
            setBackground(new Color(0, 0, 0, 20));
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
            for(AWTEventListener tt:Toolkit.getDefaultToolkit().getAWTEventListeners())
                outL.println(tt.hashCode());
        }        
    }

    public void println(String s){
        if(rec){
            waiting();
            if(T!=0)
                outL.println("[WAIT "+(T*100)+"]");
            outL.println(s);
            if(client!=null)
                client.write(s);
            //System.out.println(s);
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
    public void mouseClicked(MouseEvent e) {  
        /*if(fullscreen){
            MOUSE_X=e.getXOnScreen();
            MOUSE_Y=e.getYOnScreen();
        }else{
            MOUSE_X=e.getX();
            MOUSE_Y=e.getY();
        }*/
    }
    @Override
    public void mousePressed(MouseEvent e){
        if(!COMPLETED_MODE)
            this.getPositionMouse(e); 
        println("[MOUSE "+btn(e.getButton())+" 0]"); 
    }
    @Override
    public void mouseReleased(MouseEvent e){        
        println("[MOUSE "+btn(e.getButton())+" 1]");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e){ 
        if(!COMPLETED_MODE)
            this.getPositionMouse(e); 
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
        if(!COMPLETED_MODE)
            return;
        this.getPositionMouse(e);
    }           

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) { 
        println("["+key(e.getExtendedKeyCode())+"]");
    }

    @Override
    public void keyReleased(KeyEvent e) {    
        String key =  key(e.getExtendedKeyCode());
        if(key.contains("F12")){
            rec = !rec; 
            R.setVisible(rec);
            SwingUtilities.updateComponentTreeUI(this);
        }else if(key.contains("sca")){
            rec=false; 
            R.setVisible(rec);

            //setVisible(false);             
            //println("EXIT");
        }else{            
            println("[/"+key+"]");
        }
    } 

    private void getPositionMouse(MouseEvent e){
        Integer x,y;
        if(fullscreen){
             x=e.getXOnScreen();
             y=e.getYOnScreen();
        }else{
             x=e.getX();
             y=e.getY();
        }
        boolean out = true;
        if(COMPLETED_MODE){
            int testX = MOUSE_X - x, testY = MOUSE_Y - y;
            testX = testX>0?testX:testX*-1;
            testY = testY>0?testY:testY*-1;
            out = MOUSE_TOLERANCE > testX || MOUSE_TOLERANCE > testY;
        }
        MOUSE_X = x;
        MOUSE_Y = y;
        if(!out)
            println("[MOUSE M "+x+" "+y+"]"); 
    }
    private String key(Integer k){
        return KeyEvent.getKeyText(k).toUpperCase();    
    }     
    /*
    public static void main(String[] args) throws AWTException {   
        // TODO code application logic here
        Phanton p = new Phanton();
        p.setVisible(true);
    }*/
}
