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
import java.util.ArrayList;
/**
 *
 * @author jhord_000
 */
public abstract class Connection implements Runnable
{     
    private ArrayList<String> buffer = new ArrayList();
    public static int PORT = 5000,POST_DISPLAY = 5001;
    public static int DELAY = 100; 
    private boolean ACTIVE = false;
    protected boolean STOP = false;
    protected Thread runner = new Thread(this);
    protected DataOutputStream out;
    protected DataInputStream in;
    protected OutputStream _out;
    protected InputStream _in;
    protected String name="";
    protected UserConnection user;
    
    protected Socket so;

    public interface UserConnection{
        public void start(Connection c);
        public void write(String data);
    }

    public abstract void init();

    public void setUser(UserConnection u){
        user = u;
    }
    public UserConnection getUser(){
        return user;
    }
    
    public OutputStream getOutputStream(){
        return _out;
    }

    public InputStream getInputStream(){
        return _in;
    }
    
    public String getName(){
        return name;
    }

    @Override
    public final void run() 
    {
        ACTIVE = true;
        consumeBuffer();
        int ERRORS = 0;
        String r="";
        try 
        {
            do{
                Thread.sleep(DELAY);
                r =  in.readUTF();
                System.out.println("\n> "+r);
                if(user != null){
                    user.write(r);
                }
            }while(!r.toUpperCase().equals("EXIT"));
        } catch (IOException | InterruptedException ex) {
            System.out.println("There were errors! "+ex.getMessage());
        }
        System.out.println("Conection finished");
        close();
    }
    
    public final void acepted() throws IOException{
        System.out.println("Staring connection... ");        
        _out = so.getOutputStream();
        _in = so.getInputStream();
        out = new DataOutputStream(_out);
        in = new DataInputStream(_in);
        System.out.println("Conection completed. "+so.getInetAddress().getHostAddress());        
        runner.start();
        
        if(user != null){
            user.start(this);
        }
    }
    
    public void close(){
        try {
            so.close();
            in.close();
            out.close();
            _in.close();
            _out.close();
            System.exit(0);            
        }catch (IOException ex) {}
    }

    private void consumeBuffer(){
        for (String s : buffer) {
            write(s);
        }
    }
    public final void write(String s){
        if(!ACTIVE){
            buffer.add(s);
            return;
        }
        STOP = s.toUpperCase().equals("EXIT");
        try {
            out.writeUTF(s);
            if(STOP){
                close();
                System.out.println("Conection finished");
            }
        }catch (IOException ex) {}
    }

    public void startInteractive(){
        init();       
        Scanner teclado = new Scanner(System.in);
        String cadena;
        do{
            System.out.print(">"); 
            cadena = teclado.nextLine();
            write(cadena);
        }while(!STOP);
    }
    
}
