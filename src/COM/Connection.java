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

/**
 *
 * @author jhord_000
 */
public abstract class Connection implements Runnable
{     
    public static int PORT = 5000;
    public static int DELAY = 100; 
    protected Thread runner = new Thread(this);
    protected DataOutputStream out;
    protected DataInputStream in;
    protected String name="";
    protected UserConnection user;
    
    protected Socket so;

    public interface UserConnection{
        public String read();
        public void write(String data);
    }

    public abstract void init();

    public void setUser(UserConnection u){
        user = u;
    }
    public UserConnection getUser(){
        return user;
    }
    public String getName(){
        return name;
    }

    @Override
    public final void run() 
    {
        String r="";
        do
        {
            try 
            {
                do{
                    Thread.sleep(DELAY);
                    r =  in.readUTF();
                    if(user != null){
                        user.write(r);
                    }
                } while(r.isEmpty());
            } catch (IOException | InterruptedException ex) {                
                System.out.println("There were errors! "+ex.getMessage());
            }
            System.out.println("user: "+r);
        }while(!r.equals("EXIT"));
    }
    
    public final void acepted() throws IOException{
        System.out.println("Staring connection... ");        
        out = new DataOutputStream(so.getOutputStream());
        in = new DataInputStream(so.getInputStream());
        System.out.println("Conection completed. "+so.getLocalAddress().getHostAddress());        
        runner.start();
    }
    
    public final void close(){
        try {so.close();} 
        catch (IOException ex) {/*println();*/}
    }

    public final void write(String s){
        try {out.writeUTF(s);}
        catch (IOException ex) {}
    }

    public final void startInteractive(){
        init();       
        Scanner teclado = new Scanner(System.in);
        String cadena;
        do{
            System.out.print(">"); 
            cadena = teclado.nextLine();
            write(cadena);
        }while(!cadena.startsWith("EXIT"));
    }
    
}
