/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import core.Bot;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author jhord_000
 */
public class Popup extends JFrame implements ActionListener
{
    private boolean newMSJ;
    private String msj="";
    private Thread adm;
    private final JButton stop=new JButton("Stop");
    private final JButton pause=new JButton("Pause");
    private final JPanel panel= new JPanel();
    private final JTextPane lienzo=new JTextPane();
    
    private synchronized boolean  ThearIsNewMSJ()
    {return newMSJ;}
    public Popup()
    {
        adm=new Thread((Runnable) this)
        {
            public void run()
            {
                synchronized(this)
                {
                    if(newMSJ)
                        lienzo.setText(msj);
                }
            }
        };
        
        add(lienzo,BorderLayout.CENTER);
        add(panel,BorderLayout.NORTH);
        lienzo.setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD, 12)); 
        lienzo.setForeground(Color.red); 
        
        setUndecorated(true);
        addActionListener(this);
        setBackground(new Color(0, 0, 0, 20));
        setSize(100,50);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(((JButton) e.getSource()).equals(pause))
        { 
            if(pause.getText().equals("Puase"))
            { 
                pause.setText("Run");
                    Bot.pause(true);
            }else
            {
                 pause.setText("Puase");
                    Bot.pause(false);
            }
        }
    } 
}
